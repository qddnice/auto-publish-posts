# auto-publish-posts 技术方案

## 背景与目标

本项目将 `fast-ad-asset` 中的资产运营能力独立为新项目，覆盖 FB/TT 智能回复、FB 自动发帖、素材库、违禁词、人工审核和统计看板。一期不做 TT 自动发帖。

## 架构

- 后端：`server/`，Java 21、Spring Boot 3.2.5、Spring MVC、Spring Security、MyBatis-Plus、PostgreSQL、Redis、Flyway。
- 前端：`client/`，Vue 3、TypeScript、Vite、Pinia、Vue Router、Element Plus。
- 包名：`com.auto.post`。
- 数据库：PostgreSQL `mini_db`，schema `dev`。
- 外部边界：FB Graph API、TikTok Business API、AI 工作流、钉钉通知都通过 `platform` Client 封装。

## 模块

- `auth`：本地账号、JWT 登录。
- `credential`：FB/TT 凭证管理。
- `asset`：FB Page、TT BC、TT identity、TT advertiser、TT adgroup。
- `configuration`：FB/TT 自动化参与配置、灰度和紧急停止。
- `material`：素材库与素材。
- `posting`：FB 发帖计划、审核和发布状态机。
- `reply`：消息处理、违禁词、置信度、人工回复。
- `stats`：运营统计。

## 数据

Flyway 初始化脚本位于 `server/src/main/resources/db/migration/V1__init_schema.sql`，表名前缀统一为 `ap_`。JSON 字段使用 `jsonb`，FB 配置和消息去重使用 PostgreSQL 部分唯一索引。


