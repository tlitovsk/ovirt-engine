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


"""Utils."""


import pwd
import grp
import gettext
_ = lambda m: gettext.dgettext(message=m, domain='ovirt-engine-setup')


from otopi import util


@util.export
def processTemplate(template, subst={}):
    content = ''
    with open(template, 'r') as f:
        content = f.read()
    for k, v in subst.items():
        content = content.replace(str(k), str(v))
    return content


@util.export
def getUid(user):
    return pwd.getpwnam(user)[2]


@util.export
def getGid(group):
    return grp.getgrnam(group)[2]


@util.export
def parsePort(port):
    try:
        port = int(port)
    except ValueError:
        raise ValueError(
            _('Invalid port {number}').format(
                number=port,
            )
        )
    if port < 0 or port > 0xffff:
        raise ValueError(
            _('Invalid number {number}').format(
                number=port,
            )
        )
    return port


@util.export
def escape(s, chars):
    ret = ''
    for c in s:
        if c in chars:
            ret += '\\'
        ret += c
    return ret


# vim: expandtab tabstop=4 shiftwidth=4
