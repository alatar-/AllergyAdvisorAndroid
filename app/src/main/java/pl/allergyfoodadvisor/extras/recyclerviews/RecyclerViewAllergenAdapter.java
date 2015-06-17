package pl.allergyfoodadvisor.extras.recyclerviews;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import pl.allergyfoodadvisor.R;
import pl.allergyfoodadvisor.api.pojos.Allergen;
import pl.allergyfoodadvisor.extras.DataManager;

public class RecyclerViewAllergenAdapter
        extends RecyclerView.Adapter<RecyclerViewAllergenAdapter.ViewHolder> {

    private final TypedValue mTypedValue = new TypedValue();
    private int mBackground;
    private List<Allergen> mValues;
    private Map<String, Vote> mVotes = new HashMap<String, Vote>();

    public enum Vote{
        VOTE_CONTAINS, VOTE_NOT_CONTAINS, NOT_VOTE
    }

    public static class ViewHolder extends RecyclerView.ViewHolder
            implements View.OnClickListener {
        public String mBoundString;

        public final View mView;
        public final TextView mTextView;
        public final View mContainsBttn;
        public final View mNotContainsBttn;
        public IMyViewHolderClicks mListener;

        public ViewHolder(View view, IMyViewHolderClicks listener) {
            super(view);
            mView = view;
            mTextView = (TextView) view.findViewById(R.id.allergen_textview);
            mContainsBttn = view.findViewById(R.id.imageButton1);
            mNotContainsBttn = view.findViewById(R.id.imageButton2);
            mListener = listener;
            mContainsBttn.setOnClickListener(this);
            mNotContainsBttn.setOnClickListener(this);
        }

        @Override
        public String toString() {
            return super.toString() + " '" + mTextView.getText();
        }

        @Override
        public void onClick(View v) {
            if(v == mContainsBttn){
                mListener.onVoteContainsClick(v);
            }
            else if(v == mNotContainsBttn){
                mListener.onVoteNotContainsClick(v);
            }
        }

        public static interface IMyViewHolderClicks {
            public void onVoteContainsClick(View caller);
            public void onVoteNotContainsClick(View caller);
        }
    }

    public String getValueAt(int position) {
        return mValues.get(position).name;
    }

    public RecyclerViewAllergenAdapter(Context context, List<Allergen> items) {
        context.getTheme().resolveAttribute(R.attr.selectableItemBackground, mTypedValue, true);
        mBackground = mTypedValue.resourceId;
        mValues = getMyAllergens(items);
        for( Allergen a : mValues){
            mVotes.put(a.name, Vote.NOT_VOTE);
        }
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        final View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.allergen_list_item, parent, false);
        view.setBackgroundResource(mBackground);

        ViewHolder vh = new ViewHolder(view, new ViewHolder.IMyViewHolderClicks(){
            public void onVoteContainsClick(View caller) {
                vote(view, Vote.VOTE_CONTAINS);
            }

            public void onVoteNotContainsClick(View caller){
                vote(view, Vote.VOTE_NOT_CONTAINS);
            }
        });

        return vh;
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
                    DataManager.getInstance().saveToMyAllergens(allergen._id + "|" + allergen.name);
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return mValues.size();
    }

    public List<Allergen> getMyAllergens(List<Allergen> productAlrg){
        List<String> myAlrg = DataManager.getInstance().getMyAllergens();
        List<Allergen> productAlrgSub = new ArrayList<Allergen>();

        for(Allergen a: productAlrg) {
            for(String s: myAlrg){
                if(s.trim().contains(a.name)){
                    productAlrgSub.add(a);
                    break;
                }
            }
        }

        return productAlrgSub;
    }

    public void vote(View view, Vote vote){
        String alrgName = (String) ((TextView) view.findViewById(R.id.allergen_textview)).getText();
        TextView tvc = (TextView) view.findViewById(R.id.allergen_textview_contains);
        TextView tvn = (TextView) view.findViewById(R.id.allergen_textview_not_contains);

        if( vote == Vote.VOTE_CONTAINS){
            switch(mVotes.get(alrgName)){
                case VOTE_CONTAINS:
                    return;
                case VOTE_NOT_CONTAINS:
                    tvn.setText( decrementStr((String) tvn.getText()) );
                    tvc.setText( incrementStr((String) tvc.getText()) );
                    break;
                case NOT_VOTE:
                    tvc.setText( incrementStr((String) tvc.getText()) );
                    break;
            }

            mVotes.put(alrgName, Vote.VOTE_CONTAINS);
        }
        else if( vote == Vote.VOTE_NOT_CONTAINS){
            switch(mVotes.get(alrgName)){
                case VOTE_CONTAINS:
                    tvc.setText( decrementStr((String) tvc.getText()) );
                    tvn.setText( incrementStr((String) tvn.getText()) );
                    break;
                case VOTE_NOT_CONTAINS:
                    return;
                case NOT_VOTE:
                    tvn.setText( incrementStr((String) tvn.getText()) );
                    break;
            }

            mVotes.put(alrgName, Vote.VOTE_NOT_CONTAINS);
        }
    }

    public String incrementStr(String s){
        String ss = s.replace(".", "").replace("k", "000");
        int iVotes = Integer.parseInt(ss) + 1;

        if(iVotes > 999){
            return s;
        }
        else{
            return Integer.toString(iVotes);
        }
    }

    public String decrementStr(String s){
        String ss = s.replace(".", "").replace("k", "000");
        int iVotes = Integer.parseInt(ss) - 1;

        if(iVotes > 999){
            return s;
        }
        else{
            return Integer.toString(iVotes);
        }
    }
}