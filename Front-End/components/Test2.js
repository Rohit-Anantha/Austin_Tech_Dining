import React from 'react';
import { View, Text, Image, StyleSheet } from 'react-native';

function Test2(props) {
    return (
        <View style={styles.container}>
           <Text>Test2 asd</Text>
        </View>
    );
}

const styles = StyleSheet.create({
    container: {
      flex: 1,
      backgroundColor: '#EBCFB2',
      alignItems: 'center',
      justifyContent: 'center',
    },
});

export default Test2;