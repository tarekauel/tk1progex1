package views.components;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashMap;
import java.util.Map;

/**
 * Implementation of a multiselection
 * Inspired by http://stackoverflow.com/questions/23591501/tag-input-in-java-swing
 */
public class TagSelect extends JPanel {
  private boolean allowPublusherTags;

  ;
  private JTextField textField;
  private Map<String, TagComponent> selections;
  public TagSelect(boolean allowPublisherTags) {
    // Use WrapLayout in favor of FlowLayout as it does update the preferred size (i.e. its height) more correctly.
    super(new WrapLayout(FlowLayout.LEADING));

    ActionListener onClearTags = new ActionListener() {
      @Override
      public void actionPerformed(ActionEvent e) {
        removeAllTags();
      }
    };
    ActionListener onAddTag = new ActionListener() {
      @Override
      public void actionPerformed(ActionEvent e) {
        addTag(textField.getText(), TagType.TAG);
      }
    };
    ActionListener onAddPublisher = new ActionListener() {
      @Override
      public void actionPerformed(ActionEvent e) {
        addTag(textField.getText(), TagType.PUBLISHER);
      }
    };
    JButton clearTagsBtn = new JButton("Clear tags");
    JButton addTagBtn = new JButton("Add Tag");
    JButton addPublisherBtn = new JButton("Subscribe to Publisher");
    this.allowPublusherTags = allowPublisherTags;
    this.textField = new JTextField(10);
    this.textField.addActionListener(onAddTag);
    this.selections = new HashMap<String, TagComponent>();

    clearTagsBtn.addActionListener(onClearTags);
    addTagBtn.addActionListener(onAddTag);
    addPublisherBtn.addActionListener(onAddPublisher);

    this.add(clearTagsBtn);
    //this.add(addTagBtn);
    //this.add(addPublisherBtn);
    this.add(this.textField);
  }

  public String[] getTags() {
    return this.selections.keySet().toArray(new String[0]);
  }

  private void addTag(String tag, TagType preferredTagType) {
    if (!this.allowPublusherTags) {
      preferredTagType = TagType.TAG;
      tag = tag.replaceFirst("^@+", "");
    }

    if (tag.length() > 0) {
      if (!tag.startsWith("@") && !tag.startsWith("#")) {
        switch (preferredTagType) {
          case PUBLISHER:
            tag = "@" + tag;
            break;
          case TAG:
            tag = "#" + tag;
            break;
        }
      }

      if (!this.selections.containsKey(tag)) {
        TagComponent t = new TagComponent(tag);

        this.selections.put(tag, t);
        this.add(t);
        this.revalidate();
        this.textField.setText("");
      }
    }
  }

  public boolean noPendingTag() {
    return textField.getText().length() == 0;
  }

  private void removeTag(String tag) {
    TagComponent t = this.selections.get(tag);

    if (t != null) {
      this.selections.remove(tag);
      this.remove(t);
      this.revalidate();
    }
  }

  public void removeAllTags() {
    if (selections.size() > 0) {
      for (Map.Entry<String, TagComponent> e : selections.entrySet()) {
        this.remove(e.getValue());
      }
      this.revalidate();
      this.selections.clear();
    }
  }

  private static enum TagType {TAG, PUBLISHER}

  private class TagComponent extends JPanel {
    private String tag;

    public TagComponent(String tag) {
      this.tag = tag;
      ActionListener onRemoveTag = new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
          ((TagSelect) getParent()).removeTag(TagComponent.this.tag);
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
