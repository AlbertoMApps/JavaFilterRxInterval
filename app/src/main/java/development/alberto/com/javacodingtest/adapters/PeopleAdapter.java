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
import development.alberto.com.javacodingtest.api.Models.Person;

/**
 * Created by alber on 12/11/2016.
 */

public class PeopleAdapter  extends RecyclerView.Adapter<PeopleAdapter.ViewHolder> {
    private Context context;
    private int mLayout;
    private List<Person> text;

    public PeopleAdapter(List<Person> pData, int row_person, Context context) {
        this.context = context;
        this.mLayout = row_person;
        this.text = pData;
    }

    @Override
    public PeopleAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, final int viewType) {
        View view = LayoutInflater.from(context).inflate(mLayout, parent, false);
        return new PeopleAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final PeopleAdapter.ViewHolder holder, int position) {
        final Person text = this.text.get(position);
        holder.textWord.setText(text.getFirstName() +" : " + text.getLastName());
    }

    @Override
    public int getItemCount() {
        return this.text.size();
    }

    public void update(List<Person> textList) {
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
