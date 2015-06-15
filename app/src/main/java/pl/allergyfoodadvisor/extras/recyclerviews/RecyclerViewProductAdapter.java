package pl.allergyfoodadvisor.extras.recyclerviews;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.util.List;

import pl.allergyfoodadvisor.R;
import pl.allergyfoodadvisor.activities.ProductDetailsActivity;
import pl.allergyfoodadvisor.api.pojos.Product;
import pl.allergyfoodadvisor.extras.CommonMethods;
import pl.allergyfoodadvisor.extras.DataManager;

public class RecyclerViewProductAdapter
        extends RecyclerView.Adapter<RecyclerViewProductAdapter.ViewHolder> {

    private final TypedValue mTypedValue = new TypedValue();
    private int mBackground;
    private List<Product> mValues;

    public static class ViewHolder extends RecyclerView.ViewHolder {
        public String mBoundString;

        public final View mView;
        public final ImageView mImageView;
        public final TextView mTextView;

        public ViewHolder(View view) {
            super(view);
            mView = view;
            mImageView = (ImageView) view.findViewById(R.id.avatar);
            mTextView = (TextView) view.findViewById(android.R.id.text1);
        }

        @Override
        public String toString() {
            return super.toString() + " '" + mTextView.getText();
        }
    }

    public String getValueAt(int position) {
        return mValues.get(position).name;
    }

    public RecyclerViewProductAdapter(Context context, List<Product> items) {
        context.getTheme().resolveAttribute(R.attr.selectableItemBackground, mTypedValue, true);
        mBackground = mTypedValue.resourceId;
        mValues = items;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.product_list_item, parent, false);
        view.setBackgroundResource(mBackground);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {
        final Product product = mValues.get(position);

        holder.mBoundString = product.name;
        holder.mTextView.setText(product.name);

        holder.mView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Context context = v.getContext();

                if (context != null) {
                    Log.d("y", product._id + "|" + product.name);
                    DataManager.getInstance().saveToHistory(product._id + "|" + product.name);

                    Intent intent = new Intent(context, ProductDetailsActivity.class);
                    intent.putExtra(ProductDetailsActivity.EXTRA_PRODUCT, product);

                    context.startActivity(intent);
                }
            }
        });

        Glide.with(holder.mImageView.getContext())
                .load(CommonMethods.getDrawable(product.name))
                .fitCenter()
                .into(holder.mImageView);
    }

    @Override
    public int getItemCount() {
        return mValues.size();
    }
}