package com.epam.koryagin.wp.components.img;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import com.epam.koryagin.wp.components.NullIterator;
import com.epam.koryagin.wp.components.TextComponent;
import com.epam.koryagin.wp.components.TextComponentType;


public class Image extends TextComponent implements Serializable,
		Comparable<Image> {
	private static final long serialVersionUID = -8967983048634036540L;
	private ArrayList<Integer> pixels;
	private TextComponentType type;
	private TextComponentName name;

	public Image() {
		this.pixels = new ArrayList<>();
		this.name = TextComponentName.IMAGE;
		this.type = ImageType.UNDEFINED;
	}

	public Image(List<Integer> pixels) {
		this.pixels = (ArrayList<Integer>) pixels;
		this.type = ImageType.UNDEFINED;
	}

	@Override
	public int compareTo(Image o) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Image other = (Image) obj;
		if (pixels == null) {
			if (other.pixels != null)
				return false;
		} else if (!pixels.equals(other.pixels))
			return false;
		if (type == null) {
			if (other.type != null)
				return false;
		} else if (!type.equals(other.type))
			return false;
		return true;
	}

	@Override
	public TextComponentType getType() {
		return type;
	}
	@Override
	public TextComponentName getName() {
		return name;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((pixels == null) ? 0 : pixels.hashCode());
		result = prime * result + ((type == null) ? 0 : type.hashCode());
		return result;
	}

	@Override
	public String toString() {
		return new StringBuilder("Image [ ")
				.append(pixels.size())
				.append("  pixels, type=")
				.append(type)
				.append(" ]").toString();
	}

	@Override
	public String toOriginalString() {
		return new StringBuilder("Image [ ")
				.append(pixels.size())
				.append("  pixels, type=")
				.append(type)
				.append(" ]").toString();
	}

	public Iterator<TextComponent> createIterator(){
		return new NullIterator();
	}
}
