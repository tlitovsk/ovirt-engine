#
# ovirt-engine-setup -- ovirt engine setup
# Copyright (C) 2013 Red Hat, Inc.
#
# Licensed under the Apache License, Version 2.0 (the "License");
# you may not use this file except in compliance with the License.
# You may obtain a copy of the License at
#
#     http://www.apache.org/licenses/LICENSE-2.0
#
# Unless required by applicable law or agreed to in writing, software
# distributed under the License is distributed on an "AS IS" BASIS,
# WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
# See the License for the specific language governing permissions and
# limitations under the License.
#


"""ovirt-host-setup system plugin."""


from otopi import util


from . import environment
from . import sysctl
from . import memcheck
from . import nfs
from . import exportfs
from . import selinux


@util.export
def createPlugins(context):
    environment.Plugin(context=context)
    sysctl.Plugin(context=context)
    memcheck.Plugin(context=context)
    nfs.Plugin(context=context)
    exportfs.Plugin(context=context)
    selinux.Plugin(context=context)


# vim: expandtab tabstop=4 shiftwidth=4
