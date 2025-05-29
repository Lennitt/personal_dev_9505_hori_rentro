-- users テーブルにデータを挿入するクエリ
INSERT INTO users (email, name, password)VALUES
('tanaka@aaa.com', '田中太郎', 'test123');
INSERT INTO users (email, name, password)VALUES
('suzuki@aaa.com', '鈴木一郎', 'test456');

-- tasks テーブルにデータを挿入するクエリ
INSERT INTO tasks (user_id, category_id, title, closing_date, progress, memo)VALUES
(1, 2, 'メールボックスの整理', '2025-05-29', 0, '受信トレイを確認し、不要なメールをアーカイブまたは削除する');
INSERT INTO tasks (user_id, category_id, title, closing_date, progress, memo)VALUES
(1, 2, 'コードレビュー', '2025-05-30', 0, 'チームメンバーのプルリクエストをチェックし、改善点や質問コメントを残す');
INSERT INTO tasks (user_id, category_id, title, closing_date, progress, memo)VALUES
(1, 1, '買い物リスト作成', '2025-05-31', 1, '週末の食材と日用品のリストをまとめ、スーパーでの無駄買いを防止');
INSERT INTO tasks (user_id, category_id, title, closing_date, progress, memo)VALUES
(1, 1, '身の回りのモノ整理', '2025-06-01', 1, 'クローゼット内の服を見直し、着ないものはフリマアプリに出品する');
INSERT INTO tasks (user_id, category_id, title, closing_date, progress, memo)VALUES
(1, 2, '健康診断の予約', '2025-05-16', 1, '来月の健康診断をネット予約し、必要書類の確認する');
INSERT INTO tasks (user_id, category_id, title, closing_date, progress, memo)VALUES
(1, 1, 'ベランダの植物に水やり', '2025-05-28', 1, '観葉植物やハーブにたっぷり水をあげる');
INSERT INTO tasks (user_id, category_id, title, closing_date, progress, memo)VALUES
(1, 2, 'PC メンテナンス', '2025-05-30', 1, '不要ファイルの削除、ディスククリーンアップ、OS・ソフトのアップデート実施');
INSERT INTO tasks (user_id, category_id, title, closing_date, progress, memo)VALUES
(1, 3, 'ゴミ出しチェック', '2025-05-31', 1, '燃えるゴミ・プラごみ・資源ごみの日程を確認して分別');
INSERT INTO tasks (user_id, category_id, title, closing_date, progress, memo)VALUES
(1, 1, '冷蔵庫の賞味期限チェック', '2025-05-30', 1, '週に一度、野菜室とチルド室をチェックして食材のムダを防ぐ');
INSERT INTO tasks (user_id, category_id, title, closing_date, progress, memo)VALUES
(1, 1, 'スマホのアプリ整理', '2025-05-29', 1, '使っていないアプリをアンインストール or フォルダ分けしてホーム画面をすっきり');
INSERT INTO tasks (user_id, category_id, title, closing_date, progress, memo)VALUES
(1, 1, 'お風呂掃除', '2025-06-04', 1, '週末に浴槽とタイルの軽い洗い＆排水口のゴミ取りまでサクッと完了');
INSERT INTO tasks (user_id, category_id, title, closing_date, progress, memo)VALUES
(1, 1, '電球／照明の拭き掃除', '2025-03-05', 1, 'ホコリで暗くなりがち。ランプを外してサッと拭き取り');



-- categories テーブルにデータを挿入するクエリ
INSERT INTO categories (name)VALUES
( '個人' );
INSERT INTO categories (name)VALUES
( '会社' );
INSERT INTO categories (name)VALUES
( 'その他' );