package com.epam.koryagin.wp.components.text;

import com.epam.koryagin.wp.components.TextComponentType;
/**
 * Created for TextComponent default initialization
 * in the moment when the particular type 
 * (Document, Paragraph, Sentence, Token)
 * of the composite text is unknown
 * 
 * @author Alexandr Koryagin
 *
 */
public enum DefaultType implements TextComponentType {
	DEFAULT;
}
