package components;

import java.awt.FlowLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

/**
 * Implementation of a multiselection
 * Inspired by http://stackoverflow.com/questions/23591501/tag-input-in-java-swing
 * 
 * @author Markus Schanz
 */
public class MultiSelect extends JPanel {
	private JTextField textField;
	private Map<String, TagComponent> selections;
	
	
	public MultiSelect() {
		// super(new FlowLayout(FlowLayout.LEADING));
		super(new WrapLayout(FlowLayout.LEADING));
		//this.setComponentOrientation(ComponentOrientation.RIGHT_TO_LEFT);

		ActionListener onClearTags = new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				removeAllTags();
			}
		};
		ActionListener onAddTag = new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				addTag(textField.getText());
			}
		};
		JButton clearTagsBtn = new JButton("Clear tags");
		JButton addTagBtn = new JButton("Add tag");
		this.textField = new JTextField(10);
		this.selections = new HashMap<String, TagComponent>();
		
		clearTagsBtn.addActionListener(onClearTags);
		addTagBtn.addActionListener(onAddTag);
		this.textField.addActionListener(onAddTag);
		
		this.add(clearTagsBtn);
		//this.add(addTagBtn);
		this.add(this.textField);
	}
	
	public Set<String> getTags() {
		return this.selections.keySet();
	}
	
	private void addTag(String tag) {
		if (tag.length() > 0 && !this.selections.containsKey(tag)) {
			TagComponent t = new TagComponent(tag);
			
			this.selections.put(tag, t);
			this.add(t);
			this.revalidate();
			this.textField.setText("");
		}
	}
	
	private void removeTag(String tag) {
		TagComponent t = this.selections.get(tag);
		
		if (t != null) {
			this.selections.remove(tag);
			this.remove(t);
			this.revalidate();
		}
	}
	
	private void removeAllTags() {
		if (selections.size() > 0) {
			for (Map.Entry<String, TagComponent> e : selections.entrySet()) {
				this.remove(e.getValue());
			}
			this.revalidate();
			this.selections.clear();
		}
	}
	
	private class TagComponent extends JPanel {	
		private String tag;
		
		public TagComponent(String tag) {
			this.tag = tag;
			ActionListener onRemoveTag = new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {
					((MultiSelect) getParent()).removeTag(TagComponent.this.tag);
				}
			};
			JButton removeBtn = new JButton("X");
			removeBtn.setMargin(new Insets(0, 0, 0, 0));
			removeBtn.addActionListener(onRemoveTag);

			this.add(new JLabel(tag));
			this.add(removeBtn);
		}
		
		@Override
		public String toString() {
			return this.tag;
		}
	}
}
