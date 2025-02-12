package main

import (
	"context"
	"log"
	"net/http"

	firebase "firebase.google.com/go"
	"github.com/labstack/echo/v4"
	"google.golang.org/api/option"
)

func main() {
	ctx := context.Background()
	// Firebase Admin SDKのサービスアカウントキーのパスを指定
	opt := option.WithCredentialsFile("/Users/hasimotorion/Desktop/チーム開発/sound sns/Androud_practise/google-services.json")
	app, err := firebase.NewApp(ctx, nil, opt)
	if err != nil {
		log.Fatalf("Firebase Appの初期化に失敗しました: %v", err)
	}

	authClient, err := app.Auth(ctx)
	if err != nil {
		log.Fatalf("Authクライアントの取得に失敗しました: %v", err)
	}

	// Echoのインスタンス作成
	e := echo.New()

	// 認証ミドルウェア
	authMiddleware := func(next echo.HandlerFunc) echo.HandlerFunc {
		return func(c echo.Context) error {
			// クライアントから送られるAuthorizationヘッダーからトークンを取得
			tokenStr := c.Request().Header.Get("Authorization")
			if tokenStr == "" {
				return c.JSON(http.StatusUnauthorized, map[string]string{"error": "Authorization header がありません"})
			}

			// Firebaseでトークンの検証
			token, err := authClient.VerifyIDToken(ctx, tokenStr)
			if err != nil {
				return c.JSON(http.StatusUnauthorized, map[string]string{"error": "無効なトークンです"})
			}

			// トークン情報をEchoのコンテキストにセットして次のハンドラーへ
			c.Set("user", token)
			return next(c)
		}
	}

	// 認証が必要なエンドポイント例
	e.GET("/protected", func(c echo.Context) error {
		user := c.Get("user")
		return c.JSON(http.StatusOK, map[string]interface{}{
			"message": "認証成功",
			"user":    user,
		})
	}, authMiddleware)

	// サーバーをポート8080で起動
	e.Logger.Fatal(e.Start(":8080"))
}
