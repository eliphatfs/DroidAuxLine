package cn.s3bit.droidauxline;

import java.util.List;
import android.annotation.SuppressLint;
import android.content.Context;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.*;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import cn.s3bit.droidauxline.LineDataStructure.Reference;

public class LineListAdapter extends ArrayAdapter<LineDataStructure> {
	public final int resourceId;
	public LineListAdapter(Context context, int resource, List<LineDataStructure> objects) {
		super(context, resource, objects);
		resourceId = resource;
	}
	@SuppressLint("ViewHolder")
	@Override
	public View getView(final int position, View convertView, ViewGroup parent) {
	    View view = LayoutInflater.from(getContext()).inflate(resourceId, null);
	    view.findViewById(R.id.buttonAdd).setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				MainService.lineData.add(position, new LineDataStructure());
				notifyDataSetChanged();
			}
		});
	    view.findViewById(R.id.buttonDel).setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				MainService.lineData.remove(position);
				notifyDataSetChanged();
			}
		});
	    ((Spinner)view.findViewById(R.id.spinnerSX)).setSelection(getItem(position).SXRef.ordinal());
	    ((Spinner)view.findViewById(R.id.spinnerSY)).setSelection(getItem(position).SYRef.ordinal());
	    ((Spinner)view.findViewById(R.id.spinnerEX)).setSelection(getItem(position).EXRef.ordinal());
	    ((Spinner)view.findViewById(R.id.spinnerEY)).setSelection(getItem(position).EYRef.ordinal());
	    ((Spinner)view.findViewById(R.id.spinnerSX)).setOnItemSelectedListener(new OnItemSelectedListener() {

			@Override
			public void onItemSelected(AdapterView<?> parent, View view, int p, long id) {
				MainService.lineData.get(position).SXRef = Reference.fromValue(p);
			}

			@Override
			public void onNothingSelected(AdapterView<?> parent) {
				parent.setSelection(0);
				MainService.lineData.get(position).SXRef = Reference.Center;
			}
		});
	    ((Spinner)view.findViewById(R.id.spinnerSY)).setOnItemSelectedListener(new OnItemSelectedListener() {

			@Override
			public void onItemSelected(AdapterView<?> parent, View view, int p, long id) {
				MainService.lineData.get(position).SYRef = Reference.fromValue(p);
			}

			@Override
			public void onNothingSelected(AdapterView<?> parent) {
				parent.setSelection(0);
				MainService.lineData.get(position).SYRef = Reference.Center;
			}
		});
	    ((Spinner)view.findViewById(R.id.spinnerEX)).setOnItemSelectedListener(new OnItemSelectedListener() {

			@Override
			public void onItemSelected(AdapterView<?> parent, View view, int p, long id) {
				MainService.lineData.get(position).EXRef = Reference.fromValue(p);
			}

			@Override
			public void onNothingSelected(AdapterView<?> parent) {
				parent.setSelection(0);
				MainService.lineData.get(position).EXRef = Reference.Center;
			}
		});
	    ((Spinner)view.findViewById(R.id.spinnerEY)).setOnItemSelectedListener(new OnItemSelectedListener() {

			@Override
			public void onItemSelected(AdapterView<?> parent, View view, int p, long id) {
				MainService.lineData.get(position).EYRef = Reference.fromValue(p);
			}

			@Override
			public void onNothingSelected(AdapterView<?> parent) {
				parent.setSelection(0);
				MainService.lineData.get(position).EYRef = Reference.Center;
			}
		});
	    ((EditText)view.findViewById(R.id.numSX)).setText(Integer.toString(getItem(position).SX));
	    ((EditText)view.findViewById(R.id.numSY)).setText(Integer.toString(getItem(position).SY));
	    ((EditText)view.findViewById(R.id.numEX)).setText(Integer.toString(getItem(position).EX));
	    ((EditText)view.findViewById(R.id.numEY)).setText(Integer.toString(getItem(position).EY));
	    ((EditText)view.findViewById(R.id.numSX)).addTextChangedListener(new TextWatcher() {
			
			@Override
			public void onTextChanged(CharSequence s, int start, int before, int count) {
				
			}
			
			@Override
			public void beforeTextChanged(CharSequence s, int start, int count, int after) {
				
			}
			
			@Override
			public void afterTextChanged(Editable s) {
				try {
					MainService.lineData.get(position).SX = Integer.parseInt(s.toString());
				}
				catch (Exception e) {
					MainService.lineData.get(position).SX = 0;
				}
			}
		});
	    ((EditText)view.findViewById(R.id.numSY)).addTextChangedListener(new TextWatcher() {
			
			@Override
			public void onTextChanged(CharSequence s, int start, int before, int count) {
				
			}
			
			@Override
			public void beforeTextChanged(CharSequence s, int start, int count, int after) {
				
			}
			
			@Override
			public void afterTextChanged(Editable s) {
				try {
					MainService.lineData.get(position).SY = Integer.parseInt(s.toString());
				}
				catch (Exception e) {
					MainService.lineData.get(position).SY = 0;
				}
			}
		});
	    ((EditText)view.findViewById(R.id.numEX)).addTextChangedListener(new TextWatcher() {
			
			@Override
			public void onTextChanged(CharSequence s, int start, int before, int count) {
				
			}
			
			@Override
			public void beforeTextChanged(CharSequence s, int start, int count, int after) {
				
			}
			
			@Override
			public void afterTextChanged(Editable s) {
				try {
					MainService.lineData.get(position).EX = Integer.parseInt(s.toString());
				}
				catch (Exception e) {
					MainService.lineData.get(position).EX = 0;
				}
			}
		});
	    ((EditText)view.findViewById(R.id.numEY)).addTextChangedListener(new TextWatcher() {
			
			@Override
			public void onTextChanged(CharSequence s, int start, int before, int count) {
				
			}
			
			@Override
			public void beforeTextChanged(CharSequence s, int start, int count, int after) {
				
			}
			
			@Override
			public void afterTextChanged(Editable s) {
				try {
					MainService.lineData.get(position).EY = Integer.parseInt(s.toString());
				}
				catch (Exception e) {
					MainService.lineData.get(position).EY = 0;
				}
			}
		});
	    ((EditText)view.findViewById(R.id.editColorR)).setText(Integer.toString(getItem(position).ColorR));
	    ((EditText)view.findViewById(R.id.editColorG)).setText(Integer.toString(getItem(position).ColorG));
	    ((EditText)view.findViewById(R.id.editColorB)).setText(Integer.toString(getItem(position).ColorB));
	    ((EditText)view.findViewById(R.id.editColorA)).setText(Integer.toString(getItem(position).ColorA));
	    ((EditText)view.findViewById(R.id.editColorR)).addTextChangedListener(new TextWatcher() {
			
			@Override
			public void onTextChanged(CharSequence s, int start, int before, int count) {
				
			}
			
			@Override
			public void beforeTextChanged(CharSequence s, int start, int count, int after) {
				
			}
			
			@Override
			public void afterTextChanged(Editable s) {
				try {
					MainService.lineData.get(position).ColorR = Integer.parseInt(s.toString());
				}
				catch (Exception e) {
					MainService.lineData.get(position).ColorR = 0;
				}
			}
		});
	    ((EditText)view.findViewById(R.id.editColorG)).addTextChangedListener(new TextWatcher() {
			
			@Override
			public void onTextChanged(CharSequence s, int start, int before, int count) {
				
			}
			
			@Override
			public void beforeTextChanged(CharSequence s, int start, int count, int after) {
				
			}
			
			@Override
			public void afterTextChanged(Editable s) {
				try {
					MainService.lineData.get(position).ColorG = Integer.parseInt(s.toString());
				}
				catch (Exception e) {
					MainService.lineData.get(position).ColorG = 0;
				}
			}
		});
	    ((EditText)view.findViewById(R.id.editColorB)).addTextChangedListener(new TextWatcher() {
			
			@Override
			public void onTextChanged(CharSequence s, int start, int before, int count) {
				
			}
			
			@Override
			public void beforeTextChanged(CharSequence s, int start, int count, int after) {
				
			}
			
			@Override
			public void afterTextChanged(Editable s) {
				try {
					MainService.lineData.get(position).ColorB = Integer.parseInt(s.toString());
				}
				catch (Exception e) {
					MainService.lineData.get(position).ColorB = 0;
				}
			}
		});
	    ((EditText)view.findViewById(R.id.editColorA)).addTextChangedListener(new TextWatcher() {
			
			@Override
			public void onTextChanged(CharSequence s, int start, int before, int count) {
				
			}
			
			@Override
			public void beforeTextChanged(CharSequence s, int start, int count, int after) {
				
			}
			
			@Override
			public void afterTextChanged(Editable s) {
				try {
					MainService.lineData.get(position).ColorA = Integer.parseInt(s.toString());
				}
				catch (Exception e) {
					MainService.lineData.get(position).ColorA = 0;
				}
			}
		});
	    return view;
	}
}
