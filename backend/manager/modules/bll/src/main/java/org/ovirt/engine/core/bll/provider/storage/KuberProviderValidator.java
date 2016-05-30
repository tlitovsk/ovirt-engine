package org.ovirt.engine.core.bll.provider.storage;

import org.ovirt.engine.core.bll.provider.ProviderValidator;
import org.ovirt.engine.core.common.businessentities.Provider;
/**
 * Created by tlitovsk on 5/29/16.
 */
public class KuberProviderValidator extends ProviderValidator {

    public KuberProviderValidator(Provider<?> provider) {
        super(provider);
    }
}
