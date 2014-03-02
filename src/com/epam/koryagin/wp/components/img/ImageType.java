package com.epam.koryagin.wp.components.img;

import com.epam.koryagin.wp.components.TextComponentType;
/**
 * Type of homogeneous component
 * in composite pattern model
 * 
 * @author Alexandr Koryagin
 *
 */
public enum ImageType implements TextComponentType{
	JPEG, GIF, BMP, PNG, UNDEFINED;
}