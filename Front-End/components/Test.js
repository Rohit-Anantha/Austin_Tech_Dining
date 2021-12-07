import React from 'react';
import { View, Text, StyleSheet, SafeAreaView, TouchableOpacity, FlatList, ImageBackground, ScrollView} from 'react-native';
import MaterialIcons from 'react-native-vector-icons/MaterialIcons';
import jesterLunchData from '../assets/data/jesterLunchData.js';

function KinsLunch({navigation}) {

    function renderFlatListItem({item}) {
        return (
            <TouchableOpacity style={{alignItems: 'center'}} onPress={() => navigation.navigate('Test')}>
                <View style={styles.foodItem}>
                    <Text style={styles.foodTitle}> {item.name} </Text>
                    <Text style={styles.foodCalories}> calories: {item.calories} </Text>
                    <MaterialIcons style={styles.filterIconOther} name="add" size={30}/>
                </View>
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
                    <Text style={styles.pageTitle}> Kins Dining Lunch </Text>
                    <MaterialIcons style={styles.filterIcon}name="tune" size={30}/>
                </View>
            </SafeAreaView>

            {/* Content */}
            <View style={styles.content}>
            <ScrollView style={{}}>
                <View style={{paddingBottom: 105}}> 

                <View>
                    <Text style={styles.sectionHeader}>Lunch</Text>

                    <TouchableOpacity style={{alignItems: 'center'}} onPress={() => navigation.navigate('Test')}>
                        <View style={styles.foodItem}>
                            <Text style={styles.foodTitle}> Sauteed Mushrooms </Text>
                            <Text style={styles.foodCalories}> calories: 79kcal </Text>
                            <MaterialIcons style={styles.filterIconOther} name="add" size={30}/>
                        </View>
                    </TouchableOpacity>

                    <TouchableOpacity style={{alignItems: 'center'}} onPress={() => navigation.navigate('Test')}>
                        <View style={styles.foodItemClicked}>
                            <View>
                                <Text style={styles.foodTitle}> Scrambled Eggs </Text>
                                <Text style={styles.foodCalories, {marginBottom: 20}}> calories: 180kcal </Text>
                                <MaterialIcons style={styles.filterIconOther} name="add" size={30}/>
                            </View>
                            
                            <Text style={styles.foodCaloriesOther}> Fats: 12g                       Carbohydrates: 0g </Text>
                            <Text style={styles.foodCaloriesOther}> Cholesterol: 420mg     Protein: 14.8g </Text>
                            <Text style={styles.foodCaloriesOther}> Sodium: 160mg </Text>
                        </View>
                    </TouchableOpacity>

                    <TouchableOpacity style={{alignItems: 'center'}} onPress={() => navigation.navigate('Test')}>
                        <View style={styles.foodItemPressed}>
                            <Text style={styles.foodTitle}> Linguine Pasta </Text>
                            <Text style={styles.foodCalories}> calories: 163kcal </Text>
                            <MaterialIcons style={styles.filterIconOther} name="remove" size={30}/>
                        </View>
                    </TouchableOpacity>

                    <TouchableOpacity style={{alignItems: 'center'}} onPress={() => navigation.navigate('Test')}>
                        <View style={styles.foodItemPressed}>
                            <Text style={styles.foodTitle}> Halal Grilled Chicken Breast </Text>
                            <Text style={styles.foodCalories}> calories: 137kcal </Text>
                            <MaterialIcons style={styles.filterIconOther} name="remove" size={30}/>
                        </View>
                    </TouchableOpacity>

                    

                    <TouchableOpacity style={{alignItems: 'center'}} onPress={() => navigation.navigate('Test')}>
                        <View style={styles.foodItem}>
                            <Text style={styles.foodTitle}> Spicy Black Bean Burger </Text>
                            <Text style={styles.foodCalories}> calories: 273kcal </Text>
                            <MaterialIcons style={styles.filterIconOther} name="add" size={30}/>
                        </View>
                    </TouchableOpacity>

                    <TouchableOpacity style={{alignItems: 'center'}} onPress={() => navigation.navigate('Test')}>
                        <View style={styles.foodItemPressed}>
                            <Text style={styles.foodTitle}> Sweet Potato Tater Tots </Text>
                            <Text style={styles.foodCalories}> calories: 202kcal </Text>
                            <MaterialIcons style={styles.filterIconOther} name="remove" size={30}/>
                        </View>
                    </TouchableOpacity>

                    <FlatList 
                        data={jesterLunchData}
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

    filterIconOther: {
        position: 'absolute',
        right: 19,
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
        paddingBottom: 20,
        paddingLeft: 55,
    },

    foodItem: {
        marginBottom: 17,
        paddingLeft: 15,
        backgroundColor: '#E7DFDF',
        width: '82.5%',
        height: 67,
        borderRadius: 35,
        justifyContent: 'center',
    },

    foodItemClicked: {
        marginBottom: 17,
        paddingLeft: 15,
        backgroundColor: '#E7DFDF',
        width: '82.5%',
        height: 150,
        borderRadius: 35,
        justifyContent: 'center',
    },

    foodItemPressed: {
        marginBottom: 17,
        paddingLeft: 15,
        backgroundColor: '#decfc1',
        width: '82.5%',
        height: 67,
        borderRadius: 35,
        justifyContent: 'center',
    },

    foodTitle: {
        fontSize: 18,
        marginBottom: 2,
    },

    foodCalories: {
        color: '#584E4E',
        marginLeft: 2,
    },

    foodCaloriesOther: {
        color: '#584E4E',
        marginLeft: 2,
        marginBottom: 3,
    }

});

export default KinsLunch;