import React from 'react';
import { View, Text, Image, StyleSheet } from 'react-native';

function Stats(props) {
    return (
        <View style={styles.container}>
           <Text>Stats</Text>
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

export default Stats;