import React from 'react';
import { View, Text, Image, StyleSheet, SafeAreaView, TouchableOpacity, FlatList, ImageBackground, ScrollView} from 'react-native';
import MaterialIcons from 'react-native-vector-icons/MaterialIcons';
import diningHallMenuSelectionData from '../assets/data/diningHallMenuSelectionData.js';

function KinsDining({navigation}) {

    function renderFlatListItem({item}) {
        return (
            <TouchableOpacity style={{alignItems: 'center'}} onPress={() => navigation.navigate(item.onpress)}>
                <ImageBackground 
                source={item.image}
                style={styles.flatListItem}
                imageStyle={styles.flatListItemImage}>
                    <Text style={styles.flatListItemText}> {item.title} </Text>
                </ImageBackground>
            </TouchableOpacity>
        );
    }

    return (
        <View style={styles.container}>
            
           {/* Header */}
           <SafeAreaView>
                <View style={styles.headerWrapper}>
                    <TouchableOpacity style={styles.backIcon} onPress={() => navigation.goBack()}> 
                        <MaterialIcons name="chevron-left" size={35}/>
                    </TouchableOpacity>
                    <Text style={styles.pageTitle}> Kins Dining </Text>
                    <MaterialIcons style={styles.filterIcon}name="tune" size={30}/>
                </View>
            </SafeAreaView>

            {/* Content */}
            <View style={styles.content}>
            <ScrollView>
                <View style={{paddingBottom: 105}}> 

                <View>
                    <Text style={styles.sectionHeader}>Hours of Operation</Text>
                    <View style={styles.hoursOfOperation}>
                        <View style={styles.rowElement}>
                            <Text style={styles.operationText}>Monday</Text>
                            <Text style={styles.operationText}>7 AM - 9 PM</Text>
                        </View>

                        <View style={styles.rowElement}>
                            <Text style={styles.operationText}>Tuesday</Text>
                            <Text style={styles.operationText}>7 AM - 9 PM</Text>
                        </View>

                        <View style={styles.rowElement}>
                            <Text style={styles.operationText}>Wednesday</Text>
                            <Text style={styles.operationText}>7 AM - 9 PM</Text>
                        </View>

                        <View style={styles.rowElement}>
                            <Text style={styles.operationText}>Thursday</Text>
                            <Text style={styles.operationText}>7 AM - 9 PM</Text>
                        </View>

                        <View style={styles.rowElement}>
                            <Text style={styles.operationText}>Friday</Text>
                            <Text style={styles.operationText}>7 AM - 8 PM</Text>
                        </View>

                        
                        <View style={styles.rowElement}>
                            <Text style={styles.operationText}>Saturday</Text>
                            <Text style={styles.operationText}>9 AM - 2 PM</Text>
                        </View>

                        <View style={styles.additionalHoursElement}>
                                <Text style={[{textAlign: 'right'}, styles.operationText]}>4:30 PM - 8 PM</Text>
                        </View>

                        <View style={styles.rowElement}>
                            <Text style={styles.operationText}>Sunday</Text>
                            <Text style={styles.operationText}>9 AM - 2 PM</Text>
                        </View>

                        <View style={styles.additionalHoursElement}>
                                <Text style={[{textAlign: 'right'}, styles.operationText]}>4:30 PM - 9 PM</Text>
                        </View>
                    </View>
                </View>

                <View>
                    <Text style={styles.sectionHeader}>Menus</Text>
                    <FlatList 
                        data={diningHallMenuSelectionData}
                        renderItem={renderFlatListItem}
                        keyExtractor={(item) => item.id}
                        vertical
                        showsHorizontalScrollIndicator={false}
                            />
                </View>
                </View>
                </ScrollView>
            </View>

            
        </View>
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

    backIcon: {
        position: 'absolute',
        left: 3,
    },

    content: {
        flex: 1,
        flexDirection: 'column',
        backgroundColor: 'white',
        borderTopRightRadius: 35,
        borderTopLeftRadius: 35,
    },

    sectionHeader: {
        fontSize: 25, 
        fontWeight: 'bold',
        textAlign: 'left',
        paddingTop: 35,
        color: '#4D351F',
        paddingBottom: 15,
        paddingLeft: 35
    },

    operationText: {
        fontSize: 17,
    },

    hoursOfOperation: {
        marginLeft: 20,
        marginRight: 20,
        paddingTop: 15,
        paddingBottom: 15,
        borderColor: 'black',
        borderRadius: 30,
        borderWidth: 1,
    },

    rowElement: {
        flexDirection: 'row',
        justifyContent: 'space-between',
        paddingTop: 4,
        paddingRight: 15, 
        paddingLeft: 15, 
    },

    additionalHoursElement: {
        paddingRight: 15,
    },

    flatListItem: {
        height: 150,
        width: 320,
        borderRadius: 30,
        justifyContent: 'center',
        alignItems: 'center',
        marginBottom: 20,

    },

    flatListItemImage: {
        borderRadius: 30,
    },

    flatListItemText: {
        color: 'white',
        fontSize: 32,
        fontWeight: 'bold',
        textAlign: 'center',
    },
});

export default KinsDining;