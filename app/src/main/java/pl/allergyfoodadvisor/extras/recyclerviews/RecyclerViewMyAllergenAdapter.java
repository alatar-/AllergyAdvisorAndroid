package pl.allergyfoodadvisor.extras.recyclerviews;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

import pl.allergyfoodadvisor.R;
import pl.allergyfoodadvisor.api.pojos.Allergen;
import pl.allergyfoodadvisor.extras.DataManager;

import static pl.allergyfoodadvisor.extras.CommonMethods.getResources;

public class RecyclerViewMyAllergenAdapter
        extends RecyclerView.Adapter<RecyclerViewMyAllergenAdapter.ViewHolder>{

    private final TypedValue mTypedValue = new TypedValue();
    private int mBackground;
    private List<Allergen> mValues;
    private boolean isSavedAllergenView; //there are views for already saved or olny searched allergens
    private RecyclerViewMyAllergenAdapter twinRecycler;

    public static class ViewHolder extends RecyclerView.ViewHolder {
        public String mBoundString;

        public final View mView;
        public final TextView mTextView;

        public ViewHolder(View view) {
            super(view);
            mView = view;
            mTextView = (TextView) view.findViewById(R.id.my_allergen_textview);
        }

        @Override
        public String toString() {
            return super.toString() + " '" + mTextView.getText();
        }
    }

    public String getValueAt(int position) {
        return mValues.get(position).name;
    }

    public RecyclerViewMyAllergenAdapter(Context context, List<Allergen> items, boolean isSavedAllergenView,
                                         RecyclerViewMyAllergenAdapter twinRecycler) {
        context.getTheme().resolveAttribute(R.attr.selectableItemBackground, mTypedValue, true);
        mBackground = mTypedValue.resourceId;
        mValues = items;
        this.isSavedAllergenView = isSavedAllergenView;
        this.twinRecycler = twinRecycler;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.my_allergen_list_item, parent, false);
        view.setBackgroundResource(mBackground);
        View imageView = view.findViewById(R.id.my_allergen_imageview);
        View imageButton = view.findViewById(R.id.my_allergen_imagebutton);
        View linearLayout = view.findViewById(R.id.my_allergen_linearlayout);

        if(isSavedAllergenView){
            imageView.setVisibility(View.VISIBLE);
            imageButton.setVisibility(View.VISIBLE);
            linearLayout.setBackground( getResources().getDrawable(R.drawable.back) );
        }
        else{
            imageView.setVisibility(View.GONE);
            imageButton.setVisibility(View.GONE);
        }

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {
        final Allergen allergen = mValues.get(position);

        holder.mBoundString = allergen.name;
        holder.mTextView.setText(allergen.name);

        holder.mView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Context context = v.getContext();

                if (context != null) {
                    if(isSavedAllergenView){
                        removeFromAllergenList(allergen);
                        DataManager.getInstance().removeFromMyAllergens(allergen._id + "|" + allergen.name);
                        notifyDataSetChanged();
                    }
                    else{
                        saveToAllergenList(allergen);
                        DataManager.getInstance().saveToMyAllergens(allergen._id + "|" + allergen.name);
                        twinRecycler.notifyDataSetChanged();
                    }
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return mValues.size();
    }

    public void removeFromAllergenList(Allergen allergen){
        mValues.remove(allergen);
    }

    public void saveToAllergenList(Allergen allergen){
        if(!twinRecycler.mValues.contains(allergen)){
            twinRecycler.mValues.add(allergen);
        }
    }
}
