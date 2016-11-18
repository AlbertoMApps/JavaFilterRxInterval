package development.alberto.com.javacodingtest.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import development.alberto.com.javacodingtest.R;

/**
 * Created by alber on 12/11/2016.
 */
public class TextAdapter extends RecyclerView.Adapter<TextAdapter.ViewHolder> {
    private Context context;
    private int mLayout;
    private List<String> text;

    public TextAdapter(List<String> pData, int row_person, Context context) {
        this.context = context;
        this.mLayout = row_person;
        this.text = pData;
    }

    @Override
    public TextAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, final int viewType) {
        View view = LayoutInflater.from(context).inflate(mLayout, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {
        final String text = this.text.get(position);
        holder.textWord.setText(text);
    }

    @Override
    public int getItemCount() {
        return this.text.size();
    }

    public void update(List<String> textList) {
        this.text = textList;
        notifyDataSetChanged();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.word_text)
        TextView textWord;

        public ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }

    }
}
