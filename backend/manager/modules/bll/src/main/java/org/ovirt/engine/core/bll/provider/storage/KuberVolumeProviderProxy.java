package org.ovirt.engine.core.bll.provider.storage;

import java.security.cert.Certificate;
import java.util.List;
import java.util.concurrent.Callable;

import org.ovirt.engine.core.bll.provider.ProviderProxy;
import org.ovirt.engine.core.common.businessentities.Provider;
import org.ovirt.engine.core.common.businessentities.StorageDomainDynamic;
import org.ovirt.engine.core.common.businessentities.StorageDomainStatic;
import org.ovirt.engine.core.common.businessentities.StorageDomainStatus;
import org.ovirt.engine.core.common.businessentities.StorageDomainType;
import org.ovirt.engine.core.common.businessentities.StorageFormatType;
import org.ovirt.engine.core.common.businessentities.StoragePoolIsoMap;
import org.ovirt.engine.core.common.businessentities.storage.KuberVolumeProviderProperties;
import org.ovirt.engine.core.common.businessentities.storage.StorageType;
import org.ovirt.engine.core.compat.Guid;
import org.ovirt.engine.core.dal.dbbroker.DbFacade;
import org.ovirt.engine.core.dao.StoragePoolIsoMapDao;
import org.ovirt.engine.core.utils.transaction.TransactionMethod;
import org.ovirt.engine.core.utils.transaction.TransactionSupport;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


/**
 * Created by tlitovsk on 5/29/16.
 */
public class KuberVolumeProviderProxy implements ProviderProxy {

    protected Provider<KuberVolumeProviderProperties> provider;

    private boolean runInNewTransaction = true;

    private static Logger log = LoggerFactory.getLogger(KuberVolumeProviderProxy.class);

    public KuberVolumeProviderProxy(Provider<KuberVolumeProviderProperties> provider) {
        this.provider = provider;
    }

    /**
     * Test the connection to the provider.<br>
     * If the connection is unsuccessful, an exception will be thrown.
     */
    @Override
    public void testConnection() {

    }

    /**
     * Get the certificate chain of the provider.<br>
     * Useful when the provider is secured.
     * @return List of Certificate objects
     */
    @Override
    public List<? extends Certificate> getCertificateChain(){
        return null;
    }

    /* Just one more code copy */
    protected static DbFacade getDbFacade() {
        return DbFacade.getInstance();
    }

    private <T> void execute(final Callable<T> callable) {
        if (runInNewTransaction) {
            TransactionSupport.executeInNewTransaction(new TransactionMethod<Object>() {
                @Override
                public Object runInTransaction() {
                    invokeCallable(callable);
                    return null;
                }
            });
        } else {
            invokeCallable(callable);
        }
    }

    private <T> void invokeCallable(Callable<T> callable) {
        try {
            callable.call();
        } catch (Exception e) {
            log.error("Error in Kuber storage.", e);
        }
    }

    public void attachKuberDomainToPool(final Guid storageDomainId, final Guid storagePoolId) {
        execute(new Callable<Object>() {
            @Override
            public Object call() {
                StoragePoolIsoMap storagePoolIsoMap =
                        new StoragePoolIsoMap(storageDomainId, storagePoolId, StorageDomainStatus.Maintenance);
                getStoragePoolIsoMapDao().save(storagePoolIsoMap);
                return null;
            }
        });
    }

    /**
     * Callback executed when the provider is added.<br>
     * Useful to add provider-specific operations when the provider is added.
     */
    @Override
    public void onAddition() {

        // Storage domain static
        StorageDomainStatic domainStaticEntry = new StorageDomainStatic();
        domainStaticEntry.setId(Guid.newGuid());
        domainStaticEntry.setStorage(provider.getId().toString());
        domainStaticEntry.setStorageName(provider.getName());
        domainStaticEntry.setDescription(provider.getDescription());
        domainStaticEntry.setStorageFormat(StorageFormatType.V1);
        domainStaticEntry.setStorageType(StorageType.LOCALFS);
        domainStaticEntry.setStorageDomainType(StorageDomainType.Volume);
        domainStaticEntry.setWipeAfterDelete(false);
        getDbFacade().getStorageDomainStaticDao().save(domainStaticEntry);
        // Storage domain dynamic
        StorageDomainDynamic domainDynamicEntry = new StorageDomainDynamic();
        domainDynamicEntry.setId(domainStaticEntry.getId());
        getDbFacade().getStorageDomainDynamicDao().save(domainDynamicEntry);

        attachKuberDomainToPool(domainStaticEntry.getId() ,
                provider.getAdditionalProperties().getStoragePoolId() );

        /*
        CINDERStorageHelper CINDERStorageHelper = new CINDERStorageHelper();
        CINDERStorageHelper.setRunInNewTransaction(false);
        CINDERStorageHelper.attachCinderDomainToPool(storageDomainId, storagePoolId);
        CINDERStorageHelper.activateCinderDomain(storageDomainId, storagePoolId);
        */
    }

    private StoragePoolIsoMapDao getStoragePoolIsoMapDao() {
        return getDbFacade().getStoragePoolIsoMapDao();
    }
    /**
     * Callback executed when the provider is modified.<br>
     * Useful to add provider-specific operations when the provider is modified.
     */
    @Override
    public void onModification() {

    }

    /**
     * Callback executed when the provider is removed.<br>
     * Useful to add provider-specific operations when the provider is removed.
     */
    @Override
    public void onRemoval() {

    }

    /**
     * Gets a specific validator for the provider
     * @return
     */
    @Override
    public KuberProviderValidator getProviderValidator() {
        return new KuberProviderValidator(this.provider);
    }

}

