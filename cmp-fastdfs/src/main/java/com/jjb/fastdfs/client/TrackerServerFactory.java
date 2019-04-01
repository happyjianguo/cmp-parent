package com.jjb.fastdfs.client;

import org.apache.commons.pool2.BasePooledObjectFactory;
import org.apache.commons.pool2.PooledObject;
import org.apache.commons.pool2.impl.DefaultPooledObject;

import com.jjb.fastdfs.support.TrackerClient;
import com.jjb.fastdfs.support.TrackerServer;

/**
 * TrackerServer 工厂类，创建对象池需要 BasePooledObjectFactory 对象或子类.
 *
 * @author jiangzhou.bo@hand-china.com
 * @version 1.0
 * @date 2017-10-14 14:45
 */
public class TrackerServerFactory extends BasePooledObjectFactory<TrackerServer> {

    @Override
    public TrackerServer create() throws Exception {
        // TrackerClient
        TrackerClient trackerClient = new TrackerClient();
        // TrackerServer
        TrackerServer trackerServer = trackerClient.getConnection();

        return trackerServer;
    }

    @Override
    public PooledObject<TrackerServer> wrap(TrackerServer trackerServer) {
        return new DefaultPooledObject<TrackerServer>(trackerServer);
    }
}
