package org.uberfire.backend.server.cluster;

import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.inject.Produces;
import javax.inject.Named;

import org.uberfire.commons.cluster.ClusterService;
import org.uberfire.commons.cluster.ClusterServiceFactory;
import org.uberfire.io.impl.cluster.helix.ClusterServiceHelix;
import org.uberfire.commons.message.MessageHandlerResolver;

@ApplicationScoped
public class ClusterServiceFactoryProducer {

    private final ClusterServiceFactory factory;

    ClusterServiceFactoryProducer() {
        final String clusterName = System.getProperty( "org.uberfire.cluster.id", null );
        final String zkAddress = System.getProperty( "org.uberfire.cluster.zk", null );
        final String localId = System.getProperty( "org.uberfire.cluster.local.id", null );
        final String resourceName = System.getProperty( "org.uberfire.cluster.vfs.lock", null );

        if ( clusterName == null || zkAddress == null || localId == null || resourceName == null ) {
            this.factory = null;
        } else {
            this.factory = new ClusterServiceFactory() {
                private ClusterService clusterService;

                @Override
                public ClusterService build( final MessageHandlerResolver resolver ) {
                    if ( clusterService == null ) {
                        clusterService = new ClusterServiceHelix( clusterName, zkAddress, localId, resourceName, resolver );
                    }
                    return clusterService;
                }
            };
        }

    }

    @Produces
    @Named("clusterServiceFactory")
    public ClusterServiceFactory clusterServiceFactory() {
        return factory;
    }

}
