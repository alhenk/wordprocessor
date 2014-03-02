package com.epam.koryagin.wp.components.text;

import java.io.Serializable;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

import com.epam.koryagin.wp.components.CompositeIterator;
import com.epam.koryagin.wp.components.TextComponent;
import com.epam.koryagin.wp.components.TextComponentType;

public class CompositeText extends TextComponent implements Serializable,
		Comparable<CompositeText>, Iterable<TextComponent> {
	private static final long serialVersionUID = -1751752160132151666L;
	private List<TextComponent> components;
	private TextComponentType type;
	private TextComponentName name;
	private Iterator<?> iterator = null;

	/**
	 * Default constructor
	 */
	public CompositeText() {
		this.components = new LinkedList<TextComponent>();
	}

	private CompositeText(List<TextComponent> components) {
		this.components = components;
	}

	/**
	 * Static constructors (factory methods)
	 */
	public static CompositeText create(List<TextComponent> components) {
		CompositeText compositeText = new CompositeText(components);
		compositeText.name = TextComponentName.DEFAULT;
		compositeText.type = DefaultType.DEFAULT;
		return compositeText;
	}
	public static CompositeText create(List<TextComponent> components, TextComponentName name) {
		CompositeText compositeText = new CompositeText(components);
		compositeText.name = name;
		compositeText.type = DefaultType.DEFAULT;
		return compositeText;
	}

	public static CompositeText create(TextComponentName name) {
		CompositeText compositeText = new CompositeText();
		compositeText.name = name;
		compositeText.type = DefaultType.DEFAULT;
		return compositeText;
	}

	public static CompositeText create(TextComponentName name,
			TextComponentType type) {
		CompositeText compositeText = new CompositeText();
		compositeText.name = name;
		compositeText.type = type;
		return compositeText;
	}

	public List<TextComponent> getComponents() {
//		return (List<TextComponent>) Collections
//				.unmodifiableCollection(components);
		return components;
	}

	public void setComponents(List<TextComponent> components) {
		this.components = components;
	}

	@Override
	public void add(TextComponent component) {
		components.add(component);
	}

	@Override
	public String getValue() {
		return "";
	}

	@Override
	public TextComponentName getName() {
		return name;
	}

	@Override
	public void setName(TextComponentName name) {
		this.name = name;
	}

	@Override
	public TextComponentType getType() {
		return type;
	}

	@Override
	public void setType(TextComponentType type) {
		this.type = type;
	}

	@Override
	public void remove(TextComponent component) {
		components.remove(component);
	}

	@Override
	public String toOriginalString() {
		StringBuilder sb = new StringBuilder();
		Iterator<TextComponent> iterator = components.iterator();
		while (iterator.hasNext()) {
			TextComponent component = (TextComponent) iterator.next();
			sb.append(component.toOriginalString()).append(" ");
		}
		return sb.append("\n").toString();
	}
	@Override
	public String printXML(){
		StringBuilder sb = new StringBuilder();
		Iterator<TextComponent> iterator = components.iterator();
		while (iterator.hasNext()) {
			TextComponent component = (TextComponent) iterator.next();
			String name = DefaultType.DEFAULT.toString();
			if(component.getName()!= null){
					name = component.getName().toString();
			}
			String type = DefaultType.DEFAULT.toString();
			if(component.getType()!= null){
				type = component.getType().toString();
			}
			sb.append("\t<").append(name).append(" type=\"").append(type).append("\">\n");
			sb.append(component.printXML());
			sb.append("\t</").append(name).append(">\n");
		}
		return sb.toString();
	}

	@Override
	public Iterator<?> createIterator() {
		if (iterator == null) {
			iterator = new CompositeIterator(components.iterator());
		}
		return iterator;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((components == null) ? 0 : components.hashCode());
		result = prime * result
				+ ((iterator == null) ? 0 : iterator.hashCode());
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		result = prime * result + ((type == null) ? 0 : type.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		CompositeText other = (CompositeText) obj;
		if (components == null) {
			if (other.components != null)
				return false;
		} else if (!components.equals(other.components))
			return false;
		if (iterator == null) {
			if (other.iterator != null)
				return false;
		} else if (!iterator.equals(other.iterator))
			return false;
		if (name != other.name)
			return false;
		if (type == null) {
			if (other.type != null)
				return false;
		} else if (!type.equals(other.type))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "CompositeText [name = " + name + " type=" + type + " hash = "
				+ this.hashCode() + "]";
	}

	@Override
	public int compareTo(CompositeText o) {
		return (this.hashCode() < o.hashCode()) ? -1 : ((this.hashCode() == o
				.hashCode()) ? 0 : 1);
	}

	@Override
	public Iterator<TextComponent> iterator() {
		return this.components.iterator();
		
	}
}
