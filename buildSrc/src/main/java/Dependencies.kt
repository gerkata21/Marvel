object Dependencies {
    val kotlinStandardLibrary = "org.jetbrains.kotlin:kotlin-stdlib-jdk7:${Versions.kotlin}"
    val ktx = "androidx.core:core-ktx:${Versions.ktx}"

    // reactive
    val rxAndroid = "io.reactivex.rxjava2:rxandroid:${Versions.rxAndroid}"

    // test   ----- can be grouped together as an enhancements
    val junit = "junit:junit:${Versions.junit}"

    // ui tests
    val extJunit = "androidx.test.ext:junit:${Versions.extJunit}"
    val espresso = "androidx.test.espresso:espresso-core:${Versions.espresso}"

    // UI
    val appcompat = "androidx.appcompat:appcompat:${Versions.appcompat}"
    val constraintlayout = "androidx.constraintlayout:constraintlayout:${Versions.constraintlayout}"
    val material = "com.google.android.material:material:${Versions.material}"
    val swipeToRefresh = "androidx.swiperefreshlayout:swiperefreshlayout:${Versions.swipeToRefresh}"
    val shimmer = "com.facebook.shimmer:shimmer:${Versions.shimmer}"

    // Navigation
    val navigationFragment = "androidx.navigation:navigation-fragment-ktx:${Versions.navigation}"
    val navigationUI = "androidx.navigation:navigation-ui-ktx:${Versions.navigation}"

    // network
    val retrofit = "com.squareup.retrofit2:retrofit:${Versions.retrofitVersion}"
    val retrofitGsonConverter = "com.squareup.retrofit2:converter-gson:${Versions.retrofitVersion}"
    val okHttpInterceptor = "com.squareup.okhttp3:logging-interceptor:${Versions.okHttpInterceptor}"
    val retrofitRxJava2Adapter =
        "com.jakewharton.retrofit:retrofit2-rxjava2-adapter:${Versions.retrofitRxJava2Adapter}"

    val glide = "com.github.bumptech.glide:glide:${Versions.glide}"
    val glideCompiler = "com.github.bumptech.glide:compiler:${Versions.glide}"

    // Service locator
    val koinAndroid = "org.koin:koin-android:${Versions.koin_version}"
    val koinScope = "org.koin:koin-android-scope:${Versions.koin_version}"
    val koinViewModel = "org.koin:koin-android-viewmodel:${Versions.koin_version}"

    // lifecycle
    val lifecycleViewModel =
        "androidx.lifecycle:lifecycle-viewmodel-ktx:${Versions.lifecycle_version}"
    val lifecycleLiveData =
        "androidx.lifecycle:lifecycle-livedata-ktx:${Versions.lifecycle_version}"

    // room db ------- can be grouped together as an enhancements
    val roomRunTime = "androidx.room:room-runtime:${Versions.roomVersion}"
    val roomKTX = "androidx.room:room-ktx:${Versions.roomVersion}"
    val roomCompiler = "androidx.room:room-compiler:${Versions.roomVersion}"
    val roomRxJava2 = "androidx.room:room-rxjava2:${Versions.roomVersion}"


}
