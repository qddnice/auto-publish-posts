# auto-publish-posts API 清单

## 认证

- `POST /api/auth/login`：登录。

## 配置

- `GET /api/config/fb/list`：FB 主页配置列表。
- `POST /api/config/fb/save`：保存 FB 主页配置。
- `GET /api/config/tt/list`：TT 账号配置列表。
- `POST /api/config/tt/save`：保存 TT 账号配置。
- `GET /api/config/gray/summary`：灰度概览。
- `POST /api/config/emergency-stop`：紧急停止。

## 素材与发帖

- `GET /api/material/library/list`：素材库列表。
- `POST /api/material/library/save`：保存素材库。
- `GET /api/material/list?libraryId=1`：素材列表。
- `POST /api/material/save`：保存素材。
- `GET /api/post/records`：发帖记录。
- `POST /api/post/generate`：生成发帖计划。
- `POST /api/post/approve`：审核通过。
- `POST /api/post/reject`：审核拒绝。

## 智能回复

- `GET /api/reply/workbench`：运营工作台。
- `GET /api/reply/review-queue`：人工审核队列。
- `POST /api/reply/confidence/evaluate`：根据工作流分值和内容特征计算自动回复置信度分层。
- `POST /api/reply/manual-reply`：人工回复。

## 违禁词与统计

- `GET /api/sensitive-word/list`：违禁词列表。
- `POST /api/sensitive-word/save`：保存违禁词。
- `POST /api/sensitive-word/check`：检测违禁词。
- `GET /api/stats/summary`：统计汇总。
- `GET /api/stats/trend`：回复趋势。

所有详情、删除、状态变更接口均使用 query 参数或 request body，不新增 `@PathVariable`。
