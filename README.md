# auto-publish-posts

FB/TT 智能回复与 FB 自动发帖项目。面向广告资产运营团队，集中处理评论/私信回复、备用 FB Page 养号发帖、素材库、违禁词、人工审核和运营统计。

## 项目解决的问题

- 回复不及时：FB Page 评论/私信、TT 广告评论/私信需要人工反复巡检，容易错过回复窗口，影响转化。
- 备用主页不活跃：FB 备用 Page 长期没有内容沉淀，存在信任度低、主页被限制或封禁的风险。
- 回复质量不可控：AI 回复需要经过置信度、违禁词、时间窗口、频控和人工复核约束，避免直接放任自动发送。
- 运营效果难量化：需要按主页、账号、平台、消息类型、状态和时间维度查看回复、发帖与效果数据。

## 功能范围

一期包含：

- FB/TT 智能回复：采集评论和私信，调用 AI 工作流，自动回复或进入人工审核。
- FB 自动发帖：基于素材库和发帖策略，为 FB 备用 Page 生成、审核、发布图文内容。
- 素材库：维护通用/品牌素材库，管理图片、文案、标签、语言和使用记录。
- 违禁词：发布或回复前进行敏感词检查。
- 人工审核：处理低置信度、违禁词命中、跳过、失败或需要复查的回复。
- 记录统计：查看回复记录、发帖记录、运营看板和趋势。

一期不包含：

- TT 自动发帖。
- 主动发起私信会话。
- 非广告帖的 TT 评论自动回复。
- 完整归因分析，CPA 等外部指标仅预留导入或同步口径。

## 核心功能

### 智能回复

- 定时采集 FB Page 评论、FB Page 私信、TT 广告一级评论、TT 私信。
- 按 `platform + message_type + platform_message_id` 去重，过滤自身消息、过期消息和 TT 二级评论。
- 调用 AI 工作流获取 `reply_text`、`intents`、`primary_intent`、`ai_score`、`confidence`、`should_reply`。
- 按置信度分流：`HIGH` 自动回复，`MEDIUM` 自动回复并标记复查，`LOW` 进入人工审核。
- 发送前执行回复时间窗口、单账号回复间隔、每日上限、违禁词和紧急停止检查。
- 支持失败重试、人工编辑发送、采纳 AI 建议、跳过、不回复和复查。

### FB 自动发帖

- 支持 `COMMON` 通用素材库和 `BRAND` 品牌素材库。
- 素材类型支持图片、文本、图文组合；图片 URL 一期要求 FB 可公网访问。
- 按 FB Page 发帖配置生成计划，包括每日频次、时间段、素材库、发布模式和审核模式。
- 素材选择优先使用最久未使用素材，同一素材默认 7 天内不重复。
- 支持人工审核、编辑文案、通过、驳回、定时发布、失败重试和发布记录追踪。

### 运营配置

- FB 配置：主页、智能回复开关、自动发帖开关、回复间隔、每日上限、发帖频次、发帖时间段、审核模式、灰度状态。
- TT 配置：账号/identity、advertiser、adgroup、评论回复开关、私信回复开关、回复间隔、每日上限、凭证校验。
- 支持灰度启停、批量启停、紧急停止和操作审计。

### 数据统计

- 回复维度：平台、消息类型、状态、主页/账号、置信度、意图、人工审核量、失败量。
- 发帖维度：主页、素材库、计划时间、发布时间、发布状态、失败原因。
- 看板维度：今日回复、待审核、成功率、失败率、发帖量、趋势和账号/主页明细。

## 前端页面

原型页面对应关系：

- P01：智能回复配置 - FB 主页
- P02：智能回复配置 - TT 账户
- P03：回复记录列表
- P04：人工审核队列
- P05：素材库列表
- P06：素材库素材管理
- P07：自动发帖发帖记录
- P08：违禁词管理
- P09：数据统计看板

## 项目结构

```text
auto-publish-posts/
├── server/   # Java 21 + Spring Boot 3.2.5 后端
├── client/   # Vue 3 + TypeScript + Vite 前端
└── docs/     # 技术方案、接口、部署和验收文档
```

## 本地依赖

- JDK 21
- Maven 3.9+
- Node.js 20+、pnpm
- PostgreSQL，连接信息：



## 本地启动

后端：

```powershell
cd auto-publish-posts\server
$env:JAVA_HOME='D:\soft\jdk-21.0.10'
$env:Path="$env:JAVA_HOME\bin;$env:Path"
mvn spring-boot:run
```

前端：

```powershell
cd auto-publish-posts\client
pnpm install
pnpm dev
```


## 常用验证

后端：

```powershell
cd auto-publish-posts\server
$env:JAVA_HOME='D:\soft\jdk-21.0.10'
$env:Path="$env:JAVA_HOME\bin;$env:Path"
mvn clean test
```

前端：

```powershell
cd auto-publish-posts\client
pnpm test
pnpm run type-check
pnpm run build
```

## 外部平台接入

真实业务验收需要配置以下外部能力：

- FB Graph API：Page 授权、Page Token、评论/私信读取、图片/Feed 发布。
- TikTok Business API：BC、identity、advertiser、adgroup 和广告评论能力。
- TT Business Messaging Open Beta：TT 私信能力，未开通时应通过 feature flag 关闭。
- AI Workflow：只负责返回回复建议、意图、置信度和是否回复，系统负责状态流转与平台发送。
- 外部指标：CPA 等效果数据后续通过导入或接口同步补齐。

## 相关文档

- `docs/technical-design.md`：技术方案和模块设计。
- `docs/api.md`：接口说明。
- `docs/deployment.md`：部署和环境变量说明。
- `docs/acceptance.md`：验收用例。
