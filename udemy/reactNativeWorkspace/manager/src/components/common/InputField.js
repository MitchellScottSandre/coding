import React from 'react';
import { TextInput, View, Text } from 'react-native';

const InputField = ({ label, value, onChangeText, placeholder, secureTextEntry }) => { //destructures the values/function passes by props
    const { inputStyle, labelStyle, containerStyle } = styles;
    return (
        <View style={containerStyle} >
            <Text style={labelStyle} >{label}</Text>
            <TextInput 
                secureTextEntry={secureTextEntry}
                placeholder={placeholder}
                autoCorrect={false}
                style = {inputStyle}
                value={value}
                onChangeText={onChangeText}
            />
        </View>
    );
};

const styles = {
    inputStyle: {
        color: '#000',
        paddingRight: 5,
        paddingLeft: 5,
        fontSize: 18,
        lineHeight: 23, // how much space is between each line of text
        flex: 3 
    },
    labelStyle: {
        fontSize: 18,
        paddingLeft: 20,
        flex: 1 
    },
    containerStyle: {
        height: 40,
        flex: 1,
        flexDirection: 'row',
        alignItems: 'center'
    }
};

export { InputField };

// siblings: use flex to allocate space for each.
// so add up the values of flex (2 + 1) = 3. so 2/3 of the space will be for input style, 1/3 will be f or labelStyle