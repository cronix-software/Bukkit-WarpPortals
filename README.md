WarpPortals - A Bukkit plugin
========

A Bukkit Plugin that allows users to create any shape/sized portals for use in the Minecraft game

Here is **WarpPortals**! This plugin was created due to an inability to find a **robust**, **functional**, and **endlessly customizable** portals plugin.

## Features

  * Portals of _any_ shape, size, or block type!
  * **Now has an API!**
    * Documentation can be found at [WarpPortals/API](http://dev.bukkit.org/bukkit-plugins/warp-portals/pages/api/)
    * An example implementation can be found at [example/WarpPortalsEventListener.java](https://github.com/McCraftaholics/Bukkit-WarpPortals/blob/master/src/com/mccraftaholics/warpportals/api/example/WarpPortalsEventListener.java).
  * Create portals with any block type!
  * Portals without frames or with frames of any material 
  * Portals that work between worlds 
  * Easy to create Portals 
  * Easy to set destinations
  * Precise teleportations 
  * Global Enable or Disable normal portals functionality 

## Commands

Check out the [Table of Commands](http://dev.bukkit.org/bukkit-plugins/warp-portals/pages/commands/)

## FAQ

**How do you create a Portal?**

  1. First choose the Portal destination and set it using "/wpdc [name]". *This command will set the destination to your current location, world AND angle of view.*
  2. Second, build a portal of any shape out of Gold Blocks. These will make up the Portal's body. 
  3. Third, hold a non-block item and run the command "/wppc [portalName] [destName] [portalBlockMaterial]". This will make it so the next Gold Block you click will turn them into a Portal made of the specified portalBlockMaterial, eg WATER.
  4. Fourth, right-click any one of the Gold Blocks you built. The plugin will find all adjacent Gold Blocks and turn them all into a Portal!
  5. Fifth, profit.

**Who can use a WarpPortal?**

Any one can use a WarpPortal by default. You can revoke someone's WarpPortal rights by setting their permission "warpportal.enter" to false. _New: The WarpPortals' Event API allows for this to be overridden!_

**How do you use WarpPortals?**

WarpPortals are always active and simply walking though one will teleport you to its destination.

**Who can create/admin a WarpPortal?**

Only players who are ops or have the "warpportal.admin" permission.

**What settings are there?**

  1. You can change whether normal Nether/End portals function. For example, setting this to false would mean that when a player walked through a Nether portal that they activated with flint, nothing would happen. 
  2. You can change the general Text Color used by the plugin. By default Yellow. 
  3. You can adjust the max portal size. This limit exists so that a Portal so large that it would eternally crash the server can't be created. By default 1000. 
  4. You can change the teleportation message. By default it is "Wooooooooosh!" 
  5. You can change the teleportation message Text Color. By default Purple. 

**Is there Economy support?**

No. Portals are always active and currently don't support any form of Economy plugins. _New: The WarpPortals' Event API provides the means for an external plugin to be written to handle this._

**What bugs are there?**

<s>Ghost Portals: _Currently, anyone can break the Portal Blocks that make up the Portal and that will cause the PORTAL_ENTERED event to never fire. This means that to the plugin the Portal still exists but realistically it isn't there anymore. To keep this from happening something like WorldGuard should be used to protect the Portals (though this protection may be a built-in feature in the future). To deal with the possibility of all the Portal blocks getting broken in a Portal, you can use the "/pdelete [portalName]" command to return the Portal to its original Gold Block form. If only a few blocks get broken, simply replacing them with new Portal Blocks should work._</s> Fixed in version 4.1.3

<s>Bad Text Colors: _Also, the Text Color settings don't appear to be cross OS Compatible. By default they are setup for Windows but if they don't work for you (if you get white text from the plugin) all Text Color Codes are editable in the Plugin's settings file._</s> Fixed in version 2.0.1

<s>Single Width Portals: _Single width portals can only face East or West. This is a very weird bug but it is an unfortunately un-fixable problem. Portal block orientation is decided off of the adjacent portal blocks. Therefore, if  two portal blocks are side-by-side, they will connect and face the right way. (Side Note: Even this was a hack and required overriding portal block physics). Sadly, a single portal block doesn't have anything to base it's orientation upon and will then default to East/West. This forum thread details  the problem <https://forums.bukkit.org/threads/rotating-portal-
blocks.12598/forums.bukkit.org/threads/rotating-portal-blocks.12598/>_</s> Only applies to WarpPortals made of Nether Blocks as of version 4.1.3

## Install

  1. Download the latest version right here! 
  2. Drop it into the Plugins folder in your CraftBukkit install. 
  3. Go create some spiffy, funky, wild, seriously fun Portals! 

## Source Code and Downloads

The latest Bukkit approved version to can be found attached to this page!
Though you can always find the absolute latest version at [Latest
Version](https://github.com/McCraftaholics/Bukkit-
WarpPortals/raw/master/WarpPortals_latest.jar)

Source code can be found at [github.com/McCraftaholics/Bukkit-
WarpPortals](https://github.com/McCraftaholics/Bukkit-WarpPortals)

  * Please feel free to make changes! 
  * Pull requests are awesome. Issues can be posted at [github.com/McCraftaholics/Bukkit-WarpPortals/issues](https://github.com/McCraftaholics/Bukkit-WarpPortals/issues)

## API

WarpPortals now has an Event API. Bukkit explains Events at [Event API Reference](http://wiki.bukkit.org/Event_API_Reference). WarpPortals triggers custom events allowing other  plugins to tie into these events. The events provide player & portal information and allow other plugins to cancel the portal teleportation.

Further documentation can be found at [WarpPortals/API](http://dev.bukkit.org/bukkit-plugins/warp-portals/pages/api/)

_Open an Issue on the GitHub page if you have any questions!_

## Changelog

**4.2.1**

  * Teleportation messages can now be disabled.
    * Set portals>teleport>message to "&none", without the quotes, in the config.yml file
  * WarpPortals now auto backups the data file after every portal creation
    * This should allow for easy recovery in the event of data loss
    * As of now, there is no limit on the number of backup files that can be created. Manual cleanup may be necessary.
  * New API event added: WarpPortalsCreateEvent
    * Triggered when a user creates a WarpPortal
    * Allows access to the new portals name, destination, blocks
    * Allows these elements to be modified by other plugins

**4.1.4 Critical Fix**

  * WarpPortals now handles permissions properly
    * Non-ops can use WarpPortals
    * Follows Bukkit Permissions structure
    * Thank you [WeaselSqueezer](https://github.com/WeaselSqueezer)

**4.1.3**

  * New portal interaction engine on the backend
    * **WarpPortals can be made of any block type!**
    * WarpPortals activate faster for players in survival mode
    * **Protect WarpPortals from getting destroyed**
    * Keeps liquids from flowing inside portals so that portals can be made of water/lave
  * Renamed all WarpPortal commands in a more logical manner
    * Separated commands into wp-portal-* and wp-destination-*
    * Abbreviations added for ease-of-typing, checkout Aliases

**3.0.0** _(2.1.1 merged in)_

  * **Added soft-depends to _hopefully_ fix all the lost portals errors!**
  * **Added WarpPortals Event API**
    * Plugins can be written that tie into WarpPortals 
    * Example: An economy plugin can be created that charges people for portal use 
    * **Allows 3rd parties to add missing features**

**2.01**

  * ENDER PORTALS!!!! 
    * Horizontal portals can now be made out of Ender Portal! 
    * Instead of using Gold Block to create the portal, use Quartz Block. 
    * Unlike Normal Portals which are vertical, Ender Portals are horizontal beasts. 
  * BugFix: Chat Colors are now used for portal creation. 
  * BugFix: Portal Creation Tools stay active for retries when a portal fails to create due to MaxRecursionLimit or similar. 
  * Minor: Portal Delete Tools now alert when they are equipped. 

**1.93**

  * Chat Colors are now handled properly, the Bukkit way. 
    * Setting a Chat Color doesn't require any special characters in the config file anymore! Just the color code. (eg. '3' instead of '&3') 
    * In addition to convenience, this should fix all of the config problems! @R4m8o, @DarkenInsanity, @SpookyPirate 
  * Portals now also save their state to disk on creation. This should fix any lost portal errors. @getnjr, @iDazzah 

**1.81**

  * Error reporting for Config File Loading and Portal Saving has been improved. Instead of receiving an obscure message, the actual error is now reported. 

**1.70**

  * Compiled and coded against Java SE 6. Supports Java 6 finally! Use it on both Java 6 and 7 :)

**1.64**

  * Added MCStats support ([http:_mcstats.org_](http://mcstats.org)) for my own personal enjoyment and motivation! Don't worry, you can opt-out of course. Everything about it is explained at this forum thread [MCStats / Plugin Metrics (R7) - Easy & advanced Plugin Statistics](https://forums.bukkit.org/threads/mcstats-plugin-metrics-r7-easy-advanced-plugin-statistics.77352/). 

**1.63**

  * WarpPortals requests highest priority on Portal Enter events. This should fix normal portals acting weird (@SpookyPirate) and Warp Portals taking a while to teleport (@mrchasez) 

**1.61**

  * The functionality (as in: the ability to teleport like normal) of traditional Nether/End portals is now toggleable via settings. @sgtcaze 

**1.50**

  * Non-WarpPortals now function like normal! @SpookyPirate 

**1.43**

  * Hopefully fixed any File Reading / Scanner errors. Thanks Zylithi! 
  * Fixed command conflict with WorldGuard concerning teleporting to Portals/Destinations. 

**1.31**

  * First offical release! 

[![Paypal Donate](https://www.paypalobjects.com/en_US/i/btn/btn_donate_LG.gif)](https://www.paypal.com/cgi-bin/webscr?cmd=_donations&business=VHQHKQSCTFJGN&lc=US&item_name=WarpPortals&item_number=warpportals&currency_code=USD&bn=PP%2dDonationsBF%3abtn_donate_LG%2egif%3aNonHosted)

Enjoy :)