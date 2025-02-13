package main

import (
	"net/http"

	_ "github.com/go-sql-driver/mysql"
	"github.com/google/uuid"
	"github.com/jmoiron/sqlx"
	"github.com/labstack/echo/v4"
	"golang.org/x/crypto/bcrypt"
)

type User struct {
	ID       string `json:"id" db:"id"` // UUIDなどの一意のID
	Email    string `json:"email" db:"email"`
	Password string `json:"password,omitempty" db:"password"`
}

var db *sqlx.DB

func main() {
	var err error
	// ※MySQLのユーザー名、パスワード、DB名に適宜変更してください
	db, err = sqlx.Open("mysql", "user:password@tcp(localhost:3306)/dbname?parseTime=true")
	if err != nil {
		panic(err)
	}
	defer db.Close()

	// テーブルがない場合は作成する
	createTableQuery := `
	CREATE TABLE IF NOT EXISTS users (
		id VARCHAR(36) PRIMARY KEY,
		email VARCHAR(255) NOT NULL UNIQUE,
		password VARCHAR(255) NOT NULL
	);`
	if _, err := db.Exec(createTableQuery); err != nil {
		panic(err)
	}

	e := echo.New()

	// ユーザー登録エンドポイント
	e.POST("/signup", Signup)

	e.Logger.Fatal(e.Start(":8080"))
}

// Signup
func Signup(c echo.Context) error {
	u := new(User)
	if err := c.Bind(u); err != nil {
		return c.JSON(http.StatusBadRequest, map[string]string{"message": "入力内容が正しくありません"})
	}

	// パスワードのハッシュ化
	hashedPassword, err := bcrypt.GenerateFromPassword([]byte(u.Password), bcrypt.DefaultCost)
	if err != nil {
		return c.JSON(http.StatusInternalServerError, map[string]string{"message": "パスワードのハッシュ化に失敗しました"})
	}
	u.Password = string(hashedPassword)

	// ユーザーUID（UUID）を生成
	u.ID = uuid.New().String()

	// DBにユーザー情報を挿入（sqlx の NamedExec を使用）
	_, err = db.NamedExec("INSERT INTO users (id, email, password) VALUES (:id, :email, :password)", u)
	if err != nil {
		return c.JSON(http.StatusInternalServerError, map[string]string{"message": "ユーザー登録に失敗しました"})
	}

	return c.JSON(http.StatusOK, map[string]string{"message": "ユーザー登録が成功しました", "user_id": u.ID})
}
