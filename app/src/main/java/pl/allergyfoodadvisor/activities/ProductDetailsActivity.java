package pl.allergyfoodadvisor.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import pl.allergyfoodadvisor.R;
import pl.allergyfoodadvisor.api.pojos.Product;
import pl.allergyfoodadvisor.extras.CommonMethods;

public class ProductDetailsActivity extends BaseActivity {

    public static final String EXTRA_PRODUCT = "product_object_to_display";

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product_details);

        Intent intent = getIntent();
        Product product = (Product) intent.getSerializableExtra(EXTRA_PRODUCT);
        Log.d("product", product.description);
        Log.d("product", product.producer);

        final Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        CollapsingToolbarLayout collapsingToolbar =
                (CollapsingToolbarLayout) findViewById(R.id.collapsing_toolbar);
        collapsingToolbar.setTitle(product.name);

        ((TextView)findViewById(R.id.product_description)).setText(product.description);
        ((TextView)findViewById(R.id.product_producer)).setText(product.producer);

        loadBackdrop();
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

    private void loadBackdrop() {
        final ImageView imageView = (ImageView) findViewById(R.id.backdrop);
        Glide.with(this).load(CommonMethods.getRandomCheeseDrawable()).centerCrop().into(imageView);
    }
}
