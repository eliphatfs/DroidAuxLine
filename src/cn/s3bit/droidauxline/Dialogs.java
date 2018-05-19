package cn.s3bit.droidauxline;

import java.io.File;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemLongClickListener;
import android.widget.EditText;
import android.widget.TextView;

public final class Dialogs {
	public static void showOpenSelection() {
		final File[] files = MainActivity.instance.getFilesDir().listFiles();
		final String[] names = new String[files.length - 1];
		for (int i=0; i<files.length; i++) {
			if (files[i].getName().contentEquals("__state")) {
				File tmp = files[i];
				files[i] = files[0];
				files[0] = tmp;
			}
		}
		for (int i=1; i<files.length; i++) {
			names[i - 1] = files[i].getName();
		}
		final AlertDialog d = new AlertDialog.Builder(MainActivity.instance)
		.setTitle("Load (Long Press to Delete)")
		.setItems(names, new OnClickListener() {
			@Override
			public void onClick(DialogInterface dialog, int which) {
				MainActivity.instance.load(names[which]);
			}
		}).show();
		d.getListView().setOnItemLongClickListener(new OnItemLongClickListener() {
			@Override
			public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
				showDeleteDialog(((TextView)view).getText().toString());
				d.dismiss();
				return true;
			}
		});
	}
	public static void showSaveSelection() {
		final File[] files = MainActivity.instance.getFilesDir().listFiles();
		final String[] names = new String[files.length];
		for (int i=0; i<files.length; i++) {
			if (files[i].getName().contentEquals("__state")) {
				File tmp = files[i];
				files[i] = files[0];
				files[0] = tmp;
			}
		}
		for (int i=0; i<files.length; i++) {
			names[i] = files[i].getName();
		}
		names[0] = "New...";
		new AlertDialog.Builder(MainActivity.instance)
		.setTitle("Save...")
		.setItems(names, new OnClickListener() {
			@Override
			public void onClick(DialogInterface dialog, int which) {
				if (which == 0) {
					showNewSelection();
				} else {
					MainActivity.instance.save(names[which]);
				}
			}
		}).show();
	}
	public static void showNewSelection() {
		final EditText editText = new EditText(MainActivity.instance);
		new AlertDialog.Builder(MainActivity.instance)
		.setTitle("New...")
		.setView(editText)
		.setPositiveButton("OK", new OnClickListener() {
			
			@Override
			public void onClick(DialogInterface dialog, int which) {
				MainActivity.instance.save(editText.getText().toString());
			}
		})
		.setNegativeButton("Cancel", new OnClickListener() {
			
			@Override
			public void onClick(DialogInterface dialog, int which) {
				
			}
		}).show();
	}
	public static void showDeleteDialog(final String toDel) {

		new AlertDialog.Builder(MainActivity.instance)
		.setTitle("Delete Confirm")
		.setMessage("Delete " + toDel + "?")
		.setPositiveButton("Yes", new OnClickListener() {
			
			@Override
			public void onClick(DialogInterface dialog, int which) {
				new File(MainActivity.instance.getFilesDir(), toDel).delete();
				showOpenSelection();
			}
		})
		.setNegativeButton("No", new OnClickListener() {
			
			@Override
			public void onClick(DialogInterface dialog, int which) {
				showOpenSelection();
			}
		}).show();
	}
}
