import React from 'react';
import { View, Text, StyleSheet, ScrollView, SafeAreaView, FlatList, TouchableOpacity, ImageBackground } from 'react-native';
import MaterialIcons from 'react-native-vector-icons/MaterialIcons';
import {diningHallsData} from '../assets/data/diningHallsData.js';
import {csData} from '../assets/data/diningHallsData.js';
import {restData} from '../assets/data/diningHallsData.js';
import { NavigationContainer } from '@react-navigation/native';
import { createNativeStackNavigator } from '@react-navigation/native-stack';
import Test from './Test';
import Test2 from './Test2';
import KinsDining from './KinsDining';
import KinsLunch from './KinsLunch';

const Stack = createNativeStackNavigator();

function Menus({navigation}) {

    function renderFlatListItem({item}) {
        return (
            <TouchableOpacity onPress={() => navigation.navigate(item.onpress)}>
                <ImageBackground 
                source={item.image}
                style={[styles.flatListItem, {marginLeft: item.id === 'flatListItem1' ? 25 : 0}]}
                imageStyle={styles.flatListItemImage}>
                    <Text style={styles.flatListItemText}> {item.title} </Text>
                </ImageBackground>
            </TouchableOpacity>
        );
    }

    function MenuScreen() {

        const MenuList = (props) => {
            return (
                <View> 
                    <View style={{width: '90%'}}>
                        <Text style={styles.sectionHeader}>{props.sectionHeader}</Text>
                        <Text style={styles.sectionSubHeader}>{props.sectionSubHeader} </Text>
                    </View>
                    <View style={styles.sectionListWrapper}>
                        <FlatList 
                        data={props.data}
                        renderItem={renderFlatListItem}
                        keyExtractor={(item) => item.id}
                        horizontal
                        showsHorizontalScrollIndicator={false}
                            />
                    </View>
                </View>
            );
        }

        return (
            <View style={styles.container}>
            
                {/* Header */}
                <SafeAreaView>
                    <View style={styles.headerWrapper}>
                        <Text style={styles.pageTitle}> Menus </Text>
                        <MaterialIcons style={styles.filterIcon}name="tune" size={30}/>
                    </View>
                </SafeAreaView>

                {/* Content */}
                <View style={styles.content}>
                    <ScrollView style={{borderTopRightRadius: 35, borderTopLeftRadius: 35}}>

                        <View style={{marginBottom: 125}}> 
                            {/* Dining Halls */}
                        <MenuList 
                        sectionHeader='Dining Halls' 
                        sectionSubHeader='View our University’s curated and nutritious dining hall menus below.'
                        data={diningHallsData}/>

                        <MenuList 
                        sectionHeader='Coffee Shops' 
                        sectionSubHeader='Want to get caffeinated? View our University’s cafes below.'
                        data={csData}/>

                        <MenuList 
                        sectionHeader='Restaurants' 
                        sectionSubHeader='Grab a bite to eat at our University’s on campus restaurant options.'
                        data={restData}/>
                        </View>
                    </ScrollView>
                </View>
            </View>
        );
    }
      
    return (
            
      <Stack.Navigator>
        <Stack.Screen name='MenuScreen' component={MenuScreen} options={{headerShown: false}}/>
        <Stack.Screen name='Test' component={Test} options={{headerShown: false}}/>
        <Stack.Screen name='Test2' component={Test2} options={{headerShown: false}}/>
        <Stack.Screen name='KinsLunch' component={KinsLunch} options={{headerShown: false}}/>
        <Stack.Screen name='KinsDining' component={KinsDining} options={{headerShown: false}}/>
      </Stack.Navigator>
    );
}

const styles = StyleSheet.create({
    container: {
      flex: 1,
      backgroundColor: '#EFD6C1',
    },

    headerWrapper: {
        marginHorizontal: 20,
        marginTop: 13,
        marginBottom: 27,
        flexDirection: 'row',
        alignItems: 'center',
        justifyContent: 'center',
    },

    pageTitle: {
        fontSize: 18,
        fontWeight: '700',
    },

    filterIcon: {
        position: 'absolute',
        right: 5,
    },

    content: {
        flex: 1,
        flexDirection: 'column',
        backgroundColor: 'white',
        borderTopRightRadius: 35,
        borderTopLeftRadius: 35,
    },

    sectionListWrapper: {
        marginTop: 15,
    },

    sectionHeader: {
        fontSize: 25, 
        fontWeight: 'bold',
        textAlign: 'left',
        paddingTop: 35,
        color: '#4D351F',
        paddingLeft: 35,
    },

    sectionSubHeader: {
        fontSize: 14, 
        fontWeight: 'bold',
        textAlign: 'left',
        color: '#9B8F85',
        paddingTop: 8,
        paddingLeft: 35,
    },

    flatListItem: {
        height: 150,
        width: 140,
        marginRight: 20,
        borderRadius: 30,
        justifyContent: 'center',
        alignItems: 'center',
    },

    flatListItemImage: {
        borderRadius: 30,
    },

    flatListItemText: {
        color: 'white',
        fontSize: 23,
        fontWeight: 'bold',
        textAlign: 'center',
        width: 125,
    },
});

export default Menus;
