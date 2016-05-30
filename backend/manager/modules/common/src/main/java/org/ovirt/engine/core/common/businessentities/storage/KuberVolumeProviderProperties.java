package org.ovirt.engine.core.common.businessentities.storage;

import org.ovirt.engine.core.common.businessentities.TenantProviderProperties;
import org.ovirt.engine.core.compat.Guid;
/**
 * Created by tlitovsk on 5/29/16.
 */

public class KuberVolumeProviderProperties extends TenantProviderProperties {

    private static final long serialVersionUID = -3117979451360188295L;
    private Guid storagePoolId;


    public KuberVolumeProviderProperties() {}

    public KuberVolumeProviderProperties(Guid storagePoolId) {
        this.storagePoolId = storagePoolId;
    }

    public void setStoragePoolId(Guid storagePoolId) {
        this.storagePoolId = storagePoolId;
    }

    public Guid getStoragePoolId() {
        return this.storagePoolId;
    }
}
