package  com.example.newsfeedapp.di

import android.content.Context
import androidx.room.Room
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.request.RequestOptions
import com.example.newsfeedapp.R
import com.example.newsfeedapp.data.sources.favouriteLocalData.FavouriteNewsDataBase
import com.example.newsfeedapp.data.sources.homeCahedData.HomeNewsDataBase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Singleton


@Module
@InstallIn(ApplicationComponent::class)
object AppModule {


    @Provides
    @Singleton
    fun provideFavouriteDatabase(@ApplicationContext context: Context)= Room.databaseBuilder(context,
        FavouriteNewsDataBase::class.java, "FAV_DATABASE_NAME")
        .allowMainThreadQueries()
        .fallbackToDestructiveMigration()
        .build()

    @Singleton
    @Provides
    fun provideFavouriteDao(db:FavouriteNewsDataBase)=db.getFavouriteDao()


    @Provides
    @Singleton
    fun provideNewsDatabase(@ApplicationContext context: Context)= Room.databaseBuilder(context,
        HomeNewsDataBase::class.java, "NEWS_DATABASE_NAME")
        .fallbackToDestructiveMigration()
        .build()

    @Singleton
    @Provides
    fun provideNewsDao(db:HomeNewsDataBase)=db.getHomeNewsDao()


    @Provides
    @Singleton
    fun provideRequestOptionForGlide()=   RequestOptions
        .placeholderOf(R.drawable.placeholder)
        .error(R.drawable.placeholder)
        .diskCacheStrategy(DiskCacheStrategy.ALL)


    @Provides
    @Singleton
    fun provideGlide(@ApplicationContext context: Context,requestOptions: RequestOptions)=    Glide.with(context)
        .setDefaultRequestOptions(requestOptions)



}





