package org.ovirt.engine.core.bll;

import org.ovirt.engine.core.compat.Guid;

public enum PredefinedRoles {
    SUPER_USER(new Guid("00000000-0000-0000-0000-000000000001")),
    ENGINE_USER(new Guid("00000000-0000-0000-0001-000000000001")),
    POWER_USER(new Guid("00000000-0000-0000-0001-000000000002")),
    CLUSTER_ADMIN(new Guid("DEF00001-0000-0000-0000-DEF000000001")),
    DATA_CENTER_ADMIN(new Guid("DEF00002-0000-0000-0000-DEF000000002")),
    STORAGE_ADMIN(new Guid("DEF00003-0000-0000-0000-DEF000000003")),
    HOST_ADMIN(new Guid("DEF00004-0000-0000-0000-DEF000000004")),
    NETWORK_ADMIN(new Guid("DEF00005-0000-0000-0000-DEF000000005")),
    VM_OPERATOR(new Guid("DEF00006-0000-0000-0000-DEF000000006")),
    VM_POOL_ADMIN(new Guid("DEF00007-0000-0000-0000-DEF000000007")),
    TEMPLATE_ADMIN(new Guid("DEF00008-0000-0000-0000-DEF000000008")),
    TEMPLATE_USER(new Guid("DEF00009-0000-0000-0000-DEF000000009")),
    QUOTA_CONSUMER(new Guid("DEF0000A-0000-0000-0000-DEF00000000A")),
    DISK_OPERATOR(new Guid("DEF0000A-0000-0000-0000-DEF00000000B")),
    DISK_CREATOR(new Guid("DEF0000A-0000-0000-0000-DEF00000000C")),
    VM_CREATOR(new Guid("DEF0000A-0000-0000-0000-DEF00000000D")),
    TEMPLATE_CREATOR(new Guid("DEF0000A-0000-0000-0000-DEF00000000E")),
    TEMPLATE_OWNER(new Guid("DEF0000A-0000-0000-0000-DEF00000000F")),
    VNIC_PROFILE_USER(new Guid("DEF0000A-0000-0000-0000-DEF000000010")),
    INSTANCE_CREATOR(new Guid("DEF00011-0000-0000-0000-DEF000000011")),
    INSTANCE_OPERATOR(new Guid("DEF00012-0000-0000-0000-DEF000000012")),
    TAG_ADMIN(new Guid("DEF00011-0000-0000-0000-DEF000000013")),
    BOOKMARK_ADMIN(new Guid("DEF00011-0000-0000-0000-DEF000000014")),
    EVENT_NOTIFICATION_ADMIN(new Guid("DEF00011-0000-0000-0000-DEF000000015")),
    DISK_PROFILE_USER(new Guid("DEF00020-0000-0000-0000-ABC000000010")),
    VM_RUN_TIME_OPERATOR(new Guid("DEF00006-0000-0000-0000-DEF000000011")),

    MAC_POOL_ADMIN(new Guid("DEF00013-0000-0000-0000-DEF000000013")),
    MAC_POOL_USER(new Guid("DEF00014-0000-0000-0000-DEF000000014")),

    CPU_PROFILE_CREATOR(new Guid("DEF00016-0000-0000-0000-DEF000000016")),
    CPU_PROFILE_OPERATOR(new Guid("DEF00017-0000-0000-0000-DEF000000017"));

    private Guid id;

    private PredefinedRoles(Guid value) {
        id = value;
    }

    public Guid getId() {
        return id;
    }

}
