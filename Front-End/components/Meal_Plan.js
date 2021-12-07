import React from 'react';
import { View, Text, Image, StyleSheet, SafeAreaView, TouchableOpacity, ScrollView } from 'react-native';
import MaterialIcons from 'react-native-vector-icons/MaterialIcons';
import jesterLunchData from '../assets/data/jesterLunchData.js';


function Meal_Plan(props) {
    return (
        <View style={styles.container}>
            
        {/* Header */}
        <SafeAreaView>
             <View style={styles.headerWrapper}>
                 <Text style={styles.pageTitle}> Meal Plan </Text>
                 <MaterialIcons style={styles.filterIcon}name="tune" size={30}/>
             </View>
         </SafeAreaView>

         {/* Content */}
         <View style={styles.content}>
         <ScrollView style={{}}>
             <View style={{paddingBottom: 105}}> 

             <View>

                 <Text style={styles.sectionSUB}>Items in your meal:</Text>

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
                     <View style={styles.foodItemPressed}>
                         <Text style={styles.foodTitle}> Sweet Potato Tater Tots </Text>
                         <Text style={styles.foodCalories}> calories: 202kcal </Text>
                         <MaterialIcons style={styles.filterIconOther} name="remove" size={30}/>
                     </View>
                 </TouchableOpacity>

                 <TouchableOpacity style={{alignItems: 'center'}} onPress={() => navigation.navigate('Test')}>
                        <View style={styles.foodItemPressed}>
                            <Text style={styles.foodTitle}> Cheese Pizza </Text>
                            <Text style={styles.foodCalories}> calories: 200kcal </Text>
                            <MaterialIcons style={styles.filterIconOther} name="remove" size={30}/>
                        </View>
                    </TouchableOpacity>

                 <Text style={styles.sectionSUB2}>Total Nutritional Info: </Text>

                    <View style={{alignItems: 'center'}}>
                 <View style={styles.foodItemClicked}>
                         <Text style={styles.foodCalories2}> Calories: 702 kcal </Text>
                         <Text style={styles.foodCaloriesOther2}> Fats: 19g                       Carbohydrates: 86g </Text>
                            <Text style={styles.foodCaloriesOther2}> Cholesterol: 1180mg    Protein: 38.5g </Text>
                            <Text style={styles.foodCaloriesOther2}> Sodium: 160mg </Text>
                 </View>
                 </View>
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
        paddingLeft: 45,
    },

    sectionSUB: {
        fontSize: 25,
        textAlign: 'left',
        color: '#4D351F',
        paddingLeft: 50,
        paddingBottom: 25,
        paddingTop: 35,
    },

    sectionSUB2: {
        fontSize: 25,
        textAlign: 'left',
        color: '#4D351F',
        paddingLeft: 50,
        paddingBottom: 25,
        paddingTop: 20,
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
        width: '88.5%',
        height: 140,
        borderRadius: 35,
        justifyContent: 'center',
    },

    foodItemPressed: {
        marginBottom: 17,
        paddingLeft: 15,
        backgroundColor: '#dbbea2',
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
    },

    foodCalories2: {
        color: 'black',
        marginLeft: 2,
        fontSize: 18,
        marginBottom: 10,
    },

    foodCaloriesOther2: {
        color: "#2e2c2b",
        marginLeft: 2,
        marginBottom: 5,
        fontSize: 15,
    },

});

export default Meal_Plan;