
package development.alberto.com.javacodingtest.api.Models;



import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Generated;

@Generated("org.jsonschema2pojo")
public class People implements Parcelable {

    @SerializedName("people")
    @Expose
    private List<Person> people = new ArrayList<Person>();

    /**
     * 
     * @return
     *     The people
     */
    public List<Person> getPeople() {
        return people;
    }

    /**
     * 
     * @param people
     *     The people
     */
    public void setPeople(List<Person> people) {
        this.people = people;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int flags) {
    }
}
