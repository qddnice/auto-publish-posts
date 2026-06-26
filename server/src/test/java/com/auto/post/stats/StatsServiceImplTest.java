package com.auto.post.stats;

import com.auto.post.posting.mapper.PostRecordMapper;
import com.auto.post.reply.mapper.MessageRecordMapper;
import com.auto.post.reply.mapper.SensitiveWordMapper;
import com.baomidou.mybatisplus.core.conditions.Wrapper;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class StatsServiceImplTest {

    private final MessageRecordMapper messageRecordMapper = mock(MessageRecordMapper.class);
    private final PostRecordMapper postRecordMapper = mock(PostRecordMapper.class);
    private final SensitiveWordMapper sensitiveWordMapper = mock(SensitiveWordMapper.class);
    private final StatsService service = new StatsServiceImpl(messageRecordMapper, postRecordMapper, sensitiveWordMapper);

    @Test
    @SuppressWarnings("unchecked")
    void summaryCountsDatabaseRecords() {
        when(messageRecordMapper.selectCount(any(Wrapper.class))).thenReturn(7L, 2L);
        when(postRecordMapper.selectCount(any(Wrapper.class))).thenReturn(3L);
        when(sensitiveWordMapper.selectCount(any(Wrapper.class))).thenReturn(4L);

        StatsSummary summary = service.summary();

        assertThat(summary.todayReplyCount()).isEqualTo(7L);
        assertThat(summary.manualPendingCount()).isEqualTo(2L);
        assertThat(summary.todayPostCount()).isEqualTo(3L);
        assertThat(summary.sensitiveHitCount()).isEqualTo(4L);
    }
}
