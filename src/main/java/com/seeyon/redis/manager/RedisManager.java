package com.seeyon.redis.manager;

import java.sql.SQLException;

/**
 * Created by Administrator on 2016-9-6.
 */
public interface RedisManager {

    public void jdbcReadAndWriteOnly() throws SQLException;

    public void jdbcForRedis() throws SQLException;
}
