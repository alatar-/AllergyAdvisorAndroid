package pl.allergyfoodadvisor.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.util.List;

import pl.allergyfoodadvisor.R;
import pl.allergyfoodadvisor.api.pojos.Allergen;
import pl.allergyfoodadvisor.api.pojos.Product;
import pl.allergyfoodadvisor.extras.CommonMethods;
import pl.allergyfoodadvisor.extras.DataManager;
import pl.allergyfoodadvisor.extras.MyLinearLayoutManager;
import pl.allergyfoodadvisor.extras.recyclerviews.RecyclerViewAllergenAdapter;

public class ProductDetailsActivity extends BaseActivity {

    public static final String EXTRA_PRODUCT = "product_object_to_display";
    private Product mProduct;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product_details);

        Intent intent = getIntent();
        mProduct = (Product) intent.getSerializableExtra(EXTRA_PRODUCT);

        final Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        CollapsingToolbarLayout collapsingToolbar =
                (CollapsingToolbarLayout) findViewById(R.id.collapsing_toolbar);
        collapsingToolbar.setTitle(mProduct.name);

        ((TextView) findViewById(R.id.product_description)).setText(mProduct.description);
        ((TextView) findViewById(R.id.product_producer)).setText(mProduct.producer);

        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.allergen_recyclerview);
        recyclerView.setLayoutManager(new MyLinearLayoutManager(recyclerView.getContext(), 1, false));
        recyclerView.setAdapter(new RecyclerViewAllergenAdapter(this,
                this.mProduct.allergens));

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.edit);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Edition is not yet supported, sorry.", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
        Log.d("product", this.mProduct.allergens.get(0).name);

        loadBackdrop(mProduct.name);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                finish(); // go back
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    private void loadBackdrop(String name) {
        final ImageView imageView = (ImageView) findViewById(R.id.backdrop);
        Glide.with(this).load(CommonMethods.getDrawable(name)).centerCrop().into(imageView);
    }
}
