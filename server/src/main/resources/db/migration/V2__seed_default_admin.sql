insert into ap_user (
    username,
    password_hash,
    display_name,
    role,
    created_by,
    updated_by
)
select
    'admin',
    '{noop}admin',
    '系统管理员',
    'ADMIN',
    'flyway',
    'flyway'
where not exists (
    select 1
    from ap_user
    where username = 'admin'
      and deleted = false
);
