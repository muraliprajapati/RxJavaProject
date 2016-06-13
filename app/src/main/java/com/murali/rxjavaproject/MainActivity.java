package com.murali.rxjavaproject;

import android.app.SearchManager;
import android.content.Context;
import android.os.Bundle;
import android.os.Looper;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import com.jakewharton.rxbinding.support.v7.widget.RxSearchView;
import com.jakewharton.rxbinding.support.v7.widget.SearchViewQueryTextEvent;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import retrofit2.GsonConverterFactory;
import retrofit2.Retrofit;
import retrofit2.RxJavaCallAdapterFactory;
import rx.Observable;
import rx.Observer;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action0;
import rx.functions.Func1;
import rx.schedulers.Schedulers;
import rx.subscriptions.CompositeSubscription;

public class MainActivity extends AppCompatActivity {
    private static final String TAG = "MainActivity";
    Subscription subscription, searchSubcription;
    MovieAdapter adapter;
    RecyclerView listView;
    ArrayList<Movie> list;
    Retrofit retrofit;
    NetworkCall call;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        list = new ArrayList<>();
        adapter = new MovieAdapter(this, list);
        listView = (RecyclerView) findViewById(R.id.listView);
        listView.setLayoutManager(new LinearLayoutManager(this));
        listView.setAdapter(adapter);
        subscription = new CompositeSubscription();

        retrofit = new Retrofit.Builder()
                .baseUrl(NetworkCall.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .build();
        call = retrofit.create(NetworkCall.class);

        subscription = call.getMovies("2015", NetworkCall.AUTH_TOKEN)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .flatMap(new Func1<List<Movie>, Observable<Movie>>() {
                    @Override
                    public Observable<Movie> call(List<Movie> movies) {
                        return Observable.from(movies);
                    }
                }).subscribe(new Observer<Movie>() {
                    @Override
                    public void onCompleted() {
                        Toast.makeText(MainActivity.this, "Done", Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onNext(Movie movie) {
                        list.add(movie);
                        adapter.notifyDataSetChanged();
                    }
                });
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        subscription.unsubscribe();
        searchSubcription.unsubscribe();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.search, menu);
        SearchManager searchManager = (SearchManager) getSystemService(Context.SEARCH_SERVICE);
        final SearchView searchView = (SearchView) menu.findItem(R.id.search).getActionView();
        searchView.setSearchableInfo(searchManager.getSearchableInfo(getComponentName()));
        ImageView closeButton = (ImageView) searchView.findViewById(R.id.search_close_btn);
        final Observable<SearchViewQueryTextEvent> observable = getMovieObservable(searchView);
        observable.subscribe(new Observer<SearchViewQueryTextEvent>() {
            @Override
            public void onCompleted() {

            }

            @Override
            public void onError(Throwable e) {
                Log.i(TAG, "onError: textChange " + e.getMessage());
            }

            @Override
            public void onNext(SearchViewQueryTextEvent searchViewQueryTextEvent) {
                boolean b = Looper.getMainLooper() == Looper.myLooper();
                Log.i(TAG, "onNext: textChange " + b);
                adapter.clear();
                search(searchViewQueryTextEvent.queryText().toString());
            }
        });

        searchView.setOnSearchClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


            }
        });
        closeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                searchView.setQuery("", false);
                adapter.clear();
            }
        });
        return true;
    }

    private Observable<SearchViewQueryTextEvent> getMovieObservable(SearchView searchView) {
        return RxSearchView.queryTextChangeEvents(searchView)
                .debounce(750, TimeUnit.MILLISECONDS)
                .observeOn(AndroidSchedulers.mainThread())
                .doOnSubscribe(new Action0() {
                    @Override
                    public void call() {
                        Log.i(TAG, "call: doOnSubscribe");
                        adapter.clear();
                    }
                })
                .filter(new Func1<SearchViewQueryTextEvent, Boolean>() {
                    @Override
                    public Boolean call(SearchViewQueryTextEvent event) {
                        Log.i(TAG, "call: filter: " + event.queryText().toString());
                        return !event.queryText().toString().isEmpty();
                    }
                });


    }

    private Observable<Movie> getObservable(String searchQuery) {
        return call.search(searchQuery, NetworkCall.AUTH_TOKEN)
                .doOnSubscribe(new Action0() {
                    @Override
                    public void call() {
                        boolean b = Looper.getMainLooper() == Looper.myLooper();
                        Log.i(TAG, "call: doOnS " + b);
//                        adapter.clear();
                    }
                })
                .flatMap(new Func1<List<Movie>, Observable<Movie>>() {
                    @Override
                    public Observable<Movie> call(List<Movie> movies) {
                        return Observable.from(movies);
                    }
                });
    }

    private void search(String s) {
        Log.i(TAG, "search: ");
        searchSubcription = getObservable(s)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<Movie>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {
                        Log.i(TAG, "onError: movie " + e.getMessage());
                    }

                    @Override
                    public void onNext(Movie movie) {
                        Log.i(TAG, "onNext: " + movie.getTitle());
                        list.add(movie);
                        adapter.notifyDataSetChanged();
                    }
                });
    }
}
