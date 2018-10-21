--
-- Created by IntelliJ IDEA.
-- User: Administrator
-- Date: 2018/10/21
-- Time: 15:47
-- To change this template use File | Settings | File Templates.
--

local v=redis.call("exists",KEYS[1]);
if v==0 then -- 还没有登录，是游客
    redis.call("set",KEYS[1],KEYS[2]);
end;