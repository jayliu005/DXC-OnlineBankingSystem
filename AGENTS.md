# dxconlinebanking 專案操作規範

## 專案角色

- 舊專案：`/Users/jay/Documents/IntelliJProjects/onlinebanking`
  - 只供功能、資料與行為分析。
  - 禁止修改、格式化、提交或清除其中任何檔案。
- 新專案：`/Users/jay/Documents/IntelliJProjects/DXC-OnlineBankingSystem`
  - 所有 Migration 新程式碼與文件只寫在此專案。

## 每次開發前必讀

進行分析、設計、程式修改、資料庫變更、建置、測試或部署前，必須完整閱讀根目錄的 `專案Migration.md`。

若本規範與 `專案Migration.md` 有差異，以 `專案Migration.md` 的最新決策為準。

## 技術架構

- 前端：Vue、TypeScript、Vue Router、Pinia、Vite。
- 後端：Java 21、Spring Boot、Maven、REST API、可執行 Jar。
- 資料存取：Spring Data JPA、Oracle JDBC。
- Schema：Flyway。
- 本機環境：Docker Compose 與獨立 Oracle volume。

## 建置與啟動

後端：

```bash
cd backend
JAVA_HOME=$(/usr/libexec/java_home -v 21) ./mvnw clean verify
JAVA_HOME=$(/usr/libexec/java_home -v 21) ./mvnw spring-boot:run
```

前端：

```bash
cd frontend
npm install
npm run build
npm run dev
```

本機 Oracle 與後端：

```bash
cp infra/.env.example infra/.env
docker compose --env-file infra/.env -f infra/compose.yaml up --build
```

`.env` 不可提交版本控制。

## 驗收原則

- 每個功能切片實作前，先查閱舊專案對應畫面、Flow、Service、DAO、Entity 與資料表。
- 使用相同輸入人工比對新舊系統的資料、驗證、錯誤情境及商業行為。
- 新 Oracle schema 只能建立舊 schema 結構，不得匯入舊資料。
- 新舊系統不得共用可寫入 schema 或 Oracle data volume。
- 功能一致且差異完成記錄後，才能進入下一個切片。

## 動作紀錄

每次完成重要操作後，追加一筆到根目錄的 `專案編譯與歷程.md`，包含 Asia/Taipei 時間、執行者、動作、涉及檔案或指令及結果。不得寫入密碼、Token、連線字串或其他機密資訊。
