create table if not exists ap_team (
    id bigserial primary key,
    name varchar(128) not null,
    created_at timestamptz not null default now(),
    updated_at timestamptz not null default now(),
    created_by varchar(64),
    updated_by varchar(64),
    deleted boolean not null default false
);

create table if not exists ap_user (
    id bigserial primary key,
    username varchar(64) not null,
    password_hash varchar(256) not null,
    display_name varchar(128),
    role varchar(32) not null default 'OPERATOR',
    created_at timestamptz not null default now(),
    updated_at timestamptz not null default now(),
    created_by varchar(64),
    updated_by varchar(64),
    deleted boolean not null default false
);

create unique index if not exists uk_ap_user_username on ap_user(username) where deleted = false;

create table if not exists ap_user_team (
    id bigserial primary key,
    user_id bigint not null,
    team_id bigint not null,
    created_at timestamptz not null default now(),
    updated_at timestamptz not null default now(),
    created_by varchar(64),
    updated_by varchar(64),
    deleted boolean not null default false
);

create table if not exists ap_platform_credential (
    id bigserial primary key,
    platform varchar(16) not null,
    credential_name varchar(128) not null,
    account_name varchar(128),
    app_id varchar(128),
    encrypted_access_token text,
    encrypted_refresh_token text,
    token_expires_at timestamptz,
    status varchar(32) not null default 'VALID',
    team_id bigint,
    created_at timestamptz not null default now(),
    updated_at timestamptz not null default now(),
    created_by varchar(64),
    updated_by varchar(64),
    deleted boolean not null default false
);

create table if not exists ap_fb_page (
    id bigserial primary key,
    page_id varchar(64) not null,
    page_name varchar(256) not null,
    credential_id bigint,
    page_access_token_encrypted text,
    status varchar(32) not null default 'ACTIVE',
    team_id bigint,
    created_at timestamptz not null default now(),
    updated_at timestamptz not null default now(),
    created_by varchar(64),
    updated_by varchar(64),
    deleted boolean not null default false
);

create unique index if not exists uk_ap_fb_page on ap_fb_page(page_id) where deleted = false;

create table if not exists ap_tt_bc (
    id bigserial primary key,
    credential_id bigint,
    bc_id varchar(64) not null,
    bc_name varchar(256),
    status varchar(32) not null default 'ACTIVE',
    team_id bigint,
    created_at timestamptz not null default now(),
    updated_at timestamptz not null default now(),
    created_by varchar(64),
    updated_by varchar(64),
    deleted boolean not null default false
);

create table if not exists ap_tt_identity (
    id bigserial primary key,
    bc_id varchar(64) not null,
    identity_id varchar(64) not null,
    identity_name varchar(256),
    identity_type varchar(32) not null default 'BC_AUTH_TT',
    status varchar(32) not null default 'ACTIVE',
    team_id bigint,
    created_at timestamptz not null default now(),
    updated_at timestamptz not null default now(),
    created_by varchar(64),
    updated_by varchar(64),
    deleted boolean not null default false
);

create table if not exists ap_tt_advertiser (
    id bigserial primary key,
    credential_id bigint,
    bc_id varchar(64),
    advertiser_id varchar(64) not null,
    advertiser_name varchar(256),
    status varchar(32) not null default 'ACTIVE',
    team_id bigint,
    created_at timestamptz not null default now(),
    updated_at timestamptz not null default now(),
    created_by varchar(64),
    updated_by varchar(64),
    deleted boolean not null default false
);

create table if not exists ap_tt_adgroup (
    id bigserial primary key,
    advertiser_id varchar(64) not null,
    adgroup_id varchar(64) not null,
    adgroup_name varchar(256),
    campaign_id varchar(64),
    status varchar(32) not null default 'ACTIVE',
    last_sync_at timestamptz,
    created_at timestamptz not null default now(),
    updated_at timestamptz not null default now(),
    created_by varchar(64),
    updated_by varchar(64),
    deleted boolean not null default false
);

create table if not exists ap_fb_config (
    id bigserial primary key,
    page_id varchar(64) not null,
    page_name varchar(256) not null,
    enable_reply boolean not null default false,
    enable_post boolean not null default false,
    reply_interval_min int not null default 5,
    daily_reply_limit int not null default 100,
    post_time_slots jsonb,
    post_review_mode varchar(16) not null default 'MANUAL',
    material_library_ids jsonb,
    default_product_info jsonb,
    status varchar(16) not null default 'DISABLED',
    team_id bigint,
    created_at timestamptz not null default now(),
    updated_at timestamptz not null default now(),
    created_by varchar(64),
    updated_by varchar(64),
    deleted boolean not null default false
);

create unique index if not exists uk_ap_fb_config_page on ap_fb_config(page_id) where deleted = false;

create table if not exists ap_tt_config (
    id bigserial primary key,
    credential_id varchar(64) not null,
    bc_id varchar(64) not null,
    bc_name varchar(256),
    org_id varchar(64) not null,
    org_name varchar(256),
    identity_type varchar(32) not null default 'BC_AUTH_TT',
    linked_advertiser_id varchar(64) not null,
    linked_advertiser_name varchar(256),
    enable_comment_reply boolean not null default false,
    enable_dm_reply boolean not null default false,
    reply_interval_min int not null default 5,
    daily_reply_limit int not null default 100,
    default_product_info jsonb,
    status varchar(16) not null default 'DISABLED',
    team_id bigint,
    created_at timestamptz not null default now(),
    updated_at timestamptz not null default now(),
    created_by varchar(64),
    updated_by varchar(64),
    deleted boolean not null default false
);

create unique index if not exists uk_ap_tt_config_org_team on ap_tt_config(org_id, team_id) where deleted = false;

create table if not exists ap_sensitive_word (
    id bigserial primary key,
    word varchar(256) not null,
    match_type varchar(16) not null default 'FUZZY',
    scope varchar(16) not null default 'GLOBAL',
    status varchar(16) not null default 'ACTIVE',
    team_id bigint,
    created_at timestamptz not null default now(),
    updated_at timestamptz not null default now(),
    created_by varchar(64),
    updated_by varchar(64),
    deleted boolean not null default false
);

create unique index if not exists uk_ap_sensitive_word on ap_sensitive_word(word, team_id) where deleted = false;

create table if not exists ap_emergency_stop_log (
    id bigserial primary key,
    operator_id bigint,
    operator_name varchar(64),
    scope_desc varchar(256),
    affected_config_count int not null default 0,
    cancelled_task_count int not null default 0,
    created_at timestamptz not null default now(),
    updated_at timestamptz not null default now(),
    created_by varchar(64),
    updated_by varchar(64),
    deleted boolean not null default false
);

create table if not exists ap_material_library (
    id bigserial primary key,
    name varchar(128) not null,
    type varchar(32) not null,
    brand_name varchar(128),
    language varchar(32),
    description varchar(512),
    material_count int not null default 0,
    status varchar(32) not null default 'ACTIVE',
    team_id bigint,
    created_at timestamptz not null default now(),
    updated_at timestamptz not null default now(),
    created_by varchar(64),
    updated_by varchar(64),
    deleted boolean not null default false
);

create table if not exists ap_material (
    id bigserial primary key,
    library_id bigint not null,
    type varchar(32) not null,
    image_url varchar(1024),
    image_meta jsonb,
    text_content text,
    tags jsonb,
    status varchar(32) not null default 'ACTIVE',
    used_count int not null default 0,
    last_used_at timestamptz,
    team_id bigint,
    created_at timestamptz not null default now(),
    updated_at timestamptz not null default now(),
    created_by varchar(64),
    updated_by varchar(64),
    deleted boolean not null default false
);

create table if not exists ap_post_record (
    id bigserial primary key,
    page_id varchar(64) not null,
    page_name varchar(128),
    material_id bigint,
    library_id bigint,
    post_content text,
    post_image_url varchar(1024),
    fb_post_id varchar(128),
    status varchar(32) not null,
    review_mode varchar(32),
    reviewed_by bigint,
    reviewed_at timestamptz,
    scheduled_at timestamptz,
    published_at timestamptz,
    fail_reason varchar(512),
    retry_count int not null default 0,
    version int not null default 0,
    team_id bigint,
    created_at timestamptz not null default now(),
    updated_at timestamptz not null default now(),
    created_by varchar(64),
    updated_by varchar(64),
    deleted boolean not null default false
);

create table if not exists ap_message_record (
    id bigserial primary key,
    platform varchar(16) not null,
    message_type varchar(16) not null,
    source_id varchar(64) not null,
    source_name varchar(256),
    post_id varchar(128),
    platform_message_id varchar(128) not null,
    original_content text,
    translated_content text,
    sender_name varchar(128),
    sender_id varchar(64),
    comment_level varchar(16),
    ad_info jsonb,
    product_info jsonb,
    thread_id varchar(128),
    context_messages jsonb,
    intents jsonb,
    primary_intent varchar(64),
    reply_content text,
    reply_status varchar(32) not null default 'PENDING',
    reply_source varchar(32),
    skip_reason varchar(256),
    workflow_confidence varchar(16),
    workflow_suggestion text,
    ai_score numeric(5,4),
    final_confidence_score numeric(5,4),
    needs_review boolean not null default false,
    review_result varchar(16),
    review_issue_type varchar(32),
    version int not null default 0,
    team_id bigint,
    message_created_at timestamptz,
    fetched_at timestamptz,
    replied_at timestamptz,
    window_expires_at timestamptz,
    archived boolean not null default false,
    created_at timestamptz not null default now(),
    updated_at timestamptz not null default now(),
    created_by varchar(64),
    updated_by varchar(64),
    deleted boolean not null default false
);

create unique index if not exists uk_ap_message_dedup
    on ap_message_record(platform, message_type, platform_message_id)
    where deleted = false;

create table if not exists ap_operation_log (
    id bigserial primary key,
    module varchar(64) not null,
    operation varchar(64) not null,
    target_id varchar(128),
    detail jsonb,
    operator_name varchar(64),
    created_at timestamptz not null default now(),
    updated_at timestamptz not null default now(),
    created_by varchar(64),
    updated_by varchar(64),
    deleted boolean not null default false
);

create table if not exists ap_external_metric_snapshot (
    id bigserial primary key,
    platform varchar(16) not null,
    source_id varchar(64) not null,
    metric_date date not null,
    cpa numeric(12,4),
    lifecycle_days int,
    detail jsonb,
    created_at timestamptz not null default now(),
    updated_at timestamptz not null default now(),
    created_by varchar(64),
    updated_by varchar(64),
    deleted boolean not null default false
);
