//package com.finpay.wallet.di.module
//
//import com.finpay.wallet.view.addFarmer.fragment.FarmerAddressFormFragment
//import com.finpay.wallet.view.addFarmer.fragment.FarmerDataFormFragment
//import com.finpay.wallet.view.addGarden.fragment.*
//import com.finpay.wallet.view.delivery.DeliveryFragment
//import com.finpay.wallet.view.editprofilekoperasi.fragment.GardenFragment
//import com.finpay.wallet.view.editprofilekoperasi.fragment.KoperasiAddressFragment
//import com.finpay.wallet.view.editprofilekoperasi.fragment.KoperasiEditFragment
//import com.finpay.wallet.view.editprofilepetani.fragment.FarmerAddressFragment
//import com.finpay.wallet.view.editprofilepetani.fragment.FarmerEditFragment
//import com.finpay.wallet.view.mainkoperasi.fragment.HomeKoperasiFragment
//import com.finpay.wallet.view.mainpetani.HomePetaniFragment
//import com.finpay.wallet.view.invoice.InvoiceFragment
//import com.finpay.wallet.view.notification.farmer.NewsNotificationFragment
//import com.finpay.wallet.view.notification.farmer.OrderNotificationFragment
//import com.finpay.wallet.view.notification.farmer.PaymentNotificationFragment
//import com.finpay.wallet.view.profile.ProfileFragment
//import com.finpay.wallet.view.profilePetani.ProfilePetaniFragment
//import com.finpay.wallet.view.report.fragment.PaymentFragment
//import com.finpay.wallet.view.sendstocksupply.fragment.SendStockSupplyFragment
//import com.finpay.wallet.view.sendstocksupplyfarmer.SendStockSupplyFarmerFragment
//import com.finpay.wallet.view.transaction.fragment.TransactionFragment
//import com.finpay.wallet.view.transaction.fragment.TransactionSupplyDemandFragment
//import com.finpay.wallet.view.transactionfarmer.fragment.TransactionFarmerFragment
//import dagger.Module
//import dagger.android.ContributesAndroidInjector
//
//@Module
//abstract class FragmentModule  {
//    @ContributesAndroidInjector
//    abstract fun provideHomeKoperasiFragment(): HomeKoperasiFragment
//
//    @ContributesAndroidInjector
//    abstract fun providePaymentFragment(): PaymentFragment
//
//    @ContributesAndroidInjector
//    abstract fun provideProfileFragment(): ProfileFragment
//
//    @ContributesAndroidInjector
//    abstract fun provideProfilePetaniFragment(): ProfilePetaniFragment
//
//    @ContributesAndroidInjector
//    abstract fun provideInvoiceFragment(): InvoiceFragment
//
//    @ContributesAndroidInjector
//    abstract fun provideDeliveryFragment(): DeliveryFragment
//
//    @ContributesAndroidInjector
//    abstract fun provideHomePetaniFragment(): HomePetaniFragment
//
//    @ContributesAndroidInjector
//    abstract fun provideTransactionFragment(): TransactionFragment
//
//    @ContributesAndroidInjector
//    abstract fun provideTransactionSupplyDemandFragment(): TransactionSupplyDemandFragment
//
//    @ContributesAndroidInjector
//    abstract fun provideAddressFragment(): KoperasiAddressFragment
//
//    @ContributesAndroidInjector
//    abstract fun provideGardenFragment(): GardenFragment
//
//    @ContributesAndroidInjector
//    abstract fun provideSendSupplyFragment(): SendStockSupplyFragment
//
//    @ContributesAndroidInjector
//    abstract fun provideTransactionFramerFragment(): TransactionFarmerFragment
//
//    @ContributesAndroidInjector
//    abstract fun provideSendSupplyFarmerFragment(): SendStockSupplyFarmerFragment
//
//    @ContributesAndroidInjector
//    abstract fun provideAddressGardenFragment(): AddressGardenFragment
//
//    @ContributesAndroidInjector
//    abstract fun provideGardenAddFragment(): GardenAddFragment
//
//    @ContributesAndroidInjector
//    abstract fun provideDocumentLegalityFragment(): DocumentLegalityFragment
//
//    @ContributesAndroidInjector
//    abstract fun provideFarmerEditFragment(): FarmerEditFragment
//
//    @ContributesAndroidInjector
//    abstract fun provideOrderNotification(): OrderNotificationFragment
//
//    @ContributesAndroidInjector
//    abstract fun providePaymentNotification(): PaymentNotificationFragment
//
//    @ContributesAndroidInjector
//    abstract fun provideNewsNotification(): NewsNotificationFragment
//
//    @ContributesAndroidInjector
//    abstract fun provideFarmerAddressFragment(): FarmerAddressFragment
//
//    @ContributesAndroidInjector
//    abstract fun provideCertificateGardenFragment(): CertificateGardenFragment
//
//    @ContributesAndroidInjector
//    abstract fun provideKoperasiEditFragment(): KoperasiEditFragment
//
//    @ContributesAndroidInjector
//    abstract fun provideAddFarmerDataFormFragment(): FarmerDataFormFragment
//
//    @ContributesAndroidInjector
//    abstract fun provideAddFarmerAddressFormFragment(): FarmerAddressFormFragment
//
//    @ContributesAndroidInjector
//    abstract fun provideCertificateGardenListFragment(): CertificateGardenListFragment
//}