package com.vladmihalcea.book.high_performance_java_persistence.jdbc.batch;

import com.vladmihalcea.hibernate.masterclass.laboratory.util.AbstractPostgreSQLIntegrationTest;
import org.junit.Test;

import javax.persistence.*;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.concurrent.TimeUnit;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

/**
 * BatchStatementTest - Test batching with Statements
 *
 * @author Vlad Mihalcea
 */
public class BatchStatementTest extends AbstractBatchStatementTest {

    @Override
    protected void onStatement(Statement statement, String dml) throws SQLException {
       statement.addBatch(dml);
    }

    @Override
    protected void onEnd(Statement statement) throws SQLException {
        int[] updateCount = statement.executeBatch();
        statement.clearBatch();
        assertEquals((getPostCommentCount() + 1) * getPostCount(), updateCount.length);
    }
}
