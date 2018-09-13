package android.mdp.android_3004;

import android.content.Context;
import android.graphics.PorterDuff;
import android.graphics.drawable.Drawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;

public class ChatListAdapter extends ArrayAdapter<Message> {

	private LayoutInflater inflater;
	private ArrayList<Message> message_list;
	private int resource_id;

	protected ChatListAdapter(Context c, int r_id, ArrayList<Message> m_list) {
		super(c, r_id, m_list);
		message_list = m_list;
		inflater = (LayoutInflater) c.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		resource_id = r_id;
	}

	public View getView(int position, View convertView, ViewGroup parent) {
		convertView = inflater.inflate(resource_id, null);

		Message message = message_list.get(position);
		if (message != null) {
			TextView details = convertView.findViewById(R.id.msg_txt_details);
			details.setText(message.getDetails());

			Instruction instruction = getInstruction(message.getDetails());
			if (instruction != null) {
				TextView description = convertView.findViewById(R.id.msg_txt_description);
				description.setVisibility(View.VISIBLE);
				description.setText(":: " + instruction.getDescription());
			}

			Drawable speech = convertView.getResources().getDrawable(R.drawable.d_speech, null);
			speech.setColorFilter(message.getColor(), PorterDuff.Mode.SRC_ATOP);
			convertView.setBackground(speech);
		}
		return convertView;
	}

	public Instruction getInstruction(String text) {
		for (Instruction i : Instruction.values()) {
			if (i.getArduino().equalsIgnoreCase(text)) {
				return i;
			}
		}
		return null;
	}
}
