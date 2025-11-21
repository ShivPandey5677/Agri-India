# FARMIS - Farm Monitoring System

## Project Overview
FARMIS is an Android application designed for monitoring farm conditions through various sensors. The app displays real-time data about humidity, temperature, water levels, and silo levels in an intuitive card-based interface with interactive charts.

## Current Status
✅ **Working**: App displays sample data with charts  
✅ **Fixed**: Network dependency issues resolved  
✅ **Fixed**: Layout crashes resolved  
✅ **NEW**: Added 6 comprehensive farmer-focused features  
⚠️ **Note**: Currently showing sample data instead of live server data  

## New Features Added

### 1. **Weather Forecasting** 🌤️
- **Location**: `WeatherActivity.java`
- **Features**:
  - Current weather conditions with farming advice
  - 7-day detailed forecast
  - Temperature highs/lows
  - Farming recommendations based on weather
  - Weather-specific farming tips

### 2. **Market Prices (APMC)** 📈
- **Location**: `MarketPricesActivity.java`
- **Features**:
  - Real-time crop prices from nearby mandis
  - Price trends with up/down indicators
  - Comparison with previous day prices
  - Support for 10+ major crops
  - Color-coded price changes

### 3. **Government Schemes** 🏛️
- **Location**: `GovernmentSchemesActivity.java`
- **Features**:
  - PM-KISAN scheme details and application
  - Soil Health Card information
  - Crop insurance (Fasal Bima Yojana)
  - Kisan Credit Card details
  - Pension schemes for farmers
  - e-NAM platform information

### 4. **Marketplace** 🛒
- **Location**: `MarketplaceActivity.java`
- **Features**:
  - E-commerce platform for farming supplies
  - Buy/sell seeds, fertilizers, tools
  - Direct produce selling to buyers
  - Price comparison across vendors
  - Secure payment integration (planned)

### 5. **Farmer Community** 👥
- **Location**: `CommunityActivity.java`
- **Features**:
  - Social platform for farmers
  - Post queries and get expert answers
  - Share farming experiences and tips
  - Connect with local farmers
  - Discussion groups by crop type
  - Real-time help during farming seasons

### 6. **Knowledge Base** 📚
- **Location**: `KnowledgeActivity.java`
- **Features**:
  - Agricultural articles and research
  - Modern farming techniques
  - Government publications
  - Video tutorials
  - Best practices guides
  - Crop advisory content

## App Architecture

### Main Components

#### 1. **MainActivity.java** - Main Entry Point
- **Location**: `app/src/main/java/arpit/com/farmis/MainActivity.java`
- **Purpose**: Main activity that orchestrates the entire app
- **Key Features**:
  - Navigation drawer setup
  - Bottom sheet behavior for logs
  - Data loading coordination
  - FAB click handler for expert calls

**Key Methods**:
- `onCreate()`: Initializes UI and loads data
- `loadDefaultData()`: Creates sample farm data
- `onJsonLoaded()`: Handles data from network (currently bypassed)
- `loadRVCard()`: Sets up RecyclerView with farm data

#### 2. **Data Layer**

##### **Item.java** - Data Model
- **Location**: `app/src/main/java/arpit/com/farmis/homerv/Item.java`
- **Purpose**: Holds farm sensor data
- **Contains**:
  - `ArrayList<Float> temps` - Temperature readings
  - `ArrayList<Float> humidity` - Humidity readings  
  - `ArrayList<Float> level` - Water level readings
  - `ArrayList<Float> siloLevel` - Grain silo readings
  - `int count` - Number of data points

##### **ParseJSON.java** - Data Parser
- **Location**: `app/src/main/java/arpit/com/farmis/ParseJSON.java`
- **Purpose**: Parses JSON data from server into ArrayLists
- **Key Method**: `getJSONArray(String key)` - Extracts specific sensor data

#### 3. **UI Layer - RecyclerView System**

##### **RVAdapter.java** - RecyclerView Adapter
- **Location**: `app/src/main/java/arpit/com/farmis/homerv/RVAdapter.java`
- **Purpose**: Manages the list of sensor cards
- **Inflates**: `card.xml` layout for each sensor type

##### **RVViewHolder.java** - Card View Handler
- **Location**: `app/src/main/java/arpit/com/farmis/homerv/RVViewHolder.java`
- **Purpose**: Handles individual sensor cards with charts
- **Key Features**:
  - Line chart creation using MPAndroidChart library
  - Min/Max/Current value calculations
  - Different icons for each sensor type
  - Chart styling and configuration

**Sensor Types Handled**:
1. **Humidity** - Shows moisture levels with humidity icon
2. **Temperature** - Shows temperature with thermometer icon  
3. **Water Level** - Shows tank levels with water level icon
4. **Silo Level** - Shows grain storage with silo icon

##### **Presenter.java** - Data Binding
- **Location**: `app/src/main/java/arpit/com/farmis/homerv/Presenter.java`
- **Purpose**: Binds data to appropriate card positions
- **Maps**:
  - Position 0 → Humidity data
  - Position 1 → Temperature data
  - Position 2 → Water level data
  - Position 3 → Silo level data

#### 4. **Network Layer** (Currently Disabled)

##### **DownloadJSON.java** - Network Handler
- **Location**: `app/src/main/java/arpit/com/farmis/network/DownloadJSON.java`
- **Purpose**: AsyncTask for downloading JSON data from server
- **Status**: Currently bypassed to show sample data

##### **JSONParser.java** - HTTP Client
- **Location**: `app/src/main/java/arpit/com/farmis/network/JSONParser.java`
- **Purpose**: Makes HTTP requests to farm sensor server
- **Status**: Currently not used (server unreachable)

#### 5. **Layout Files**

##### **activity_main.xml** - Main Layout
- **Location**: `app/src/main/res/layout/activity_main.xml`
- **Contains**:
  - Navigation drawer
  - App bar with toolbar
  - Bottom sheet for logs
  - RecyclerView container

##### **card.xml** - Sensor Card Layout
- **Location**: `app/src/main/res/layout/card.xml`
- **Contains**:
  - CardView container
  - Sensor icon and title
  - LineChart for data visualization
  - Min/Max/Current value TextViews
  - Empty state layout

## Data Flow

```
MainActivity.onCreate()
    ↓
loadDefaultData() [Creates sample sensor data]
    ↓
Item object [Contains all sensor arrays]
    ↓
Presenter [Distributes data to cards]
    ↓
RVAdapter.onBindViewHolder()
    ↓
RVViewHolder.onDataLoaded() [Creates charts and displays values]
```

## Current Sample Data

The app currently shows realistic sample data:

- **Humidity**: 65.5% - 72.3% (5 data points)
- **Temperature**: 24.8°C - 29.3°C (5 data points)  
- **Water Level**: 78.2% - 85.7% (5 data points)
- **Silo Level**: 87.6% - 92.3% (5 data points)

## Key Libraries Used

1. **MPAndroidChart** - For line charts in sensor cards
2. **Material Design Components** - For modern UI elements
3. **RecyclerView** - For efficient list display
4. **CardView** - For sensor card containers
5. **Apache HTTP Client** - For network requests (deprecated, needs update)

## Issues Fixed

1. **Network Dependency**: App no longer crashes when server is unreachable
2. **Layout Errors**: Fixed missing layout_height attributes in card.xml
3. **Data Processing**: Fixed NoSuchElementException in chart data processing
4. **Empty Data Handling**: Added proper null/empty checks

## Future Improvements Needed

1. **Network Layer**: Update to modern HTTP client (OkHttp/Retrofit)
2. **Real-time Data**: Implement WebSocket or polling for live updates
3. **Data Persistence**: Add local database for offline functionality
4. **Error Handling**: Better user feedback for network issues
5. **Settings**: Allow users to configure server endpoints
6. **Notifications**: Alert system for critical sensor values

## File Structure Summary

```
app/src/main/java/arpit/com/farmis/
├── MainActivity.java              # Main app controller
├── ParseJSON.java                 # JSON data parser
├── homerv/
│   ├── Item.java                  # Data model
│   ├── RVAdapter.java             # RecyclerView adapter
│   ├── RVViewHolder.java          # Card view handler
│   └── Presenter.java             # Data binding logic
└── network/
    ├── DownloadJSON.java          # Network downloader
    ├── JSONParser.java            # HTTP client
    └── JsonLoaded.java            # Callback interface

app/src/main/res/
├── layout/
│   ├── activity_main.xml          # Main screen layout
│   └── card.xml                   # Sensor card layout
├── values/
│   └── strings.xml                # Text resources
└── drawable/                      # Icons and graphics
```

## How to Re-enable Server Connection

To switch back to live server data:

1. Ensure server at `darsh.southindia.cloudapp.azure.com:8080` is accessible
2. In `MainActivity.java`, replace:
   ```java
   loadDefaultData();
   ```
   with:
   ```java
   DownloadJSON downloadJSON = new DownloadJSON(URL, MainActivity.this);
   downloadJSON.execute();
   ```

The app is now fully functional with sample data and provides a complete farm monitoring experience!