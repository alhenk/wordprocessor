package com.epam.koryagin.wp.components.text;

import com.epam.koryagin.wp.components.TextComponentType;
/**
 * Type of composite text
 * collection of tokens
 * @author Alexandr Koryagin
 *
 */
public enum SentenceType implements TextComponentType{
	DECLARATIVE, INTERROGATIVE, EXCLAMATORY, DEFAULT;
}
