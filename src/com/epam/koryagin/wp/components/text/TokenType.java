package com.epam.koryagin.wp.components.text;

import com.epam.koryagin.wp.components.TextComponentType;
/**
 * Type of homogeneous component
 * in composite pattern model
 * 
 * @author Alexandr Koryagin
 *
 */
public enum TokenType implements TextComponentType{
	WORD, NUMERIC, PUNCTUATION, DEFAULT;
}
