//Maya ASCII 2022 scene
//Name: rig_player.ma
//Last modified: Tue, Nov 09, 2021 11:51:34 PM
//Codeset: 1252
requires maya "2022";
requires -nodeType "floatConstant" -nodeType "colorConstant" -nodeType "floatMath"
		 -nodeType "colorMath" "lookdevKit" "1.0";
requires -nodeType "displayPoints" "Type" "2.0a";
requires -nodeType "RedshiftOptions" -nodeType "RedshiftPostEffects" -nodeType "RedshiftBumpMap"
		 -nodeType "RedshiftBumpBlender" -nodeType "RedshiftRoundCorners" -nodeType "RedshiftMaterial"
		 "redshift4maya" "3.0.51";
requires "stereoCamera" "10.0";
requires -nodeType "aiOptions" -nodeType "aiAOVDriver" -nodeType "aiAOVFilter" "mtoa" "4.2.3";
currentUnit -l centimeter -a degree -t film;
fileInfo "exportedFrom" "D:/Dropbox/Misc Projects/Minecraft/dungeons/rig_dungeons_character_v2.ma";
fileInfo "application" "maya";
fileInfo "product" "Maya 2022";
fileInfo "version" "2022";
fileInfo "cutIdentifier" "202106180615-26a94e7f8c";
fileInfo "osv" "Windows 10 Home v2009 (Build: 19043)";
fileInfo "UUID" "C0DB0457-4FAA-5A01-7BF0-0AAD5FBBA53B";
createNode transform -s -n "persp";
	rename -uid "8ED9B9FD-4AB8-BA14-3EB4-A3B20AC5EF6B";
	setAttr ".v" no;
	setAttr ".t" -type "double3" 10.09988451033349 43.783396804438006 58.290522080978207 ;
	setAttr ".r" -type "double3" -18.338352814913744 5050.999999998432 6.0751577872642719e-16 ;
createNode camera -s -n "perspShape" -p "persp";
	rename -uid "41AE15EB-40A3-8E87-641E-8EAE5433612E";
	setAttr -k off ".v" no;
	setAttr ".fl" 34.999999999999993;
	setAttr ".coi" 66.290605064277813;
	setAttr ".imn" -type "string" "persp";
	setAttr ".den" -type "string" "persp_depth";
	setAttr ".man" -type "string" "persp_mask";
	setAttr ".tp" -type "double3" 2 25 5 ;
	setAttr ".hc" -type "string" "viewSet -p %camera";
	setAttr ".ai_translator" -type "string" "perspective";
createNode transform -s -n "top";
	rename -uid "79D78392-4366-3ABF-853F-E690FA73B839";
	setAttr ".v" no;
	setAttr ".t" -type "double3" 12.25 1000.1761593930192 2.1876393127962395e-13 ;
	setAttr ".r" -type "double3" -89.999999999999986 0 0 ;
createNode camera -s -n "topShape" -p "top";
	rename -uid "02CD5AB9-4A80-7C5E-3D5D-3D9052F08DCB";
	setAttr -k off ".v" no;
	setAttr ".rnd" no;
	setAttr ".coi" 985.22515939301923;
	setAttr ".ow" 78.94736842105263;
	setAttr ".imn" -type "string" "top";
	setAttr ".den" -type "string" "top_depth";
	setAttr ".man" -type "string" "top_mask";
	setAttr ".tp" -type "double3" 12.25 14.951 0 ;
	setAttr ".hc" -type "string" "viewSet -t %camera";
	setAttr ".o" yes;
	setAttr ".ai_translator" -type "string" "orthographic";
createNode transform -s -n "front";
	rename -uid "1A544F9F-4F51-8A6F-325B-B8947D4D7A38";
	setAttr ".v" no;
	setAttr ".t" -type "double3" -5.4478301236635831 29.20828296631732 1000.3738874248029 ;
createNode camera -s -n "frontShape" -p "front";
	rename -uid "D5F2EC45-403D-E996-1E0D-DEBAB16F7885";
	setAttr -k off ".v" no;
	setAttr ".rnd" no;
	setAttr ".coi" 1000.3738874248029;
	setAttr ".ow" 8.7363744548226094;
	setAttr ".imn" -type "string" "front";
	setAttr ".den" -type "string" "front_depth";
	setAttr ".man" -type "string" "front_mask";
	setAttr ".tp" -type "double3" 12.25 14.951 0 ;
	setAttr ".hc" -type "string" "viewSet -f %camera";
	setAttr ".o" yes;
	setAttr ".ai_translator" -type "string" "orthographic";
createNode transform -s -n "side";
	rename -uid "CF4C6996-4F0E-FD1C-2F0A-6D853D3DF358";
	setAttr ".v" no;
	setAttr ".t" -type "double3" 1000.2131343835659 14.951 2.1937188385469451e-13 ;
	setAttr ".r" -type "double3" 0 89.999999999999986 0 ;
createNode camera -s -n "sideShape" -p "side";
	rename -uid "849FB943-4FFB-7925-FAFC-2783ED562ED8";
	setAttr -k off ".v" no;
	setAttr ".rnd" no;
	setAttr ".coi" 987.96313438356594;
	setAttr ".ow" 72.781058953690533;
	setAttr ".imn" -type "string" "side";
	setAttr ".den" -type "string" "side_depth";
	setAttr ".man" -type "string" "side_mask";
	setAttr ".tp" -type "double3" 12.25 14.951 0 ;
	setAttr ".hc" -type "string" "viewSet -s %camera";
	setAttr ".o" yes;
	setAttr ".ai_translator" -type "string" "orthographic";
createNode transform -n "transform1";
	rename -uid "8ED98105-4EFD-732F-9214-B3B572AD23B9";
	setAttr ".hio" yes;
createNode displayPoints -n "displayPoints1" -p "transform1";
	rename -uid "BDBEED74-4D9F-2B7D-D87A-0AB920CF6FD8";
	setAttr -k off ".v";
	setAttr ".hio" yes;
createNode transform -n "player_RIG";
	rename -uid "A4F49C45-45A9-2116-C109-AA920721F804";
createNode transform -n "extraNodes_GRP" -p "player_RIG";
	rename -uid "16427FBB-4E94-1355-FB00-97A1305EFCE5";
	setAttr ".rp" -type "double3" 0 1.0078125 0 ;
	setAttr ".sp" -type "double3" 0 1.0078125 0 ;
createNode transform -n "grp_meshes" -p "extraNodes_GRP";
	rename -uid "A574FAF4-4EE3-EEF4-F23A-8D9B2C5691A0";
createNode transform -n "mesh_body" -p "grp_meshes";
	rename -uid "2DA477B5-42AA-2C70-0909-EF804EE2E4A7";
	setAttr -l on ".tx";
	setAttr -l on ".ty";
	setAttr -l on ".tz";
	setAttr -l on ".rx";
	setAttr -l on ".ry";
	setAttr -l on ".rz";
	setAttr -l on ".sx";
	setAttr -l on ".sy";
	setAttr -l on ".sz";
	setAttr ".rp" -type "double3" 0 18 0 ;
	setAttr ".sp" -type "double3" 0 18 0 ;
createNode mesh -n "mesh_bodyShape" -p "mesh_body";
	rename -uid "D5D70677-452E-B9E1-954C-6EA39E0BE33E";
	setAttr -k off ".v";
	setAttr -s 4 ".iog[0].og";
	setAttr ".vir" yes;
	setAttr ".vif" yes;
	setAttr ".pv" -type "double2" 0.5 0.5 ;
	setAttr ".uvst[0].uvsn" -type "string" "map1";
	setAttr ".cuvs" -type "string" "map1";
	setAttr ".dcc" -type "string" "Ambient+Diffuse";
	setAttr ".covm[0]"  0 1 1;
	setAttr ".cdvm[0]"  0 1 1;
	setAttr ".dfgi" 4907;
	setAttr ".vcs" 2;
	setAttr ".ai_translator" -type "string" "polymesh";
createNode mesh -n "mesh_bodyShapeOrig" -p "mesh_body";
	rename -uid "471F2D1F-4088-DD88-7CAE-D18ED3C27EF2";
	setAttr -k off ".v";
	setAttr ".io" yes;
	setAttr ".vir" yes;
	setAttr ".vif" yes;
	setAttr ".uvst[0].uvsn" -type "string" "map1";
	setAttr -s 24 ".uvst[0].uvsp[0:23]" -type "float2" 0.31225586 0.68725586
		 0.25024414 0.68725586 0.25024414 0.50024414 0.31225586 0.50024414 0.49975586 0.68725586
		 0.43774414 0.68725586 0.43774414 0.50024414 0.49975586 0.50024414 0.31274414 0.68774414
		 0.43725586 0.68774414 0.43725586 0.74975586 0.31274414 0.74975586 0.43774414 0.74975586
		 0.56225586 0.74975586 0.56225586 0.68774414 0.43774414 0.68774414 0.62475586 0.68725586
		 0.50024414 0.68725586 0.50024414 0.50024414 0.62475586 0.50024414 0.43725586 0.68725586
		 0.31274414 0.68725586 0.31274414 0.50024414 0.43725586 0.50024414;
	setAttr ".cuvs" -type "string" "map1";
	setAttr ".dcc" -type "string" "Ambient+Diffuse";
	setAttr ".covm[0]"  0 1 1;
	setAttr ".cdvm[0]"  0 1 1;
	setAttr -s 8 ".vt[0:7]"  0.25 1.5 0.125 0.25 1.5 -0.125 0.25 0.75 0.125
		 0.25 0.75 -0.125 -0.25 1.5 -0.125 -0.25 1.5 0.125 -0.25 0.75 -0.125 -0.25 0.75 0.125;
	setAttr -s 12 ".ed[0:11]"  0 2 0 1 0 0 2 3 0 3 1 0 4 6 0 5 4 0 6 7 0
		 7 5 0 1 4 0 5 0 0 2 7 0 6 3 0;
	setAttr -s 24 ".n[0:23]" -type "float3"  1 0 0 1 0 0 1 0 0 1 0 0 -1
		 0 0 -1 0 0 -1 0 0 -1 0 0 0 1 0 0 1 0 0 1 0 0 1 0 0 -1 0 0 -1 0 0 -1 0 0 -1 0 0 0
		 1 0 0 1 0 0 1 0 0 1 0 0 -1 0 0 -1 0 0 -1 0 0 -1;
	setAttr -s 6 -ch 24 ".fc[0:5]" -type "polyFaces" 
		f 4 1 0 2 3
		mu 0 4 0 1 2 3
		f 4 5 4 6 7
		mu 0 4 4 5 6 7
		f 4 8 -6 9 -2
		mu 0 4 8 9 10 11
		f 4 10 -7 11 -3
		mu 0 4 12 13 14 15
		f 4 -10 -8 -11 -1
		mu 0 4 16 17 18 19
		f 4 -9 -4 -12 -5
		mu 0 4 20 21 22 23;
	setAttr ".cd" -type "dataPolyComponent" Index_Data Edge 0 ;
	setAttr ".cvd" -type "dataPolyComponent" Index_Data Vertex 0 ;
	setAttr ".pd[0]" -type "dataPolyComponent" Index_Data UV 0 ;
	setAttr ".hfd" -type "dataPolyComponent" Index_Data Face 0 ;
	setAttr ".ai_translator" -type "string" "polymesh";
createNode transform -n "mesh_leg_r" -p "grp_meshes";
	rename -uid "F910B2A2-46B9-1779-0026-4494B4CDB508";
	setAttr -l on ".tx";
	setAttr -l on ".ty";
	setAttr -l on ".tz";
	setAttr -l on ".rx";
	setAttr -l on ".ry";
	setAttr -l on ".rz";
	setAttr -l on ".sx";
	setAttr -l on ".sy";
	setAttr -l on ".sz";
	setAttr ".rp" -type "double3" -1.95 6 -2.4492935982947094e-16 ;
	setAttr ".sp" -type "double3" -1.95 6 -2.4492935982947094e-16 ;
createNode mesh -n "mesh_leg_rShape" -p "mesh_leg_r";
	rename -uid "DCC10D06-497A-644F-0657-DCAF10B3705C";
	setAttr -k off ".v";
	setAttr -s 4 ".iog[0].og";
	setAttr ".vir" yes;
	setAttr ".vif" yes;
	setAttr ".uvst[0].uvsn" -type "string" "map1";
	setAttr ".cuvs" -type "string" "map1";
	setAttr ".dcc" -type "string" "Ambient+Diffuse";
	setAttr ".covm[0]"  0 1 1;
	setAttr ".cdvm[0]"  0 1 1;
	setAttr ".dfgi" 4903;
	setAttr ".vcs" 2;
	setAttr ".ai_translator" -type "string" "polymesh";
createNode mesh -n "mesh_leg_rShapeOrig" -p "mesh_leg_r";
	rename -uid "85E0DB04-44A7-3741-BF71-2B89D4461307";
	setAttr -k off ".v";
	setAttr ".io" yes;
	setAttr ".vir" yes;
	setAttr ".vif" yes;
	setAttr ".uvst[0].uvsn" -type "string" "map1";
	setAttr -s 24 ".uvst[0].uvsp[0:23]" -type "float2" 0.0625 0.6875 0 0.6875
		 0 0.5 0.0625 0.5 0.1875 0.6875 0.125 0.6875 0.125 0.5 0.1875 0.5 0.0625 0.6875 0.125
		 0.6875 0.125 0.75 0.0625 0.75 0.125 0.75 0.1875 0.75 0.1875 0.6875 0.125 0.6875 0.25
		 0.6875 0.1875 0.6875 0.1875 0.5 0.25 0.5 0.125 0.6875 0.0625 0.6875 0.0625 0.5 0.125
		 0.5;
	setAttr ".cuvs" -type "string" "map1";
	setAttr ".dcc" -type "string" "Ambient+Diffuse";
	setAttr ".covm[0]"  0 1 1;
	setAttr ".cdvm[0]"  0 1 1;
	setAttr -s 8 ".vt[0:7]"  -3.95000005 12 -2 -3.95000005 12 2 -3.95000005 -2.9605948e-16 -2
		 -3.95000005 -2.9605948e-16 2 0.050000053 12 2 0.050000053 12 -2 0.050000053 -2.9605948e-16 2
		 0.050000053 -2.9605948e-16 -2;
	setAttr -s 12 ".ed[0:11]"  0 2 0 1 0 0 2 3 0 3 1 0 4 6 0 5 4 0 6 7 0
		 7 5 0 1 4 0 5 0 0 2 7 0 6 3 0;
	setAttr -s 24 ".n[0:23]" -type "float3"  -0.0625 0 -7.6540428e-18 -0.0625
		 0 -7.6540428e-18 -0.0625 0 -7.6540428e-18 -0.0625 0 -7.6540428e-18 0.0625 0 7.6540428e-18
		 0.0625 0 7.6540428e-18 0.0625 0 7.6540428e-18 0.0625 0 7.6540428e-18 0 0.0625 0 0
		 0.0625 0 0 0.0625 0 0 0.0625 0 0 -0.0625 0 0 -0.0625 0 0 -0.0625 0 0 -0.0625 0 7.6540428e-18
		 0 -0.0625 7.6540428e-18 0 -0.0625 7.6540428e-18 0 -0.0625 7.6540428e-18 0 -0.0625
		 -7.6540428e-18 0 0.0625 -7.6540428e-18 0 0.0625 -7.6540428e-18 0 0.0625 -7.6540428e-18
		 0 0.0625;
	setAttr -s 6 -ch 24 ".fc[0:5]" -type "polyFaces" 
		f 4 1 0 2 3
		mu 0 4 0 1 2 3
		f 4 5 4 6 7
		mu 0 4 4 5 6 7
		f 4 8 -6 9 -2
		mu 0 4 8 9 10 11
		f 4 10 -7 11 -3
		mu 0 4 12 13 14 15
		f 4 -10 -8 -11 -1
		mu 0 4 16 17 18 19
		f 4 -9 -4 -12 -5
		mu 0 4 20 21 22 23;
	setAttr ".cd" -type "dataPolyComponent" Index_Data Edge 0 ;
	setAttr ".cvd" -type "dataPolyComponent" Index_Data Vertex 0 ;
	setAttr ".pd[0]" -type "dataPolyComponent" Index_Data UV 0 ;
	setAttr ".hfd" -type "dataPolyComponent" Index_Data Face 0 ;
	setAttr ".dfgi" 4903;
	setAttr ".ai_translator" -type "string" "polymesh";
createNode transform -n "mesh_leg_l" -p "grp_meshes";
	rename -uid "150D550D-4A46-2A97-782C-65B15A70C51B";
	setAttr -l on ".tx";
	setAttr -l on ".ty";
	setAttr -l on ".tz";
	setAttr -l on ".rx";
	setAttr -l on ".ry";
	setAttr -l on ".rz";
	setAttr -l on ".sx";
	setAttr -l on ".sy";
	setAttr -l on ".sz";
	setAttr ".rp" -type "double3" 1.95 6 2.4492935982947094e-16 ;
	setAttr ".sp" -type "double3" 1.95 6 2.4492935982947094e-16 ;
createNode mesh -n "mesh_leg_lShape" -p "mesh_leg_l";
	rename -uid "93FC2BE3-471D-B3E7-F462-30B5E7FD662F";
	setAttr -k off ".v";
	setAttr -s 4 ".iog[0].og";
	setAttr -av ".iog[0].og[5].gco";
	setAttr -av ".iog[0].og[6].gco";
	setAttr ".vir" yes;
	setAttr ".vif" yes;
	setAttr ".uvst[0].uvsn" -type "string" "map1";
	setAttr ".cuvs" -type "string" "map1";
	setAttr ".dcc" -type "string" "Ambient+Diffuse";
	setAttr ".covm[0]"  0 1 1;
	setAttr ".cdvm[0]"  0 1 1;
	setAttr ".dfgi" 4905;
	setAttr ".vcs" 2;
	setAttr ".ai_translator" -type "string" "polymesh";
createNode mesh -n "mesh_leg_lShapeOrig" -p "mesh_leg_l";
	rename -uid "7EA2EAAA-4966-E7FA-85E3-6280FEFA3D7E";
	setAttr -k off ".v";
	setAttr ".io" yes;
	setAttr ".vir" yes;
	setAttr ".vif" yes;
	setAttr ".uvst[0].uvsn" -type "string" "map1";
	setAttr -s 24 ".uvst[0].uvsp[0:23]" -type "float2" 0.3125 0.1875 0.25
		 0.1875 0.25 0 0.3125 0 0.4375 0.1875 0.375 0.1875 0.375 0 0.4375 0 0.3125 0.1875
		 0.375 0.1875 0.375 0.25 0.3125 0.25 0.375 0.25 0.4375 0.25 0.4375 0.1875 0.375 0.1875
		 0.5 0.1875 0.4375 0.1875 0.4375 0 0.5 0 0.375 0.1875 0.3125 0.1875 0.3125 0 0.375
		 0;
	setAttr ".cuvs" -type "string" "map1";
	setAttr ".dcc" -type "string" "Ambient+Diffuse";
	setAttr ".covm[0]"  0 1 1;
	setAttr ".cdvm[0]"  0 1 1;
	setAttr -s 8 ".vt[0:7]"  -0.050000053 12 -2 -0.050000053 12 2 -0.050000053 -2.9605948e-16 -2
		 -0.050000053 -2.9605948e-16 2 3.95000005 12 2 3.95000005 12 -2 3.95000005 -2.9605948e-16 2
		 3.95000005 -2.9605948e-16 -2;
	setAttr -s 12 ".ed[0:11]"  0 2 0 1 0 0 2 3 0 3 1 0 4 6 0 5 4 0 6 7 0
		 7 5 0 1 4 0 5 0 0 2 7 0 6 3 0;
	setAttr -s 24 ".n[0:23]" -type "float3"  -0.0625 0 -7.6540428e-18 -0.0625
		 0 -7.6540428e-18 -0.0625 0 -7.6540428e-18 -0.0625 0 -7.6540428e-18 0.0625 0 7.6540428e-18
		 0.0625 0 7.6540428e-18 0.0625 0 7.6540428e-18 0.0625 0 7.6540428e-18 0 0.0625 0 0
		 0.0625 0 0 0.0625 0 0 0.0625 0 0 -0.0625 0 0 -0.0625 0 0 -0.0625 0 0 -0.0625 0 7.6540428e-18
		 0 -0.0625 7.6540428e-18 0 -0.0625 7.6540428e-18 0 -0.0625 7.6540428e-18 0 -0.0625
		 -7.6540428e-18 0 0.0625 -7.6540428e-18 0 0.0625 -7.6540428e-18 0 0.0625 -7.6540428e-18
		 0 0.0625;
	setAttr -s 6 -ch 24 ".fc[0:5]" -type "polyFaces" 
		f 4 1 0 2 3
		mu 0 4 0 1 2 3
		f 4 5 4 6 7
		mu 0 4 4 5 6 7
		f 4 8 -6 9 -2
		mu 0 4 8 9 10 11
		f 4 10 -7 11 -3
		mu 0 4 12 13 14 15
		f 4 -10 -8 -11 -1
		mu 0 4 16 17 18 19
		f 4 -9 -4 -12 -5
		mu 0 4 20 21 22 23;
	setAttr ".cd" -type "dataPolyComponent" Index_Data Edge 0 ;
	setAttr ".cvd" -type "dataPolyComponent" Index_Data Vertex 0 ;
	setAttr ".pd[0]" -type "dataPolyComponent" Index_Data UV 0 ;
	setAttr ".hfd" -type "dataPolyComponent" Index_Data Face 0 ;
	setAttr ".dfgi" 4905;
	setAttr ".ai_translator" -type "string" "polymesh";
createNode transform -n "grp_mesh_armor" -p "grp_meshes";
	rename -uid "235D53BA-4727-D66C-8F56-959A8CC3C1E7";
createNode transform -n "grp_mesh_chestplate" -p "grp_mesh_armor";
	rename -uid "D31D2C02-40E5-CB45-3CF1-639016566FB3";
createNode transform -n "mesh_armor_chest_r" -p "grp_mesh_chestplate";
	rename -uid "50D4F099-49B6-D9E5-6ABB-A9A8FF680189";
	setAttr -l on ".tx";
	setAttr -l on ".ty";
	setAttr -l on ".tz";
	setAttr -l on ".rx";
	setAttr -l on ".ry";
	setAttr -l on ".rz";
	setAttr -l on ".sx";
	setAttr -l on ".sy";
	setAttr -l on ".sz";
	setAttr ".rp" -type "double3" -6.0500000000000611 22.000000000000966 0 ;
	setAttr ".sp" -type "double3" -6.0500000000000327 22.000000000000966 0 ;
createNode mesh -n "mesh_armor_chest_rShape" -p "mesh_armor_chest_r";
	rename -uid "4C7FE1C3-4AFB-22DD-AAC4-208E2693B3A9";
	setAttr -k off ".v";
	setAttr -s 4 ".iog[0].og";
	setAttr ".vir" yes;
	setAttr ".vif" yes;
	setAttr ".pv" -type "double2" 0.25 0.96875 ;
	setAttr ".uvst[0].uvsn" -type "string" "map1";
	setAttr ".cuvs" -type "string" "map1";
	setAttr ".dcc" -type "string" "Ambient+Diffuse";
	setAttr ".covm[0]"  0 1 1;
	setAttr ".cdvm[0]"  0 1 1;
	setAttr ".dr" 1;
	setAttr ".vcs" 2;
	setAttr ".ai_opaque" no;
	setAttr ".ai_translator" -type "string" "polymesh";
createNode mesh -n "mesh_armor_chest_rShapeOrig" -p "mesh_armor_chest_r";
	rename -uid "C49A6FE4-4180-35D0-57B8-48BCD9E61DF5";
	setAttr -k off ".v";
	setAttr ".io" yes;
	setAttr ".vir" yes;
	setAttr ".vif" yes;
	setAttr ".uvst[0].uvsn" -type "string" "map1";
	setAttr -s 24 ".uvst[0].uvsp[0:23]" -type "float2" 0.234375 0.359375
		 0.21875 0.359375 0.21875 0.3125 0.234375 0.3125 0.265625 0.359375 0.25 0.359375 0.25
		 0.3125 0.265625 0.3125 0.234375 0.359375 0.25 0.359375 0.25 0.375 0.234375 0.375
		 0.25 0.375 0.265625 0.375 0.265625 0.359375 0.25 0.359375 0.28125 0.359375 0.265625
		 0.359375 0.265625 0.3125 0.28125 0.3125 0.25 0.359375 0.234375 0.359375 0.234375
		 0.3125 0.25 0.3125;
	setAttr ".cuvs" -type "string" "map1";
	setAttr ".dcc" -type "string" "Ambient+Diffuse";
	setAttr ".covm[0]"  0 1 1;
	setAttr ".cdvm[0]"  0 1 1;
	setAttr -s 8 ".pt[0:7]" -type "float3"  -3.7174339 23.112091 -2.7370908 
		-3.7174339 23.112091 2.7370908 -17.382568 23.862091 -2.7370908 -17.382568 23.862091 
		2.7370908 -3.4674339 17.887909 2.7370908 -3.4674339 17.887909 -2.7370908 -17.132568 
		18.637909 2.7370908 -17.132568 18.637909 -2.7370908;
	setAttr -s 8 ".vt[0:7]"  0.5 1.5 0.125 0.5 1.5 -0.125 0.5 0.75 0.125
		 0.5 0.75 -0.125 0.25 1.5 -0.125 0.25 1.5 0.125 0.25 0.75 -0.125 0.25 0.75 0.125;
	setAttr -s 12 ".ed[0:11]"  0 2 0 1 0 0 2 3 0 3 1 0 4 6 0 5 4 0 6 7 0
		 7 5 0 1 4 0 5 0 0 2 7 0 6 3 0;
	setAttr -s 24 ".n[0:23]" -type "float3"  2.4373482e-17 0.047854386 2.6025726e-33
		 2.4373482e-17 0.047854386 2.6025726e-33 2.4373482e-17 0.047854386 2.6025726e-33 2.4373482e-17
		 0.047854386 2.6025726e-33 -2.4373482e-17 -0.047854386 -2.6025726e-33 -2.4373482e-17
		 -0.047854386 -2.6025726e-33 -2.4373482e-17 -0.047854386 -2.6025726e-33 -2.4373482e-17
		 -0.047854386 -2.6025726e-33 0.054884203 -2.1251617e-17 5.8604725e-18 0.054884203
		 -2.1251617e-17 5.8604725e-18 0.054884203 -2.1251617e-17 5.8604725e-18 0.054884203
		 -2.1251617e-17 5.8604725e-18 -0.054884203 2.1251617e-17 -5.8604725e-18 -0.054884203
		 2.1251617e-17 -5.8604725e-18 -0.054884203 2.1251617e-17 -5.8604725e-18 -0.054884203
		 2.1251617e-17 -5.8604725e-18 6.7213768e-18 0 -0.047854386 6.7213768e-18 0 -0.047854386
		 6.7213768e-18 0 -0.047854386 6.7213768e-18 0 -0.047854386 -6.7213768e-18 0 0.047854386
		 -6.7213768e-18 0 0.047854386 -6.7213768e-18 0 0.047854386 -6.7213768e-18 0 0.047854386;
	setAttr -s 6 -ch 24 ".fc[0:5]" -type "polyFaces" 
		f 4 1 0 2 3
		mu 0 4 0 1 2 3
		f 4 5 4 6 7
		mu 0 4 4 5 6 7
		f 4 8 -6 9 -2
		mu 0 4 8 9 10 11
		f 4 10 -7 11 -3
		mu 0 4 12 13 14 15
		f 4 -10 -8 -11 -1
		mu 0 4 16 17 18 19
		f 4 -9 -4 -12 -5
		mu 0 4 20 21 22 23;
	setAttr ".cd" -type "dataPolyComponent" Index_Data Edge 0 ;
	setAttr ".cvd" -type "dataPolyComponent" Index_Data Vertex 0 ;
	setAttr ".pd[0]" -type "dataPolyComponent" Index_Data UV 0 ;
	setAttr ".hfd" -type "dataPolyComponent" Index_Data Face 0 ;
	setAttr ".ai_translator" -type "string" "polymesh";
createNode transform -n "mesh_armor_chest_l" -p "grp_mesh_chestplate";
	rename -uid "A153628B-430E-8EDC-607A-99BC02046E4C";
	setAttr -l on ".tx";
	setAttr -l on ".ty";
	setAttr -l on ".tz";
	setAttr -l on ".rx";
	setAttr -l on ".ry";
	setAttr -l on ".rz";
	setAttr -l on ".sx";
	setAttr -l on ".sy";
	setAttr -l on ".sz";
	setAttr ".rp" -type "double3" 5.9999999999999574 21.999999999999975 0 ;
	setAttr ".sp" -type "double3" 5.9999999999998792 21.999999999999975 0 ;
createNode mesh -n "mesh_armor_chest_lShape" -p "mesh_armor_chest_l";
	rename -uid "38A14A99-40CB-06E0-CF4B-FFB498277149";
	setAttr -k off ".v";
	setAttr -s 4 ".iog[0].og";
	setAttr ".vir" yes;
	setAttr ".vif" yes;
	setAttr ".pv" -type "double2" 0.25 0.96875 ;
	setAttr ".uvst[0].uvsn" -type "string" "map1";
	setAttr ".cuvs" -type "string" "map1";
	setAttr ".dcc" -type "string" "Ambient+Diffuse";
	setAttr ".covm[0]"  0 1 1;
	setAttr ".cdvm[0]"  0 1 1;
	setAttr ".dr" 1;
	setAttr ".vcs" 2;
	setAttr ".ai_opaque" no;
	setAttr ".ai_translator" -type "string" "polymesh";
createNode mesh -n "mesh_armor_chest_lShapeOrig" -p "mesh_armor_chest_l";
	rename -uid "92B4D6EC-41AB-C3B6-B24A-16ACF23F037F";
	setAttr -k off ".v";
	setAttr ".io" yes;
	setAttr ".vir" yes;
	setAttr ".vif" yes;
	setAttr ".uvst[0].uvsn" -type "string" "map1";
	setAttr -s 24 ".uvst[0].uvsp[0:23]" -type "float2" 0.234375 0.359375
		 0.21875 0.359375 0.21875 0.3125 0.234375 0.3125 0.265625 0.359375 0.25 0.359375 0.25
		 0.3125 0.265625 0.3125 0.234375 0.359375 0.25 0.359375 0.25 0.375 0.234375 0.375
		 0.25 0.375 0.265625 0.375 0.265625 0.359375 0.25 0.359375 0.28125 0.359375 0.265625
		 0.359375 0.265625 0.3125 0.28125 0.3125 0.25 0.359375 0.234375 0.359375 0.234375
		 0.3125 0.25 0.3125;
	setAttr ".cuvs" -type "string" "map1";
	setAttr ".dcc" -type "string" "Ambient+Diffuse";
	setAttr ".covm[0]"  0 1 1;
	setAttr ".cdvm[0]"  0 1 1;
	setAttr -s 8 ".pt[0:7]" -type "float3"  2.6674347 23.112091 -2.7370908 
		2.6674347 23.112091 2.7370908 16.332569 23.862091 -2.7370908 16.332569 23.862091 
		2.7370908 2.9174347 17.887909 2.7370908 2.9174347 17.887909 -2.7370908 16.582569 
		18.637909 2.7370908 16.582569 18.637909 -2.7370908;
	setAttr -s 8 ".vt[0:7]"  0.5 1.5 0.125 0.5 1.5 -0.125 0.5 0.75 0.125
		 0.5 0.75 -0.125 0.25 1.5 -0.125 0.25 1.5 0.125 0.25 0.75 -0.125 0.25 0.75 0.125;
	setAttr -s 12 ".ed[0:11]"  0 2 0 1 0 0 2 3 0 3 1 0 4 6 0 5 4 0 6 7 0
		 7 5 0 1 4 0 5 0 0 2 7 0 6 3 0;
	setAttr -s 24 ".n[0:23]" -type "float3"  -2.4373482e-17 0.047854386
		 2.6025726e-33 -2.4373482e-17 0.047854386 2.6025726e-33 -2.4373482e-17 0.047854386
		 2.6025726e-33 -2.4373482e-17 0.047854386 2.6025726e-33 2.4373482e-17 -0.047854386
		 -2.6025726e-33 2.4373482e-17 -0.047854386 -2.6025726e-33 2.4373482e-17 -0.047854386
		 -2.6025726e-33 2.4373482e-17 -0.047854386 -2.6025726e-33 -0.054884203 -2.1251617e-17
		 5.8604725e-18 -0.054884203 -2.1251617e-17 5.8604725e-18 -0.054884203 -2.1251617e-17
		 5.8604725e-18 -0.054884203 -2.1251617e-17 5.8604725e-18 0.054884203 2.1251617e-17
		 -5.8604725e-18 0.054884203 2.1251617e-17 -5.8604725e-18 0.054884203 2.1251617e-17
		 -5.8604725e-18 0.054884203 2.1251617e-17 -5.8604725e-18 -6.7213768e-18 0 -0.047854386
		 -6.7213768e-18 0 -0.047854386 -6.7213768e-18 0 -0.047854386 -6.7213768e-18 0 -0.047854386
		 6.7213768e-18 0 0.047854386 6.7213768e-18 0 0.047854386 6.7213768e-18 0 0.047854386
		 6.7213768e-18 0 0.047854386;
	setAttr -s 6 -ch 24 ".fc[0:5]" -type "polyFaces" 
		f 4 -4 -3 -1 -2
		mu 0 4 0 3 2 1
		f 4 -8 -7 -5 -6
		mu 0 4 4 7 6 5
		f 4 1 -10 5 -9
		mu 0 4 8 11 10 9
		f 4 2 -12 6 -11
		mu 0 4 12 15 14 13
		f 4 0 10 7 9
		mu 0 4 16 19 18 17
		f 4 4 11 3 8
		mu 0 4 20 23 22 21;
	setAttr ".cd" -type "dataPolyComponent" Index_Data Edge 0 ;
	setAttr ".cvd" -type "dataPolyComponent" Index_Data Vertex 0 ;
	setAttr ".pd[0]" -type "dataPolyComponent" Index_Data UV 0 ;
	setAttr ".hfd" -type "dataPolyComponent" Index_Data Face 0 ;
	setAttr ".ai_translator" -type "string" "polymesh";
createNode transform -n "mesh_armor_chest_m" -p "grp_mesh_chestplate";
	rename -uid "F162D2F3-4661-CEFF-4DA5-54BD6FE7A005";
	setAttr ".t" -type "double3" 2.1316282072803006e-14 0 0 ;
	setAttr -l on ".tx";
	setAttr -l on ".ty";
	setAttr -l on ".tz";
	setAttr -l on ".rx";
	setAttr -l on ".ry";
	setAttr -l on ".rz";
	setAttr -l on ".sx";
	setAttr -l on ".sy";
	setAttr -l on ".sz";
	setAttr ".rp" -type "double3" -2.1316282072803006e-14 18 0 ;
	setAttr ".sp" -type "double3" -2.1316282072803006e-14 18 0 ;
createNode mesh -n "mesh_armor_chest_mShape" -p "mesh_armor_chest_m";
	rename -uid "08DBBE47-4445-6FB5-82F3-E79E0E6A41CE";
	setAttr -k off ".v";
	setAttr -s 4 ".iog[0].og";
	setAttr ".vir" yes;
	setAttr ".vif" yes;
	setAttr ".pv" -type "double2" 0.171875 0.96875 ;
	setAttr ".uvst[0].uvsn" -type "string" "map1";
	setAttr ".cuvs" -type "string" "map1";
	setAttr ".dcc" -type "string" "Ambient+Diffuse";
	setAttr ".covm[0]"  0 1 1;
	setAttr ".cdvm[0]"  0 1 1;
	setAttr ".dfgi" 4907;
	setAttr ".vcs" 2;
	setAttr ".ai_opaque" no;
	setAttr ".ai_translator" -type "string" "polymesh";
createNode mesh -n "mesh_armor_chest_mShapeOrig" -p "mesh_armor_chest_m";
	rename -uid "CA7EB9E5-4D90-6015-EEEC-BD9B93C96C43";
	setAttr -k off ".v";
	setAttr ".io" yes;
	setAttr ".vir" yes;
	setAttr ".vif" yes;
	setAttr ".pv" -type "double2" 0 -0.125 ;
	setAttr ".uvst[0].uvsn" -type "string" "map1";
	setAttr -s 24 ".uvst[0].uvsp[0:23]" -type "float2" 0.31225586 0.68725586
		 0.25024414 0.68725586 0.25024414 0.50024414 0.31225586 0.50024414 0.49975586 0.68725586
		 0.43774414 0.68725586 0.43774414 0.50024414 0.49975586 0.50024414 0.31274414 0.68774414
		 0.43725586 0.68774414 0.43725586 0.74975586 0.31274414 0.74975586 0.43774414 0.74975586
		 0.56225586 0.74975586 0.56225586 0.68774414 0.43774414 0.68774414 0.62475586 0.68725586
		 0.50024414 0.68725586 0.50024414 0.50024414 0.62475586 0.50024414 0.43725586 0.68725586
		 0.31274414 0.68725586 0.31274414 0.50024414 0.43725586 0.50024414;
	setAttr ".cuvs" -type "string" "map1";
	setAttr ".dcc" -type "string" "Ambient+Diffuse";
	setAttr ".covm[0]"  0 1 1;
	setAttr ".cdvm[0]"  0 1 1;
	setAttr -s 8 ".vt[0:7]"  0.25 1.5 0.125 0.25 1.5 -0.125 0.25 0.75 0.125
		 0.25 0.75 -0.125 -0.25 1.5 -0.125 -0.25 1.5 0.125 -0.25 0.75 -0.125 -0.25 0.75 0.125;
	setAttr -s 12 ".ed[0:11]"  0 2 0 1 0 0 2 3 0 3 1 0 4 6 0 5 4 0 6 7 0
		 7 5 0 1 4 0 5 0 0 2 7 0 6 3 0;
	setAttr -s 24 ".n[0:23]" -type "float3"  1 0 0 1 0 0 1 0 0 1 0 0 -1
		 0 0 -1 0 0 -1 0 0 -1 0 0 0 1 0 0 1 0 0 1 0 0 1 0 0 -1 0 0 -1 0 0 -1 0 0 -1 0 0 0
		 1 0 0 1 0 0 1 0 0 1 0 0 -1 0 0 -1 0 0 -1 0 0 -1;
	setAttr -s 6 -ch 24 ".fc[0:5]" -type "polyFaces" 
		f 4 1 0 2 3
		mu 0 4 0 1 2 3
		f 4 5 4 6 7
		mu 0 4 4 5 6 7
		f 4 8 -6 9 -2
		mu 0 4 8 9 10 11
		f 4 10 -7 11 -3
		mu 0 4 12 13 14 15
		f 4 -10 -8 -11 -1
		mu 0 4 16 17 18 19
		f 4 -9 -4 -12 -5
		mu 0 4 20 21 22 23;
	setAttr ".cd" -type "dataPolyComponent" Index_Data Edge 0 ;
	setAttr ".cvd" -type "dataPolyComponent" Index_Data Vertex 0 ;
	setAttr ".pd[0]" -type "dataPolyComponent" Index_Data UV 0 ;
	setAttr ".hfd" -type "dataPolyComponent" Index_Data Face 0 ;
	setAttr ".ai_translator" -type "string" "polymesh";
createNode mesh -n "mesh_armor_chest_mShapeOrig1" -p "mesh_armor_chest_m";
	rename -uid "8179F060-4CE5-7B20-62F9-67BEC2BED906";
	setAttr -k off ".v";
	setAttr ".io" yes;
	setAttr ".vir" yes;
	setAttr ".vif" yes;
	setAttr ".uvst[0].uvsn" -type "string" "map1";
	setAttr -s 24 ".uvst[0].uvsp[0:23]" -type "float2" 0.140625 0.359375
		 0.125 0.359375 0.125 0.3125 0.140625 0.3125 0.1875 0.359375 0.171875 0.359375 0.171875
		 0.3125 0.1875 0.3125 0.140625 0.359375 0.171875 0.359375 0.171875 0.375 0.140625
		 0.375 0.171875 0.375 0.203125 0.375 0.203125 0.359375 0.171875 0.359375 0.21875 0.359375
		 0.1875 0.359375 0.1875 0.3125 0.21875 0.3125 0.171875 0.359375 0.140625 0.359375
		 0.140625 0.3125 0.171875 0.3125;
	setAttr ".cuvs" -type "string" "map1";
	setAttr ".dcc" -type "string" "Ambient+Diffuse";
	setAttr ".covm[0]"  0 1 1;
	setAttr ".cdvm[0]"  0 1 1;
	setAttr -s 8 ".pt[0:7]" -type "float3"  -0.8180275 1.0698912 -0.84368664 
		-0.8180275 1.0698912 0.84368664 -0.8180275 -1.0698912 -0.84368664 -0.8180275 -1.0698912 
		0.84368664 0.8180275 1.0698912 0.84368664 0.8180275 1.0698912 -0.84368664 0.8180275 
		-1.0698912 0.84368664 0.8180275 -1.0698912 -0.84368664;
	setAttr -s 8 ".vt[0:7]"  -4 24 -2 -4 24 2 -4 12 -2 -4 12 2 4 24 2
		 4 24 -2 4 12 2 4 12 -2;
	setAttr -s 12 ".ed[0:11]"  0 2 0 1 0 0 2 3 0 3 1 0 4 6 0 5 4 0 6 7 0
		 7 5 0 1 4 0 5 0 0 2 7 0 6 3 0;
	setAttr -s 24 ".n[0:23]" -type "float3"  -0.83021522 0 -8.6130929e-17
		 -0.83021522 0 -8.6130929e-17 -0.83021522 0 -8.6130929e-17 -0.83021522 0 -8.6130929e-17
		 0.83021522 0 8.6130929e-17 0.83021522 0 8.6130929e-17 0.83021522 0 8.6130929e-17
		 0.83021522 0 8.6130929e-17 0 0.84866941 0 0 0.84866941 0 0 0.84866941 0 0 0.84866941
		 0 0 -0.84866941 0 0 -0.84866941 0 0 -0.84866941 0 0 -0.84866941 0 1.0167204e-16 0
		 -0.7033124 1.0167204e-16 0 -0.7033124 1.0167204e-16 0 -0.7033124 1.0167204e-16 0
		 -0.7033124 -1.0167204e-16 0 0.7033124 -1.0167204e-16 0 0.7033124 -1.0167204e-16 0
		 0.7033124 -1.0167204e-16 0 0.7033124;
	setAttr -s 6 -ch 24 ".fc[0:5]" -type "polyFaces" 
		f 4 1 0 2 3
		mu 0 4 0 1 2 3
		f 4 5 4 6 7
		mu 0 4 4 5 6 7
		f 4 8 -6 9 -2
		mu 0 4 8 9 10 11
		f 4 10 -7 11 -3
		mu 0 4 12 13 14 15
		f 4 -10 -8 -11 -1
		mu 0 4 16 17 18 19
		f 4 -9 -4 -12 -5
		mu 0 4 20 21 22 23;
	setAttr ".cd" -type "dataPolyComponent" Index_Data Edge 0 ;
	setAttr ".cvd" -type "dataPolyComponent" Index_Data Vertex 0 ;
	setAttr ".pd[0]" -type "dataPolyComponent" Index_Data UV 0 ;
	setAttr ".hfd" -type "dataPolyComponent" Index_Data Face 0 ;
	setAttr ".dfgi" 4907;
	setAttr ".vcs" 2;
	setAttr ".ai_translator" -type "string" "polymesh";
createNode transform -n "grp_mesh_leggings" -p "grp_mesh_armor";
	rename -uid "61F395B9-4863-5B02-B6C6-7AAE3761C333";
createNode transform -n "mesh_leggings_armor_r" -p "grp_mesh_leggings";
	rename -uid "7288B98F-4E77-D75B-4CA3-BEBE48FED6A3";
	setAttr -l on ".tx";
	setAttr -l on ".ty";
	setAttr -l on ".tz";
	setAttr -l on ".rx";
	setAttr -l on ".ry";
	setAttr -l on ".rz";
	setAttr -l on ".sx";
	setAttr -l on ".sy";
	setAttr -l on ".sz";
	setAttr ".rp" -type "double3" -2 6 -2.4492935982947094e-16 ;
	setAttr ".sp" -type "double3" -2 6 -2.4492935982947094e-16 ;
createNode mesh -n "mesh_leggings_armor_rShape" -p "mesh_leggings_armor_r";
	rename -uid "AA9A698E-4A7C-3AAB-F97D-93841C723A03";
	setAttr -k off ".v";
	setAttr -s 4 ".iog[0].og";
	setAttr ".vir" yes;
	setAttr ".vif" yes;
	setAttr ".pv" -type "double2" 0.3125 0.96875 ;
	setAttr ".uvst[0].uvsn" -type "string" "map1";
	setAttr ".cuvs" -type "string" "map1";
	setAttr ".dcc" -type "string" "Ambient+Diffuse";
	setAttr ".covm[0]"  0 1 1;
	setAttr ".cdvm[0]"  0 1 1;
	setAttr ".dfgi" 4903;
	setAttr ".vcs" 2;
	setAttr ".ai_opaque" no;
	setAttr ".ai_translator" -type "string" "polymesh";
createNode mesh -n "mesh_leggings_armor_rShapeOrig" -p "mesh_leggings_armor_r";
	rename -uid "D9550AA3-46F2-9182-43E5-7BBE0A7A0012";
	setAttr -k off ".v";
	setAttr ".io" yes;
	setAttr ".vir" yes;
	setAttr ".vif" yes;
	setAttr ".uvst[0].uvsn" -type "string" "map1";
	setAttr -s 24 ".uvst[0].uvsp[0:23]" -type "float2" 0.0625 0.6875 0 0.6875
		 0 0.5 0.0625 0.5 0.1875 0.6875 0.125 0.6875 0.125 0.5 0.1875 0.5 0.0625 0.6875 0.125
		 0.6875 0.125 0.75 0.0625 0.75 0.125 0.75 0.1875 0.75 0.1875 0.6875 0.125 0.6875 0.25
		 0.6875 0.1875 0.6875 0.1875 0.5 0.25 0.5 0.125 0.6875 0.0625 0.6875 0.0625 0.5 0.125
		 0.5;
	setAttr ".cuvs" -type "string" "map1";
	setAttr ".dcc" -type "string" "Ambient+Diffuse";
	setAttr ".covm[0]"  0 1 1;
	setAttr ".cdvm[0]"  0 1 1;
	setAttr -s 8 ".vt[0:7]"  -3.95000005 12 -2 -3.95000005 12 2 -3.95000005 -2.9605948e-16 -2
		 -3.95000005 -2.9605948e-16 2 0.050000053 12 2 0.050000053 12 -2 0.050000053 -2.9605948e-16 2
		 0.050000053 -2.9605948e-16 -2;
	setAttr -s 12 ".ed[0:11]"  0 2 0 1 0 0 2 3 0 3 1 0 4 6 0 5 4 0 6 7 0
		 7 5 0 1 4 0 5 0 0 2 7 0 6 3 0;
	setAttr -s 24 ".n[0:23]" -type "float3"  -0.0625 0 -7.6540428e-18 -0.0625
		 0 -7.6540428e-18 -0.0625 0 -7.6540428e-18 -0.0625 0 -7.6540428e-18 0.0625 0 7.6540428e-18
		 0.0625 0 7.6540428e-18 0.0625 0 7.6540428e-18 0.0625 0 7.6540428e-18 0 0.0625 0 0
		 0.0625 0 0 0.0625 0 0 0.0625 0 0 -0.0625 0 0 -0.0625 0 0 -0.0625 0 0 -0.0625 0 7.6540428e-18
		 0 -0.0625 7.6540428e-18 0 -0.0625 7.6540428e-18 0 -0.0625 7.6540428e-18 0 -0.0625
		 -7.6540428e-18 0 0.0625 -7.6540428e-18 0 0.0625 -7.6540428e-18 0 0.0625 -7.6540428e-18
		 0 0.0625;
	setAttr -s 6 -ch 24 ".fc[0:5]" -type "polyFaces" 
		f 4 1 0 2 3
		mu 0 4 0 1 2 3
		f 4 5 4 6 7
		mu 0 4 4 5 6 7
		f 4 8 -6 9 -2
		mu 0 4 8 9 10 11
		f 4 10 -7 11 -3
		mu 0 4 12 13 14 15
		f 4 -10 -8 -11 -1
		mu 0 4 16 17 18 19
		f 4 -9 -4 -12 -5
		mu 0 4 20 21 22 23;
	setAttr ".cd" -type "dataPolyComponent" Index_Data Edge 0 ;
	setAttr ".cvd" -type "dataPolyComponent" Index_Data Vertex 0 ;
	setAttr ".pd[0]" -type "dataPolyComponent" Index_Data UV 0 ;
	setAttr ".hfd" -type "dataPolyComponent" Index_Data Face 0 ;
	setAttr ".dfgi" 4903;
	setAttr ".ai_translator" -type "string" "polymesh";
createNode mesh -n "mesh_leggings_armor_rShapeOrig1" -p "mesh_leggings_armor_r";
	rename -uid "89CAF077-4C96-63CA-5552-52BAF6F7B585";
	setAttr -k off ".v";
	setAttr ".io" yes;
	setAttr ".vir" yes;
	setAttr ".vif" yes;
	setAttr ".uvst[0].uvsn" -type "string" "map1";
	setAttr -s 24 ".uvst[0].uvsp[0:23]" -type "float2" 0.296875 0.296875
		 0.28125 0.296875 0.28125 0.25 0.296875 0.25 0.328125 0.296875 0.3125 0.296875 0.3125
		 0.25 0.328125 0.25 0.296875 0.296875 0.3125 0.296875 0.3125 0.3125 0.296875 0.3125
		 0.3125 0.3125 0.328125 0.3125 0.328125 0.296875 0.3125 0.296875 0.34375 0.296875
		 0.328125 0.296875 0.328125 0.25 0.34375 0.25 0.3125 0.296875 0.296875 0.296875 0.296875
		 0.25 0.3125 0.25;
	setAttr ".cuvs" -type "string" "map1";
	setAttr ".dcc" -type "string" "Ambient+Diffuse";
	setAttr ".covm[0]"  0 1 1;
	setAttr ".cdvm[0]"  0 1 1;
	setAttr -s 8 ".pt[0:7]" -type "float3"  -0.56040573 0.38010561 -0.51103187 
		-0.56040573 0.38010561 0.51103187 -0.56040573 -0.38010561 -0.51103187 -0.56040573 
		-0.38010561 0.51103187 0.46040344 0.38010561 0.51103187 0.46040344 0.38010561 -0.51103187 
		0.46040344 -0.38010561 0.51103187 0.46040344 -0.38010561 -0.51103187;
	setAttr -s 8 ".vt[0:7]"  -3.95000005 12 -2 -3.95000005 12 2 -3.95000005 -2.9606022e-16 -2
		 -3.95000005 -2.9606022e-16 2 0.050000053 12 2 0.050000053 12 -2 0.050000053 -2.9606022e-16 2
		 0.050000053 -2.9606022e-16 -2;
	setAttr -s 12 ".ed[0:11]"  0 2 0 1 0 0 2 3 0 3 1 0 4 6 0 5 4 0 6 7 0
		 7 5 0 1 4 0 5 0 0 2 7 0 6 3 0;
	setAttr -s 24 ".n[0:23]" -type "float3"  -0.7966845 0 -9.7541323e-17
		 -0.7966845 0 -9.7541323e-17 -0.7966845 0 -9.7541323e-17 -0.7966845 0 -9.7541323e-17
		 0.7966845 0 9.7541323e-17 0.7966845 0 9.7541323e-17 0.7966845 0 9.7541323e-17 0.7966845
		 0 9.7541323e-17 0 0.94042337 0 0 0.94042337 0 0 0.94042337 0 0 0.94042337 0 0 -0.94042337
		 0 0 -0.94042337 0 0 -0.94042337 0 0 -0.94042337 0 9.7565715e-17 0 -0.79648525 9.7565715e-17
		 0 -0.79648525 9.7565715e-17 0 -0.79648525 9.7565715e-17 0 -0.79648525 -9.7565715e-17
		 0 0.79648525 -9.7565715e-17 0 0.79648525 -9.7565715e-17 0 0.79648525 -9.7565715e-17
		 0 0.79648525;
	setAttr -s 6 -ch 24 ".fc[0:5]" -type "polyFaces" 
		f 4 1 0 2 3
		mu 0 4 0 1 2 3
		f 4 5 4 6 7
		mu 0 4 4 5 6 7
		f 4 8 -6 9 -2
		mu 0 4 8 9 10 11
		f 4 10 -7 11 -3
		mu 0 4 12 13 14 15
		f 4 -10 -8 -11 -1
		mu 0 4 16 17 18 19
		f 4 -9 -4 -12 -5
		mu 0 4 20 21 22 23;
	setAttr ".cd" -type "dataPolyComponent" Index_Data Edge 0 ;
	setAttr ".cvd" -type "dataPolyComponent" Index_Data Vertex 0 ;
	setAttr ".pd[0]" -type "dataPolyComponent" Index_Data UV 0 ;
	setAttr ".hfd" -type "dataPolyComponent" Index_Data Face 0 ;
	setAttr ".dfgi" 4903;
	setAttr ".vcs" 2;
	setAttr ".ai_translator" -type "string" "polymesh";
createNode transform -n "mesh_armor_leggings_m" -p "grp_mesh_leggings";
	rename -uid "5364316D-4996-16D4-AEC9-FA8C59C6A565";
	setAttr -l on ".tx";
	setAttr -l on ".ty";
	setAttr -l on ".tz";
	setAttr -l on ".rx";
	setAttr -l on ".ry";
	setAttr -l on ".rz";
	setAttr -l on ".sx";
	setAttr -l on ".sy";
	setAttr -l on ".sz";
	setAttr ".rp" -type "double3" 0 18 0 ;
	setAttr ".sp" -type "double3" 0 18 0 ;
createNode mesh -n "mesh_armor_leggings_mShape" -p "mesh_armor_leggings_m";
	rename -uid "1143AD32-4A82-8FAC-7976-BDBDACBBA289";
	setAttr -k off ".v";
	setAttr -s 4 ".iog[0].og";
	setAttr ".vir" yes;
	setAttr ".vif" yes;
	setAttr ".pv" -type "double2" 0.390625 0.96875 ;
	setAttr ".uvst[0].uvsn" -type "string" "map1";
	setAttr ".cuvs" -type "string" "map1";
	setAttr ".dcc" -type "string" "Ambient+Diffuse";
	setAttr ".covm[0]"  0 1 1;
	setAttr ".cdvm[0]"  0 1 1;
	setAttr ".dfgi" 4907;
	setAttr ".vcs" 2;
	setAttr ".ai_opaque" no;
	setAttr ".ai_translator" -type "string" "polymesh";
createNode mesh -n "mesh_armor_leggings_mShapeOrig" -p "mesh_armor_leggings_m";
	rename -uid "B71E64D9-4B8E-9837-5C9D-63B3F28F5C21";
	setAttr -k off ".v";
	setAttr ".io" yes;
	setAttr ".vir" yes;
	setAttr ".vif" yes;
	setAttr ".uvst[0].uvsn" -type "string" "map1";
	setAttr -s 24 ".uvst[0].uvsp[0:23]" -type "float2" 0.31225586 0.68725586
		 0.25024414 0.68725586 0.25024414 0.50024414 0.31225586 0.50024414 0.49975586 0.68725586
		 0.43774414 0.68725586 0.43774414 0.50024414 0.49975586 0.50024414 0.31274414 0.68774414
		 0.43725586 0.68774414 0.43725586 0.74975586 0.31274414 0.74975586 0.43774414 0.74975586
		 0.56225586 0.74975586 0.56225586 0.68774414 0.43774414 0.68774414 0.62475586 0.68725586
		 0.50024414 0.68725586 0.50024414 0.50024414 0.62475586 0.50024414 0.43725586 0.68725586
		 0.31274414 0.68725586 0.31274414 0.50024414 0.43725586 0.50024414;
	setAttr ".cuvs" -type "string" "map1";
	setAttr ".dcc" -type "string" "Ambient+Diffuse";
	setAttr ".covm[0]"  0 1 1;
	setAttr ".cdvm[0]"  0 1 1;
	setAttr -s 8 ".vt[0:7]"  0.25 1.5 0.125 0.25 1.5 -0.125 0.25 0.75 0.125
		 0.25 0.75 -0.125 -0.25 1.5 -0.125 -0.25 1.5 0.125 -0.25 0.75 -0.125 -0.25 0.75 0.125;
	setAttr -s 12 ".ed[0:11]"  0 2 0 1 0 0 2 3 0 3 1 0 4 6 0 5 4 0 6 7 0
		 7 5 0 1 4 0 5 0 0 2 7 0 6 3 0;
	setAttr -s 24 ".n[0:23]" -type "float3"  1 0 0 1 0 0 1 0 0 1 0 0 -1
		 0 0 -1 0 0 -1 0 0 -1 0 0 0 1 0 0 1 0 0 1 0 0 1 0 0 -1 0 0 -1 0 0 -1 0 0 -1 0 0 0
		 1 0 0 1 0 0 1 0 0 1 0 0 -1 0 0 -1 0 0 -1 0 0 -1;
	setAttr -s 6 -ch 24 ".fc[0:5]" -type "polyFaces" 
		f 4 1 0 2 3
		mu 0 4 0 1 2 3
		f 4 5 4 6 7
		mu 0 4 4 5 6 7
		f 4 8 -6 9 -2
		mu 0 4 8 9 10 11
		f 4 10 -7 11 -3
		mu 0 4 12 13 14 15
		f 4 -10 -8 -11 -1
		mu 0 4 16 17 18 19
		f 4 -9 -4 -12 -5
		mu 0 4 20 21 22 23;
	setAttr ".cd" -type "dataPolyComponent" Index_Data Edge 0 ;
	setAttr ".cvd" -type "dataPolyComponent" Index_Data Vertex 0 ;
	setAttr ".pd[0]" -type "dataPolyComponent" Index_Data UV 0 ;
	setAttr ".hfd" -type "dataPolyComponent" Index_Data Face 0 ;
	setAttr ".ai_translator" -type "string" "polymesh";
createNode mesh -n "mesh_armor_leggings_mShapeOrig1" -p "mesh_armor_leggings_m";
	rename -uid "7AD8104D-4588-3683-D537-73A613ED4DC9";
	setAttr -k off ".v";
	setAttr ".io" yes;
	setAttr ".vir" yes;
	setAttr ".vif" yes;
	setAttr ".uvst[0].uvsn" -type "string" "map1";
	setAttr -s 24 ".uvst[0].uvsp[0:23]" -type "float2" 0.359375 0.296875
		 0.34375 0.296875 0.34375 0.25 0.359375 0.25 0.40625 0.296875 0.390625 0.296875 0.390625
		 0.25 0.40625 0.25 0.359375 0.296875 0.390625 0.296875 0.390625 0.3125 0.359375 0.3125
		 0.390625 0.3125 0.421875 0.3125 0.421875 0.296875 0.390625 0.296875 0.4375 0.296875
		 0.40625 0.296875 0.40625 0.25 0.4375 0.25 0.390625 0.296875 0.359375 0.296875 0.359375
		 0.25 0.390625 0.25;
	setAttr ".cuvs" -type "string" "map1";
	setAttr ".dcc" -type "string" "Ambient+Diffuse";
	setAttr ".covm[0]"  0 1 1;
	setAttr ".cdvm[0]"  0 1 1;
	setAttr -s 8 ".pt[0:7]" -type "float3"  -0.52206421 0.66666001 -0.54823238 
		-0.52206421 0.66666001 0.54823238 -0.52206421 -0.66666001 -0.54823238 -0.52206421 
		-0.66666001 0.54823238 0.52206421 0.66666001 0.54823238 0.52206421 0.66666001 -0.54823238 
		0.52206421 -0.66666001 0.54823238 0.52206421 -0.66666001 -0.54823238;
	setAttr -s 8 ".vt[0:7]"  -4 24 -2 -4 24 2 -4 12 -2 -4 12 2 4 24 2
		 4 24 -2 4 12 2 4 12 -2;
	setAttr -s 12 ".ed[0:11]"  0 2 0 1 0 0 2 3 0 3 1 0 4 6 0 5 4 0 6 7 0
		 7 5 0 1 4 0 5 0 0 2 7 0 6 3 0;
	setAttr -s 24 ".n[0:23]" -type "float3"  -0.88455158 0 -9.6117361e-17
		 -0.88455158 0 -9.6117361e-17 -0.88455158 0 -9.6117361e-17 -0.88455158 0 -9.6117361e-17
		 0.88455158 0 9.6117361e-17 0.88455158 0 9.6117361e-17 0.88455158 0 9.6117361e-17
		 0.88455158 0 9.6117361e-17 0 0.90000093 0 0 0.90000093 0 0 0.90000093 0 0 0.90000093
		 0 0 -0.90000093 0 0 -0.90000093 0 0 -0.90000093 0 0 -0.90000093 0 1.0832633e-16 0
		 -0.78485781 1.0832633e-16 0 -0.78485781 1.0832633e-16 0 -0.78485781 1.0832633e-16
		 0 -0.78485781 -1.0832633e-16 0 0.78485781 -1.0832633e-16 0 0.78485781 -1.0832633e-16
		 0 0.78485781 -1.0832633e-16 0 0.78485781;
	setAttr -s 6 -ch 24 ".fc[0:5]" -type "polyFaces" 
		f 4 1 0 2 3
		mu 0 4 0 1 2 3
		f 4 5 4 6 7
		mu 0 4 4 5 6 7
		f 4 8 -6 9 -2
		mu 0 4 8 9 10 11
		f 4 10 -7 11 -3
		mu 0 4 12 13 14 15
		f 4 -10 -8 -11 -1
		mu 0 4 16 17 18 19
		f 4 -9 -4 -12 -5
		mu 0 4 20 21 22 23;
	setAttr ".cd" -type "dataPolyComponent" Index_Data Edge 0 ;
	setAttr ".cvd" -type "dataPolyComponent" Index_Data Vertex 0 ;
	setAttr ".pd[0]" -type "dataPolyComponent" Index_Data UV 0 ;
	setAttr ".hfd" -type "dataPolyComponent" Index_Data Face 0 ;
	setAttr ".dfgi" 4907;
	setAttr ".vcs" 2;
	setAttr ".ai_translator" -type "string" "polymesh";
createNode transform -n "mesh_armor_leggings_l" -p "grp_mesh_leggings";
	rename -uid "346B4D36-4E44-0CA5-572C-38886D8DCBF3";
	setAttr -l on ".tx";
	setAttr -l on ".ty";
	setAttr -l on ".tz";
	setAttr -l on ".rx";
	setAttr -l on ".ry";
	setAttr -l on ".rz";
	setAttr -l on ".sx";
	setAttr -l on ".sy";
	setAttr -l on ".sz";
	setAttr ".rp" -type "double3" 2 6 -2.4492935982947094e-16 ;
	setAttr ".sp" -type "double3" 2 6 -2.4492935982947094e-16 ;
createNode mesh -n "mesh_armor_leggings_lShape" -p "mesh_armor_leggings_l";
	rename -uid "C0E6F9B1-492C-FB0E-BB9D-7DAAAA83CD75";
	setAttr -k off ".v";
	setAttr -s 4 ".iog[0].og";
	setAttr ".vir" yes;
	setAttr ".vif" yes;
	setAttr ".pv" -type "double2" 0.3125 0.96875 ;
	setAttr ".uvst[0].uvsn" -type "string" "map1";
	setAttr ".cuvs" -type "string" "map1";
	setAttr ".dcc" -type "string" "Ambient+Diffuse";
	setAttr ".covm[0]"  0 1 1;
	setAttr ".cdvm[0]"  0 1 1;
	setAttr ".dfgi" 4903;
	setAttr ".vcs" 2;
	setAttr ".ai_opaque" no;
	setAttr ".ai_translator" -type "string" "polymesh";
createNode mesh -n "mesh_armor_leggings_lShapeOrig" -p "mesh_armor_leggings_l";
	rename -uid "4557EDB1-4FB5-3928-0065-C7974CADE0D2";
	setAttr -k off ".v";
	setAttr ".io" yes;
	setAttr ".vir" yes;
	setAttr ".vif" yes;
	setAttr ".uvst[0].uvsn" -type "string" "map1";
	setAttr -s 24 ".uvst[0].uvsp[0:23]" -type "float2" 0.0625 0.6875 0 0.6875
		 0 0.5 0.0625 0.5 0.1875 0.6875 0.125 0.6875 0.125 0.5 0.1875 0.5 0.0625 0.6875 0.125
		 0.6875 0.125 0.75 0.0625 0.75 0.125 0.75 0.1875 0.75 0.1875 0.6875 0.125 0.6875 0.25
		 0.6875 0.1875 0.6875 0.1875 0.5 0.25 0.5 0.125 0.6875 0.0625 0.6875 0.0625 0.5 0.125
		 0.5;
	setAttr ".cuvs" -type "string" "map1";
	setAttr ".dcc" -type "string" "Ambient+Diffuse";
	setAttr ".covm[0]"  0 1 1;
	setAttr ".cdvm[0]"  0 1 1;
	setAttr -s 8 ".vt[0:7]"  -3.95000005 12 -2 -3.95000005 12 2 -3.95000005 -2.9605948e-16 -2
		 -3.95000005 -2.9605948e-16 2 0.050000053 12 2 0.050000053 12 -2 0.050000053 -2.9605948e-16 2
		 0.050000053 -2.9605948e-16 -2;
	setAttr -s 12 ".ed[0:11]"  0 2 0 1 0 0 2 3 0 3 1 0 4 6 0 5 4 0 6 7 0
		 7 5 0 1 4 0 5 0 0 2 7 0 6 3 0;
	setAttr -s 24 ".n[0:23]" -type "float3"  -0.0625 0 -7.6540428e-18 -0.0625
		 0 -7.6540428e-18 -0.0625 0 -7.6540428e-18 -0.0625 0 -7.6540428e-18 0.0625 0 7.6540428e-18
		 0.0625 0 7.6540428e-18 0.0625 0 7.6540428e-18 0.0625 0 7.6540428e-18 0 0.0625 0 0
		 0.0625 0 0 0.0625 0 0 0.0625 0 0 -0.0625 0 0 -0.0625 0 0 -0.0625 0 0 -0.0625 0 7.6540428e-18
		 0 -0.0625 7.6540428e-18 0 -0.0625 7.6540428e-18 0 -0.0625 7.6540428e-18 0 -0.0625
		 -7.6540428e-18 0 0.0625 -7.6540428e-18 0 0.0625 -7.6540428e-18 0 0.0625 -7.6540428e-18
		 0 0.0625;
	setAttr -s 6 -ch 24 ".fc[0:5]" -type "polyFaces" 
		f 4 1 0 2 3
		mu 0 4 0 1 2 3
		f 4 5 4 6 7
		mu 0 4 4 5 6 7
		f 4 8 -6 9 -2
		mu 0 4 8 9 10 11
		f 4 10 -7 11 -3
		mu 0 4 12 13 14 15
		f 4 -10 -8 -11 -1
		mu 0 4 16 17 18 19
		f 4 -9 -4 -12 -5
		mu 0 4 20 21 22 23;
	setAttr ".cd" -type "dataPolyComponent" Index_Data Edge 0 ;
	setAttr ".cvd" -type "dataPolyComponent" Index_Data Vertex 0 ;
	setAttr ".pd[0]" -type "dataPolyComponent" Index_Data UV 0 ;
	setAttr ".hfd" -type "dataPolyComponent" Index_Data Face 0 ;
	setAttr ".dfgi" 4903;
	setAttr ".ai_translator" -type "string" "polymesh";
createNode mesh -n "mesh_armor_leggings_lShapeOrig1" -p "mesh_armor_leggings_l";
	rename -uid "BD12805B-4D4E-2F3A-54D3-5F85256344D8";
	setAttr -k off ".v";
	setAttr ".io" yes;
	setAttr ".vir" yes;
	setAttr ".vif" yes;
	setAttr ".uvst[0].uvsn" -type "string" "map1";
	setAttr -s 24 ".uvst[0].uvsp[0:23]" -type "float2" 0.296875 0.296875
		 0.28125 0.296875 0.28125 0.25 0.296875 0.25 0.328125 0.296875 0.3125 0.296875 0.3125
		 0.25 0.328125 0.25 0.296875 0.296875 0.3125 0.296875 0.3125 0.3125 0.296875 0.3125
		 0.3125 0.3125 0.328125 0.3125 0.328125 0.296875 0.3125 0.296875 0.34375 0.296875
		 0.328125 0.296875 0.328125 0.25 0.34375 0.25 0.3125 0.296875 0.296875 0.296875 0.296875
		 0.25 0.3125 0.25;
	setAttr ".cuvs" -type "string" "map1";
	setAttr ".dcc" -type "string" "Ambient+Diffuse";
	setAttr ".covm[0]"  0 1 1;
	setAttr ".cdvm[0]"  0 1 1;
	setAttr -s 8 ".pt[0:7]" -type "float3"  8.4604053 0.38010561 -0.51103187 
		8.4604053 0.38010561 0.51103187 8.4604053 -0.38010561 -0.51103187 8.4604053 -0.38010561 
		0.51103187 -0.56040573 0.38010561 0.51103187 -0.56040573 0.38010561 -0.51103187 -0.56040573 
		-0.38010561 0.51103187 -0.56040573 -0.38010561 -0.51103187;
	setAttr -s 8 ".vt[0:7]"  -3.95000005 12 -2 -3.95000005 12 2 -3.95000005 -2.9606022e-16 -2
		 -3.95000005 -2.9606022e-16 2 0.050000053 12 2 0.050000053 12 -2 0.050000053 -2.9606022e-16 2
		 0.050000053 -2.9606022e-16 -2;
	setAttr -s 12 ".ed[0:11]"  0 2 0 1 0 0 2 3 0 3 1 0 4 6 0 5 4 0 6 7 0
		 7 5 0 1 4 0 5 0 0 2 7 0 6 3 0;
	setAttr -s 24 ".n[0:23]" -type "float3"  0.7966845 0 -9.7541323e-17
		 0.7966845 0 -9.7541323e-17 0.7966845 0 -9.7541323e-17 0.7966845 0 -9.7541323e-17
		 -0.7966845 0 9.7541323e-17 -0.7966845 0 9.7541323e-17 -0.7966845 0 9.7541323e-17
		 -0.7966845 0 9.7541323e-17 0 0.94042337 0 0 0.94042337 0 0 0.94042337 0 0 0.94042337
		 0 0 -0.94042337 0 0 -0.94042337 0 0 -0.94042337 0 0 -0.94042337 0 -9.7565715e-17
		 0 -0.79648525 -9.7565715e-17 0 -0.79648525 -9.7565715e-17 0 -0.79648525 -9.7565715e-17
		 0 -0.79648525 9.7565715e-17 0 0.79648525 9.7565715e-17 0 0.79648525 9.7565715e-17
		 0 0.79648525 9.7565715e-17 0 0.79648525;
	setAttr -s 6 -ch 24 ".fc[0:5]" -type "polyFaces" 
		f 4 -4 -3 -1 -2
		mu 0 4 0 3 2 1
		f 4 -8 -7 -5 -6
		mu 0 4 4 7 6 5
		f 4 1 -10 5 -9
		mu 0 4 8 11 10 9
		f 4 2 -12 6 -11
		mu 0 4 12 15 14 13
		f 4 0 10 7 9
		mu 0 4 16 19 18 17
		f 4 4 11 3 8
		mu 0 4 20 23 22 21;
	setAttr ".cd" -type "dataPolyComponent" Index_Data Edge 0 ;
	setAttr ".cvd" -type "dataPolyComponent" Index_Data Vertex 0 ;
	setAttr ".pd[0]" -type "dataPolyComponent" Index_Data UV 0 ;
	setAttr ".hfd" -type "dataPolyComponent" Index_Data Face 0 ;
	setAttr ".dfgi" 4903;
	setAttr ".vcs" 2;
	setAttr ".ai_translator" -type "string" "polymesh";
createNode transform -n "grp_mesh_helmet" -p "grp_mesh_armor";
	rename -uid "2F7C095C-46C5-7038-67D0-3CB787B34B5F";
createNode transform -n "mesh_helmet" -p "grp_mesh_helmet";
	rename -uid "37DC8FB9-4CFA-C47A-5762-7A8D47C72B01";
	setAttr -l on ".tx";
	setAttr -l on ".ty";
	setAttr -l on ".tz";
	setAttr -l on ".rx";
	setAttr -l on ".ry";
	setAttr -l on ".rz";
	setAttr -l on ".sx";
	setAttr -l on ".sy";
	setAttr -l on ".sz";
	setAttr ".rp" -type "double3" 0 28 0 ;
	setAttr ".sp" -type "double3" 0 28 0 ;
createNode mesh -n "mesh_helmetShape" -p "mesh_helmet";
	rename -uid "C913787D-4B38-D694-0F36-2E98B5860BF4";
	setAttr -k off ".v";
	setAttr -s 4 ".iog[0].og";
	setAttr ".vir" yes;
	setAttr ".vif" yes;
	setAttr ".pv" -type "double2" 0.0625 1.03125 ;
	setAttr ".uvst[0].uvsn" -type "string" "map1";
	setAttr ".cuvs" -type "string" "map1";
	setAttr ".dcc" -type "string" "Ambient+Diffuse";
	setAttr ".covm[0]"  0 1 1;
	setAttr ".cdvm[0]"  0 1 1;
	setAttr ".vcs" 2;
	setAttr ".ai_opaque" no;
	setAttr ".ai_translator" -type "string" "polymesh";
createNode mesh -n "mesh_helmetShapeOrig" -p "mesh_helmet";
	rename -uid "8BEA6542-4431-F275-3B4C-698B79C107E3";
	setAttr -k off ".v";
	setAttr ".io" yes;
	setAttr ".vir" yes;
	setAttr ".vif" yes;
	setAttr ".uvst[0].uvsn" -type "string" "map1";
	setAttr -s 24 ".uvst[0].uvsp[0:23]" -type "float2" 0.03125 0.46875 0
		 0.46875 0 0.4375 0.03125 0.4375 0.09375 0.46875 0.0625 0.46875 0.0625 0.4375 0.09375
		 0.4375 0.03125 0.46875 0.0625 0.46875 0.0625 0.5 0.03125 0.5 0.0625 0.5 0.09375 0.5
		 0.09375 0.46875 0.0625 0.46875 0.125 0.46875 0.09375 0.46875 0.09375 0.4375 0.125
		 0.4375 0.0625 0.46875 0.03125 0.46875 0.03125 0.4375 0.0625 0.4375;
	setAttr ".cuvs" -type "string" "map1";
	setAttr ".dcc" -type "string" "Ambient+Diffuse";
	setAttr ".covm[0]"  0 1 1;
	setAttr ".cdvm[0]"  0 1 1;
	setAttr -s 8 ".pt[0:7]" -type "float3"  -4.9281349 30.678137 -4.9281363 
		-4.9281349 30.678137 4.9281363 -4.9281349 21.821863 -4.9281363 -4.9281349 21.821863 
		4.9281363 4.9281349 30.678137 4.9281363 4.9281349 30.678137 -4.9281363 4.9281349 
		21.821863 4.9281363 4.9281349 21.821863 -4.9281363;
	setAttr -s 8 ".vt[0:7]"  0.25 2 0.25 0.25 2 -0.25 0.25 1.5 0.25 0.25 1.5 -0.25
		 -0.25 2 -0.25 -0.25 2 0.25 -0.25 1.5 -0.25 -0.25 1.5 0.25;
	setAttr -s 12 ".ed[0:11]"  0 2 0 1 0 0 2 3 0 3 1 0 4 6 0 5 4 0 6 7 0
		 7 5 0 1 4 0 5 0 0 2 7 0 6 3 0;
	setAttr -s 24 ".n[0:23]" -type "float3"  -0.053440083 0 -6.5445227e-18
		 -0.053440083 0 -6.5445227e-18 -0.053440083 0 -6.5445227e-18 -0.053440083 0 -6.5445227e-18
		 0.053440083 0 6.5445227e-18 0.053440083 0 6.5445227e-18 0.053440083 0 6.5445227e-18
		 0.053440083 0 6.5445227e-18 0 0.053440083 0 0 0.053440083 0 0 0.053440083 0 0 0.053440083
		 0 0 -0.053440083 0 0 -0.053440083 0 0 -0.053440083 0 0 -0.053440083 0 6.5445227e-18
		 0 -0.053440083 6.5445227e-18 0 -0.053440083 6.5445227e-18 0 -0.053440083 6.5445227e-18
		 0 -0.053440083 -6.5445227e-18 0 0.053440083 -6.5445227e-18 0 0.053440083 -6.5445227e-18
		 0 0.053440083 -6.5445227e-18 0 0.053440083;
	setAttr -s 6 -ch 24 ".fc[0:5]" -type "polyFaces" 
		f 4 1 0 2 3
		mu 0 4 0 1 2 3
		f 4 5 4 6 7
		mu 0 4 4 5 6 7
		f 4 8 -6 9 -2
		mu 0 4 8 9 10 11
		f 4 10 -7 11 -3
		mu 0 4 12 13 14 15
		f 4 -10 -8 -11 -1
		mu 0 4 16 17 18 19
		f 4 -9 -4 -12 -5
		mu 0 4 20 21 22 23;
	setAttr ".cd" -type "dataPolyComponent" Index_Data Edge 0 ;
	setAttr ".cvd" -type "dataPolyComponent" Index_Data Vertex 0 ;
	setAttr ".pd[0]" -type "dataPolyComponent" Index_Data UV 0 ;
	setAttr ".hfd" -type "dataPolyComponent" Index_Data Face 0 ;
	setAttr ".ai_translator" -type "string" "polymesh";
createNode transform -n "grp_mesh_boots" -p "grp_mesh_armor";
	rename -uid "75A78806-4358-B1FB-0E76-69AF3B929FF7";
createNode transform -n "mesh_boots_l" -p "grp_mesh_boots";
	rename -uid "8A5EEE53-4E91-303C-F092-2CBB3E4CAB94";
	setAttr -l on ".tx";
	setAttr -l on ".ty";
	setAttr -l on ".tz";
	setAttr -l on ".rx";
	setAttr -l on ".ry";
	setAttr -l on ".rz";
	setAttr -l on ".sx";
	setAttr -l on ".sy";
	setAttr -l on ".sz";
	setAttr ".rp" -type "double3" 2 6 -2.4492935982947094e-16 ;
	setAttr ".sp" -type "double3" 2 6 -2.4492935982947094e-16 ;
createNode mesh -n "mesh_boots_lShape" -p "mesh_boots_l";
	rename -uid "70C96328-482B-B787-EF5C-2796DF6A56AF";
	setAttr -k off ".v";
	setAttr -s 4 ".iog[0].og";
	setAttr ".vir" yes;
	setAttr ".vif" yes;
	setAttr ".pv" -type "double2" 0.46875 0.96875 ;
	setAttr ".uvst[0].uvsn" -type "string" "map1";
	setAttr ".cuvs" -type "string" "map1";
	setAttr ".dcc" -type "string" "Ambient+Diffuse";
	setAttr ".covm[0]"  0 1 1;
	setAttr ".cdvm[0]"  0 1 1;
	setAttr ".dfgi" 4903;
	setAttr ".dr" 1;
	setAttr ".vcs" 2;
	setAttr ".ai_opaque" no;
	setAttr ".ai_translator" -type "string" "polymesh";
createNode mesh -n "mesh_boots_lShapeOrig" -p "mesh_boots_l";
	rename -uid "269443BF-40AA-DD52-7BC0-20B1CE4BE442";
	setAttr -k off ".v";
	setAttr ".io" yes;
	setAttr ".vir" yes;
	setAttr ".vif" yes;
	setAttr ".pv" -type "double2" 0 0.125 ;
	setAttr ".uvst[0].uvsn" -type "string" "map1";
	setAttr -s 24 ".uvst[0].uvsp[0:23]" -type "float2" 0.0625 0.6875 0 0.6875
		 0 0.5 0.0625 0.5 0.1875 0.6875 0.125 0.6875 0.125 0.5 0.1875 0.5 0.0625 0.6875 0.125
		 0.6875 0.125 0.75 0.0625 0.75 0.125 0.75 0.1875 0.75 0.1875 0.6875 0.125 0.6875 0.25
		 0.6875 0.1875 0.6875 0.1875 0.5 0.25 0.5 0.125 0.6875 0.0625 0.6875 0.0625 0.5 0.125
		 0.5;
	setAttr ".cuvs" -type "string" "map1";
	setAttr ".dcc" -type "string" "Ambient+Diffuse";
	setAttr ".covm[0]"  0 1 1;
	setAttr ".cdvm[0]"  0 1 1;
	setAttr -s 8 ".vt[0:7]"  -3.95000005 12 -2 -3.95000005 12 2 -3.95000005 -2.9605948e-16 -2
		 -3.95000005 -2.9605948e-16 2 0.050000053 12 2 0.050000053 12 -2 0.050000053 -2.9605948e-16 2
		 0.050000053 -2.9605948e-16 -2;
	setAttr -s 12 ".ed[0:11]"  0 2 0 1 0 0 2 3 0 3 1 0 4 6 0 5 4 0 6 7 0
		 7 5 0 1 4 0 5 0 0 2 7 0 6 3 0;
	setAttr -s 24 ".n[0:23]" -type "float3"  -0.0625 0 -7.6540428e-18 -0.0625
		 0 -7.6540428e-18 -0.0625 0 -7.6540428e-18 -0.0625 0 -7.6540428e-18 0.0625 0 7.6540428e-18
		 0.0625 0 7.6540428e-18 0.0625 0 7.6540428e-18 0.0625 0 7.6540428e-18 0 0.0625 0 0
		 0.0625 0 0 0.0625 0 0 0.0625 0 0 -0.0625 0 0 -0.0625 0 0 -0.0625 0 0 -0.0625 0 7.6540428e-18
		 0 -0.0625 7.6540428e-18 0 -0.0625 7.6540428e-18 0 -0.0625 7.6540428e-18 0 -0.0625
		 -7.6540428e-18 0 0.0625 -7.6540428e-18 0 0.0625 -7.6540428e-18 0 0.0625 -7.6540428e-18
		 0 0.0625;
	setAttr -s 6 -ch 24 ".fc[0:5]" -type "polyFaces" 
		f 4 1 0 2 3
		mu 0 4 0 1 2 3
		f 4 5 4 6 7
		mu 0 4 4 5 6 7
		f 4 8 -6 9 -2
		mu 0 4 8 9 10 11
		f 4 10 -7 11 -3
		mu 0 4 12 13 14 15
		f 4 -10 -8 -11 -1
		mu 0 4 16 17 18 19
		f 4 -9 -4 -12 -5
		mu 0 4 20 21 22 23;
	setAttr ".cd" -type "dataPolyComponent" Index_Data Edge 0 ;
	setAttr ".cvd" -type "dataPolyComponent" Index_Data Vertex 0 ;
	setAttr ".pd[0]" -type "dataPolyComponent" Index_Data UV 0 ;
	setAttr ".hfd" -type "dataPolyComponent" Index_Data Face 0 ;
	setAttr ".dfgi" 4903;
	setAttr ".ai_translator" -type "string" "polymesh";
createNode mesh -n "mesh_boots_lShapeOrig1" -p "mesh_boots_l";
	rename -uid "A3B0EC54-4729-EDC5-2196-3BAC8AA38429";
	setAttr -k off ".v";
	setAttr ".io" yes;
	setAttr ".vir" yes;
	setAttr ".vif" yes;
	setAttr ".pv" -type "double2" 0 0.125 ;
	setAttr ".uvst[0].uvsn" -type "string" "map1";
	setAttr -s 24 ".uvst[0].uvsp[0:23]" -type "float2" 0.453125 0.359375
		 0.4375 0.359375 0.4375 0.3125 0.453125 0.3125 0.484375 0.359375 0.46875 0.359375
		 0.46875 0.3125 0.484375 0.3125 0.453125 0.359375 0.46875 0.359375 0.46875 0.375 0.453125
		 0.375 0.46875 0.375 0.484375 0.375 0.484375 0.359375 0.46875 0.359375 0.5 0.359375
		 0.484375 0.359375 0.484375 0.3125 0.5 0.3125 0.46875 0.359375 0.453125 0.359375 0.453125
		 0.3125 0.46875 0.3125;
	setAttr ".cuvs" -type "string" "map1";
	setAttr ".dcc" -type "string" "Ambient+Diffuse";
	setAttr ".covm[0]"  0 1 1;
	setAttr ".cdvm[0]"  0 1 1;
	setAttr -s 8 ".pt[0:7]" -type "float3"  3.1250877 0.74696845 -0.82491088 
		3.1250877 0.74696845 0.82491088 3.1250877 -0.74696845 -0.82491088 3.1250877 -0.74696845 
		0.82491088 4.77491 0.74696845 0.82491088 4.77491 0.74696845 -0.82491088 4.77491 -0.74696845 
		0.82491088 4.77491 -0.74696845 -0.82491088;
	setAttr -s 8 ".vt[0:7]"  -3.95000005 12 -2 -3.95000005 12 2 -3.95000005 -2.9606022e-16 -2
		 -3.95000005 -2.9606022e-16 2 0.050000053 12 2 0.050000053 12 -2 0.050000053 -2.9606022e-16 2
		 0.050000053 -2.9606022e-16 -2;
	setAttr -s 12 ".ed[0:11]"  0 2 0 1 0 0 2 3 0 3 1 0 4 6 0 5 4 0 6 7 0
		 7 5 0 1 4 0 5 0 0 2 7 0 6 3 0;
	setAttr -s 24 ".n[0:23]" -type "float3"  -0.70798695 0 -8.6703398e-17
		 -0.70798695 0 -8.6703398e-17 -0.70798695 0 -8.6703398e-17 -0.70798695 0 -8.6703398e-17
		 0.70798695 0 8.6703398e-17 0.70798695 0 8.6703398e-17 0.70798695 0 8.6703398e-17
		 0.70798695 0 8.6703398e-17 0 0.88928837 0 0 0.88928837 0 0 0.88928837 0 0 0.88928837
		 0 0 -0.88928837 0 0 -0.88928837 0 0 -0.88928837 0 0 -0.88928837 0 8.6703398e-17 0
		 -0.70798695 8.6703398e-17 0 -0.70798695 8.6703398e-17 0 -0.70798695 8.6703398e-17
		 0 -0.70798695 -8.6703398e-17 0 0.70798695 -8.6703398e-17 0 0.70798695 -8.6703398e-17
		 0 0.70798695 -8.6703398e-17 0 0.70798695;
	setAttr -s 6 -ch 24 ".fc[0:5]" -type "polyFaces" 
		f 4 1 0 2 3
		mu 0 4 0 1 2 3
		f 4 5 4 6 7
		mu 0 4 4 5 6 7
		f 4 8 -6 9 -2
		mu 0 4 8 9 10 11
		f 4 10 -7 11 -3
		mu 0 4 12 13 14 15
		f 4 -10 -8 -11 -1
		mu 0 4 16 17 18 19
		f 4 -9 -4 -12 -5
		mu 0 4 20 21 22 23;
	setAttr ".cd" -type "dataPolyComponent" Index_Data Edge 0 ;
	setAttr ".cvd" -type "dataPolyComponent" Index_Data Vertex 0 ;
	setAttr ".pd[0]" -type "dataPolyComponent" Index_Data UV 0 ;
	setAttr ".hfd" -type "dataPolyComponent" Index_Data Face 0 ;
	setAttr ".dfgi" 4903;
	setAttr ".vcs" 2;
	setAttr ".ai_translator" -type "string" "polymesh";
createNode transform -n "mesh_boots_r" -p "grp_mesh_boots";
	rename -uid "761486E6-4F33-C448-F4D9-62801C9726D5";
	setAttr -l on ".tx";
	setAttr -l on ".ty";
	setAttr -l on ".tz";
	setAttr -l on ".rx";
	setAttr -l on ".ry";
	setAttr -l on ".rz";
	setAttr -l on ".sx";
	setAttr -l on ".sy";
	setAttr -l on ".sz";
	setAttr ".rp" -type "double3" -2 6 -2.4492935982947094e-16 ;
	setAttr ".sp" -type "double3" -2 6 -2.4492935982947094e-16 ;
createNode mesh -n "mesh_boots_rShape" -p "mesh_boots_r";
	rename -uid "F3691376-4360-56E0-ABE4-59B28A8D451E";
	setAttr -k off ".v";
	setAttr -s 4 ".iog[0].og";
	setAttr ".vir" yes;
	setAttr ".vif" yes;
	setAttr ".pv" -type "double2" 0.46875 0.96875 ;
	setAttr ".uvst[0].uvsn" -type "string" "map1";
	setAttr ".cuvs" -type "string" "map1";
	setAttr ".dcc" -type "string" "Ambient+Diffuse";
	setAttr ".covm[0]"  0 1 1;
	setAttr ".cdvm[0]"  0 1 1;
	setAttr ".dfgi" 4903;
	setAttr ".vcs" 2;
	setAttr ".ai_opaque" no;
	setAttr ".ai_translator" -type "string" "polymesh";
createNode mesh -n "mesh_boots_rShapeOrig" -p "mesh_boots_r";
	rename -uid "27A4F893-4C44-2733-B0C3-F0B47A177EB4";
	setAttr -k off ".v";
	setAttr ".io" yes;
	setAttr ".vir" yes;
	setAttr ".vif" yes;
	setAttr ".pv" -type "double2" 0 0.125 ;
	setAttr ".uvst[0].uvsn" -type "string" "map1";
	setAttr -s 24 ".uvst[0].uvsp[0:23]" -type "float2" 0.0625 0.6875 0 0.6875
		 0 0.5 0.0625 0.5 0.1875 0.6875 0.125 0.6875 0.125 0.5 0.1875 0.5 0.0625 0.6875 0.125
		 0.6875 0.125 0.75 0.0625 0.75 0.125 0.75 0.1875 0.75 0.1875 0.6875 0.125 0.6875 0.25
		 0.6875 0.1875 0.6875 0.1875 0.5 0.25 0.5 0.125 0.6875 0.0625 0.6875 0.0625 0.5 0.125
		 0.5;
	setAttr ".cuvs" -type "string" "map1";
	setAttr ".dcc" -type "string" "Ambient+Diffuse";
	setAttr ".covm[0]"  0 1 1;
	setAttr ".cdvm[0]"  0 1 1;
	setAttr -s 8 ".vt[0:7]"  -3.95000005 12 -2 -3.95000005 12 2 -3.95000005 -2.9605948e-16 -2
		 -3.95000005 -2.9605948e-16 2 0.050000053 12 2 0.050000053 12 -2 0.050000053 -2.9605948e-16 2
		 0.050000053 -2.9605948e-16 -2;
	setAttr -s 12 ".ed[0:11]"  0 2 0 1 0 0 2 3 0 3 1 0 4 6 0 5 4 0 6 7 0
		 7 5 0 1 4 0 5 0 0 2 7 0 6 3 0;
	setAttr -s 24 ".n[0:23]" -type "float3"  -0.0625 0 -7.6540428e-18 -0.0625
		 0 -7.6540428e-18 -0.0625 0 -7.6540428e-18 -0.0625 0 -7.6540428e-18 0.0625 0 7.6540428e-18
		 0.0625 0 7.6540428e-18 0.0625 0 7.6540428e-18 0.0625 0 7.6540428e-18 0 0.0625 0 0
		 0.0625 0 0 0.0625 0 0 0.0625 0 0 -0.0625 0 0 -0.0625 0 0 -0.0625 0 0 -0.0625 0 7.6540428e-18
		 0 -0.0625 7.6540428e-18 0 -0.0625 7.6540428e-18 0 -0.0625 7.6540428e-18 0 -0.0625
		 -7.6540428e-18 0 0.0625 -7.6540428e-18 0 0.0625 -7.6540428e-18 0 0.0625 -7.6540428e-18
		 0 0.0625;
	setAttr -s 6 -ch 24 ".fc[0:5]" -type "polyFaces" 
		f 4 1 0 2 3
		mu 0 4 0 1 2 3
		f 4 5 4 6 7
		mu 0 4 4 5 6 7
		f 4 8 -6 9 -2
		mu 0 4 8 9 10 11
		f 4 10 -7 11 -3
		mu 0 4 12 13 14 15
		f 4 -10 -8 -11 -1
		mu 0 4 16 17 18 19
		f 4 -9 -4 -12 -5
		mu 0 4 20 21 22 23;
	setAttr ".cd" -type "dataPolyComponent" Index_Data Edge 0 ;
	setAttr ".cvd" -type "dataPolyComponent" Index_Data Vertex 0 ;
	setAttr ".pd[0]" -type "dataPolyComponent" Index_Data UV 0 ;
	setAttr ".hfd" -type "dataPolyComponent" Index_Data Face 0 ;
	setAttr ".dfgi" 4903;
	setAttr ".ai_translator" -type "string" "polymesh";
createNode mesh -n "mesh_boots_rShapeOrig1" -p "mesh_boots_r";
	rename -uid "A7963D5C-4396-3C62-AAC1-1D81E1DF820A";
	setAttr -k off ".v";
	setAttr ".io" yes;
	setAttr ".vir" yes;
	setAttr ".vif" yes;
	setAttr ".pv" -type "double2" 0 0.125 ;
	setAttr ".uvst[0].uvsn" -type "string" "map1";
	setAttr -s 24 ".uvst[0].uvsp[0:23]" -type "float2" 0.453125 0.359375
		 0.4375 0.359375 0.4375 0.3125 0.453125 0.3125 0.484375 0.359375 0.46875 0.359375
		 0.46875 0.3125 0.484375 0.3125 0.453125 0.359375 0.46875 0.359375 0.46875 0.375 0.453125
		 0.375 0.46875 0.375 0.484375 0.375 0.484375 0.359375 0.46875 0.359375 0.5 0.359375
		 0.484375 0.359375 0.484375 0.3125 0.5 0.3125 0.46875 0.359375 0.453125 0.359375 0.453125
		 0.3125 0.46875 0.3125;
	setAttr ".cuvs" -type "string" "map1";
	setAttr ".dcc" -type "string" "Ambient+Diffuse";
	setAttr ".covm[0]"  0 1 1;
	setAttr ".cdvm[0]"  0 1 1;
	setAttr -s 8 ".pt[0:7]" -type "float3"  -0.87491226 0.74696845 -0.82491088 
		-0.87491226 0.74696845 0.82491088 -0.87491226 -0.74696845 -0.82491088 -0.87491226 
		-0.74696845 0.82491088 0.77490997 0.74696845 0.82491088 0.77490997 0.74696845 -0.82491088 
		0.77490997 -0.74696845 0.82491088 0.77490997 -0.74696845 -0.82491088;
	setAttr -s 8 ".vt[0:7]"  -3.95000005 12 -2 -3.95000005 12 2 -3.95000005 -2.9606022e-16 -2
		 -3.95000005 -2.9606022e-16 2 0.050000053 12 2 0.050000053 12 -2 0.050000053 -2.9606022e-16 2
		 0.050000053 -2.9606022e-16 -2;
	setAttr -s 12 ".ed[0:11]"  0 2 0 1 0 0 2 3 0 3 1 0 4 6 0 5 4 0 6 7 0
		 7 5 0 1 4 0 5 0 0 2 7 0 6 3 0;
	setAttr -s 24 ".n[0:23]" -type "float3"  -0.70798695 0 -8.6703398e-17
		 -0.70798695 0 -8.6703398e-17 -0.70798695 0 -8.6703398e-17 -0.70798695 0 -8.6703398e-17
		 0.70798695 0 8.6703398e-17 0.70798695 0 8.6703398e-17 0.70798695 0 8.6703398e-17
		 0.70798695 0 8.6703398e-17 0 0.88928837 0 0 0.88928837 0 0 0.88928837 0 0 0.88928837
		 0 0 -0.88928837 0 0 -0.88928837 0 0 -0.88928837 0 0 -0.88928837 0 8.6703398e-17 0
		 -0.70798695 8.6703398e-17 0 -0.70798695 8.6703398e-17 0 -0.70798695 8.6703398e-17
		 0 -0.70798695 -8.6703398e-17 0 0.70798695 -8.6703398e-17 0 0.70798695 -8.6703398e-17
		 0 0.70798695 -8.6703398e-17 0 0.70798695;
	setAttr -s 6 -ch 24 ".fc[0:5]" -type "polyFaces" 
		f 4 1 0 2 3
		mu 0 4 0 1 2 3
		f 4 5 4 6 7
		mu 0 4 4 5 6 7
		f 4 8 -6 9 -2
		mu 0 4 8 9 10 11
		f 4 10 -7 11 -3
		mu 0 4 12 13 14 15
		f 4 -10 -8 -11 -1
		mu 0 4 16 17 18 19
		f 4 -9 -4 -12 -5
		mu 0 4 20 21 22 23;
	setAttr ".cd" -type "dataPolyComponent" Index_Data Edge 0 ;
	setAttr ".cvd" -type "dataPolyComponent" Index_Data Vertex 0 ;
	setAttr ".pd[0]" -type "dataPolyComponent" Index_Data UV 0 ;
	setAttr ".hfd" -type "dataPolyComponent" Index_Data Face 0 ;
	setAttr ".dfgi" 4903;
	setAttr ".vcs" 2;
	setAttr ".ai_translator" -type "string" "polymesh";
createNode transform -n "mesh_head_plain" -p "grp_meshes";
	rename -uid "488488CC-4619-CD4E-D124-E4A4928F7437";
	setAttr -l on ".tx";
	setAttr -l on ".ty";
	setAttr -l on ".tz";
	setAttr -l on ".rx";
	setAttr -l on ".ry";
	setAttr -l on ".rz";
	setAttr -l on ".sx";
	setAttr -l on ".sy";
	setAttr -l on ".sz";
	setAttr ".rp" -type "double3" 0 28 0 ;
	setAttr ".sp" -type "double3" 0 28 0 ;
createNode mesh -n "mesh_head_plainShape" -p "mesh_head_plain";
	rename -uid "A472C5AF-4259-BF2B-E67F-28B400E3D386";
	setAttr -k off ".v";
	setAttr -s 8 ".iog[0].og";
	setAttr ".vir" yes;
	setAttr ".vif" yes;
	setAttr ".pv" -type "double2" 0.062500000398099331 0.8125 ;
	setAttr ".uvst[0].uvsn" -type "string" "map1";
	setAttr ".cuvs" -type "string" "map1";
	setAttr ".dcc" -type "string" "Ambient+Diffuse";
	setAttr ".covm[0]"  0 1 1;
	setAttr ".cdvm[0]"  0 1 1;
	setAttr ".dr" 1;
	setAttr ".vcs" 2;
	setAttr ".ai_opaque" no;
	setAttr ".ai_translator" -type "string" "polymesh";
createNode mesh -n "mesh_head_plainShapeOrig" -p "mesh_head_plain";
	rename -uid "DBFA2231-4BFF-64A1-281D-5AB5C0E86F4D";
	setAttr -k off ".v";
	setAttr ".io" yes;
	setAttr ".vir" yes;
	setAttr ".vif" yes;
	setAttr ".uvst[0].uvsn" -type "string" "map1";
	setAttr -s 24 ".uvst[0].uvsp[0:23]" -type "float2" 0.625 0.875 0.5 0.875
		 0.5 0.75 0.625 0.75 0.875 0.875 0.75 0.875 0.75 0.75 0.875 0.75 0.625 0.875 0.75
		 0.875 0.75 1 0.625 1 0.75 1 0.875 1 0.875 0.875 0.75 0.875 1 0.875 0.875 0.875 0.875
		 0.75 1 0.75 0.75 0.875 0.625 0.875 0.625 0.75 0.75 0.75;
	setAttr ".cuvs" -type "string" "map1";
	setAttr ".dcc" -type "string" "Ambient+Diffuse";
	setAttr ".covm[0]"  0 1 1;
	setAttr ".cdvm[0]"  0 1 1;
	setAttr -s 8 ".pt[0:7]" -type "float3"  -4.4461703 30.196171 -4.4461713 
		-4.4461703 30.196171 4.4461713 -4.4461703 22.303829 -4.4461713 -4.4461703 22.303829 
		4.4461713 4.4461722 30.196171 4.4461713 4.4461722 30.196171 -4.4461713 4.4461722 
		22.303829 4.4461713 4.4461722 22.303829 -4.4461713;
	setAttr -s 8 ".vt[0:7]"  0.25 2 0.25 0.25 2 -0.25 0.25 1.5 0.25 0.25 1.5 -0.25
		 -0.25 2 -0.25 -0.25 2 0.25 -0.25 1.5 -0.25 -0.25 1.5 0.25;
	setAttr -s 12 ".ed[0:11]"  0 2 0 1 0 0 2 3 0 3 1 0 4 6 0 5 4 0 6 7 0
		 7 5 0 1 4 0 5 0 0 2 7 0 6 3 0;
	setAttr -s 24 ".n[0:23]" -type "float3"  -0.059578121 0 -7.2962161e-18
		 -0.059578121 0 -7.2962161e-18 -0.059578121 0 -7.2962161e-18 -0.059578121 0 -7.2962161e-18
		 0.059578121 0 7.2962161e-18 0.059578121 0 7.2962161e-18 0.059578121 0 7.2962161e-18
		 0.059578121 0 7.2962161e-18 0 0.059578121 0 0 0.059578121 0 0 0.059578121 0 0 0.059578121
		 0 0 -0.059578121 0 0 -0.059578121 0 0 -0.059578121 0 0 -0.059578121 0 7.2962161e-18
		 0 -0.059578121 7.2962161e-18 0 -0.059578121 7.2962161e-18 0 -0.059578121 7.2962161e-18
		 0 -0.059578121 -7.2962161e-18 0 0.059578121 -7.2962161e-18 0 0.059578121 -7.2962161e-18
		 0 0.059578121 -7.2962161e-18 0 0.059578121;
	setAttr -s 6 -ch 24 ".fc[0:5]" -type "polyFaces" 
		f 4 1 0 2 3
		mu 0 4 0 1 2 3
		f 4 5 4 6 7
		mu 0 4 4 5 6 7
		f 4 8 -6 9 -2
		mu 0 4 8 9 10 11
		f 4 10 -7 11 -3
		mu 0 4 12 13 14 15
		f 4 -10 -8 -11 -1
		mu 0 4 16 17 18 19
		f 4 -9 -4 -12 -5
		mu 0 4 20 21 22 23;
	setAttr ".cd" -type "dataPolyComponent" Index_Data Edge 0 ;
	setAttr ".cvd" -type "dataPolyComponent" Index_Data Vertex 0 ;
	setAttr ".pd[0]" -type "dataPolyComponent" Index_Data UV 0 ;
	setAttr ".hfd" -type "dataPolyComponent" Index_Data Face 0 ;
	setAttr ".ai_translator" -type "string" "polymesh";
createNode mesh -n "mesh_head_plainShapeOrig1" -p "mesh_head_plain";
	rename -uid "A5089291-4442-F03C-4651-FCAC5C5F582B";
	setAttr -k off ".v";
	setAttr ".io" yes;
	setAttr ".vir" yes;
	setAttr ".vif" yes;
	setAttr ".uvst[0].uvsn" -type "string" "map1";
	setAttr -s 24 ".uvst[0].uvsp[0:23]" -type "float2" 0.125 0.875 0 0.875
		 0 0.75 0.125 0.75 0.375 0.875 0.25 0.875 0.25 0.75 0.375 0.75 0.125 0.875 0.25 0.875
		 0.25 1 0.125 1 0.25 1 0.375 1 0.375 0.875 0.25 0.875 0.5 0.875 0.375 0.875 0.375
		 0.75 0.5 0.75 0.25 0.875 0.125 0.875 0.125 0.75 0.25 0.75;
	setAttr ".cuvs" -type "string" "map1";
	setAttr ".dcc" -type "string" "Ambient+Diffuse";
	setAttr ".covm[0]"  0 1 1;
	setAttr ".cdvm[0]"  0 1 1;
	setAttr -s 8 ".pt[0:7]" -type "float3"  0.19612315 -0.19612317 0.19612315 
		0.19612315 -0.19612317 -0.19612315 0.19612315 0.19612315 0.19612315 0.19612315 0.19612315 
		-0.19612315 -0.19612315 -0.19612317 -0.19612315 -0.19612315 -0.19612317 0.19612315 
		-0.19612315 0.19612315 -0.19612315 -0.19612315 0.19612315 0.19612315;
	setAttr -s 8 ".vt[0:7]"  -4.19617033 32.19617081 -4.19617128 -4.19617033 32.19617081 4.19617128
		 -4.19617033 23.80382919 -4.19617128 -4.19617033 23.80382919 4.19617128 4.19617224 32.19617081 4.19617128
		 4.19617224 32.19617081 -4.19617128 4.19617224 23.80382919 4.19617128 4.19617224 23.80382919 -4.19617128;
	setAttr -s 12 ".ed[0:11]"  0 2 0 1 0 0 2 3 0 3 1 0 4 6 0 5 4 0 6 7 0
		 7 5 0 1 4 0 5 0 0 2 7 0 6 3 0;
	setAttr -s 24 ".n[0:23]" -type "float3"  -1 0 -1.2246469e-16 -1 0 -1.2246469e-16
		 -1 0 -1.2246469e-16 -1 0 -1.2246469e-16 1 0 1.2246469e-16 1 0 1.2246469e-16 1 0 1.2246469e-16
		 1 0 1.2246469e-16 0 1 0 0 1 0 0 1 0 0 1 0 0 -1 0 0 -1 0 0 -1 0 0 -1 0 1.2246469e-16
		 0 -1 1.2246469e-16 0 -1 1.2246469e-16 0 -1 1.2246469e-16 0 -1 -1.2246469e-16 0 1
		 -1.2246469e-16 0 1 -1.2246469e-16 0 1 -1.2246469e-16 0 1;
	setAttr -s 6 -ch 24 ".fc[0:5]" -type "polyFaces" 
		f 4 1 0 2 3
		mu 0 4 0 1 2 3
		f 4 5 4 6 7
		mu 0 4 4 5 6 7
		f 4 8 -6 9 -2
		mu 0 4 8 9 10 11
		f 4 10 -7 11 -3
		mu 0 4 12 13 14 15
		f 4 -10 -8 -11 -1
		mu 0 4 16 17 18 19
		f 4 -9 -4 -12 -5
		mu 0 4 20 21 22 23;
	setAttr ".cd" -type "dataPolyComponent" Index_Data Edge 0 ;
	setAttr ".cvd" -type "dataPolyComponent" Index_Data Vertex 0 ;
	setAttr ".pd[0]" -type "dataPolyComponent" Index_Data UV 0 ;
	setAttr ".hfd" -type "dataPolyComponent" Index_Data Face 0 ;
	setAttr ".vcs" 2;
	setAttr ".ai_translator" -type "string" "polymesh";
createNode transform -n "grp_mesh_arm_steve" -p "grp_meshes";
	rename -uid "AED7D668-4807-2FE8-41E2-5ABD02DB332B";
createNode transform -n "mesh_arm_r" -p "grp_mesh_arm_steve";
	rename -uid "244C3231-4599-EB5C-870F-C9AC19F73965";
	setAttr ".t" -type "double3" 0 2.4868995751603507e-14 0 ;
	setAttr -l on ".tx";
	setAttr -l on ".ty";
	setAttr -l on ".tz";
	setAttr -l on ".rx";
	setAttr -l on ".ry";
	setAttr -l on ".rz";
	setAttr -l on ".sx";
	setAttr -l on ".sy";
	setAttr -l on ".sz";
	setAttr ".rp" -type "double3" -9.9999999999999893 21.999999999999975 -1.2246467991473546e-15 ;
	setAttr ".sp" -type "double3" -9.9999999999999893 21.999999999999975 -1.2246467991473546e-15 ;
createNode mesh -n "mesh_arm_rShape" -p "mesh_arm_r";
	rename -uid "B265D6CD-4712-A80D-FD11-B0864989934D";
	setAttr -k off ".v";
	setAttr -s 4 ".iog[0].og";
	setAttr ".vir" yes;
	setAttr ".vif" yes;
	setAttr ".uvst[0].uvsn" -type "string" "map1";
	setAttr ".cuvs" -type "string" "map1";
	setAttr ".dcc" -type "string" "Ambient+Diffuse";
	setAttr ".covm[0]"  0 1 1;
	setAttr ".cdvm[0]"  0 1 1;
	setAttr ".vcs" 2;
	setAttr ".ai_translator" -type "string" "polymesh";
createNode mesh -n "mesh_arm_rShapeOrig" -p "mesh_arm_r";
	rename -uid "6FC04CAB-47B4-80EC-F536-C4A6E108F849";
	setAttr -k off ".v";
	setAttr ".io" yes;
	setAttr ".vir" yes;
	setAttr ".vif" yes;
	setAttr ".uvst[0].uvsn" -type "string" "map1";
	setAttr -s 24 ".uvst[0].uvsp[0:23]" -type "float2" 0.6875 0.6875 0.625
		 0.6875 0.625 0.5 0.6875 0.5 0.8125 0.6875 0.75 0.6875 0.75 0.5 0.8125 0.5 0.6875
		 0.6875 0.75 0.6875 0.75 0.75 0.6875 0.75 0.75 0.75 0.8125 0.75 0.8125 0.6875 0.75
		 0.6875 0.875 0.6875 0.8125 0.6875 0.8125 0.5 0.875 0.5 0.75 0.6875 0.6875 0.6875
		 0.6875 0.5 0.75 0.5;
	setAttr ".cuvs" -type "string" "map1";
	setAttr ".dcc" -type "string" "Ambient+Diffuse";
	setAttr ".covm[0]"  0 1 1;
	setAttr ".cdvm[0]"  0 1 1;
	setAttr -s 8 ".pt[0:7]" -type "float3"  -4.5 22.5 -2.125 -4.5 22.5 
		2.125 -16.5 23.25 -2.125 -16.5 23.25 2.125 -4.25 18.5 2.125 -4.25 18.5 -2.125 -16.25 
		19.25 2.125 -16.25 19.25 -2.125;
	setAttr -s 8 ".vt[0:7]"  0.5 1.5 0.125 0.5 1.5 -0.125 0.5 0.75 0.125
		 0.5 0.75 -0.125 0.25 1.5 -0.125 0.25 1.5 0.125 0.25 0.75 -0.125 0.25 0.75 0.125;
	setAttr -s 12 ".ed[0:11]"  0 2 0 1 0 0 2 3 0 3 1 0 4 6 0 5 4 0 6 7 0
		 7 5 0 1 4 0 5 0 0 2 7 0 6 3 0;
	setAttr -s 24 ".n[0:23]" -type "float3"  2.7755576e-17 0.0625 3.3990778e-33
		 2.7755576e-17 0.0625 3.3990778e-33 2.7755576e-17 0.0625 3.3990778e-33 2.7755576e-17
		 0.0625 3.3990778e-33 -2.7755576e-17 -0.0625 -3.3990778e-33 -2.7755576e-17 -0.0625
		 -3.3990778e-33 -2.7755576e-17 -0.0625 -3.3990778e-33 -2.7755576e-17 -0.0625 -3.3990778e-33
		 0.0625 -2.7755576e-17 7.6540428e-18 0.0625 -2.7755576e-17 7.6540428e-18 0.0625 -2.7755576e-17
		 7.6540428e-18 0.0625 -2.7755576e-17 7.6540428e-18 -0.0625 2.7755576e-17 -7.6540428e-18
		 -0.0625 2.7755576e-17 -7.6540428e-18 -0.0625 2.7755576e-17 -7.6540428e-18 -0.0625
		 2.7755576e-17 -7.6540428e-18 7.6540428e-18 0 -0.0625 7.6540428e-18 0 -0.0625 7.6540428e-18
		 0 -0.0625 7.6540428e-18 0 -0.0625 -7.6540428e-18 0 0.0625 -7.6540428e-18 0 0.0625
		 -7.6540428e-18 0 0.0625 -7.6540428e-18 0 0.0625;
	setAttr -s 6 -ch 24 ".fc[0:5]" -type "polyFaces" 
		f 4 1 0 2 3
		mu 0 4 0 1 2 3
		f 4 5 4 6 7
		mu 0 4 4 5 6 7
		f 4 8 -6 9 -2
		mu 0 4 8 9 10 11
		f 4 10 -7 11 -3
		mu 0 4 12 13 14 15
		f 4 -10 -8 -11 -1
		mu 0 4 16 17 18 19
		f 4 -9 -4 -12 -5
		mu 0 4 20 21 22 23;
	setAttr ".cd" -type "dataPolyComponent" Index_Data Edge 0 ;
	setAttr ".cvd" -type "dataPolyComponent" Index_Data Vertex 0 ;
	setAttr ".pd[0]" -type "dataPolyComponent" Index_Data UV 0 ;
	setAttr ".hfd" -type "dataPolyComponent" Index_Data Face 0 ;
	setAttr ".ai_translator" -type "string" "polymesh";
createNode transform -n "mesh_arm_l" -p "grp_mesh_arm_steve";
	rename -uid "585EAB70-49FE-1943-31A1-C9932489F683";
	setAttr -l on ".tx";
	setAttr -l on ".ty";
	setAttr -l on ".tz";
	setAttr -l on ".rx";
	setAttr -l on ".ry";
	setAttr -l on ".rz";
	setAttr -l on ".sx";
	setAttr -l on ".sy";
	setAttr -l on ".sz";
	setAttr ".rp" -type "double3" 9.9999999999999893 21.999999999999975 1.2246467991473546e-15 ;
	setAttr ".sp" -type "double3" 9.9999999999999893 21.999999999999975 1.2246467991473546e-15 ;
createNode mesh -n "mesh_arm_lShape" -p "mesh_arm_l";
	rename -uid "810DCD48-4BEB-C3D6-2910-8890B81BEA5A";
	setAttr -k off ".v";
	setAttr -s 4 ".iog[0].og";
	setAttr ".vir" yes;
	setAttr ".vif" yes;
	setAttr ".pv" -type "double2" 0.5 0.5 ;
	setAttr ".uvst[0].uvsn" -type "string" "map1";
	setAttr ".cuvs" -type "string" "map1";
	setAttr ".dcc" -type "string" "Ambient+Diffuse";
	setAttr ".covm[0]"  0 1 1;
	setAttr ".cdvm[0]"  0 1 1;
	setAttr ".vcs" 2;
	setAttr ".ai_translator" -type "string" "polymesh";
createNode mesh -n "mesh_arm_lShapeOrig" -p "mesh_arm_l";
	rename -uid "96E73781-4586-236C-351A-2CBC1BAD2D98";
	setAttr -k off ".v";
	setAttr ".io" yes;
	setAttr ".vir" yes;
	setAttr ".vif" yes;
	setAttr ".uvst[0].uvsn" -type "string" "map1";
	setAttr -s 24 ".uvst[0].uvsp[0:23]" -type "float2" 0.5625 0.1875 0.5
		 0.1875 0.5 0 0.5625 0 0.6875 0.1875 0.625 0.1875 0.625 0 0.6875 0 0.5625 0.1875 0.625
		 0.1875 0.625 0.25 0.5625 0.25 0.625 0.25 0.6875 0.25 0.6875 0.1875 0.625 0.1875 0.75
		 0.1875 0.6875 0.1875 0.6875 0 0.75 0 0.625 0.1875 0.5625 0.1875 0.5625 0 0.625 0;
	setAttr ".cuvs" -type "string" "map1";
	setAttr ".dcc" -type "string" "Ambient+Diffuse";
	setAttr ".covm[0]"  0 1 1;
	setAttr ".cdvm[0]"  0 1 1;
	setAttr -s 8 ".pt[0:7]" -type "float3"  4.25 18.5 -2.125 4.25 18.5 
		2.125 16.25 19.25 -2.125 16.25 19.25 2.125 4.5 22.5 2.125 4.5 22.5 -2.125 16.5 23.25 
		2.125 16.5 23.25 -2.125;
	setAttr -s 8 ".vt[0:7]"  -0.25 1.5 0.125 -0.25 1.5 -0.125 -0.25 0.75 0.125
		 -0.25 0.75 -0.125 -0.5 1.5 -0.125 -0.5 1.5 0.125 -0.5 0.75 -0.125 -0.5 0.75 0.125;
	setAttr -s 12 ".ed[0:11]"  0 2 0 1 0 0 2 3 0 3 1 0 4 6 0 5 4 0 6 7 0
		 7 5 0 1 4 0 5 0 0 2 7 0 6 3 0;
	setAttr -s 24 ".n[0:23]" -type "float3"  2.7755576e-17 -0.0625 3.3990778e-33
		 2.7755576e-17 -0.0625 3.3990778e-33 2.7755576e-17 -0.0625 3.3990778e-33 2.7755576e-17
		 -0.0625 3.3990778e-33 -2.7755576e-17 0.0625 -3.3990778e-33 -2.7755576e-17 0.0625
		 -3.3990778e-33 -2.7755576e-17 0.0625 -3.3990778e-33 -2.7755576e-17 0.0625 -3.3990778e-33
		 -0.0625 -2.7755576e-17 -7.6540428e-18 -0.0625 -2.7755576e-17 -7.6540428e-18 -0.0625
		 -2.7755576e-17 -7.6540428e-18 -0.0625 -2.7755576e-17 -7.6540428e-18 0.0625 2.7755576e-17
		 7.6540428e-18 0.0625 2.7755576e-17 7.6540428e-18 0.0625 2.7755576e-17 7.6540428e-18
		 0.0625 2.7755576e-17 7.6540428e-18 7.6540428e-18 0 -0.0625 7.6540428e-18 0 -0.0625
		 7.6540428e-18 0 -0.0625 7.6540428e-18 0 -0.0625 -7.6540428e-18 0 0.0625 -7.6540428e-18
		 0 0.0625 -7.6540428e-18 0 0.0625 -7.6540428e-18 0 0.0625;
	setAttr -s 6 -ch 24 ".fc[0:5]" -type "polyFaces" 
		f 4 1 0 2 3
		mu 0 4 0 1 2 3
		f 4 5 4 6 7
		mu 0 4 4 5 6 7
		f 4 8 -6 9 -2
		mu 0 4 8 9 10 11
		f 4 10 -7 11 -3
		mu 0 4 12 13 14 15
		f 4 -10 -8 -11 -1
		mu 0 4 16 17 18 19
		f 4 -9 -4 -12 -5
		mu 0 4 20 21 22 23;
	setAttr ".cd" -type "dataPolyComponent" Index_Data Edge 0 ;
	setAttr ".cvd" -type "dataPolyComponent" Index_Data Vertex 0 ;
	setAttr ".pd[0]" -type "dataPolyComponent" Index_Data UV 0 ;
	setAttr ".hfd" -type "dataPolyComponent" Index_Data Face 0 ;
	setAttr ".ai_translator" -type "string" "polymesh";
createNode transform -n "mesh_arm_second_l" -p "grp_mesh_arm_steve";
	rename -uid "C8E759FA-4CDF-F15A-C72B-AA8C78EDD33A";
	setAttr -l on ".tx";
	setAttr -l on ".ty";
	setAttr -l on ".tz";
	setAttr -l on ".rx";
	setAttr -l on ".ry";
	setAttr -l on ".rz";
	setAttr -l on ".sx";
	setAttr -l on ".sy";
	setAttr -l on ".sz";
	setAttr ".rp" -type "double3" 6 21.999999999999975 1.2246467991473546e-15 ;
	setAttr ".sp" -type "double3" 6 21.999999999999975 1.2246467991473546e-15 ;
createNode mesh -n "mesh_arm_second_lShape" -p "mesh_arm_second_l";
	rename -uid "46AA0115-42CC-A86E-D5C1-12AF0300B22C";
	setAttr -k off ".v";
	setAttr -s 4 ".iog[0].og";
	setAttr ".lodv" no;
	setAttr ".vir" yes;
	setAttr ".vif" yes;
	setAttr ".pv" -type "double2" 0.875 0.125 ;
	setAttr ".uvst[0].uvsn" -type "string" "map1";
	setAttr ".cuvs" -type "string" "map1";
	setAttr ".dcc" -type "string" "Ambient+Diffuse";
	setAttr ".covm[0]"  0 1 1;
	setAttr ".cdvm[0]"  0 1 1;
	setAttr ".vcs" 2;
	setAttr ".ai_opaque" no;
	setAttr ".ai_translator" -type "string" "polymesh";
createNode mesh -n "mesh_arm_second_lShapeOrig" -p "mesh_arm_second_l";
	rename -uid "C5EB047F-426B-A3AF-6EAA-478BF0045EAF";
	setAttr -k off ".v";
	setAttr ".io" yes;
	setAttr ".vir" yes;
	setAttr ".vif" yes;
	setAttr ".uvst[0].uvsn" -type "string" "map1";
	setAttr -s 24 ".uvst[0].uvsp[0:23]" -type "float2" 0.5625 0.1875 0.5
		 0.1875 0.5 0 0.5625 0 0.6875 0.1875 0.625 0.1875 0.625 0 0.6875 0 0.5625 0.1875 0.625
		 0.1875 0.625 0.25 0.5625 0.25 0.625 0.25 0.6875 0.25 0.6875 0.1875 0.625 0.1875 0.75
		 0.1875 0.6875 0.1875 0.6875 0 0.75 0 0.625 0.1875 0.5625 0.1875 0.5625 0 0.625 0;
	setAttr ".cuvs" -type "string" "map1";
	setAttr ".dcc" -type "string" "Ambient+Diffuse";
	setAttr ".covm[0]"  0 1 1;
	setAttr ".cdvm[0]"  0 1 1;
	setAttr -s 8 ".pt[0:7]" -type "float3"  4.25 18.5 -2.125 4.25 18.5 
		2.125 16.25 19.25 -2.125 16.25 19.25 2.125 4.5 22.5 2.125 4.5 22.5 -2.125 16.5 23.25 
		2.125 16.5 23.25 -2.125;
	setAttr -s 8 ".vt[0:7]"  -0.25 1.5 0.125 -0.25 1.5 -0.125 -0.25 0.75 0.125
		 -0.25 0.75 -0.125 -0.5 1.5 -0.125 -0.5 1.5 0.125 -0.5 0.75 -0.125 -0.5 0.75 0.125;
	setAttr -s 12 ".ed[0:11]"  0 2 0 1 0 0 2 3 0 3 1 0 4 6 0 5 4 0 6 7 0
		 7 5 0 1 4 0 5 0 0 2 7 0 6 3 0;
	setAttr -s 24 ".n[0:23]" -type "float3"  2.7755576e-17 -0.0625 3.3990778e-33
		 2.7755576e-17 -0.0625 3.3990778e-33 2.7755576e-17 -0.0625 3.3990778e-33 2.7755576e-17
		 -0.0625 3.3990778e-33 -2.7755576e-17 0.0625 -3.3990778e-33 -2.7755576e-17 0.0625
		 -3.3990778e-33 -2.7755576e-17 0.0625 -3.3990778e-33 -2.7755576e-17 0.0625 -3.3990778e-33
		 -0.0625 -2.7755576e-17 -7.6540428e-18 -0.0625 -2.7755576e-17 -7.6540428e-18 -0.0625
		 -2.7755576e-17 -7.6540428e-18 -0.0625 -2.7755576e-17 -7.6540428e-18 0.0625 2.7755576e-17
		 7.6540428e-18 0.0625 2.7755576e-17 7.6540428e-18 0.0625 2.7755576e-17 7.6540428e-18
		 0.0625 2.7755576e-17 7.6540428e-18 7.6540428e-18 0 -0.0625 7.6540428e-18 0 -0.0625
		 7.6540428e-18 0 -0.0625 7.6540428e-18 0 -0.0625 -7.6540428e-18 0 0.0625 -7.6540428e-18
		 0 0.0625 -7.6540428e-18 0 0.0625 -7.6540428e-18 0 0.0625;
	setAttr -s 6 -ch 24 ".fc[0:5]" -type "polyFaces" 
		f 4 1 0 2 3
		mu 0 4 0 1 2 3
		f 4 5 4 6 7
		mu 0 4 4 5 6 7
		f 4 8 -6 9 -2
		mu 0 4 8 9 10 11
		f 4 10 -7 11 -3
		mu 0 4 12 13 14 15
		f 4 -10 -8 -11 -1
		mu 0 4 16 17 18 19
		f 4 -9 -4 -12 -5
		mu 0 4 20 21 22 23;
	setAttr ".cd" -type "dataPolyComponent" Index_Data Edge 0 ;
	setAttr ".cvd" -type "dataPolyComponent" Index_Data Vertex 0 ;
	setAttr ".pd[0]" -type "dataPolyComponent" Index_Data UV 0 ;
	setAttr ".hfd" -type "dataPolyComponent" Index_Data Face 0 ;
	setAttr ".ai_translator" -type "string" "polymesh";
createNode mesh -n "mesh_arm_second_lShapeOrig1" -p "mesh_arm_second_l";
	rename -uid "ED15A1F8-433B-FE73-CE70-92B98AC29B98";
	setAttr -k off ".v";
	setAttr ".io" yes;
	setAttr ".vir" yes;
	setAttr ".vif" yes;
	setAttr ".uvst[0].uvsn" -type "string" "map1";
	setAttr -s 24 ".uvst[0].uvsp[0:23]" -type "float2" 0.8125 0.1875 0.75
		 0.1875 0.75 0 0.8125 0 0.9375 0.1875 0.875 0.1875 0.875 0 0.9375 0 0.8125 0.1875
		 0.875 0.1875 0.875 0.25 0.8125 0.25 0.875 0.25 0.9375 0.25 0.9375 0.1875 0.875 0.1875
		 1 0.1875 0.9375 0.1875 0.9375 0 1 0 0.875 0.1875 0.8125 0.1875 0.8125 0 0.875 0;
	setAttr ".cuvs" -type "string" "map1";
	setAttr ".dcc" -type "string" "Ambient+Diffuse";
	setAttr ".covm[0]"  0 1 1;
	setAttr ".cdvm[0]"  0 1 1;
	setAttr -s 8 ".pt[0:7]" -type "float3"  -0.075577542 -0.33963808 
		-0.33963808 -0.075577542 -0.33963808 0.33963808 0.37787932 -0.33963808 -0.33963808 
		0.37787932 -0.33963808 0.33963808 -0.075577542 0.33963808 0.33963808 -0.075577542 
		0.33963808 -0.33963808 0.37787932 0.33963808 0.33963808 0.37787932 0.33963808 -0.33963808;
	setAttr -s 8 ".vt[0:7]"  4 20 -2 4 20 2 16 20 -2 16 20 2 4 24 2 4 24 -2
		 16 24 2 16 24 -2;
	setAttr -s 12 ".ed[0:11]"  0 2 0 1 0 0 2 3 0 3 1 0 4 6 0 5 4 0 6 7 0
		 7 5 0 1 4 0 5 0 0 2 7 0 6 3 0;
	setAttr -s 24 ".n[0:23]" -type "float3"  4.2791903e-16 -0.85483313 4.6490308e-32
		 4.2791903e-16 -0.85483313 4.6490308e-32 4.2791903e-16 -0.85483313 4.6490308e-32 4.2791903e-16
		 -0.85483313 4.6490308e-32 -4.2791903e-16 0.85483313 -4.6490308e-32 -4.2791903e-16
		 0.85483313 -4.6490308e-32 -4.2791903e-16 0.85483313 -4.6490308e-32 -4.2791903e-16
		 0.85483313 -4.6490308e-32 -0.963588 -3.7962217e-16 -1.0468687e-16 -0.963588 -3.7962217e-16
		 -1.0468687e-16 -0.963588 -3.7962217e-16 -1.0468687e-16 -0.963588 -3.7962217e-16 -1.0468687e-16
		 0.963588 3.7962217e-16 1.0468687e-16 0.963588 3.7962217e-16 1.0468687e-16 0.963588
		 3.7962217e-16 1.0468687e-16 0.963588 3.7962217e-16 1.0468687e-16 1.1800551e-16 0
		 -0.85483313 1.1800551e-16 0 -0.85483313 1.1800551e-16 0 -0.85483313 1.1800551e-16
		 0 -0.85483313 -1.1800551e-16 0 0.85483313 -1.1800551e-16 0 0.85483313 -1.1800551e-16
		 0 0.85483313 -1.1800551e-16 0 0.85483313;
	setAttr -s 6 -ch 24 ".fc[0:5]" -type "polyFaces" 
		f 4 1 0 2 3
		mu 0 4 0 1 2 3
		f 4 5 4 6 7
		mu 0 4 4 5 6 7
		f 4 8 -6 9 -2
		mu 0 4 8 9 10 11
		f 4 10 -7 11 -3
		mu 0 4 12 13 14 15
		f 4 -10 -8 -11 -1
		mu 0 4 16 17 18 19
		f 4 -9 -4 -12 -5
		mu 0 4 20 21 22 23;
	setAttr ".cd" -type "dataPolyComponent" Index_Data Edge 0 ;
	setAttr ".cvd" -type "dataPolyComponent" Index_Data Vertex 0 ;
	setAttr ".pd[0]" -type "dataPolyComponent" Index_Data UV 0 ;
	setAttr ".hfd" -type "dataPolyComponent" Index_Data Face 0 ;
	setAttr ".vcs" 2;
	setAttr ".ai_translator" -type "string" "polymesh";
createNode transform -n "mesh_arm_second_r" -p "grp_mesh_arm_steve";
	rename -uid "C4F4E996-4406-6394-3E9A-5B87A80E9677";
	setAttr -l on ".tx";
	setAttr -l on ".ty";
	setAttr -l on ".tz";
	setAttr -l on ".rx";
	setAttr -l on ".ry";
	setAttr -l on ".rz";
	setAttr -l on ".sx";
	setAttr -l on ".sy";
	setAttr -l on ".sz";
	setAttr ".rp" -type "double3" -6 21.999999999999975 -1.2246467991473546e-15 ;
	setAttr ".sp" -type "double3" -6 21.999999999999975 -1.2246467991473546e-15 ;
createNode mesh -n "mesh_arm_second_rShape" -p "mesh_arm_second_r";
	rename -uid "549D3FD0-4534-46C4-6489-3DA0911D97D7";
	setAttr -k off ".v";
	setAttr -s 4 ".iog[0].og";
	setAttr ".lodv" no;
	setAttr ".vir" yes;
	setAttr ".vif" yes;
	setAttr ".pv" -type "double2" 0.4375 0.375 ;
	setAttr ".uvst[0].uvsn" -type "string" "map1";
	setAttr ".cuvs" -type "string" "map1";
	setAttr ".dcc" -type "string" "Ambient+Diffuse";
	setAttr ".covm[0]"  0 1 1;
	setAttr ".cdvm[0]"  0 1 1;
	setAttr ".vcs" 2;
	setAttr ".ai_opaque" no;
	setAttr ".ai_translator" -type "string" "polymesh";
createNode mesh -n "mesh_arm_second_rShapeOrig" -p "mesh_arm_second_r";
	rename -uid "D41FA524-4B67-C07E-4349-A3AE1BFC8A92";
	setAttr -k off ".v";
	setAttr ".io" yes;
	setAttr ".vir" yes;
	setAttr ".vif" yes;
	setAttr ".uvst[0].uvsn" -type "string" "map1";
	setAttr -s 24 ".uvst[0].uvsp[0:23]" -type "float2" 0.6875 0.4375 0.625
		 0.4375 0.625 0.25 0.6875 0.25 0.8125 0.4375 0.75 0.4375 0.75 0.25 0.8125 0.25 0.6875
		 0.4375 0.75 0.4375 0.75 0.5 0.6875 0.5 0.75 0.5 0.8125 0.5 0.8125 0.4375 0.75 0.4375
		 0.875 0.4375 0.8125 0.4375 0.8125 0.25 0.875 0.25 0.75 0.4375 0.6875 0.4375 0.6875
		 0.25 0.75 0.25;
	setAttr ".cuvs" -type "string" "map1";
	setAttr ".dcc" -type "string" "Ambient+Diffuse";
	setAttr ".covm[0]"  0 1 1;
	setAttr ".cdvm[0]"  0 1 1;
	setAttr -s 8 ".pt[0:7]" -type "float3"  -4.4244242 22.839638 -2.464638 
		-4.4244242 22.839638 2.464638 -16.87788 23.589638 -2.464638 -16.87788 23.589638 2.464638 
		-4.1744242 18.160362 2.464638 -4.1744242 18.160362 -2.464638 -16.62788 18.910362 
		2.464638 -16.62788 18.910362 -2.464638;
	setAttr -s 8 ".vt[0:7]"  0.5 1.5 0.125 0.5 1.5 -0.125 0.5 0.75 0.125
		 0.5 0.75 -0.125 0.25 1.5 -0.125 0.25 1.5 0.125 0.25 0.75 -0.125 0.25 0.75 0.125;
	setAttr -s 12 ".ed[0:11]"  0 2 0 1 0 0 2 3 0 3 1 0 4 6 0 5 4 0 6 7 0
		 7 5 0 1 4 0 5 0 0 2 7 0 6 3 0;
	setAttr -s 24 ".n[0:23]" -type "float3"  2.674494e-17 0.05342707 2.9056442e-33
		 2.674494e-17 0.05342707 2.9056442e-33 2.674494e-17 0.05342707 2.9056442e-33 2.674494e-17
		 0.05342707 2.9056442e-33 -2.674494e-17 -0.05342707 -2.9056442e-33 -2.674494e-17 -0.05342707
		 -2.9056442e-33 -2.674494e-17 -0.05342707 -2.9056442e-33 -2.674494e-17 -0.05342707
		 -2.9056442e-33 0.06022425 -2.3726385e-17 6.5429292e-18 0.06022425 -2.3726385e-17
		 6.5429292e-18 0.06022425 -2.3726385e-17 6.5429292e-18 0.06022425 -2.3726385e-17 6.5429292e-18
		 -0.06022425 2.3726385e-17 -6.5429292e-18 -0.06022425 2.3726385e-17 -6.5429292e-18
		 -0.06022425 2.3726385e-17 -6.5429292e-18 -0.06022425 2.3726385e-17 -6.5429292e-18
		 7.3753442e-18 0 -0.05342707 7.3753442e-18 0 -0.05342707 7.3753442e-18 0 -0.05342707
		 7.3753442e-18 0 -0.05342707 -7.3753442e-18 0 0.05342707 -7.3753442e-18 0 0.05342707
		 -7.3753442e-18 0 0.05342707 -7.3753442e-18 0 0.05342707;
	setAttr -s 6 -ch 24 ".fc[0:5]" -type "polyFaces" 
		f 4 1 0 2 3
		mu 0 4 0 1 2 3
		f 4 5 4 6 7
		mu 0 4 4 5 6 7
		f 4 8 -6 9 -2
		mu 0 4 8 9 10 11
		f 4 10 -7 11 -3
		mu 0 4 12 13 14 15
		f 4 -10 -8 -11 -1
		mu 0 4 16 17 18 19
		f 4 -9 -4 -12 -5
		mu 0 4 20 21 22 23;
	setAttr ".cd" -type "dataPolyComponent" Index_Data Edge 0 ;
	setAttr ".cvd" -type "dataPolyComponent" Index_Data Vertex 0 ;
	setAttr ".pd[0]" -type "dataPolyComponent" Index_Data UV 0 ;
	setAttr ".hfd" -type "dataPolyComponent" Index_Data Face 0 ;
	setAttr ".ai_translator" -type "string" "polymesh";
createNode transform -n "grp_mesh_arm_alex" -p "grp_meshes";
	rename -uid "BA964967-44C3-EFDC-ACDF-CAB8D6042579";
createNode transform -n "mesh_arm_alex_second_l" -p "grp_mesh_arm_alex";
	rename -uid "6540234D-4D60-CC8E-4D73-BCA719672CAD";
	setAttr -l on ".tx";
	setAttr -l on ".ty";
	setAttr -l on ".tz";
	setAttr -l on ".rx";
	setAttr -l on ".ry";
	setAttr -l on ".rz";
	setAttr -l on ".sx";
	setAttr -l on ".sy";
	setAttr -l on ".sz";
	setAttr ".rp" -type "double3" 6 20.5 1.2246467991473546e-15 ;
	setAttr ".sp" -type "double3" 6 20.5 1.2246467991473546e-15 ;
createNode mesh -n "mesh_arm_alex_second_lShape" -p "mesh_arm_alex_second_l";
	rename -uid "17D48B89-47E4-F012-0C15-709D411CCEBB";
	setAttr -k off ".v";
	setAttr -s 4 ".iog[0].og";
	setAttr ".lodv" no;
	setAttr ".vir" yes;
	setAttr ".vif" yes;
	setAttr ".pv" -type "double2" 0.9140625 0.09375 ;
	setAttr ".uvst[0].uvsn" -type "string" "map1";
	setAttr ".cuvs" -type "string" "map1";
	setAttr ".dcc" -type "string" "Ambient+Diffuse";
	setAttr ".covm[0]"  0 1 1;
	setAttr ".cdvm[0]"  0 1 1;
	setAttr ".vcs" 2;
	setAttr ".ai_opaque" no;
	setAttr ".ai_translator" -type "string" "polymesh";
createNode mesh -n "mesh_arm_alex_second_lShapeOrig" -p "mesh_arm_alex_second_l";
	rename -uid "AE39BBE3-4F60-385F-92C3-38A2EFB5F37E";
	setAttr -k off ".v";
	setAttr ".io" yes;
	setAttr ".vir" yes;
	setAttr ".vif" yes;
	setAttr ".uvst[0].uvsn" -type "string" "map1";
	setAttr -s 24 ".uvst[0].uvsp[0:23]" -type "float2" 0.5625 0.1875 0.5
		 0.1875 0.5 0 0.5625 0 0.6875 0.1875 0.625 0.1875 0.625 0 0.6875 0 0.5625 0.1875 0.625
		 0.1875 0.625 0.25 0.5625 0.25 0.625 0.25 0.6875 0.25 0.6875 0.1875 0.625 0.1875 0.75
		 0.1875 0.6875 0.1875 0.6875 0 0.75 0 0.625 0.1875 0.5625 0.1875 0.5625 0 0.625 0;
	setAttr ".cuvs" -type "string" "map1";
	setAttr ".dcc" -type "string" "Ambient+Diffuse";
	setAttr ".covm[0]"  0 1 1;
	setAttr ".cdvm[0]"  0 1 1;
	setAttr -s 8 ".pt[0:7]" -type "float3"  4.25 18.5 -2.125 4.25 18.5 
		2.125 16.25 19.25 -2.125 16.25 19.25 2.125 4.5 22.5 2.125 4.5 22.5 -2.125 16.5 23.25 
		2.125 16.5 23.25 -2.125;
	setAttr -s 8 ".vt[0:7]"  -0.25 1.5 0.125 -0.25 1.5 -0.125 -0.25 0.75 0.125
		 -0.25 0.75 -0.125 -0.5 1.5 -0.125 -0.5 1.5 0.125 -0.5 0.75 -0.125 -0.5 0.75 0.125;
	setAttr -s 12 ".ed[0:11]"  0 2 0 1 0 0 2 3 0 3 1 0 4 6 0 5 4 0 6 7 0
		 7 5 0 1 4 0 5 0 0 2 7 0 6 3 0;
	setAttr -s 24 ".n[0:23]" -type "float3"  2.7755576e-17 -0.0625 3.3990778e-33
		 2.7755576e-17 -0.0625 3.3990778e-33 2.7755576e-17 -0.0625 3.3990778e-33 2.7755576e-17
		 -0.0625 3.3990778e-33 -2.7755576e-17 0.0625 -3.3990778e-33 -2.7755576e-17 0.0625
		 -3.3990778e-33 -2.7755576e-17 0.0625 -3.3990778e-33 -2.7755576e-17 0.0625 -3.3990778e-33
		 -0.0625 -2.7755576e-17 -7.6540428e-18 -0.0625 -2.7755576e-17 -7.6540428e-18 -0.0625
		 -2.7755576e-17 -7.6540428e-18 -0.0625 -2.7755576e-17 -7.6540428e-18 0.0625 2.7755576e-17
		 7.6540428e-18 0.0625 2.7755576e-17 7.6540428e-18 0.0625 2.7755576e-17 7.6540428e-18
		 0.0625 2.7755576e-17 7.6540428e-18 7.6540428e-18 0 -0.0625 7.6540428e-18 0 -0.0625
		 7.6540428e-18 0 -0.0625 7.6540428e-18 0 -0.0625 -7.6540428e-18 0 0.0625 -7.6540428e-18
		 0 0.0625 -7.6540428e-18 0 0.0625 -7.6540428e-18 0 0.0625;
	setAttr -s 6 -ch 24 ".fc[0:5]" -type "polyFaces" 
		f 4 1 0 2 3
		mu 0 4 0 1 2 3
		f 4 5 4 6 7
		mu 0 4 4 5 6 7
		f 4 8 -6 9 -2
		mu 0 4 8 9 10 11
		f 4 10 -7 11 -3
		mu 0 4 12 13 14 15
		f 4 -10 -8 -11 -1
		mu 0 4 16 17 18 19
		f 4 -9 -4 -12 -5
		mu 0 4 20 21 22 23;
	setAttr ".cd" -type "dataPolyComponent" Index_Data Edge 0 ;
	setAttr ".cvd" -type "dataPolyComponent" Index_Data Vertex 0 ;
	setAttr ".pd[0]" -type "dataPolyComponent" Index_Data UV 0 ;
	setAttr ".hfd" -type "dataPolyComponent" Index_Data Face 0 ;
	setAttr ".ai_translator" -type "string" "polymesh";
createNode mesh -n "mesh_arm_alex_second_lShapeOrig1" -p "mesh_arm_alex_second_l";
	rename -uid "EF9ADFB3-4D63-CE81-1E27-C2B09076212C";
	setAttr -k off ".v";
	setAttr ".io" yes;
	setAttr ".vir" yes;
	setAttr ".vif" yes;
	setAttr ".uvst[0].uvsn" -type "string" "map1";
	setAttr -s 24 ".uvst[0].uvsp[0:23]" -type "float2" 0.8125 0.1875 0.75
		 0.1875 0.75 0 0.8125 0 0.9375 0.1875 0.875 0.1875 0.875 0 0.9375 0 0.8125 0.1875
		 0.875 0.1875 0.875 0.25 0.8125 0.25 0.875 0.25 0.9375 0.25 0.9375 0.1875 0.875 0.1875
		 1 0.1875 0.9375 0.1875 0.9375 0 1 0 0.875 0.1875 0.8125 0.1875 0.8125 0 0.875 0;
	setAttr ".cuvs" -type "string" "map1";
	setAttr ".dcc" -type "string" "Ambient+Diffuse";
	setAttr ".covm[0]"  0 1 1;
	setAttr ".cdvm[0]"  0 1 1;
	setAttr -s 8 ".pt[0:7]" -type "float3"  -0.075577542 -0.33963808 
		-0.33963808 -0.075577542 -0.33963808 0.33963808 0.37787932 -0.33963808 -0.33963808 
		0.37787932 -0.33963808 0.33963808 -0.075577542 0.33963808 0.33963808 -0.075577542 
		0.33963808 -0.33963808 0.37787932 0.33963808 0.33963808 0.37787932 0.33963808 -0.33963808;
	setAttr -s 8 ".vt[0:7]"  4 20 -2 4 20 2 16 20 -2 16 20 2 4 24 2 4 24 -2
		 16 24 2 16 24 -2;
	setAttr -s 12 ".ed[0:11]"  0 2 0 1 0 0 2 3 0 3 1 0 4 6 0 5 4 0 6 7 0
		 7 5 0 1 4 0 5 0 0 2 7 0 6 3 0;
	setAttr -s 24 ".n[0:23]" -type "float3"  4.2791903e-16 -0.85483313 4.6490308e-32
		 4.2791903e-16 -0.85483313 4.6490308e-32 4.2791903e-16 -0.85483313 4.6490308e-32 4.2791903e-16
		 -0.85483313 4.6490308e-32 -4.2791903e-16 0.85483313 -4.6490308e-32 -4.2791903e-16
		 0.85483313 -4.6490308e-32 -4.2791903e-16 0.85483313 -4.6490308e-32 -4.2791903e-16
		 0.85483313 -4.6490308e-32 -0.963588 -3.7962217e-16 -1.0468687e-16 -0.963588 -3.7962217e-16
		 -1.0468687e-16 -0.963588 -3.7962217e-16 -1.0468687e-16 -0.963588 -3.7962217e-16 -1.0468687e-16
		 0.963588 3.7962217e-16 1.0468687e-16 0.963588 3.7962217e-16 1.0468687e-16 0.963588
		 3.7962217e-16 1.0468687e-16 0.963588 3.7962217e-16 1.0468687e-16 1.1800551e-16 0
		 -0.85483313 1.1800551e-16 0 -0.85483313 1.1800551e-16 0 -0.85483313 1.1800551e-16
		 0 -0.85483313 -1.1800551e-16 0 0.85483313 -1.1800551e-16 0 0.85483313 -1.1800551e-16
		 0 0.85483313 -1.1800551e-16 0 0.85483313;
	setAttr -s 6 -ch 24 ".fc[0:5]" -type "polyFaces" 
		f 4 1 0 2 3
		mu 0 4 0 1 2 3
		f 4 5 4 6 7
		mu 0 4 4 5 6 7
		f 4 8 -6 9 -2
		mu 0 4 8 9 10 11
		f 4 10 -7 11 -3
		mu 0 4 12 13 14 15
		f 4 -10 -8 -11 -1
		mu 0 4 16 17 18 19
		f 4 -9 -4 -12 -5
		mu 0 4 20 21 22 23;
	setAttr ".cd" -type "dataPolyComponent" Index_Data Edge 0 ;
	setAttr ".cvd" -type "dataPolyComponent" Index_Data Vertex 0 ;
	setAttr ".pd[0]" -type "dataPolyComponent" Index_Data UV 0 ;
	setAttr ".hfd" -type "dataPolyComponent" Index_Data Face 0 ;
	setAttr ".vcs" 2;
	setAttr ".ai_translator" -type "string" "polymesh";
createNode mesh -n "mesh_arm_alex_second_lShapeOrig2" -p "mesh_arm_alex_second_l";
	rename -uid "1EE1B0FA-4729-C665-AB1F-1F8E88260C11";
	setAttr -k off ".v";
	setAttr ".io" yes;
	setAttr ".vir" yes;
	setAttr ".vif" yes;
	setAttr ".uvst[0].uvsn" -type "string" "map1";
	setAttr -s 24 ".uvst[0].uvsp[0:23]" -type "float2" 0.8125 0.1875 0.75
		 0.1875 0.75 0 0.8125 0 0.921875 0.1875 0.859375 0.1875 0.859375 0 0.921875 0 0.8125
		 0.1875 0.859375 0.1875 0.859375 0.25 0.8125 0.25 0.859375 0.25 0.90625 0.25 0.90625
		 0.1875 0.859375 0.1875 0.96875 0.1875 0.921875 0.1875 0.921875 0 0.96875 0 0.859375
		 0.1875 0.8125 0.1875 0.8125 0 0.859375 0;
	setAttr ".cuvs" -type "string" "map1";
	setAttr ".dcc" -type "string" "Ambient+Diffuse";
	setAttr ".covm[0]"  0 1 1;
	setAttr ".cdvm[0]"  0 1 1;
	setAttr -s 8 ".pt[0:7]" -type "float3"  0 0.58490944 0 0 0.58490944 
		0 0 0.58490944 0 0 0.58490944 0 0 -0.58490944 0 0 -0.58490944 0 0 -0.58490944 0 0 
		-0.58490944 0;
	setAttr -s 8 ".vt[0:7]"  3.9244225 19.66036224 -2.33963799 3.9244225 19.66036224 2.33963799
		 16.3778801 19.66036224 -2.33963799 16.3778801 19.66036224 2.33963799 3.9244225 24.33963776 2.33963799
		 3.9244225 24.33963776 -2.33963799 16.3778801 24.33963776 2.33963799 16.3778801 24.33963776 -2.33963799;
	setAttr -s 12 ".ed[0:11]"  0 2 0 1 0 0 2 3 0 3 1 0 4 6 0 5 4 0 6 7 0
		 7 5 0 1 4 0 5 0 0 2 7 0 6 3 0;
	setAttr -s 24 ".n[0:23]" -type "float3"  5.0058779e-16 -1.33333337 5.4385245e-32
		 5.0058779e-16 -1.33333337 5.4385245e-32 5.0058779e-16 -1.33333337 5.4385245e-32 5.0058779e-16
		 -1.33333337 5.4385245e-32 -5.0058779e-16 1.33333337 -5.4385245e-32 -5.0058779e-16
		 1.33333337 -5.4385245e-32 -5.0058779e-16 1.33333337 -5.4385245e-32 -5.0058779e-16
		 1.33333337 -5.4385245e-32 -1 -5.2528976e-16 -1.0864278e-16 -1 -5.2528976e-16 -1.0864278e-16
		 -1 -5.2528976e-16 -1.0864278e-16 -1 -5.2528976e-16 -1.0864278e-16 1 5.2528976e-16
		 1.0864278e-16 1 5.2528976e-16 1.0864278e-16 1 5.2528976e-16 1.0864278e-16 1 5.2528976e-16
		 1.0864278e-16 1.3804508e-16 0 -1 1.3804508e-16 0 -1 1.3804508e-16 0 -1 1.3804508e-16
		 0 -1 -1.3804508e-16 0 1 -1.3804508e-16 0 1 -1.3804508e-16 0 1 -1.3804508e-16 0 1;
	setAttr -s 6 -ch 24 ".fc[0:5]" -type "polyFaces" 
		f 4 1 0 2 3
		mu 0 4 0 1 2 3
		f 4 5 4 6 7
		mu 0 4 4 5 6 7
		f 4 8 -6 9 -2
		mu 0 4 8 9 10 11
		f 4 10 -7 11 -3
		mu 0 4 12 13 14 15
		f 4 -10 -8 -11 -1
		mu 0 4 16 17 18 19
		f 4 -9 -4 -12 -5
		mu 0 4 20 21 22 23;
	setAttr ".cd" -type "dataPolyComponent" Index_Data Edge 0 ;
	setAttr ".cvd" -type "dataPolyComponent" Index_Data Vertex 0 ;
	setAttr ".pd[0]" -type "dataPolyComponent" Index_Data UV 0 ;
	setAttr ".hfd" -type "dataPolyComponent" Index_Data Face 0 ;
	setAttr ".vcs" 2;
	setAttr ".ai_translator" -type "string" "polymesh";
createNode transform -n "mesh_arm_alex_l" -p "grp_mesh_arm_alex";
	rename -uid "F87156E3-4041-EA02-8745-7BBC834A57E6";
	setAttr -l on ".tx";
	setAttr -l on ".ty";
	setAttr -l on ".tz";
	setAttr -l on ".rx";
	setAttr -l on ".ry";
	setAttr -l on ".rz";
	setAttr -l on ".sx";
	setAttr -l on ".sy";
	setAttr -l on ".sz";
	setAttr ".rp" -type "double3" 9.9999999999999893 20.5 1.2246467991473546e-15 ;
	setAttr ".sp" -type "double3" 9.9999999999999893 20.5 1.2246467991473546e-15 ;
createNode mesh -n "mesh_arm_alex_lShape" -p "mesh_arm_alex_l";
	rename -uid "1A9BD2BB-42B4-F94D-AC47-49A3214B87F5";
	setAttr -k off ".v";
	setAttr -s 4 ".iog[0].og";
	setAttr ".vir" yes;
	setAttr ".vif" yes;
	setAttr ".pv" -type "double2" 0.6640625 0.09375 ;
	setAttr ".uvst[0].uvsn" -type "string" "map1";
	setAttr ".cuvs" -type "string" "map1";
	setAttr ".dcc" -type "string" "Ambient+Diffuse";
	setAttr ".covm[0]"  0 1 1;
	setAttr ".cdvm[0]"  0 1 1;
	setAttr ".vcs" 2;
	setAttr ".ai_translator" -type "string" "polymesh";
createNode mesh -n "mesh_arm_alex_lShapeOrig" -p "mesh_arm_alex_l";
	rename -uid "7AED5185-4F17-FE31-1BC6-AEA80B8B4B7D";
	setAttr -k off ".v";
	setAttr ".io" yes;
	setAttr ".vir" yes;
	setAttr ".vif" yes;
	setAttr ".uvst[0].uvsn" -type "string" "map1";
	setAttr -s 24 ".uvst[0].uvsp[0:23]" -type "float2" 0.5625 0.1875 0.5
		 0.1875 0.5 0 0.5625 0 0.6875 0.1875 0.625 0.1875 0.625 0 0.6875 0 0.5625 0.1875 0.625
		 0.1875 0.625 0.25 0.5625 0.25 0.625 0.25 0.6875 0.25 0.6875 0.1875 0.625 0.1875 0.75
		 0.1875 0.6875 0.1875 0.6875 0 0.75 0 0.625 0.1875 0.5625 0.1875 0.5625 0 0.625 0;
	setAttr ".cuvs" -type "string" "map1";
	setAttr ".dcc" -type "string" "Ambient+Diffuse";
	setAttr ".covm[0]"  0 1 1;
	setAttr ".cdvm[0]"  0 1 1;
	setAttr -s 8 ".pt[0:7]" -type "float3"  4.25 18.5 -2.125 4.25 18.5 
		2.125 16.25 19.25 -2.125 16.25 19.25 2.125 4.5 22.5 2.125 4.5 22.5 -2.125 16.5 23.25 
		2.125 16.5 23.25 -2.125;
	setAttr -s 8 ".vt[0:7]"  -0.25 1.5 0.125 -0.25 1.5 -0.125 -0.25 0.75 0.125
		 -0.25 0.75 -0.125 -0.5 1.5 -0.125 -0.5 1.5 0.125 -0.5 0.75 -0.125 -0.5 0.75 0.125;
	setAttr -s 12 ".ed[0:11]"  0 2 0 1 0 0 2 3 0 3 1 0 4 6 0 5 4 0 6 7 0
		 7 5 0 1 4 0 5 0 0 2 7 0 6 3 0;
	setAttr -s 24 ".n[0:23]" -type "float3"  2.7755576e-17 -0.0625 3.3990778e-33
		 2.7755576e-17 -0.0625 3.3990778e-33 2.7755576e-17 -0.0625 3.3990778e-33 2.7755576e-17
		 -0.0625 3.3990778e-33 -2.7755576e-17 0.0625 -3.3990778e-33 -2.7755576e-17 0.0625
		 -3.3990778e-33 -2.7755576e-17 0.0625 -3.3990778e-33 -2.7755576e-17 0.0625 -3.3990778e-33
		 -0.0625 -2.7755576e-17 -7.6540428e-18 -0.0625 -2.7755576e-17 -7.6540428e-18 -0.0625
		 -2.7755576e-17 -7.6540428e-18 -0.0625 -2.7755576e-17 -7.6540428e-18 0.0625 2.7755576e-17
		 7.6540428e-18 0.0625 2.7755576e-17 7.6540428e-18 0.0625 2.7755576e-17 7.6540428e-18
		 0.0625 2.7755576e-17 7.6540428e-18 7.6540428e-18 0 -0.0625 7.6540428e-18 0 -0.0625
		 7.6540428e-18 0 -0.0625 7.6540428e-18 0 -0.0625 -7.6540428e-18 0 0.0625 -7.6540428e-18
		 0 0.0625 -7.6540428e-18 0 0.0625 -7.6540428e-18 0 0.0625;
	setAttr -s 6 -ch 24 ".fc[0:5]" -type "polyFaces" 
		f 4 1 0 2 3
		mu 0 4 0 1 2 3
		f 4 5 4 6 7
		mu 0 4 4 5 6 7
		f 4 8 -6 9 -2
		mu 0 4 8 9 10 11
		f 4 10 -7 11 -3
		mu 0 4 12 13 14 15
		f 4 -10 -8 -11 -1
		mu 0 4 16 17 18 19
		f 4 -9 -4 -12 -5
		mu 0 4 20 21 22 23;
	setAttr ".cd" -type "dataPolyComponent" Index_Data Edge 0 ;
	setAttr ".cvd" -type "dataPolyComponent" Index_Data Vertex 0 ;
	setAttr ".pd[0]" -type "dataPolyComponent" Index_Data UV 0 ;
	setAttr ".hfd" -type "dataPolyComponent" Index_Data Face 0 ;
	setAttr ".ai_translator" -type "string" "polymesh";
createNode mesh -n "mesh_arm_alex_lShapeOrig1" -p "mesh_arm_alex_l";
	rename -uid "39275979-409E-C2E5-CD71-68A231F2F9E0";
	setAttr -k off ".v";
	setAttr ".io" yes;
	setAttr ".vir" yes;
	setAttr ".vif" yes;
	setAttr ".uvst[0].uvsn" -type "string" "map1";
	setAttr -s 24 ".uvst[0].uvsp[0:23]" -type "float2" 0.5625 0.1875 0.5
		 0.1875 0.5 0 0.5625 0 0.671875 0.1875 0.609375 0.1875 0.609375 0 0.671875 0 0.5625
		 0.1875 0.609375 0.1875 0.609375 0.25 0.5625 0.25 0.609375 0.25 0.65625 0.25 0.65625
		 0.1875 0.609375 0.1875 0.71875 0.1875 0.671875 0.1875 0.671875 0 0.71875 0 0.609375
		 0.1875 0.5625 0.1875 0.5625 0 0.609375 0;
	setAttr ".cuvs" -type "string" "map1";
	setAttr ".dcc" -type "string" "Ambient+Diffuse";
	setAttr ".covm[0]"  0 1 1;
	setAttr ".cdvm[0]"  0 1 1;
	setAttr -s 8 ".pt[0:7]" -type "float3"  0 0.5 0 0 0.5 0 0 0.5 0 0 
		0.5 0 0 -0.5 0 0 -0.5 0 0 -0.5 0 0 -0.5 0;
	setAttr -s 8 ".vt[0:7]"  4 20 -2 4 20 2 16 20 -2 16 20 2 4 24 2 4 24 -2
		 16 24 2 16 24 -2;
	setAttr -s 12 ".ed[0:11]"  0 2 0 1 0 0 2 3 0 3 1 0 4 6 0 5 4 0 6 7 0
		 7 5 0 1 4 0 5 0 0 2 7 0 6 3 0;
	setAttr -s 24 ".n[0:23]" -type "float3"  4.4408921e-16 -1.33333337 5.4385245e-32
		 4.4408921e-16 -1.33333337 5.4385245e-32 4.4408921e-16 -1.33333337 5.4385245e-32 4.4408921e-16
		 -1.33333337 5.4385245e-32 -4.4408921e-16 1.33333337 -5.4385245e-32 -4.4408921e-16
		 1.33333337 -5.4385245e-32 -4.4408921e-16 1.33333337 -5.4385245e-32 -4.4408921e-16
		 1.33333337 -5.4385245e-32 -1 -5.9211896e-16 -1.2246469e-16 -1 -5.9211896e-16 -1.2246469e-16
		 -1 -5.9211896e-16 -1.2246469e-16 -1 -5.9211896e-16 -1.2246469e-16 1 5.9211896e-16
		 1.2246469e-16 1 5.9211896e-16 1.2246469e-16 1 5.9211896e-16 1.2246469e-16 1 5.9211896e-16
		 1.2246469e-16 1.2246469e-16 0 -1 1.2246469e-16 0 -1 1.2246469e-16 0 -1 1.2246469e-16
		 0 -1 -1.2246469e-16 0 1 -1.2246469e-16 0 1 -1.2246469e-16 0 1 -1.2246469e-16 0 1;
	setAttr -s 6 -ch 24 ".fc[0:5]" -type "polyFaces" 
		f 4 1 0 2 3
		mu 0 4 0 1 2 3
		f 4 5 4 6 7
		mu 0 4 4 5 6 7
		f 4 8 -6 9 -2
		mu 0 4 8 9 10 11
		f 4 10 -7 11 -3
		mu 0 4 12 13 14 15
		f 4 -10 -8 -11 -1
		mu 0 4 16 17 18 19
		f 4 -9 -4 -12 -5
		mu 0 4 20 21 22 23;
	setAttr ".cd" -type "dataPolyComponent" Index_Data Edge 0 ;
	setAttr ".cvd" -type "dataPolyComponent" Index_Data Vertex 0 ;
	setAttr ".pd[0]" -type "dataPolyComponent" Index_Data UV 0 ;
	setAttr ".hfd" -type "dataPolyComponent" Index_Data Face 0 ;
	setAttr ".vcs" 2;
	setAttr ".ai_translator" -type "string" "polymesh";
createNode transform -n "mesh_arm_alex_second_r" -p "grp_mesh_arm_alex";
	rename -uid "84061594-43B8-EADA-B1E0-A4BFFF12B499";
	setAttr -l on ".tx";
	setAttr -l on ".ty";
	setAttr -l on ".tz";
	setAttr -l on ".rx";
	setAttr -l on ".ry";
	setAttr -l on ".rz";
	setAttr -l on ".sx";
	setAttr -l on ".sy";
	setAttr -l on ".sz";
	setAttr ".rp" -type "double3" -6 20.5 -1.2246467991473546e-15 ;
	setAttr ".sp" -type "double3" -6 20.5 -1.2246467991473546e-15 ;
createNode mesh -n "mesh_arm_alex_second_rShape" -p "mesh_arm_alex_second_r";
	rename -uid "71FDA885-433F-4199-C9C0-EEBC5E07F17E";
	setAttr -k off ".v";
	setAttr -s 4 ".iog[0].og";
	setAttr ".lodv" no;
	setAttr ".vir" yes;
	setAttr ".vif" yes;
	setAttr ".pv" -type "double2" 0.78125 0.46875 ;
	setAttr ".uvst[0].uvsn" -type "string" "map1";
	setAttr ".cuvs" -type "string" "map1";
	setAttr ".dcc" -type "string" "Ambient+Diffuse";
	setAttr ".covm[0]"  0 1 1;
	setAttr ".cdvm[0]"  0 1 1;
	setAttr ".vcs" 2;
	setAttr ".ai_opaque" no;
	setAttr ".ai_translator" -type "string" "polymesh";
createNode mesh -n "mesh_arm_alex_second_rShapeOrig" -p "mesh_arm_alex_second_r";
	rename -uid "D9EE9F7F-464C-C1A0-4E94-2482DDF1AB9F";
	setAttr -k off ".v";
	setAttr ".io" yes;
	setAttr ".vir" yes;
	setAttr ".vif" yes;
	setAttr ".uvst[0].uvsn" -type "string" "map1";
	setAttr -s 24 ".uvst[0].uvsp[0:23]" -type "float2" 0.6875 0.4375 0.625
		 0.4375 0.625 0.25 0.6875 0.25 0.8125 0.4375 0.75 0.4375 0.75 0.25 0.8125 0.25 0.6875
		 0.4375 0.75 0.4375 0.75 0.5 0.6875 0.5 0.75 0.5 0.8125 0.5 0.8125 0.4375 0.75 0.4375
		 0.875 0.4375 0.8125 0.4375 0.8125 0.25 0.875 0.25 0.75 0.4375 0.6875 0.4375 0.6875
		 0.25 0.75 0.25;
	setAttr ".cuvs" -type "string" "map1";
	setAttr ".dcc" -type "string" "Ambient+Diffuse";
	setAttr ".covm[0]"  0 1 1;
	setAttr ".cdvm[0]"  0 1 1;
	setAttr -s 8 ".pt[0:7]" -type "float3"  -4.4244242 22.839638 -2.464638 
		-4.4244242 22.839638 2.464638 -16.87788 23.589638 -2.464638 -16.87788 23.589638 2.464638 
		-4.1744242 18.160362 2.464638 -4.1744242 18.160362 -2.464638 -16.62788 18.910362 
		2.464638 -16.62788 18.910362 -2.464638;
	setAttr -s 8 ".vt[0:7]"  0.5 1.5 0.125 0.5 1.5 -0.125 0.5 0.75 0.125
		 0.5 0.75 -0.125 0.25 1.5 -0.125 0.25 1.5 0.125 0.25 0.75 -0.125 0.25 0.75 0.125;
	setAttr -s 12 ".ed[0:11]"  0 2 0 1 0 0 2 3 0 3 1 0 4 6 0 5 4 0 6 7 0
		 7 5 0 1 4 0 5 0 0 2 7 0 6 3 0;
	setAttr -s 24 ".n[0:23]" -type "float3"  2.674494e-17 0.05342707 2.9056442e-33
		 2.674494e-17 0.05342707 2.9056442e-33 2.674494e-17 0.05342707 2.9056442e-33 2.674494e-17
		 0.05342707 2.9056442e-33 -2.674494e-17 -0.05342707 -2.9056442e-33 -2.674494e-17 -0.05342707
		 -2.9056442e-33 -2.674494e-17 -0.05342707 -2.9056442e-33 -2.674494e-17 -0.05342707
		 -2.9056442e-33 0.06022425 -2.3726385e-17 6.5429292e-18 0.06022425 -2.3726385e-17
		 6.5429292e-18 0.06022425 -2.3726385e-17 6.5429292e-18 0.06022425 -2.3726385e-17 6.5429292e-18
		 -0.06022425 2.3726385e-17 -6.5429292e-18 -0.06022425 2.3726385e-17 -6.5429292e-18
		 -0.06022425 2.3726385e-17 -6.5429292e-18 -0.06022425 2.3726385e-17 -6.5429292e-18
		 7.3753442e-18 0 -0.05342707 7.3753442e-18 0 -0.05342707 7.3753442e-18 0 -0.05342707
		 7.3753442e-18 0 -0.05342707 -7.3753442e-18 0 0.05342707 -7.3753442e-18 0 0.05342707
		 -7.3753442e-18 0 0.05342707 -7.3753442e-18 0 0.05342707;
	setAttr -s 6 -ch 24 ".fc[0:5]" -type "polyFaces" 
		f 4 1 0 2 3
		mu 0 4 0 1 2 3
		f 4 5 4 6 7
		mu 0 4 4 5 6 7
		f 4 8 -6 9 -2
		mu 0 4 8 9 10 11
		f 4 10 -7 11 -3
		mu 0 4 12 13 14 15
		f 4 -10 -8 -11 -1
		mu 0 4 16 17 18 19
		f 4 -9 -4 -12 -5
		mu 0 4 20 21 22 23;
	setAttr ".cd" -type "dataPolyComponent" Index_Data Edge 0 ;
	setAttr ".cvd" -type "dataPolyComponent" Index_Data Vertex 0 ;
	setAttr ".pd[0]" -type "dataPolyComponent" Index_Data UV 0 ;
	setAttr ".hfd" -type "dataPolyComponent" Index_Data Face 0 ;
	setAttr ".ai_translator" -type "string" "polymesh";
createNode mesh -n "mesh_arm_alex_second_rShapeOrig1" -p "mesh_arm_alex_second_r";
	rename -uid "8B4E732D-42AA-7CCB-3FD9-64A09FB8E65F";
	setAttr -k off ".v";
	setAttr ".io" yes;
	setAttr ".vir" yes;
	setAttr ".vif" yes;
	setAttr ".uvst[0].uvsn" -type "string" "map1";
	setAttr -s 24 ".uvst[0].uvsp[0:23]" -type "float2" 0.6875 0.4375 0.625
		 0.4375 0.625 0.25 0.6875 0.25 0.796875 0.4375 0.734375 0.4375 0.734375 0.25 0.796875
		 0.25 0.6875 0.4375 0.734375 0.4375 0.734375 0.5 0.6875 0.5 0.734375 0.5 0.78125 0.5
		 0.78125 0.4375 0.734375 0.4375 0.84375 0.4375 0.796875 0.4375 0.796875 0.25 0.84375
		 0.25 0.734375 0.4375 0.6875 0.4375 0.6875 0.25 0.734375 0.25;
	setAttr ".cuvs" -type "string" "map1";
	setAttr ".dcc" -type "string" "Ambient+Diffuse";
	setAttr ".covm[0]"  0 1 1;
	setAttr ".cdvm[0]"  0 1 1;
	setAttr -s 8 ".pt[0:7]" -type "float3"  0 -0.58490944 0 0 -0.58490944 
		0 0 -0.58490944 0 0 -0.58490944 0 0 0.58490944 0 0 0.58490944 0 0 0.58490944 0 0 
		0.58490944 0;
	setAttr -s 8 ".vt[0:7]"  -3.92442417 24.33963776 -2.33963799 -3.92442417 24.33963776 2.33963799
		 -16.3778801 24.33963776 -2.33963799 -16.3778801 24.33963776 2.33963799 -3.92442417 19.66036224 2.33963799
		 -3.92442417 19.66036224 -2.33963799 -16.3778801 19.66036224 2.33963799 -16.3778801 19.66036224 -2.33963799;
	setAttr -s 12 ".ed[0:11]"  0 2 0 1 0 0 2 3 0 3 1 0 4 6 0 5 4 0 6 7 0
		 7 5 0 1 4 0 5 0 0 2 7 0 6 3 0;
	setAttr -s 24 ".n[0:23]" -type "float3"  5.0058779e-16 1.33333337 5.4385245e-32
		 5.0058779e-16 1.33333337 5.4385245e-32 5.0058779e-16 1.33333337 5.4385245e-32 5.0058779e-16
		 1.33333337 5.4385245e-32 -5.0058779e-16 -1.33333337 -5.4385245e-32 -5.0058779e-16
		 -1.33333337 -5.4385245e-32 -5.0058779e-16 -1.33333337 -5.4385245e-32 -5.0058779e-16
		 -1.33333337 -5.4385245e-32 1 -5.2528976e-16 1.0864277e-16 1 -5.2528976e-16 1.0864277e-16
		 1 -5.2528976e-16 1.0864277e-16 1 -5.2528976e-16 1.0864277e-16 -1 5.2528976e-16 -1.0864277e-16
		 -1 5.2528976e-16 -1.0864277e-16 -1 5.2528976e-16 -1.0864277e-16 -1 5.2528976e-16
		 -1.0864277e-16 1.3804508e-16 0 -1 1.3804508e-16 0 -1 1.3804508e-16 0 -1 1.3804508e-16
		 0 -1 -1.3804508e-16 0 1 -1.3804508e-16 0 1 -1.3804508e-16 0 1 -1.3804508e-16 0 1;
	setAttr -s 6 -ch 24 ".fc[0:5]" -type "polyFaces" 
		f 4 1 0 2 3
		mu 0 4 0 1 2 3
		f 4 5 4 6 7
		mu 0 4 4 5 6 7
		f 4 8 -6 9 -2
		mu 0 4 8 9 10 11
		f 4 10 -7 11 -3
		mu 0 4 12 13 14 15
		f 4 -10 -8 -11 -1
		mu 0 4 16 17 18 19
		f 4 -9 -4 -12 -5
		mu 0 4 20 21 22 23;
	setAttr ".cd" -type "dataPolyComponent" Index_Data Edge 0 ;
	setAttr ".cvd" -type "dataPolyComponent" Index_Data Vertex 0 ;
	setAttr ".pd[0]" -type "dataPolyComponent" Index_Data UV 0 ;
	setAttr ".hfd" -type "dataPolyComponent" Index_Data Face 0 ;
	setAttr ".vcs" 2;
	setAttr ".ai_translator" -type "string" "polymesh";
createNode transform -n "mesh_arm_alex_r" -p "grp_mesh_arm_alex";
	rename -uid "429CEF9E-4844-D7C0-F82C-A88C1643F6EA";
	setAttr -l on ".tx";
	setAttr -l on ".ty";
	setAttr -l on ".tz";
	setAttr -l on ".rx";
	setAttr -l on ".ry";
	setAttr -l on ".rz";
	setAttr -l on ".sx";
	setAttr -l on ".sy";
	setAttr -l on ".sz";
	setAttr ".rp" -type "double3" -9.9999999999999893 20.500000000000025 -1.2246467991473546e-15 ;
	setAttr ".sp" -type "double3" -9.9999999999999893 20.500000000000025 -1.2246467991473546e-15 ;
createNode mesh -n "mesh_arm_alex_rShape" -p "mesh_arm_alex_r";
	rename -uid "E6A00F5D-4594-C788-474D-77B958F1C8D5";
	setAttr -k off ".v";
	setAttr -s 4 ".iog[0].og";
	setAttr ".vir" yes;
	setAttr ".vif" yes;
	setAttr ".pv" -type "double2" 0.7890625 0.59375 ;
	setAttr ".uvst[0].uvsn" -type "string" "map1";
	setAttr ".cuvs" -type "string" "map1";
	setAttr ".dcc" -type "string" "Ambient+Diffuse";
	setAttr ".covm[0]"  0 1 1;
	setAttr ".cdvm[0]"  0 1 1;
	setAttr ".vcs" 2;
	setAttr ".ai_translator" -type "string" "polymesh";
createNode mesh -n "mesh_arm_alex_rShapeOrig" -p "mesh_arm_alex_r";
	rename -uid "8A8C0814-47CF-3394-6FDA-63A49A4CD426";
	setAttr -k off ".v";
	setAttr ".io" yes;
	setAttr ".vir" yes;
	setAttr ".vif" yes;
	setAttr ".uvst[0].uvsn" -type "string" "map1";
	setAttr -s 24 ".uvst[0].uvsp[0:23]" -type "float2" 0.6875 0.6875 0.625
		 0.6875 0.625 0.5 0.6875 0.5 0.8125 0.6875 0.75 0.6875 0.75 0.5 0.8125 0.5 0.6875
		 0.6875 0.75 0.6875 0.75 0.75 0.6875 0.75 0.75 0.75 0.8125 0.75 0.8125 0.6875 0.75
		 0.6875 0.875 0.6875 0.8125 0.6875 0.8125 0.5 0.875 0.5 0.75 0.6875 0.6875 0.6875
		 0.6875 0.5 0.75 0.5;
	setAttr ".cuvs" -type "string" "map1";
	setAttr ".dcc" -type "string" "Ambient+Diffuse";
	setAttr ".covm[0]"  0 1 1;
	setAttr ".cdvm[0]"  0 1 1;
	setAttr -s 8 ".pt[0:7]" -type "float3"  -4.5 22.5 -2.125 -4.5 22.5 
		2.125 -16.5 23.25 -2.125 -16.5 23.25 2.125 -4.25 18.5 2.125 -4.25 18.5 -2.125 -16.25 
		19.25 2.125 -16.25 19.25 -2.125;
	setAttr -s 8 ".vt[0:7]"  0.5 1.5 0.125 0.5 1.5 -0.125 0.5 0.75 0.125
		 0.5 0.75 -0.125 0.25 1.5 -0.125 0.25 1.5 0.125 0.25 0.75 -0.125 0.25 0.75 0.125;
	setAttr -s 12 ".ed[0:11]"  0 2 0 1 0 0 2 3 0 3 1 0 4 6 0 5 4 0 6 7 0
		 7 5 0 1 4 0 5 0 0 2 7 0 6 3 0;
	setAttr -s 24 ".n[0:23]" -type "float3"  2.7755576e-17 0.0625 3.3990778e-33
		 2.7755576e-17 0.0625 3.3990778e-33 2.7755576e-17 0.0625 3.3990778e-33 2.7755576e-17
		 0.0625 3.3990778e-33 -2.7755576e-17 -0.0625 -3.3990778e-33 -2.7755576e-17 -0.0625
		 -3.3990778e-33 -2.7755576e-17 -0.0625 -3.3990778e-33 -2.7755576e-17 -0.0625 -3.3990778e-33
		 0.0625 -2.7755576e-17 7.6540428e-18 0.0625 -2.7755576e-17 7.6540428e-18 0.0625 -2.7755576e-17
		 7.6540428e-18 0.0625 -2.7755576e-17 7.6540428e-18 -0.0625 2.7755576e-17 -7.6540428e-18
		 -0.0625 2.7755576e-17 -7.6540428e-18 -0.0625 2.7755576e-17 -7.6540428e-18 -0.0625
		 2.7755576e-17 -7.6540428e-18 7.6540428e-18 0 -0.0625 7.6540428e-18 0 -0.0625 7.6540428e-18
		 0 -0.0625 7.6540428e-18 0 -0.0625 -7.6540428e-18 0 0.0625 -7.6540428e-18 0 0.0625
		 -7.6540428e-18 0 0.0625 -7.6540428e-18 0 0.0625;
	setAttr -s 6 -ch 24 ".fc[0:5]" -type "polyFaces" 
		f 4 1 0 2 3
		mu 0 4 0 1 2 3
		f 4 5 4 6 7
		mu 0 4 4 5 6 7
		f 4 8 -6 9 -2
		mu 0 4 8 9 10 11
		f 4 10 -7 11 -3
		mu 0 4 12 13 14 15
		f 4 -10 -8 -11 -1
		mu 0 4 16 17 18 19
		f 4 -9 -4 -12 -5
		mu 0 4 20 21 22 23;
	setAttr ".cd" -type "dataPolyComponent" Index_Data Edge 0 ;
	setAttr ".cvd" -type "dataPolyComponent" Index_Data Vertex 0 ;
	setAttr ".pd[0]" -type "dataPolyComponent" Index_Data UV 0 ;
	setAttr ".hfd" -type "dataPolyComponent" Index_Data Face 0 ;
	setAttr ".ai_translator" -type "string" "polymesh";
createNode mesh -n "mesh_arm_alex_rShapeOrig1" -p "mesh_arm_alex_r";
	rename -uid "36B80C88-4E01-9CF9-16FC-AF9E3A711157";
	setAttr -k off ".v";
	setAttr ".io" yes;
	setAttr ".vir" yes;
	setAttr ".vif" yes;
	setAttr ".uvst[0].uvsn" -type "string" "map1";
	setAttr -s 24 ".uvst[0].uvsp[0:23]" -type "float2" 0.6875 0.6875 0.625
		 0.6875 0.625 0.5 0.6875 0.5 0.796875 0.6875 0.734375 0.6875 0.734375 0.5 0.796875
		 0.5 0.6875 0.6875 0.734375 0.6875 0.734375 0.75 0.6875 0.75 0.734375 0.75 0.78125
		 0.75 0.78125 0.6875 0.734375 0.6875 0.84375 0.6875 0.796875 0.6875 0.796875 0.5 0.84375
		 0.5 0.734375 0.6875 0.6875 0.6875 0.6875 0.5 0.734375 0.5;
	setAttr ".cuvs" -type "string" "map1";
	setAttr ".dcc" -type "string" "Ambient+Diffuse";
	setAttr ".covm[0]"  0 1 1;
	setAttr ".cdvm[0]"  0 1 1;
	setAttr -s 8 ".pt[0:7]" -type "float3"  0 -0.5 0 0 -0.5 0 0 -0.5 
		0 0 -0.5 0 0 0.5 0 0 0.5 0 0 0.5 0 0 0.5 0;
	setAttr -s 8 ".vt[0:7]"  -4 24 -2 -4 24 2 -16 24 -2 -16 24 2 -4 20 2
		 -4 20 -2 -16 20 2 -16 20 -2;
	setAttr -s 12 ".ed[0:11]"  0 2 0 1 0 0 2 3 0 3 1 0 4 6 0 5 4 0 6 7 0
		 7 5 0 1 4 0 5 0 0 2 7 0 6 3 0;
	setAttr -s 24 ".n[0:23]" -type "float3"  4.4408921e-16 1.33333337 5.4385245e-32
		 4.4408921e-16 1.33333337 5.4385245e-32 4.4408921e-16 1.33333337 5.4385245e-32 4.4408921e-16
		 1.33333337 5.4385245e-32 -4.4408921e-16 -1.33333337 -5.4385245e-32 -4.4408921e-16
		 -1.33333337 -5.4385245e-32 -4.4408921e-16 -1.33333337 -5.4385245e-32 -4.4408921e-16
		 -1.33333337 -5.4385245e-32 1 -5.9211896e-16 1.2246469e-16 1 -5.9211896e-16 1.2246469e-16
		 1 -5.9211896e-16 1.2246469e-16 1 -5.9211896e-16 1.2246469e-16 -1 5.9211896e-16 -1.2246469e-16
		 -1 5.9211896e-16 -1.2246469e-16 -1 5.9211896e-16 -1.2246469e-16 -1 5.9211896e-16
		 -1.2246469e-16 1.2246469e-16 0 -1 1.2246469e-16 0 -1 1.2246469e-16 0 -1 1.2246469e-16
		 0 -1 -1.2246469e-16 0 1 -1.2246469e-16 0 1 -1.2246469e-16 0 1 -1.2246469e-16 0 1;
	setAttr -s 6 -ch 24 ".fc[0:5]" -type "polyFaces" 
		f 4 1 0 2 3
		mu 0 4 0 1 2 3
		f 4 5 4 6 7
		mu 0 4 4 5 6 7
		f 4 8 -6 9 -2
		mu 0 4 8 9 10 11
		f 4 10 -7 11 -3
		mu 0 4 12 13 14 15
		f 4 -10 -8 -11 -1
		mu 0 4 16 17 18 19
		f 4 -9 -4 -12 -5
		mu 0 4 20 21 22 23;
	setAttr ".cd" -type "dataPolyComponent" Index_Data Edge 0 ;
	setAttr ".cvd" -type "dataPolyComponent" Index_Data Vertex 0 ;
	setAttr ".pd[0]" -type "dataPolyComponent" Index_Data UV 0 ;
	setAttr ".hfd" -type "dataPolyComponent" Index_Data Face 0 ;
	setAttr ".vcs" 2;
	setAttr ".ai_translator" -type "string" "polymesh";
createNode transform -n "grp_mesh_second_layer" -p "grp_meshes";
	rename -uid "E55C54E1-4424-177E-19CB-8FB34AC6D731";
	setAttr ".v" no;
createNode transform -n "mesh_head_second" -p "grp_mesh_second_layer";
	rename -uid "AB013B90-460A-0374-B59D-B49C1232B363";
	setAttr -l on ".tx";
	setAttr -l on ".ty";
	setAttr -l on ".tz";
	setAttr -l on ".rx";
	setAttr -l on ".ry";
	setAttr -l on ".rz";
	setAttr -l on ".sx";
	setAttr -l on ".sy";
	setAttr -l on ".sz";
	setAttr ".rp" -type "double3" 0 28 0 ;
	setAttr ".sp" -type "double3" 0 28 0 ;
createNode mesh -n "mesh_head_secondShape" -p "mesh_head_second";
	rename -uid "84035D8B-4B4C-16F4-07C7-B7A56E988268";
	setAttr -k off ".v";
	setAttr -s 4 ".iog[0].og";
	setAttr -av ".iog[0].og[0].gco";
	setAttr -av ".iog[0].og[1].gco";
	setAttr ".lodv" no;
	setAttr ".vir" yes;
	setAttr ".vif" yes;
	setAttr ".pv" -type "double2" 0.75 0.875 ;
	setAttr ".uvst[0].uvsn" -type "string" "map1";
	setAttr ".cuvs" -type "string" "map1";
	setAttr ".dcc" -type "string" "Ambient+Diffuse";
	setAttr ".covm[0]"  0 1 1;
	setAttr ".cdvm[0]"  0 1 1;
	setAttr ".dr" 1;
	setAttr ".vcs" 2;
	setAttr ".ai_opaque" no;
	setAttr ".ai_translator" -type "string" "polymesh";
createNode mesh -n "mesh_head_secondShapeOrig" -p "mesh_head_second";
	rename -uid "0D5FC972-46AA-3245-8E48-92978D4A02D3";
	setAttr -k off ".v";
	setAttr ".io" yes;
	setAttr ".vir" yes;
	setAttr ".vif" yes;
	setAttr ".uvst[0].uvsn" -type "string" "map1";
	setAttr -s 24 ".uvst[0].uvsp[0:23]" -type "float2" 0.625 0.875 0.5 0.875
		 0.5 0.75 0.625 0.75 0.875 0.875 0.75 0.875 0.75 0.75 0.875 0.75 0.625 0.875 0.75
		 0.875 0.75 1 0.625 1 0.75 1 0.875 1 0.875 0.875 0.75 0.875 1 0.875 0.875 0.875 0.875
		 0.75 1 0.75 0.75 0.875 0.625 0.875 0.625 0.75 0.75 0.75;
	setAttr ".cuvs" -type "string" "map1";
	setAttr ".dcc" -type "string" "Ambient+Diffuse";
	setAttr ".covm[0]"  0 1 1;
	setAttr ".cdvm[0]"  0 1 1;
	setAttr -s 8 ".pt[0:7]" -type "float3"  -4.4461703 30.196171 -4.4461713 
		-4.4461703 30.196171 4.4461713 -4.4461703 22.303829 -4.4461713 -4.4461703 22.303829 
		4.4461713 4.4461722 30.196171 4.4461713 4.4461722 30.196171 -4.4461713 4.4461722 
		22.303829 4.4461713 4.4461722 22.303829 -4.4461713;
	setAttr -s 8 ".vt[0:7]"  0.25 2 0.25 0.25 2 -0.25 0.25 1.5 0.25 0.25 1.5 -0.25
		 -0.25 2 -0.25 -0.25 2 0.25 -0.25 1.5 -0.25 -0.25 1.5 0.25;
	setAttr -s 12 ".ed[0:11]"  0 2 0 1 0 0 2 3 0 3 1 0 4 6 0 5 4 0 6 7 0
		 7 5 0 1 4 0 5 0 0 2 7 0 6 3 0;
	setAttr -s 24 ".n[0:23]" -type "float3"  -0.059578121 0 -7.2962161e-18
		 -0.059578121 0 -7.2962161e-18 -0.059578121 0 -7.2962161e-18 -0.059578121 0 -7.2962161e-18
		 0.059578121 0 7.2962161e-18 0.059578121 0 7.2962161e-18 0.059578121 0 7.2962161e-18
		 0.059578121 0 7.2962161e-18 0 0.059578121 0 0 0.059578121 0 0 0.059578121 0 0 0.059578121
		 0 0 -0.059578121 0 0 -0.059578121 0 0 -0.059578121 0 0 -0.059578121 0 7.2962161e-18
		 0 -0.059578121 7.2962161e-18 0 -0.059578121 7.2962161e-18 0 -0.059578121 7.2962161e-18
		 0 -0.059578121 -7.2962161e-18 0 0.059578121 -7.2962161e-18 0 0.059578121 -7.2962161e-18
		 0 0.059578121 -7.2962161e-18 0 0.059578121;
	setAttr -s 6 -ch 24 ".fc[0:5]" -type "polyFaces" 
		f 4 1 0 2 3
		mu 0 4 0 1 2 3
		f 4 5 4 6 7
		mu 0 4 4 5 6 7
		f 4 8 -6 9 -2
		mu 0 4 8 9 10 11
		f 4 10 -7 11 -3
		mu 0 4 12 13 14 15
		f 4 -10 -8 -11 -1
		mu 0 4 16 17 18 19
		f 4 -9 -4 -12 -5
		mu 0 4 20 21 22 23;
	setAttr ".cd" -type "dataPolyComponent" Index_Data Edge 0 ;
	setAttr ".cvd" -type "dataPolyComponent" Index_Data Vertex 0 ;
	setAttr ".pd[0]" -type "dataPolyComponent" Index_Data UV 0 ;
	setAttr ".hfd" -type "dataPolyComponent" Index_Data Face 0 ;
	setAttr ".ai_translator" -type "string" "polymesh";
createNode transform -n "mesh_body_second" -p "grp_mesh_second_layer";
	rename -uid "74760AC6-4CE5-12B8-B143-E9A4190692B8";
	setAttr -l on ".tx";
	setAttr -l on ".ty";
	setAttr -l on ".tz";
	setAttr -l on ".rx";
	setAttr -l on ".ry";
	setAttr -l on ".rz";
	setAttr -l on ".sx";
	setAttr -l on ".sy";
	setAttr -l on ".sz";
	setAttr ".rp" -type "double3" 0 18 0 ;
	setAttr ".sp" -type "double3" 0 18 0 ;
createNode mesh -n "mesh_body_secondShape" -p "mesh_body_second";
	rename -uid "3EB62B65-4A62-457E-CF4D-87929A2A5223";
	setAttr -k off ".v";
	setAttr -s 4 ".iog[0].og";
	setAttr ".lodv" no;
	setAttr ".vir" yes;
	setAttr ".vif" yes;
	setAttr ".pv" -type "double2" 0.4375 0.375 ;
	setAttr ".uvst[0].uvsn" -type "string" "map1";
	setAttr ".cuvs" -type "string" "map1";
	setAttr ".dcc" -type "string" "Ambient+Diffuse";
	setAttr ".covm[0]"  0 1 1;
	setAttr ".cdvm[0]"  0 1 1;
	setAttr ".dfgi" 4907;
	setAttr ".vcs" 2;
	setAttr ".ai_opaque" no;
	setAttr ".ai_translator" -type "string" "polymesh";
createNode mesh -n "mesh_body_secondShapeOrig" -p "mesh_body_second";
	rename -uid "5F52B959-4E73-DBA4-06D1-9BAD3CC95564";
	setAttr -k off ".v";
	setAttr ".io" yes;
	setAttr ".vir" yes;
	setAttr ".vif" yes;
	setAttr ".uvst[0].uvsn" -type "string" "map1";
	setAttr -s 24 ".uvst[0].uvsp[0:23]" -type "float2" 0.31225586 0.68725586
		 0.25024414 0.68725586 0.25024414 0.50024414 0.31225586 0.50024414 0.49975586 0.68725586
		 0.43774414 0.68725586 0.43774414 0.50024414 0.49975586 0.50024414 0.31274414 0.68774414
		 0.43725586 0.68774414 0.43725586 0.74975586 0.31274414 0.74975586 0.43774414 0.74975586
		 0.56225586 0.74975586 0.56225586 0.68774414 0.43774414 0.68774414 0.62475586 0.68725586
		 0.50024414 0.68725586 0.50024414 0.50024414 0.62475586 0.50024414 0.43725586 0.68725586
		 0.31274414 0.68725586 0.31274414 0.50024414 0.43725586 0.50024414;
	setAttr ".cuvs" -type "string" "map1";
	setAttr ".dcc" -type "string" "Ambient+Diffuse";
	setAttr ".covm[0]"  0 1 1;
	setAttr ".cdvm[0]"  0 1 1;
	setAttr -s 8 ".vt[0:7]"  0.25 1.5 0.125 0.25 1.5 -0.125 0.25 0.75 0.125
		 0.25 0.75 -0.125 -0.25 1.5 -0.125 -0.25 1.5 0.125 -0.25 0.75 -0.125 -0.25 0.75 0.125;
	setAttr -s 12 ".ed[0:11]"  0 2 0 1 0 0 2 3 0 3 1 0 4 6 0 5 4 0 6 7 0
		 7 5 0 1 4 0 5 0 0 2 7 0 6 3 0;
	setAttr -s 24 ".n[0:23]" -type "float3"  1 0 0 1 0 0 1 0 0 1 0 0 -1
		 0 0 -1 0 0 -1 0 0 -1 0 0 0 1 0 0 1 0 0 1 0 0 1 0 0 -1 0 0 -1 0 0 -1 0 0 -1 0 0 0
		 1 0 0 1 0 0 1 0 0 1 0 0 -1 0 0 -1 0 0 -1 0 0 -1;
	setAttr -s 6 -ch 24 ".fc[0:5]" -type "polyFaces" 
		f 4 1 0 2 3
		mu 0 4 0 1 2 3
		f 4 5 4 6 7
		mu 0 4 4 5 6 7
		f 4 8 -6 9 -2
		mu 0 4 8 9 10 11
		f 4 10 -7 11 -3
		mu 0 4 12 13 14 15
		f 4 -10 -8 -11 -1
		mu 0 4 16 17 18 19
		f 4 -9 -4 -12 -5
		mu 0 4 20 21 22 23;
	setAttr ".cd" -type "dataPolyComponent" Index_Data Edge 0 ;
	setAttr ".cvd" -type "dataPolyComponent" Index_Data Vertex 0 ;
	setAttr ".pd[0]" -type "dataPolyComponent" Index_Data UV 0 ;
	setAttr ".hfd" -type "dataPolyComponent" Index_Data Face 0 ;
	setAttr ".ai_translator" -type "string" "polymesh";
createNode mesh -n "mesh_body_secondShapeOrig1" -p "mesh_body_second";
	rename -uid "D1673744-4A25-4206-1C9B-C2A9589344D8";
	setAttr -k off ".v";
	setAttr ".io" yes;
	setAttr ".vir" yes;
	setAttr ".vif" yes;
	setAttr ".uvst[0].uvsn" -type "string" "map1";
	setAttr -s 24 ".uvst[0].uvsp[0:23]" -type "float2" 0.3125 0.4375 0.25
		 0.4375 0.25 0.25 0.3125 0.25 0.5 0.4375 0.4375 0.4375 0.4375 0.25 0.5 0.25 0.3125
		 0.4375 0.4375 0.4375 0.4375 0.5 0.3125 0.5 0.4375 0.5 0.5625 0.5 0.5625 0.4375 0.4375
		 0.4375 0.625 0.4375 0.5 0.4375 0.5 0.25 0.625 0.25 0.4375 0.4375 0.3125 0.4375 0.3125
		 0.25 0.4375 0.25;
	setAttr ".cuvs" -type "string" "map1";
	setAttr ".dcc" -type "string" "Ambient+Diffuse";
	setAttr ".covm[0]"  0 1 1;
	setAttr ".cdvm[0]"  0 1 1;
	setAttr -s 8 ".pt[0:7]" -type "float3"  -0.20795812 0.17073013 -0.10397971 
		-0.20795812 0.17073013 0.10397971 -0.20795812 -0.17073013 -0.10397971 -0.20795812 
		-0.17073013 0.10397971 0.20796037 0.17073013 0.10397971 0.20796037 0.17073013 -0.10397971 
		0.20796037 -0.17073013 0.10397971 0.20796037 -0.17073013 -0.10397971;
	setAttr -s 8 ".vt[0:7]"  -4 24 -2 -4 24 2 -4 12 -2 -4 12 2 4 24 2
		 4 24 -2 4 12 2 4 12 -2;
	setAttr -s 12 ".ed[0:11]"  0 2 0 1 0 0 2 3 0 3 1 0 4 6 0 5 4 0 6 7 0
		 7 5 0 1 4 0 5 0 0 2 7 0 6 3 0;
	setAttr -s 24 ".n[0:23]" -type "float3"  -0.95057952 0 -1.1641242e-16
		 -0.95057952 0 -1.1641242e-16 -0.95057952 0 -1.1641242e-16 -0.95057952 0 -1.1641242e-16
		 0.95057952 0 1.1641242e-16 0.95057952 0 1.1641242e-16 0.95057952 0 1.1641242e-16
		 0.95057952 0 1.1641242e-16 0 0.97233218 0 0 0.97233218 0 0 0.97233218 0 0 0.97233218
		 0 0 -0.97233218 0 0 -0.97233218 0 0 -0.97233218 0 0 -0.97233218 0 1.1641242e-16 0
		 -0.95057952 1.1641242e-16 0 -0.95057952 1.1641242e-16 0 -0.95057952 1.1641242e-16
		 0 -0.95057952 -1.1641242e-16 0 0.95057952 -1.1641242e-16 0 0.95057952 -1.1641242e-16
		 0 0.95057952 -1.1641242e-16 0 0.95057952;
	setAttr -s 6 -ch 24 ".fc[0:5]" -type "polyFaces" 
		f 4 1 0 2 3
		mu 0 4 0 1 2 3
		f 4 5 4 6 7
		mu 0 4 4 5 6 7
		f 4 8 -6 9 -2
		mu 0 4 8 9 10 11
		f 4 10 -7 11 -3
		mu 0 4 12 13 14 15
		f 4 -10 -8 -11 -1
		mu 0 4 16 17 18 19
		f 4 -9 -4 -12 -5
		mu 0 4 20 21 22 23;
	setAttr ".cd" -type "dataPolyComponent" Index_Data Edge 0 ;
	setAttr ".cvd" -type "dataPolyComponent" Index_Data Vertex 0 ;
	setAttr ".pd[0]" -type "dataPolyComponent" Index_Data UV 0 ;
	setAttr ".hfd" -type "dataPolyComponent" Index_Data Face 0 ;
	setAttr ".dfgi" 4907;
	setAttr ".vcs" 2;
	setAttr ".ai_translator" -type "string" "polymesh";
createNode transform -n "mesh_leg_second_l" -p "grp_mesh_second_layer";
	rename -uid "2A25B660-4708-795B-69BA-1FB99C6EE400";
	setAttr -l on ".tx";
	setAttr -l on ".ty";
	setAttr -l on ".tz";
	setAttr -l on ".rx";
	setAttr -l on ".ry";
	setAttr -l on ".rz";
	setAttr -l on ".sx";
	setAttr -l on ".sy";
	setAttr -l on ".sz";
	setAttr ".rp" -type "double3" 2 6 2.4492935982947094e-16 ;
	setAttr ".sp" -type "double3" 2 6 2.4492935982947094e-16 ;
createNode mesh -n "mesh_leg_second_lShape" -p "mesh_leg_second_l";
	rename -uid "EB86D405-4D42-0DA6-2E3E-1FB0D9896DBA";
	setAttr -k off ".v";
	setAttr -s 4 ".iog[0].og";
	setAttr ".lodv" no;
	setAttr ".vir" yes;
	setAttr ".vif" yes;
	setAttr ".pv" -type "double2" 0.125 0.125 ;
	setAttr ".uvst[0].uvsn" -type "string" "map1";
	setAttr ".cuvs" -type "string" "map1";
	setAttr ".dcc" -type "string" "Ambient+Diffuse";
	setAttr ".covm[0]"  0 1 1;
	setAttr ".cdvm[0]"  0 1 1;
	setAttr ".dfgi" 4905;
	setAttr ".vcs" 2;
	setAttr ".ai_opaque" no;
	setAttr ".ai_translator" -type "string" "polymesh";
createNode mesh -n "mesh_leg_second_lShapeOrig" -p "mesh_leg_second_l";
	rename -uid "191B041C-4018-21BD-A27C-0082C279ADE6";
	setAttr -k off ".v";
	setAttr ".io" yes;
	setAttr ".vir" yes;
	setAttr ".vif" yes;
	setAttr ".uvst[0].uvsn" -type "string" "map1";
	setAttr -s 24 ".uvst[0].uvsp[0:23]" -type "float2" 0.3125 0.1875 0.25
		 0.1875 0.25 0 0.3125 0 0.4375 0.1875 0.375 0.1875 0.375 0 0.4375 0 0.3125 0.1875
		 0.375 0.1875 0.375 0.25 0.3125 0.25 0.375 0.25 0.4375 0.25 0.4375 0.1875 0.375 0.1875
		 0.5 0.1875 0.4375 0.1875 0.4375 0 0.5 0 0.375 0.1875 0.3125 0.1875 0.3125 0 0.375
		 0;
	setAttr ".cuvs" -type "string" "map1";
	setAttr ".dcc" -type "string" "Ambient+Diffuse";
	setAttr ".covm[0]"  0 1 1;
	setAttr ".cdvm[0]"  0 1 1;
	setAttr -s 8 ".vt[0:7]"  -0.050000053 12 -2 -0.050000053 12 2 -0.050000053 -2.9605948e-16 -2
		 -0.050000053 -2.9605948e-16 2 3.95000005 12 2 3.95000005 12 -2 3.95000005 -2.9605948e-16 2
		 3.95000005 -2.9605948e-16 -2;
	setAttr -s 12 ".ed[0:11]"  0 2 0 1 0 0 2 3 0 3 1 0 4 6 0 5 4 0 6 7 0
		 7 5 0 1 4 0 5 0 0 2 7 0 6 3 0;
	setAttr -s 24 ".n[0:23]" -type "float3"  -0.0625 0 -7.6540428e-18 -0.0625
		 0 -7.6540428e-18 -0.0625 0 -7.6540428e-18 -0.0625 0 -7.6540428e-18 0.0625 0 7.6540428e-18
		 0.0625 0 7.6540428e-18 0.0625 0 7.6540428e-18 0.0625 0 7.6540428e-18 0 0.0625 0 0
		 0.0625 0 0 0.0625 0 0 0.0625 0 0 -0.0625 0 0 -0.0625 0 0 -0.0625 0 0 -0.0625 0 7.6540428e-18
		 0 -0.0625 7.6540428e-18 0 -0.0625 7.6540428e-18 0 -0.0625 7.6540428e-18 0 -0.0625
		 -7.6540428e-18 0 0.0625 -7.6540428e-18 0 0.0625 -7.6540428e-18 0 0.0625 -7.6540428e-18
		 0 0.0625;
	setAttr -s 6 -ch 24 ".fc[0:5]" -type "polyFaces" 
		f 4 1 0 2 3
		mu 0 4 0 1 2 3
		f 4 5 4 6 7
		mu 0 4 4 5 6 7
		f 4 8 -6 9 -2
		mu 0 4 8 9 10 11
		f 4 10 -7 11 -3
		mu 0 4 12 13 14 15
		f 4 -10 -8 -11 -1
		mu 0 4 16 17 18 19
		f 4 -9 -4 -12 -5
		mu 0 4 20 21 22 23;
	setAttr ".cd" -type "dataPolyComponent" Index_Data Edge 0 ;
	setAttr ".cvd" -type "dataPolyComponent" Index_Data Vertex 0 ;
	setAttr ".pd[0]" -type "dataPolyComponent" Index_Data UV 0 ;
	setAttr ".hfd" -type "dataPolyComponent" Index_Data Face 0 ;
	setAttr ".dfgi" 4905;
	setAttr ".ai_translator" -type "string" "polymesh";
createNode mesh -n "mesh_leg_second_lShapeOrig1" -p "mesh_leg_second_l";
	rename -uid "31F951F0-439F-0E75-BEFE-6C9316231208";
	setAttr -k off ".v";
	setAttr ".io" yes;
	setAttr ".vir" yes;
	setAttr ".vif" yes;
	setAttr ".uvst[0].uvsn" -type "string" "map1";
	setAttr -s 24 ".uvst[0].uvsp[0:23]" -type "float2" 0.0625 0.1875 0 0.1875
		 0 0 0.0625 0 0.1875 0.1875 0.125 0.1875 0.125 0 0.1875 0 0.0625 0.1875 0.125 0.1875
		 0.125 0.25 0.0625 0.25 0.125 0.25 0.1875 0.25 0.1875 0.1875 0.125 0.1875 0.25 0.1875
		 0.1875 0.1875 0.1875 0 0.25 0 0.125 0.1875 0.0625 0.1875 0.0625 0 0.125 0;
	setAttr ".cuvs" -type "string" "map1";
	setAttr ".dcc" -type "string" "Ambient+Diffuse";
	setAttr ".covm[0]"  0 1 1;
	setAttr ".cdvm[0]"  0 1 1;
	setAttr -s 8 ".pt[0:7]" -type "float3"  -0.23046494 0.30984101 -0.28046361 
		-0.23046494 0.30984101 0.28046361 -0.23046494 -0.30984101 -0.28046361 -0.23046494 
		-0.30984101 0.28046361 0.33046341 0.30984101 0.28046361 0.33046341 0.30984101 -0.28046361 
		0.33046341 -0.30984101 0.28046361 0.33046341 -0.30984101 -0.28046361;
	setAttr -s 8 ".vt[0:7]"  -0.050000053 12 -2 -0.050000053 12 2 -0.050000053 -2.9605882e-16 -2
		 -0.050000053 -2.9605882e-16 2 3.95000005 12 2 3.95000005 12 -2 3.95000005 -2.9605882e-16 2
		 3.95000005 -2.9605882e-16 -2;
	setAttr -s 12 ".ed[0:11]"  0 2 0 1 0 0 2 3 0 3 1 0 4 6 0 5 4 0 6 7 0
		 7 5 0 1 4 0 5 0 0 2 7 0 6 3 0;
	setAttr -s 24 ".n[0:23]" -type "float3"  -0.87701464 0 -1.0740332e-16
		 -0.87701464 0 -1.0740332e-16 -0.87701464 0 -1.0740332e-16 -0.87701464 0 -1.0740332e-16
		 0.87701464 0 1.0740332e-16 0.87701464 0 1.0740332e-16 0.87701464 0 1.0740332e-16
		 0.87701464 0 1.0740332e-16 0 0.95089561 0 0 0.95089561 0 0 0.95089561 0 0 0.95089561
		 0 0 -0.95089561 0 0 -0.95089561 0 0 -0.95089561 0 0 -0.95089561 0 1.0740332e-16 0
		 -0.87701464 1.0740332e-16 0 -0.87701464 1.0740332e-16 0 -0.87701464 1.0740332e-16
		 0 -0.87701464 -1.0740332e-16 0 0.87701464 -1.0740332e-16 0 0.87701464 -1.0740332e-16
		 0 0.87701464 -1.0740332e-16 0 0.87701464;
	setAttr -s 6 -ch 24 ".fc[0:5]" -type "polyFaces" 
		f 4 1 0 2 3
		mu 0 4 0 1 2 3
		f 4 5 4 6 7
		mu 0 4 4 5 6 7
		f 4 8 -6 9 -2
		mu 0 4 8 9 10 11
		f 4 10 -7 11 -3
		mu 0 4 12 13 14 15
		f 4 -10 -8 -11 -1
		mu 0 4 16 17 18 19
		f 4 -9 -4 -12 -5
		mu 0 4 20 21 22 23;
	setAttr ".cd" -type "dataPolyComponent" Index_Data Edge 0 ;
	setAttr ".cvd" -type "dataPolyComponent" Index_Data Vertex 0 ;
	setAttr ".pd[0]" -type "dataPolyComponent" Index_Data UV 0 ;
	setAttr ".hfd" -type "dataPolyComponent" Index_Data Face 0 ;
	setAttr ".dfgi" 4905;
	setAttr ".vcs" 2;
	setAttr ".ai_translator" -type "string" "polymesh";
createNode transform -n "mesh_leg_second_r" -p "grp_mesh_second_layer";
	rename -uid "1EB7F9AE-4731-A81E-7986-889D06EE4D39";
	setAttr -l on ".tx";
	setAttr -l on ".ty";
	setAttr -l on ".tz";
	setAttr -l on ".rx";
	setAttr -l on ".ry";
	setAttr -l on ".rz";
	setAttr -l on ".sx";
	setAttr -l on ".sy";
	setAttr -l on ".sz";
	setAttr ".rp" -type "double3" -2 6 -2.4492935982947094e-16 ;
	setAttr ".sp" -type "double3" -2 6 -2.4492935982947094e-16 ;
createNode mesh -n "mesh_leg_second_rShape" -p "mesh_leg_second_r";
	rename -uid "9E222F0C-4637-3D7F-992B-498B5FE6755F";
	setAttr -k off ".v";
	setAttr -s 4 ".iog[0].og";
	setAttr ".lodv" no;
	setAttr ".vir" yes;
	setAttr ".vif" yes;
	setAttr ".uvst[0].uvsn" -type "string" "map1";
	setAttr ".cuvs" -type "string" "map1";
	setAttr ".dcc" -type "string" "Ambient+Diffuse";
	setAttr ".covm[0]"  0 1 1;
	setAttr ".cdvm[0]"  0 1 1;
	setAttr ".dfgi" 4903;
	setAttr ".vcs" 2;
	setAttr ".ai_opaque" no;
	setAttr ".ai_translator" -type "string" "polymesh";
createNode mesh -n "mesh_leg_second_rShapeOrig" -p "mesh_leg_second_r";
	rename -uid "B52C7B04-42A2-B7AB-FE72-AC866C78D73F";
	setAttr -k off ".v";
	setAttr ".io" yes;
	setAttr ".vir" yes;
	setAttr ".vif" yes;
	setAttr ".uvst[0].uvsn" -type "string" "map1";
	setAttr -s 24 ".uvst[0].uvsp[0:23]" -type "float2" 0.0625 0.6875 0 0.6875
		 0 0.5 0.0625 0.5 0.1875 0.6875 0.125 0.6875 0.125 0.5 0.1875 0.5 0.0625 0.6875 0.125
		 0.6875 0.125 0.75 0.0625 0.75 0.125 0.75 0.1875 0.75 0.1875 0.6875 0.125 0.6875 0.25
		 0.6875 0.1875 0.6875 0.1875 0.5 0.25 0.5 0.125 0.6875 0.0625 0.6875 0.0625 0.5 0.125
		 0.5;
	setAttr ".cuvs" -type "string" "map1";
	setAttr ".dcc" -type "string" "Ambient+Diffuse";
	setAttr ".covm[0]"  0 1 1;
	setAttr ".cdvm[0]"  0 1 1;
	setAttr -s 8 ".vt[0:7]"  -3.95000005 12 -2 -3.95000005 12 2 -3.95000005 -2.9605948e-16 -2
		 -3.95000005 -2.9605948e-16 2 0.050000053 12 2 0.050000053 12 -2 0.050000053 -2.9605948e-16 2
		 0.050000053 -2.9605948e-16 -2;
	setAttr -s 12 ".ed[0:11]"  0 2 0 1 0 0 2 3 0 3 1 0 4 6 0 5 4 0 6 7 0
		 7 5 0 1 4 0 5 0 0 2 7 0 6 3 0;
	setAttr -s 24 ".n[0:23]" -type "float3"  -0.0625 0 -7.6540428e-18 -0.0625
		 0 -7.6540428e-18 -0.0625 0 -7.6540428e-18 -0.0625 0 -7.6540428e-18 0.0625 0 7.6540428e-18
		 0.0625 0 7.6540428e-18 0.0625 0 7.6540428e-18 0.0625 0 7.6540428e-18 0 0.0625 0 0
		 0.0625 0 0 0.0625 0 0 0.0625 0 0 -0.0625 0 0 -0.0625 0 0 -0.0625 0 0 -0.0625 0 7.6540428e-18
		 0 -0.0625 7.6540428e-18 0 -0.0625 7.6540428e-18 0 -0.0625 7.6540428e-18 0 -0.0625
		 -7.6540428e-18 0 0.0625 -7.6540428e-18 0 0.0625 -7.6540428e-18 0 0.0625 -7.6540428e-18
		 0 0.0625;
	setAttr -s 6 -ch 24 ".fc[0:5]" -type "polyFaces" 
		f 4 1 0 2 3
		mu 0 4 0 1 2 3
		f 4 5 4 6 7
		mu 0 4 4 5 6 7
		f 4 8 -6 9 -2
		mu 0 4 8 9 10 11
		f 4 10 -7 11 -3
		mu 0 4 12 13 14 15
		f 4 -10 -8 -11 -1
		mu 0 4 16 17 18 19
		f 4 -9 -4 -12 -5
		mu 0 4 20 21 22 23;
	setAttr ".cd" -type "dataPolyComponent" Index_Data Edge 0 ;
	setAttr ".cvd" -type "dataPolyComponent" Index_Data Vertex 0 ;
	setAttr ".pd[0]" -type "dataPolyComponent" Index_Data UV 0 ;
	setAttr ".hfd" -type "dataPolyComponent" Index_Data Face 0 ;
	setAttr ".dfgi" 4903;
	setAttr ".ai_translator" -type "string" "polymesh";
createNode mesh -n "mesh_leg_second_rShapeOrig1" -p "mesh_leg_second_r";
	rename -uid "BDD3ADFB-4CAC-200E-A05D-5CB85A997265";
	setAttr -k off ".v";
	setAttr ".io" yes;
	setAttr ".vir" yes;
	setAttr ".vif" yes;
	setAttr ".uvst[0].uvsn" -type "string" "map1";
	setAttr -s 24 ".uvst[0].uvsp[0:23]" -type "float2" 0.0625 0.4375 0 0.4375
		 0 0.25 0.0625 0.25 0.1875 0.4375 0.125 0.4375 0.125 0.25 0.1875 0.25 0.0625 0.4375
		 0.125 0.4375 0.125 0.5 0.0625 0.5 0.125 0.5 0.1875 0.5 0.1875 0.4375 0.125 0.4375
		 0.25 0.4375 0.1875 0.4375 0.1875 0.25 0.25 0.25 0.125 0.4375 0.0625 0.4375 0.0625
		 0.25 0.125 0.25;
	setAttr ".cuvs" -type "string" "map1";
	setAttr ".dcc" -type "string" "Ambient+Diffuse";
	setAttr ".covm[0]"  0 1 1;
	setAttr ".cdvm[0]"  0 1 1;
	setAttr -s 8 ".pt[0:7]" -type "float3"  -0.33046493 0.30984101 -0.28046361 
		-0.33046493 0.30984101 0.28046361 -0.33046493 -0.30984101 -0.28046361 -0.33046493 
		-0.30984101 0.28046361 0.23046342 0.30984101 0.28046361 0.23046342 0.30984101 -0.28046361 
		0.23046342 -0.30984101 0.28046361 0.23046342 -0.30984101 -0.28046361;
	setAttr -s 8 ".vt[0:7]"  -3.95000005 12 -2 -3.95000005 12 2 -3.95000005 -2.9606022e-16 -2
		 -3.95000005 -2.9606022e-16 2 0.050000053 12 2 0.050000053 12 -2 0.050000053 -2.9606022e-16 2
		 0.050000053 -2.9606022e-16 -2;
	setAttr -s 12 ".ed[0:11]"  0 2 0 1 0 0 2 3 0 3 1 0 4 6 0 5 4 0 6 7 0
		 7 5 0 1 4 0 5 0 0 2 7 0 6 3 0;
	setAttr -s 24 ".n[0:23]" -type "float3"  -0.87701464 0 -1.0740332e-16
		 -0.87701464 0 -1.0740332e-16 -0.87701464 0 -1.0740332e-16 -0.87701464 0 -1.0740332e-16
		 0.87701464 0 1.0740332e-16 0.87701464 0 1.0740332e-16 0.87701464 0 1.0740332e-16
		 0.87701464 0 1.0740332e-16 0 0.95089561 0 0 0.95089561 0 0 0.95089561 0 0 0.95089561
		 0 0 -0.95089561 0 0 -0.95089561 0 0 -0.95089561 0 0 -0.95089561 0 1.0740332e-16 0
		 -0.87701464 1.0740332e-16 0 -0.87701464 1.0740332e-16 0 -0.87701464 1.0740332e-16
		 0 -0.87701464 -1.0740332e-16 0 0.87701464 -1.0740332e-16 0 0.87701464 -1.0740332e-16
		 0 0.87701464 -1.0740332e-16 0 0.87701464;
	setAttr -s 6 -ch 24 ".fc[0:5]" -type "polyFaces" 
		f 4 1 0 2 3
		mu 0 4 0 1 2 3
		f 4 5 4 6 7
		mu 0 4 4 5 6 7
		f 4 8 -6 9 -2
		mu 0 4 8 9 10 11
		f 4 10 -7 11 -3
		mu 0 4 12 13 14 15
		f 4 -10 -8 -11 -1
		mu 0 4 16 17 18 19
		f 4 -9 -4 -12 -5
		mu 0 4 20 21 22 23;
	setAttr ".cd" -type "dataPolyComponent" Index_Data Edge 0 ;
	setAttr ".cvd" -type "dataPolyComponent" Index_Data Vertex 0 ;
	setAttr ".pd[0]" -type "dataPolyComponent" Index_Data UV 0 ;
	setAttr ".hfd" -type "dataPolyComponent" Index_Data Face 0 ;
	setAttr ".dfgi" 4903;
	setAttr ".vcs" 2;
	setAttr ".ai_translator" -type "string" "polymesh";
createNode transform -n "grp_rigging" -p "extraNodes_GRP";
	rename -uid "92791C54-4A9F-4338-4662-8BB5F81FC0C3";
createNode transform -n "grp_leg_rigging_r" -p "grp_rigging";
	rename -uid "EEAFAFDE-4915-2A25-1779-22AA3EA32E4B";
createNode transform -n "grp_joint_leg_fk_r" -p "grp_leg_rigging_r";
	rename -uid "F435CAB0-49AC-1AE1-8D9B-CDB46964D071";
	setAttr ".rp" -type "double3" -2 0 0 ;
	setAttr ".sp" -type "double3" -2 0 0 ;
createNode joint -n "joint_leg_fk_r" -p "grp_joint_leg_fk_r";
	rename -uid "0DBF13D7-49A0-E1DE-9DC8-C7ACF3B7C6AD";
	setAttr ".v" no;
	setAttr ".ove" yes;
	setAttr ".ovrgbf" yes;
	setAttr ".ovrgb" -type "float3" 1 0.13216358 0.084072948 ;
	setAttr ".t" -type "double3" -2 0 0 ;
	setAttr ".mnrl" -type "double3" -360 -360 -360 ;
	setAttr ".mxrl" -type "double3" 360 360 360 ;
	setAttr ".radi" 3;
	setAttr ".uocol" yes;
	setAttr ".oclr" -type "float3" 1 0.3990109 0.32493308 ;
createNode transform -n "grp_joint_leg_bind_r" -p "grp_leg_rigging_r";
	rename -uid "7EFEFEC1-4D21-BACF-62F7-70B2CACDA3F1";
createNode joint -n "joint_leg_bind_r" -p "grp_joint_leg_bind_r";
	rename -uid "0EAA3283-4152-7B32-0AD0-AD9B3EDB8FA5";
	addAttr -ci true -sn "liw" -ln "lockInfluenceWeights" -min 0 -max 1 -at "bool";
	setAttr ".v" no;
	setAttr ".uoc" 1;
	setAttr ".ove" yes;
	setAttr ".ovrgbf" yes;
	setAttr ".ovrgb" -type "float3" 1 0.13216358 0.084072948 ;
	setAttr ".mnrl" -type "double3" -360 -360 -360 ;
	setAttr ".mxrl" -type "double3" 360 360 360 ;
	setAttr ".bps" -type "matrix" 0.99999999922813898 2.4278695477406022e-05 -3.0891213095490472e-05 0
		 -2.4321874407121765e-05 0.99999902174375588 -0.0013985420900933965 0 3.0857228098450656e-05 0.0013985428403461215 0.99999902156239895 0
		 -2 0 6.1714526964351535e-05 1;
	setAttr ".radi" 2;
	setAttr ".uocol" yes;
	setAttr ".oclr" -type "float3" 1 0.3990109 0.32493308 ;
createNode transform -n "grp_leg_rigging_l" -p "grp_rigging";
	rename -uid "E5AF6F4A-4EC5-4A0C-C8DE-35AC199D44AB";
createNode transform -n "grp_joint_leg_fk_l" -p "grp_leg_rigging_l";
	rename -uid "3E39DADA-4D46-3E90-9868-8EB75A5498C6";
	setAttr ".rp" -type "double3" -2 0 0 ;
	setAttr ".sp" -type "double3" -2 0 0 ;
createNode joint -n "joint_leg_fk_l" -p "grp_joint_leg_fk_l";
	rename -uid "BDA64001-48A9-75A2-FFE2-5997FA13499A";
	setAttr ".v" no;
	setAttr ".ove" yes;
	setAttr ".ovrgbf" yes;
	setAttr ".ovrgb" -type "float3" 1 0.13216358 0.084072948 ;
	setAttr ".t" -type "double3" -2 0 0 ;
	setAttr ".mnrl" -type "double3" -360 -360 -360 ;
	setAttr ".mxrl" -type "double3" 360 360 360 ;
	setAttr ".radi" 3;
	setAttr ".uocol" yes;
	setAttr ".oclr" -type "float3" 1 0.3990109 0.32493308 ;
createNode transform -n "grp_joint_leg_bind_l" -p "grp_leg_rigging_l";
	rename -uid "9CF4B00D-453E-EFC0-7A1D-9F87E26F439E";
createNode joint -n "joint_leg_bind_l" -p "grp_joint_leg_bind_l";
	rename -uid "DA08AF3A-4C73-1979-FEBC-AFA3ECB6CAF6";
	addAttr -ci true -sn "liw" -ln "lockInfluenceWeights" -min 0 -max 1 -at "bool";
	setAttr ".v" no;
	setAttr ".uoc" 1;
	setAttr ".ove" yes;
	setAttr ".ovrgbf" yes;
	setAttr ".ovrgb" -type "float3" 0.053311527 0.35221207 1 ;
	setAttr ".mnrl" -type "double3" -360 -360 -360 ;
	setAttr ".mxrl" -type "double3" 360 360 360 ;
	setAttr ".bps" -type "matrix" 0.99999999922813898 2.4278695477406022e-05 -3.0891213095490472e-05 0
		 -2.4321874407121765e-05 0.99999902174375588 -0.0013985420900933965 0 3.0857228098450656e-05 0.0013985428403461215 0.99999902156239895 0
		 2 0 6.1714526964351535e-05 1;
	setAttr ".radi" 2;
	setAttr ".uocol" yes;
	setAttr ".oclr" -type "float3" 0.26422676 0.62265736 1 ;
createNode transform -n "grp_arm_rigging_l" -p "grp_rigging";
	rename -uid "0FBF726E-4241-BD64-D3E6-E582C0016884";
createNode transform -n "grp_arm_bind_l" -p "grp_arm_rigging_l";
	rename -uid "A7E9EF7E-4D01-46BB-1A19-A88043B51AFA";
	setAttr ".rp" -type "double3" 6 22 0 ;
	setAttr ".sp" -type "double3" 6 22 0 ;
createNode joint -n "joint_arm_bind_l" -p "grp_arm_bind_l";
	rename -uid "56B40488-4B00-2FA7-3A44-8BA91359B6BC";
	addAttr -ci true -sn "liw" -ln "lockInfluenceWeights" -min 0 -max 1 -at "bool";
	setAttr ".v" no;
	setAttr ".uoc" 1;
	setAttr ".mnrl" -type "double3" -360 -360 -360 ;
	setAttr ".mxrl" -type "double3" 360 360 360 ;
	setAttr ".bps" -type "matrix" 1 0 0 0 0 1 0 0 0 0 1 0 6 22 0 1;
	setAttr ".radi" 2;
createNode transform -n "grp_arm_fk_l" -p "grp_arm_rigging_l";
	rename -uid "3C79BCB4-48AD-B4B7-E4D6-FC80FAA9812D";
	setAttr ".rp" -type "double3" 8.0000000000000213 18 0 ;
	setAttr ".sp" -type "double3" 8.0000000000000213 18 0 ;
createNode transform -n "grp_arm_rigging_r" -p "grp_rigging";
	rename -uid "ED2BD859-4986-21EC-9A1E-1A88BA1D8A63";
createNode transform -n "grp_arm_bind_r" -p "grp_arm_rigging_r";
	rename -uid "3E45AB6A-45CE-76D5-A612-B28FF2C30F31";
	setAttr ".rp" -type "double3" -6 22 0 ;
	setAttr ".sp" -type "double3" -6 22 0 ;
createNode joint -n "joint_arm_bind_r" -p "grp_arm_bind_r";
	rename -uid "AE9CDE6F-4375-3945-B86D-21B303117A30";
	addAttr -ci true -sn "liw" -ln "lockInfluenceWeights" -min 0 -max 1 -at "bool";
	setAttr ".v" no;
	setAttr ".uoc" 1;
	setAttr ".mnrl" -type "double3" -360 -360 -360 ;
	setAttr ".mxrl" -type "double3" 360 360 360 ;
	setAttr ".bps" -type "matrix" -1 0 -1.2246467991473532e-16 0 0 1 0 0 1.2246467991473532e-16 0 -1 0
		 -6 22 9.7971748206813428e-16 1;
	setAttr ".radi" 2;
createNode transform -n "grp_arm_fk_r" -p "grp_arm_rigging_r";
	rename -uid "9E282DED-4C2E-308F-D4AA-50999D22BF00";
	setAttr ".rp" -type "double3" 8.0000000000000213 18 0 ;
	setAttr ".sp" -type "double3" 8.0000000000000213 18 0 ;
createNode transform -n "grp_armor_rigging" -p "grp_rigging";
	rename -uid "301909CC-41EC-BF11-1D84-0EA34A11F988";
createNode transform -n "grp_uvcluster_chestplate" -p "grp_armor_rigging";
	rename -uid "21F96445-4199-79FE-8FEC-D5B932A57284";
createNode transform -n "uvCluster_chestplate_l" -p "grp_uvcluster_chestplate";
	rename -uid "86984085-4DA6-A204-B532-8FA8E699C261";
	setAttr ".v" no;
	setAttr -l on -k off ".tz";
	setAttr -l on -k off ".ry";
	setAttr -l on -k off ".rz";
	setAttr -l on -k off ".sz";
createNode clusterHandle -n "uvCluster_chestplate_lShape" -p "uvCluster_chestplate_l";
	rename -uid "9772B28C-4E80-49FC-BA0D-9F851992AB1D";
	setAttr ".ihi" 0;
	setAttr -k off ".v";
createNode transform -n "uvCluster_chestplate_r" -p "grp_uvcluster_chestplate";
	rename -uid "4824C9B0-4A78-58D0-6905-B79D12D94402";
	setAttr ".v" no;
	setAttr -l on -k off ".tz";
	setAttr -l on -k off ".ry";
	setAttr -l on -k off ".rz";
	setAttr -l on -k off ".sz";
createNode clusterHandle -n "uvCluster_chestplate_rShape" -p "uvCluster_chestplate_r";
	rename -uid "02B49ED7-4974-D6CE-414F-238572FF4F29";
	setAttr ".ihi" 0;
	setAttr -k off ".v";
createNode transform -n "uvCluster_chestplace_m" -p "grp_uvcluster_chestplate";
	rename -uid "C5FD28AB-4C35-27E4-BFCF-16AF4F5A0157";
	setAttr ".v" no;
	setAttr -l on -k off ".tz";
	setAttr -l on -k off ".ry";
	setAttr -l on -k off ".rz";
	setAttr -l on -k off ".sz";
createNode clusterHandle -n "uvCluster_chestplace_mShape" -p "uvCluster_chestplace_m";
	rename -uid "7FDF9FCB-48BA-2999-3D27-E2BA30658F65";
	setAttr ".ihi" 0;
	setAttr -k off ".v";
createNode transform -n "grp_uvcluster_helmet" -p "grp_armor_rigging";
	rename -uid "3F5824BD-45EB-4A19-4F6A-E5980369B27B";
createNode transform -n "uvCluster_helmet" -p "grp_uvcluster_helmet";
	rename -uid "922182C2-445C-4F1B-C294-DFB2FFC32A7D";
	setAttr ".v" no;
	setAttr -l on -k off ".tz";
	setAttr -l on -k off ".ry";
	setAttr -l on -k off ".rz";
	setAttr -l on -k off ".sz";
createNode clusterHandle -n "uvCluster_helmetShape" -p "uvCluster_helmet";
	rename -uid "88E7710D-4259-E170-4896-EEB4F296607B";
	setAttr ".ihi" 0;
	setAttr -k off ".v";
createNode transform -n "grp_uvcluster_leggings" -p "grp_armor_rigging";
	rename -uid "2D41F550-4572-2CFA-0CA2-37B825611DB8";
createNode transform -n "uvCluster_leggings_r" -p "grp_uvcluster_leggings";
	rename -uid "425FE5E3-4569-A9AA-47D9-F094F1FEA3D0";
	setAttr ".v" no;
	setAttr -l on -k off ".tz";
	setAttr -l on -k off ".ry";
	setAttr -l on -k off ".rz";
	setAttr -l on -k off ".sz";
createNode clusterHandle -n "uvCluster_leggings_rShape" -p "uvCluster_leggings_r";
	rename -uid "C637EFA7-42E8-04B0-AF16-2FA29B84A369";
	setAttr ".ihi" 0;
	setAttr -k off ".v";
createNode transform -n "uvCluster_leggings_l" -p "grp_uvcluster_leggings";
	rename -uid "4DB1C048-476D-AEF2-7DB3-6BB1D6AF558B";
	setAttr ".v" no;
	setAttr -l on -k off ".tz";
	setAttr -l on -k off ".ry";
	setAttr -l on -k off ".rz";
	setAttr -l on -k off ".sz";
createNode clusterHandle -n "uvCluster_leggings_lShape" -p "uvCluster_leggings_l";
	rename -uid "726326B9-46DE-CBD8-6764-47834E5DF1CD";
	setAttr ".ihi" 0;
	setAttr -k off ".v";
createNode transform -n "uvCluster_leggings_m" -p "grp_uvcluster_leggings";
	rename -uid "7C7D5173-4D18-EE7D-F5C1-8BBFC956430E";
	setAttr ".v" no;
	setAttr -l on -k off ".tz";
	setAttr -l on -k off ".ry";
	setAttr -l on -k off ".rz";
	setAttr -l on -k off ".sz";
createNode clusterHandle -n "uvCluster_leggings_mShape" -p "uvCluster_leggings_m";
	rename -uid "F44F534C-4396-9493-91CD-E9AFF7447B6E";
	setAttr ".ihi" 0;
	setAttr -k off ".v";
createNode transform -n "grp_uvcluster_boots" -p "grp_armor_rigging";
	rename -uid "BDD1C822-427B-D09F-AB47-2081086EBFC1";
createNode transform -n "uvCluster_boots_l" -p "grp_uvcluster_boots";
	rename -uid "50F1F11D-481A-385F-0C19-BF9EBFF30F06";
	setAttr ".v" no;
	setAttr -l on -k off ".tz";
	setAttr -l on -k off ".ry";
	setAttr -l on -k off ".rz";
	setAttr -l on -k off ".sz";
createNode clusterHandle -n "uvCluster_boots_lShape" -p "uvCluster_boots_l";
	rename -uid "DFC0AF82-46E6-317E-7602-0084AB87C52E";
	setAttr ".ihi" 0;
	setAttr -k off ".v";
createNode transform -n "uvCluster_boots_r" -p "grp_uvcluster_boots";
	rename -uid "874B8449-4F97-A776-B086-699A17E8C87B";
	setAttr ".v" no;
	setAttr -l on -k off ".tz";
	setAttr -l on -k off ".ry";
	setAttr -l on -k off ".rz";
	setAttr -l on -k off ".sz";
createNode clusterHandle -n "uvCluster_boots_rShape" -p "uvCluster_boots_r";
	rename -uid "66A8AD90-4B9E-43B7-C9E2-8AB04D8CB8AA";
	setAttr ".ihi" 0;
	setAttr -k off ".v";
createNode transform -n "grp_armor_con_clusters" -p "grp_armor_rigging";
	rename -uid "15E642F2-4CDD-9B8C-77F6-5DBDF7DA37A1";
createNode transform -n "cluster_headcon" -p "grp_armor_con_clusters";
	rename -uid "5C20800C-4DED-16FF-86C2-F1B17E702E71";
	setAttr ".v" no;
	setAttr ".rp" -type "double3" 0 28 0 ;
	setAttr ".sp" -type "double3" 0 28 0 ;
createNode clusterHandle -n "cluster_headconShape" -p "cluster_headcon";
	rename -uid "B90937E7-4E14-9056-AE66-9A9CB24C4F38";
	setAttr ".ihi" 0;
	setAttr -k off ".v";
	setAttr ".or" -type "double3" 0 28 0 ;
createNode transform -n "cluster_bodycon" -p "grp_armor_con_clusters";
	rename -uid "F3C57150-40E2-C835-FF1E-9284021D111C";
	setAttr ".v" no;
	setAttr ".rp" -type "double3" 0 17 0 ;
	setAttr ".sp" -type "double3" 0 17 0 ;
createNode clusterHandle -n "cluster_bodyconShape" -p "cluster_bodycon";
	rename -uid "A8DAC8E5-4A07-9033-3CCB-3F8B2A91EBEB";
	setAttr ".ihi" 0;
	setAttr -k off ".v";
	setAttr ".or" -type "double3" 0 17 0 ;
createNode transform -n "cluster_shouldercon" -p "grp_armor_con_clusters";
	rename -uid "FC3C03D3-4FE0-2549-50E9-89B95A253CA2";
	setAttr ".v" no;
	setAttr ".rp" -type "double3" 0 21.999999999999986 2.2500000000000058 ;
	setAttr ".sp" -type "double3" 0 21.999999999999986 2.2500000000000058 ;
createNode clusterHandle -n "cluster_shoulderconShape" -p "cluster_shouldercon";
	rename -uid "41F5CEDD-44D9-5F49-4B38-40A252E1EB2C";
	setAttr ".ihi" 0;
	setAttr -k off ".v";
	setAttr ".or" -type "double3" 0 21.999999999999986 2.2500000000000058 ;
createNode transform -n "cluster_legikcon_r" -p "grp_armor_con_clusters";
	rename -uid "516E8605-40AC-7D89-8AC0-E6A0977333A8";
	setAttr ".v" no;
	setAttr ".rp" -type "double3" -2 0 0 ;
	setAttr ".sp" -type "double3" -2 0 0 ;
createNode clusterHandle -n "cluster_legikcon_rShape" -p "cluster_legikcon_r";
	rename -uid "9B33299A-4CB0-A05B-1576-95BCDC519DD0";
	setAttr ".ihi" 0;
	setAttr -k off ".v";
	setAttr ".or" -type "double3" -2 1 0.5 ;
createNode transform -n "cluster_legikcon_l" -p "grp_armor_con_clusters";
	rename -uid "AFCA2D0B-4550-9D80-E120-879737E30DE0";
	setAttr ".v" no;
	setAttr ".rp" -type "double3" 2 0 0 ;
	setAttr ".sp" -type "double3" 2 0 0 ;
createNode clusterHandle -n "cluster_legikcon_lShape" -p "cluster_legikcon_l";
	rename -uid "BAE9C197-4A50-DB66-33A5-C8A22884CB1F";
	setAttr ".ihi" 0;
	setAttr -k off ".v";
	setAttr ".or" -type "double3" 2 1 0.5 ;
createNode transform -n "cluster_armfkcon_r" -p "grp_armor_con_clusters";
	rename -uid "20454AA0-4000-6758-E27F-839FCA4E9206";
	setAttr ".v" no;
	setAttr ".rp" -type "double3" -8.0000000000000018 22.000000000000004 6.6613381477509392e-16 ;
	setAttr ".sp" -type "double3" -8.0000000000000018 22.000000000000004 6.6613381477509392e-16 ;
createNode clusterHandle -n "cluster_armfkcon_rShape" -p "cluster_armfkcon_r";
	rename -uid "0967BD9A-4D48-4A14-B49A-6F9A99556C07";
	setAttr ".ihi" 0;
	setAttr -k off ".v";
	setAttr ".or" -type "double3" -8.0000000000000018 22.000000000000004 6.6613381477509392e-16 ;
createNode transform -n "cluster_armfkcon_l" -p "grp_armor_con_clusters";
	rename -uid "92754C85-4FE0-3EF3-D2F9-D3BD02433E8A";
	setAttr ".v" no;
	setAttr ".rp" -type "double3" 8.0000000000000018 22.000000000000004 0 ;
	setAttr ".sp" -type "double3" 8.0000000000000018 22.000000000000004 0 ;
createNode clusterHandle -n "cluster_armfkcon_lShape" -p "cluster_armfkcon_l";
	rename -uid "A2769B5A-4524-AC08-05EC-EF912CB94C95";
	setAttr ".ihi" 0;
	setAttr -k off ".v";
	setAttr ".or" -type "double3" 8.0000000000000018 22.000000000000004 0 ;
createNode transform -n "cluster_legfkcon_l" -p "grp_armor_con_clusters";
	rename -uid "218025BD-48FF-751D-1C82-45BFB6F62431";
	setAttr ".v" no;
	setAttr ".rp" -type "double3" 2 10 0 ;
	setAttr ".sp" -type "double3" 2 10 0 ;
createNode clusterHandle -n "cluster_legfkcon_lShape" -p "cluster_legfkcon_l";
	rename -uid "2D68DB05-4CE1-290B-5505-5B9C6AEDFBC6";
	setAttr ".ihi" 0;
	setAttr -k off ".v";
	setAttr ".or" -type "double3" 2 10 0 ;
createNode transform -n "cluster_legfkcon_r" -p "grp_armor_con_clusters";
	rename -uid "DC9E3E0E-4C1D-4DD7-A637-3092FA819975";
	setAttr ".v" no;
	setAttr ".rp" -type "double3" -2 10 0 ;
	setAttr ".sp" -type "double3" -2 10 0 ;
createNode clusterHandle -n "cluster_legfkcon_rShape" -p "cluster_legfkcon_r";
	rename -uid "0825BE3A-4EAC-F812-673D-A9BB5C3ED0D3";
	setAttr ".ihi" 0;
	setAttr -k off ".v";
	setAttr ".or" -type "double3" -2 10 0 ;
createNode transform -n "cluster_legtopcon" -p "grp_armor_con_clusters";
	rename -uid "23B14587-469D-6781-E3DE-A2B9F6F0937F";
	setAttr ".v" no;
	setAttr ".rp" -type "double3" 0 12.999999999999996 2.2500000000000058 ;
	setAttr ".sp" -type "double3" 0 12.999999999999996 2.2500000000000058 ;
createNode clusterHandle -n "cluster_legtopconShape" -p "cluster_legtopcon";
	rename -uid "BFCA67AC-4434-A8EA-DE5B-E39F5CD232B9";
	setAttr ".ihi" 0;
	setAttr -k off ".v";
	setAttr ".or" -type "double3" 0 12.999999999999996 2.2500000000000058 ;
createNode transform -n "main_CNST" -p "player_RIG";
	rename -uid "6650447D-4114-CF64-2D56-1DAD131DFB97";
createNode transform -n "main_CON" -p "main_CNST";
	rename -uid "B9329EA5-4775-A33A-5153-2BA5EFE82879";
	setAttr ".ove" yes;
	setAttr ".ovrgbf" yes;
	setAttr ".ovrgb" -type "float3" 0.5 0.5 0.5 ;
	setAttr ".uocol" yes;
	setAttr ".oclr" -type "float3" 0.73001599 0.73001599 0.73001599 ;
createNode nurbsCurve -n "main_CONShape" -p "main_CON";
	rename -uid "E3794DF6-4152-A99C-D6D2-479EB92BEEFF";
	setAttr -k off ".v";
	setAttr ".cc" -type "nurbsCurve" 
		1 32 0 no 3
		33 0 1 2 3 4 5 6 7 8 9 10 11 12 13 14 15 16 17 18 19 20 21 22 23 24 25 26 27
		 28 29 30 31 32
		33
		-2 0 4
		-2 0 5
		-3 0 5
		0 0 8
		3 0 5
		2 0 5
		2 0 4
		5 0 4
		5 0 2
		6 0 2
		6 0 3
		9 0 0
		6 0 -3
		6 0 -2
		5 0 -2
		5 0 -4
		2 0 -4
		2 0 -5
		3 0 -5
		0 0 -8
		-3 0 -5
		-2 0 -5
		-2 0 -4
		-5 0 -4
		-5 0 -2
		-6 0 -2
		-6 0 -3
		-9 0 0
		-6 0 3
		-6 0 2
		-5 0 2
		-5 0 4
		-2 0 4
		;
createNode transform -n "head_CNST" -p "main_CON";
	rename -uid "AC9D7E16-46FC-AA7C-F7FA-52A536239AFE";
	setAttr ".rp" -type "double3" 0 24 0 ;
	setAttr ".sp" -type "double3" 0 24 0 ;
createNode transform -n "head_CON" -p "head_CNST";
	rename -uid "4A5D5584-4185-A8FF-BAE8-7AB00B4FF6E3";
	setAttr ".ove" yes;
	setAttr ".ovrgbf" yes;
	setAttr ".ovrgb" -type "float3" 1 1 0.12597102 ;
	setAttr ".rp" -type "double3" 0 24 0 ;
	setAttr ".sp" -type "double3" 0 24 0 ;
	setAttr ".uocol" yes;
	setAttr ".oclr" -type "float3" 1 1 0.39041173 ;
createNode nurbsCurve -n "head_CONShape" -p "head_CON";
	rename -uid "F83CCC96-4C11-7C21-6795-50A7D37360B9";
	setAttr -k off ".v";
	setAttr -s 4 ".iog[0].og";
	setAttr ".tw" yes;
createNode joint -n "head_JNT" -p "head_CON";
	rename -uid "F1B107EF-4796-9DB8-E051-7DBAA25C9E9A";
	addAttr -ci true -sn "liw" -ln "lockInfluenceWeights" -min 0 -max 1 -at "bool";
	setAttr ".v" no;
	setAttr ".uoc" 1;
	setAttr ".t" -type "double3" 0 24 0 ;
	setAttr ".mnrl" -type "double3" -360 -360 -360 ;
	setAttr ".mxrl" -type "double3" 360 360 360 ;
	setAttr ".bps" -type "matrix" 1 0 0 0 0 1 0 0 0 0 1 0 0 24 0 1;
createNode transform -n "outline_CNST" -p "head_CON";
	rename -uid "A8098B33-4547-59AA-2C79-1C8E6A9061C8";
	setAttr ".v" no;
	setAttr ".t" -type "double3" 0 2 0 ;
createNode transform -n "anim_outline" -p "outline_CNST";
	rename -uid "9F66B5F2-442A-52DB-3E46-DFA28E942932";
	setAttr ".ove" yes;
	setAttr ".ovrgbf" yes;
	setAttr ".ovrgb" -type "float3" 0.5 0.5 0.5 ;
	setAttr ".rp" -type "double3" 9.5 28 0 ;
	setAttr ".sp" -type "double3" 9.5 28 0 ;
	setAttr ".uocol" yes;
	setAttr ".oclr" -type "float3" 0.73001599 0.73001599 0.73001599 ;
createNode nurbsCurve -n "anim_outlineShape" -p "anim_outline";
	rename -uid "BF2EDCB3-4966-11DB-3755-B4BEBC14AC92";
	setAttr -k off ".v";
	setAttr ".cc" -type "nurbsCurve" 
		1 4 0 no 3
		5 0 1 2 3 4
		5
		7 26 0
		7 30 0
		12 30 0
		12 26 0
		7 26 0
		;
createNode nurbsCurve -n "anim_outlineShape1" -p "anim_outline";
	rename -uid "655D719B-4C50-EF89-C34B-96BE0CA3C774";
	setAttr -k off ".v";
	setAttr ".cc" -type "nurbsCurve" 
		1 4 0 no 3
		5 0 1 2 3 4
		5
		5.75 26 0
		5.75 30 0
		6.75 30 0
		6.75 26 0
		5.75 26 0
		;
createNode nurbsCurve -n "anim_outlineShape2" -p "anim_outline";
	rename -uid "96E0F09E-4269-7150-F9A6-43B1D8072427";
	setAttr -k off ".v";
	setAttr ".cc" -type "nurbsCurve" 
		1 4 0 no 3
		5 0 1 2 3 4
		5
		12.25 26 0
		12.25 30 0
		13.25 30 0
		13.25 26 0
		12.25 26 0
		;
createNode nurbsCurve -n "anim_outlineShape3" -p "anim_outline";
	rename -uid "7538694B-4488-1529-4832-9095B455F830";
	setAttr -k off ".v";
	setAttr ".cc" -type "nurbsCurve" 
		1 4 0 no 3
		5 0 1 2 3 4
		5
		5.5 25.75 0
		5.5 30.25 0
		13.5 30.25 0
		13.5 25.75 0
		5.5 25.75 0
		;
createNode transform -n "const_facial_settings" -p "anim_outline";
	rename -uid "E2F57550-477F-B398-4515-C79239BFD2BF";
createNode transform -n "anim_facial_settings" -p "const_facial_settings";
	rename -uid "08EDAFDC-469D-CB44-0CB1-29A87D828238";
	addAttr -ci true -sn "HelmetMaterial" -ln "HelmetMaterial" -min 0 -max 32 -at "long";
	addAttr -ci true -sn "ChestplateMaterial" -ln "ChestplateMaterial" -min 0 -max 32 
		-at "long";
	addAttr -ci true -sn "LeggingsMaterial" -ln "LeggingsMaterial" -min 0 -max 32 -at "long";
	addAttr -ci true -sn "BootsMaterial" -ln "BootsMaterial" -min 0 -max 32 -at "long";
	addAttr -ci true -sn "ArmType" -ln "ArmType" -min 0 -max 1 -en "Steve:Alex" -at "enum";
	setAttr -l on ".v";
	setAttr ".ove" yes;
	setAttr ".ovrgbf" yes;
	setAttr ".ovrgb" -type "float3" 1 1 0.12597102 ;
	setAttr -l on -k off ".tx";
	setAttr -l on -k off ".ty";
	setAttr -l on -k off ".tz";
	setAttr -l on -k off ".rx";
	setAttr -l on -k off ".ry";
	setAttr -l on -k off ".rz";
	setAttr -l on -k off ".sx";
	setAttr -l on -k off ".sy";
	setAttr -l on -k off ".sz";
	setAttr ".rp" -type "double3" 5.5 30.5 0 ;
	setAttr ".sp" -type "double3" 5.5 30.5 0 ;
	setAttr ".uocol" yes;
	setAttr ".oclr" -type "float3" 1 1 0.39041173 ;
	setAttr -k on ".HelmetMaterial";
	setAttr -k on ".ChestplateMaterial";
	setAttr -k on ".LeggingsMaterial";
	setAttr -k on ".BootsMaterial";
	setAttr -k on ".ArmType";
createNode nurbsCurve -n "anim_facial_settingsShape" -p "anim_facial_settings";
	rename -uid "C1EC39D1-4280-F9E6-A59A-EA828D438E68";
	setAttr -k off ".v";
	setAttr ".cc" -type "nurbsCurve" 
		1 8 2 no 3
		9 0 1 2 3 4 5 6 7 8
		9
		6.5319849008066972 31.9742643027028 0
		5.4999998282070468 31.9742643027028 0
		5.4999998282070468 31.679411591596555 0
		5.7948526871909998 31.679411591596555 0
		5.7948526871909998 30.5 0
		6.2371320029075585 30.5 0
		6.2371320029075585 31.679411591596555 0
		6.5319849008066972 31.679411591596555 0
		6.5319849008066972 31.9742643027028 0
		;
createNode nurbsCurve -n "anim_facial_settingsShape1" -p "anim_facial_settings";
	rename -uid "2154A133-4D9E-7366-87CB-5C916BFA3C58";
	setAttr -k off ".v";
	setAttr ".cc" -type "nurbsCurve" 
		1 4 2 no 3
		5 0 1 2 3 4
		5
		9.3752090110059925 31.9742643027028 0
		8.9329297575537332 31.9742643027028 0
		8.9329297575537332 30.5 0
		9.3752090110059925 30.5 0
		9.3752090110059925 31.9742643027028 0
		;
createNode nurbsCurve -n "anim_facial_settingsShape2" -p "anim_facial_settings";
	rename -uid "234EEE4E-4137-EA76-AE8F-0ABACB45004E";
	setAttr -k off ".v";
	setAttr ".cc" -type "nurbsCurve" 
		1 12 2 no 3
		13 0 1 2 3 4 5 6 7 8 9 10 11 12
		13
		8.7223205180839329 31.9742643027028 0
		7.6903353443047973 31.9742643027028 0
		7.6903353443047973 30.5 0
		8.1326148468142527 30.5 0
		8.1326148468142527 31.089705795798277 0
		8.2800412646316737 31.089705795798277 0
		8.2800412646316737 30.5 0
		8.7223205180839329 30.5 0
		8.7223205180839329 31.2371321513514 0
		8.574894100266512 31.2371321513514 0
		8.574894100266512 31.384558693697418 0
		8.7223205180839329 31.384558693697418 0
		8.7223205180839329 31.9742643027028 0
		;
createNode nurbsCurve -n "anim_facial_settingsShape3" -p "anim_facial_settings";
	rename -uid "EA51D21D-4501-D048-9BD9-F1AC5BD64031";
	setAttr -k off ".v";
	setAttr ".cc" -type "nurbsCurve" 
		1 10 2 no 3
		11 0 1 2 3 4 5 6 7 8 9 10
		11
		10.617803175197732 31.9742643027028 0
		9.5858182504757909 31.9742643027028 0
		9.5858182504757909 30.5 0
		10.617803175197732 30.5 0
		10.617803175197732 31.384558693697418 0
		10.175523921745471 31.384558693697418 0
		10.175523921745471 30.79485289789914 0
		10.028097503928052 30.79485289789914 0
		10.028097503928052 31.679411591596555 0
		10.617803175197732 31.679411591596555 0
		10.617803175197732 31.9742643027028 0
		;
createNode nurbsCurve -n "anim_facial_settingsShape4" -p "anim_facial_settings";
	rename -uid "8179DC2D-423A-D2C0-7064-93BCDD6DA22A";
	setAttr -k off ".v";
	setAttr ".cc" -type "nurbsCurve" 
		1 4 2 no 3
		5 0 1 2 3 4
		5
		8.2796726599832642 31.678674382299739 0
		8.2796726599832642 31.385295840729935 0
		8.132983202405466 31.385295840729935 0
		8.132983202405466 31.678674382299739 0
		8.2796726599832642 31.678674382299739 0
		;
createNode nurbsCurve -n "anim_facial_settingsShape5" -p "anim_facial_settings";
	rename -uid "EA6EDEF3-475B-EF9E-B035-25B7B910AA0F";
	setAttr -k off ".v";
	setAttr ".cc" -type "nurbsCurve" 
		1 4 2 no 3
		5 0 1 2 3 4
		5
		7.479726229363596 31.384558693697418 0
		6.7425940157478985 31.384558693697418 0
		6.7425940157478985 31.089705795798277 0
		7.479726229363596 31.089705795798277 0
		7.479726229363596 31.384558693697418 0
		;
createNode nurbsCurve -n "head_CONShapeOrig" -p "head_CON";
	rename -uid "62E10AEA-4F7A-CE66-D1CC-E1B3C30C30BE";
	setAttr -k off ".v";
	setAttr ".io" yes;
	setAttr ".cc" -type "nurbsCurve" 
		1 16 0 no 3
		17 0 1 2 3 4 5 6 7 8 9 10 11 12 13 14 15 16
		17
		-4.25 23.75 4.25
		-4.25 32.25 4.25
		-4.25 23.75 4.25
		4.25 23.75 4.25
		4.25 32.25 4.25
		-4.25 32.25 4.25
		-4.25 32.25 -4.25
		4.25 32.25 -4.25
		4.25 32.25 4.25
		4.25 23.75 4.25
		4.25 23.75 -4.25
		4.25 32.25 -4.25
		-4.25 32.25 -4.25
		-4.25 23.75 -4.25
		4.25 23.75 -4.25
		-4.25 23.75 -4.25
		-4.25 23.75 4.25
		;
createNode transform -n "faceLocators_GRP" -p "head_CON";
	rename -uid "5B854307-4526-7966-D849-A78C61A1202E";
	setAttr ".v" no;
	setAttr ".rp" -type "double3" 0 25 11 ;
	setAttr ".sp" -type "double3" 0 25 11 ;
createNode transform -n "t_mouthOpen_LOC" -p "faceLocators_GRP";
	rename -uid "A2DA787A-4E58-E8B6-9F60-818CB7FE6E7E";
	setAttr ".ove" yes;
	setAttr ".ovrgbf" yes;
	setAttr ".ovrgb" -type "float3" 1 0.19511259 0.025308609 ;
	setAttr ".rp" -type "double3" 0 26 5 ;
	setAttr ".sp" -type "double3" 0 26 5 ;
	setAttr ".uocol" yes;
	setAttr ".oclr" -type "float3" 1 0.47620013 0.18840125 ;
createNode locator -n "t_mouthOpen_LOCShape" -p "t_mouthOpen_LOC";
	rename -uid "83AE1B6E-4AEE-2493-F300-FD8AC0FA56EB";
	setAttr -k off ".v";
	setAttr ".lp" -type "double3" 0 26 5 ;
createNode transform -n "m_mouth_projectionGRP" -p "t_mouthOpen_LOC";
	rename -uid "65F7C882-4157-FF5F-2B3F-12AEC4E472EC";
	setAttr ".rp" -type "double3" -4.163336342344337e-17 26 15 ;
	setAttr ".sp" -type "double3" -0.041666666666666685 26 15 ;
	setAttr ".spt" -type "double3" 0.041666666666666685 0 0 ;
createNode place3dTexture -n "m_mouth_projectionHandle" -p "m_mouth_projectionGRP";
	rename -uid "160676FF-42E5-6809-993A-649BBBDB5A8F";
	setAttr ".t" -type "double3" -0.041666666666666664 25 15 ;
createNode transform -n "rt_mouth_projectionGRP" -p "t_mouthOpen_LOC";
	rename -uid "A1B0E491-48C6-CF97-49E9-2FBA5CABCBE7";
	setAttr ".rp" -type "double3" -1.765625 26 14.875 ;
	setAttr ".sp" -type "double3" -1.765625 26 14.875 ;
createNode place3dTexture -n "rt_mouth_projectionHandle" -p "rt_mouth_projectionGRP";
	rename -uid "94C1C5EF-4F41-3DDA-19BC-70834DFEBD2E";
	setAttr ".t" -type "double3" -1.75 25 15 ;
	setAttr ".s" -type "double3" 0.25 1 1 ;
createNode transform -n "lf_mouth_projectionGRP" -p "t_mouthOpen_LOC";
	rename -uid "EF9DCCAC-4549-D38D-2675-2EA4E83EB9CE";
	setAttr ".rp" -type "double3" 1.734375 26 14.875 ;
	setAttr ".sp" -type "double3" 1.734375 26 14.875 ;
createNode place3dTexture -n "lf_mouth_projectionHandle" -p "lf_mouth_projectionGRP";
	rename -uid "2FBEDA3D-4F94-FAC0-CEA0-449A7354C3BF";
	setAttr ".t" -type "double3" 1.75 25 15 ;
	setAttr ".s" -type "double3" 0.25 1 1 ;
createNode transform -n "lf_lip_projectionGRP" -p "t_mouthOpen_LOC";
	rename -uid "A9A37246-4C9E-7FA6-0D06-BD9FC380E7DC";
	setAttr ".rp" -type "double3" 1.7500000000000007 26 15 ;
	setAttr ".sp" -type "double3" 1.1666666666666667 26 15 ;
	setAttr ".spt" -type "double3" 0.58333333333333703 0 0 ;
createNode place3dTexture -n "lf_lip_projectionHandle" -p "lf_lip_projectionGRP";
	rename -uid "2813E56E-46E2-A8F1-BDB8-63BCF5770B08";
	setAttr ".t" -type "double3" 1.166666666666663 25 14 ;
	setAttr ".s" -type "double3" 0.25 1 1 ;
createNode transform -n "m_lip_projectionGRP" -p "t_mouthOpen_LOC";
	rename -uid "C0E634BA-4331-A069-EEC2-EEB9CFF99E0C";
	setAttr ".rp" -type "double3" 0 26 15 ;
	setAttr ".sp" -type "double3" 0 26 15 ;
createNode place3dTexture -n "m_lip_projectionHandle" -p "m_lip_projectionGRP";
	rename -uid "1694F3A3-4ABD-A08B-3DFF-6CAD985E71FF";
	setAttr ".t" -type "double3" 0 25 14 ;
createNode transform -n "rt_lip_projectionGRP" -p "t_mouthOpen_LOC";
	rename -uid "67C43615-4D3B-F235-6C37-A5B7195BFE3A";
	setAttr ".rp" -type "double3" -1.75 26 15 ;
	setAttr ".sp" -type "double3" -1.75 26 15 ;
createNode place3dTexture -n "rt_lip_projectionHandle" -p "rt_lip_projectionGRP";
	rename -uid "AC3BD7F5-45EC-6EC0-014A-59BF4DE16767";
	setAttr ".t" -type "double3" -1.7499999999999982 25 14 ;
	setAttr ".s" -type "double3" 0.25 1 1 ;
createNode transform -n "b_mouthOpen_LOC" -p "faceLocators_GRP";
	rename -uid "B3F8CEBA-4EB9-4D9A-281D-1A9A1CA7EF09";
	setAttr ".ove" yes;
	setAttr ".ovrgbf" yes;
	setAttr ".ovrgb" -type "float3" 1 0.19511259 0.025308609 ;
	setAttr ".rp" -type "double3" 0 24 5 ;
	setAttr ".sp" -type "double3" 0 24 5 ;
	setAttr ".uocol" yes;
	setAttr ".oclr" -type "float3" 1 0.47620013 0.18840125 ;
createNode locator -n "b_mouthOpen_LOCShape" -p "b_mouthOpen_LOC";
	rename -uid "BF99D6CA-4BDB-7F98-8DC6-10A75740B64F";
	setAttr -k off ".v";
	setAttr ".lp" -type "double3" 0 24 5 ;
createNode transform -n "lf_mouthOpen_LOC" -p "faceLocators_GRP";
	rename -uid "1DCBBB86-48FE-9C9A-E5B8-71BCC55A597E";
	setAttr ".ove" yes;
	setAttr ".ovrgbf" yes;
	setAttr ".ovrgb" -type "float3" 1 0.19511259 0.025308609 ;
	setAttr ".rp" -type "double3" 2 25 5 ;
	setAttr ".sp" -type "double3" 2 25 5 ;
	setAttr ".uocol" yes;
	setAttr ".oclr" -type "float3" 1 0.47620013 0.18840125 ;
createNode locator -n "lf_mouthOpen_LOCShape" -p "lf_mouthOpen_LOC";
	rename -uid "4B4A3555-44B4-EABF-2509-89B52D503B4B";
	setAttr -k off ".v";
	setAttr ".lp" -type "double3" 2 25 5 ;
createNode transform -n "rt_mouthOpen_LOC" -p "faceLocators_GRP";
	rename -uid "E98026A6-45D3-8F73-0CA0-228EED36A03D";
	setAttr ".ove" yes;
	setAttr ".ovrgbf" yes;
	setAttr ".ovrgb" -type "float3" 1 0.19511259 0.025308609 ;
	setAttr ".rp" -type "double3" -2 25 5 ;
	setAttr ".sp" -type "double3" -2 25 5 ;
	setAttr ".uocol" yes;
	setAttr ".oclr" -type "float3" 1 0.47620013 0.18840125 ;
createNode locator -n "rt_mouthOpen_LOCShape" -p "rt_mouthOpen_LOC";
	rename -uid "973848C6-4DDE-E800-6F3D-5CAA695AD1BE";
	setAttr -k off ".v";
	setAttr ".lp" -type "double3" -2 25 5 ;
createNode transform -n "b_mouthTeeth_LOC" -p "faceLocators_GRP";
	rename -uid "8FCC755B-4062-A799-DAD9-42B654E9B01E";
	setAttr ".ove" yes;
	setAttr ".ovrgbf" yes;
	setAttr ".ovrgb" -type "float3" 1 0.19511259 0.025308609 ;
	setAttr ".rp" -type "double3" 0 24 7 ;
	setAttr ".sp" -type "double3" 0 24 7 ;
	setAttr ".uocol" yes;
	setAttr ".oclr" -type "float3" 1 0.47620013 0.18840125 ;
createNode locator -n "b_mouthTeeth_LOCShape" -p "b_mouthTeeth_LOC";
	rename -uid "C4CA8A6C-4EAB-B82A-E5FA-BAA57C647E44";
	setAttr -k off ".v";
	setAttr ".lp" -type "double3" 0 24 7 ;
createNode transform -n "b_teeth_projectionGRP" -p "b_mouthTeeth_LOC";
	rename -uid "D9474E12-478A-53A2-F0CC-4BB284DF5E90";
	setAttr ".rp" -type "double3" -0.09375 23.9375 16.875 ;
	setAttr ".sp" -type "double3" -0.09375 23.9375 16.875 ;
createNode place3dTexture -n "b_teeth_projectionHandle" -p "b_teeth_projectionGRP";
	rename -uid "33BC2D74-4BF3-6FC8-9BFB-7E9D531D6462";
	setAttr ".t" -type "double3" 0 24 17 ;
	setAttr ".s" -type "double3" 1.5 0.5 1 ;
createNode transform -n "t_mouthTeeth_LOC" -p "faceLocators_GRP";
	rename -uid "8CD7F021-4B4F-AF62-BD9E-96BDC397D76A";
	setAttr ".ove" yes;
	setAttr ".ovrgbf" yes;
	setAttr ".ovrgb" -type "float3" 1 0.19511259 0.025308609 ;
	setAttr ".rp" -type "double3" 0 26 7 ;
	setAttr ".sp" -type "double3" 0 26 7 ;
	setAttr ".uocol" yes;
	setAttr ".oclr" -type "float3" 1 0.47620013 0.18840125 ;
createNode locator -n "t_mouthTeeth_LOCShape" -p "t_mouthTeeth_LOC";
	rename -uid "70DCBBA7-4BB2-22EE-2492-E8936F0F582E";
	setAttr -k off ".v";
	setAttr ".lp" -type "double3" 0 26 7 ;
createNode transform -n "t_teeth_projectionGRP" -p "t_mouthTeeth_LOC";
	rename -uid "4D33C1E0-4B21-3FA9-2FA9-E5BF115B9B5B";
	setAttr ".rp" -type "double3" -0.09375 25.9375 16.875 ;
	setAttr ".sp" -type "double3" -0.09375 25.9375 16.875 ;
createNode place3dTexture -n "t_teeth_projectionHandle" -p "t_teeth_projectionGRP";
	rename -uid "28D62E9E-43DE-7AF3-1E3E-5F86FD729AFD";
	setAttr ".t" -type "double3" 0 26 17 ;
	setAttr ".s" -type "double3" 1.5 0.5 1 ;
createNode transform -n "mouthTongue_LOC" -p "faceLocators_GRP";
	rename -uid "8E7A1446-41E7-265A-C8F3-3DBE175C3E8A";
	setAttr ".ove" yes;
	setAttr ".ovrgbf" yes;
	setAttr ".ovrgb" -type "float3" 1 0.19511259 0.025308609 ;
	setAttr ".rp" -type "double3" 0 24.5 9 ;
	setAttr ".sp" -type "double3" 0 24.5 9 ;
	setAttr ".uocol" yes;
	setAttr ".oclr" -type "float3" 1 0.47620013 0.18840125 ;
createNode locator -n "mouthTongue_LOCShape" -p "mouthTongue_LOC";
	rename -uid "ACBAC3F1-4384-3B1A-D1CF-3196834D8256";
	setAttr -k off ".v";
	setAttr ".lp" -type "double3" 0 24.5 9 ;
createNode transform -n "tongue_projectionGRP" -p "mouthTongue_LOC";
	rename -uid "31A5A8DE-4149-2104-0E36-69BE01109929";
createNode place3dTexture -n "tongue_projectionHandle" -p "tongue_projectionGRP";
	rename -uid "F791DDBE-4D79-2BA2-147B-5E8CFA3CD7B5";
	setAttr ".t" -type "double3" 1.7763568394002505e-15 24.499999999999996 16 ;
	setAttr ".s" -type "double3" 0.5 0.5 1 ;
createNode transform -n "faceProjections_GRP" -p "head_CON";
	rename -uid "4E6D0CA5-482B-FFCD-953B-879C20CB477D";
	setAttr ".v" no;
createNode place3dTexture -n "rt_eyeWhite_projectionHandle" -p "faceProjections_GRP";
	rename -uid "15132FE2-4F45-0D60-D2CF-0DB61A1A56E9";
	setAttr ".t" -type "double3" -2 27.499999999999996 14 ;
	setAttr ".s" -type "double3" 1 0.5 1 ;
createNode place3dTexture -n "lf_eyeWhite_projectionHandle" -p "faceProjections_GRP";
	rename -uid "7F06323E-4363-4A4C-13C7-78919D2A8500";
	setAttr ".t" -type "double3" 2.0000000000000027 27.5 14 ;
	setAttr ".s" -type "double3" 1 0.5 1 ;
createNode place3dTexture -n "rt_eyePupil_projectionHandle" -p "faceProjections_GRP";
	rename -uid "3D3881A6-4B69-6F75-2A0F-899559D005A6";
	setAttr ".t" -type "double3" -1.5000000000000009 27.499999999999996 16 ;
	setAttr ".s" -type "double3" 0.5 0.5 1 ;
createNode place3dTexture -n "lf_eyePupil_projectionHandle" -p "faceProjections_GRP";
	rename -uid "1066C6C7-46DD-6672-EA60-8C87BA6BD238";
	setAttr ".t" -type "double3" 1.5000000000000027 27.5 16 ;
	setAttr ".s" -type "double3" 0.5 0.5 1 ;
createNode place3dTexture -n "lf_eyebrow_projectionHandle" -p "faceProjections_GRP";
	rename -uid "890CDA70-450F-DE77-2552-AE885CA7EB67";
	setAttr ".t" -type "double3" 2.0000000000000018 30.000000000000004 15 ;
	setAttr ".s" -type "double3" 2 2 1 ;
	setAttr ".rp" -type "double3" 0 -1.5 0 ;
	setAttr ".sp" -type "double3" 0 -0.75 0 ;
	setAttr ".spt" -type "double3" 0 -0.75 0 ;
createNode place3dTexture -n "rt_eyebrow_projectionHandle" -p "faceProjections_GRP";
	rename -uid "39F77A5A-40EF-FB5F-AA6C-DE8F803E0DEA";
	setAttr ".t" -type "double3" -2.0000000000000009 29.999999999999996 15 ;
	setAttr ".r" -type "double3" 0 180 0 ;
	setAttr ".s" -type "double3" 2 2 -1 ;
	setAttr ".rp" -type "double3" 0 -1.5 0 ;
	setAttr ".sp" -type "double3" 0 -0.75 0 ;
	setAttr ".spt" -type "double3" 0 -0.75 0 ;
createNode transform -n "lf_mouthTweak_CNST" -p "head_CON";
	rename -uid "7B7ED371-4B9D-4557-31B7-CA9E437F0DBD";
	setAttr ".v" no;
createNode transform -n "lf_mouthTweak_CON" -p "lf_mouthTweak_CNST";
	rename -uid "DEE0B6A1-4E1D-B588-E141-548D53896938";
	setAttr ".ove" yes;
	setAttr ".ovrgbf" yes;
	setAttr ".ovrgb" -type "float3" 0.23988932 1 0.061915278 ;
	setAttr ".t" -type "double3" -0.5 0 0 ;
	setAttr -l on ".tz";
	setAttr -l on ".rx";
	setAttr -l on ".ry";
	setAttr -l on ".rz";
	setAttr -l on ".sx";
	setAttr -l on ".sy";
	setAttr -l on ".sz";
	setAttr ".rp" -type "double3" 2 25.75 3.9999999999999996 ;
	setAttr ".sp" -type "double3" 2 25.75 3.9999999999999996 ;
	setAttr ".uocol" yes;
	setAttr ".oclr" -type "float3" 0.52302802 1 0.28279793 ;
createNode nurbsCurve -n "lf_mouthTweak_CONShape" -p "lf_mouthTweak_CON";
	rename -uid "8755B140-4A36-645A-3445-C8A42A612C91";
	setAttr -k off ".v";
	setAttr ".cc" -type "nurbsCurve" 
		1 4 0 no 3
		5 0 1 2 3 4
		5
		2 25.5 4.125
		2 26 4.125
		2.5 26.25 4.125
		2.5 25.5 4.125
		2 25.5 4.125
		;
createNode transform -n "rt_mouthTweak_CNST" -p "head_CON";
	rename -uid "6FCAA514-4831-EB53-D818-009E3296B7AB";
	setAttr ".v" no;
createNode transform -n "rt_mouthTweak_CON" -p "rt_mouthTweak_CNST";
	rename -uid "CE5728D5-42DA-56F7-B29E-FFB3B4306A8B";
	setAttr ".ove" yes;
	setAttr ".ovrgbf" yes;
	setAttr ".ovrgb" -type "float3" 0.23988932 1 0.061915278 ;
	setAttr ".t" -type "double3" 0.5 0 0 ;
	setAttr -l on ".tz";
	setAttr -l on ".rx";
	setAttr -l on ".ry";
	setAttr -l on ".rz";
	setAttr -l on ".sx";
	setAttr -l on ".sy";
	setAttr -l on ".sz";
	setAttr ".rp" -type "double3" -2 25.75 4 ;
	setAttr ".sp" -type "double3" -2 25.75 4 ;
	setAttr ".uocol" yes;
	setAttr ".oclr" -type "float3" 0.52302802 1 0.28279793 ;
createNode nurbsCurve -n "rt_mouthTweak_CONShape" -p "rt_mouthTweak_CON";
	rename -uid "E00248F1-47E2-1143-24E6-3E86622B16EB";
	setAttr -k off ".v";
	setAttr ".cc" -type "nurbsCurve" 
		1 4 0 no 3
		5 0 1 2 3 4
		5
		-2 25.5 4.125
		-2 26 4.125
		-2.5 26.25 4.125
		-2.5 25.5 4.125
		-2 25.5 4.125
		;
createNode transform -n "t_mouthTweak_CNST" -p "head_CON";
	rename -uid "67CB7B68-4292-4DD7-156C-63B5CD56E8E2";
	setAttr ".v" no;
createNode transform -n "t_mouthTweak_CON" -p "t_mouthTweak_CNST";
	rename -uid "604BB3E0-47CD-6969-4307-5E95D752270D";
	setAttr ".ove" yes;
	setAttr ".ovrgbf" yes;
	setAttr ".ovrgb" -type "float3" 0.23988932 1 0.061915278 ;
	setAttr ".t" -type "double3" 0 0.00059342228087899684 0 ;
	setAttr -l on ".tx";
	setAttr -l on ".tz";
	setAttr -l on ".rx";
	setAttr -l on ".ry";
	setAttr -l on ".rz";
	setAttr -l on ".sx";
	setAttr -l on ".sy";
	setAttr -l on ".sz";
	setAttr ".rp" -type "double3" -7.1054273576010019e-15 26 4 ;
	setAttr ".sp" -type "double3" -7.1054273576010019e-15 26 4 ;
	setAttr ".uocol" yes;
	setAttr ".oclr" -type "float3" 0.52302802 1 0.28279793 ;
createNode nurbsCurve -n "t_mouthTweak_CONShape" -p "t_mouthTweak_CON";
	rename -uid "F12A5040-4140-BE8A-21C5-DC9E86363FC3";
	setAttr -k off ".v";
	setAttr ".cc" -type "nurbsCurve" 
		1 4 0 no 3
		5 0 1 2 3 4
		5
		-0.75000000000000711 26 4.125
		0.74999999999999289 26 4.125
		0.99999999999999289 26.5 4.125
		-1.0000000000000071 26.5 4.125
		-0.75000000000000711 26 4.125
		;
createNode transform -n "t_teethTweak_CNST" -p "t_mouthTweak_CON";
	rename -uid "B83B4751-4EE9-A2AB-38CD-51B9A08E36F4";
createNode transform -n "t_teethTweak_CON" -p "t_teethTweak_CNST";
	rename -uid "EDE26A73-4178-B667-512F-4F80E32D0308";
	setAttr ".ove" yes;
	setAttr ".ovrgbf" yes;
	setAttr ".ovrgb" -type "float3" 0.23988932 1 0.061915278 ;
	setAttr -l on ".tx";
	setAttr -l on ".tz";
	setAttr -l on ".rx";
	setAttr -l on ".ry";
	setAttr -l on ".rz";
	setAttr -l on ".sx";
	setAttr -l on ".sy";
	setAttr -l on ".sz";
	setAttr ".rp" -type "double3" -7.1054273576010019e-15 25.5 4 ;
	setAttr ".sp" -type "double3" -7.1054273576010019e-15 25.5 4 ;
	setAttr ".uocol" yes;
	setAttr ".oclr" -type "float3" 0.52302802 1 0.28279793 ;
createNode nurbsCurve -n "t_teethTweak_CONShape" -p "t_teethTweak_CON";
	rename -uid "E1D997D1-4A8F-8C77-5539-FE909BFD9854";
	setAttr -k off ".v";
	setAttr ".cc" -type "nurbsCurve" 
		1 4 0 no 3
		5 0 1 2 3 4
		5
		-1.125 25.75 4.125
		1.125 25.75 4.125
		1.2500000000000036 26 4.125
		-1.25 26 4.125
		-1.125 25.75 4.125
		;
createNode transform -n "b_mouthTweak_CNST" -p "head_CON";
	rename -uid "98BAEC8E-4D59-1627-876D-75966F95A1E1";
	setAttr ".v" no;
createNode transform -n "b_mouthTweak_CON" -p "b_mouthTweak_CNST";
	rename -uid "575BA749-49A2-BDB3-498F-14B788335B7E";
	setAttr ".ove" yes;
	setAttr ".ovrgbf" yes;
	setAttr ".ovrgb" -type "float3" 0.23988932 1 0.061915278 ;
	setAttr ".t" -type "double3" 0 2 0 ;
	setAttr -l on ".tx";
	setAttr -l on ".tz";
	setAttr -l on ".rx";
	setAttr -l on ".ry";
	setAttr -l on ".rz";
	setAttr -l on ".sx";
	setAttr -l on ".sy";
	setAttr -l on ".sz";
	setAttr ".rp" -type "double3" -7.1054273576010019e-15 24 4 ;
	setAttr ".sp" -type "double3" -7.1054273576010019e-15 24 4 ;
	setAttr ".uocol" yes;
	setAttr ".oclr" -type "float3" 0.52302802 1 0.28279793 ;
createNode nurbsCurve -n "b_mouthTweak_CONShape" -p "b_mouthTweak_CON";
	rename -uid "1DDD561B-4F0D-DC42-EC43-3DB794DBAF76";
	setAttr -k off ".v";
	setAttr ".cc" -type "nurbsCurve" 
		1 4 0 no 3
		5 0 1 2 3 4
		5
		0.74999999999999289 24 4.125
		-0.75000000000000711 24 4.125
		-1.0000000000000071 23.5 4.125
		0.99999999999999289 23.5 4.125
		0.74999999999999289 24 4.125
		;
createNode transform -n "b_teethTweak_CNST" -p "b_mouthTweak_CON";
	rename -uid "6735B694-4EA4-F760-3AB3-FE82D6051B76";
createNode transform -n "b_teethTweak_CON" -p "b_teethTweak_CNST";
	rename -uid "5C52C9B1-4839-6A1D-768F-508D33631D70";
	setAttr ".ove" yes;
	setAttr ".ovrgbf" yes;
	setAttr ".ovrgb" -type "float3" 0.23988932 1 0.061915278 ;
	setAttr -l on ".tx";
	setAttr -l on ".tz";
	setAttr -l on ".rx";
	setAttr -l on ".ry";
	setAttr -l on ".rz";
	setAttr -l on ".sx";
	setAttr -l on ".sy";
	setAttr -l on ".sz";
	setAttr ".rp" -type "double3" -7.1054273576010019e-15 24.5 4 ;
	setAttr ".sp" -type "double3" -7.1054273576010019e-15 24.5 4 ;
	setAttr ".uocol" yes;
	setAttr ".oclr" -type "float3" 0.52302802 1 0.28279793 ;
createNode nurbsCurve -n "b_teethTweak_CONShape" -p "b_teethTweak_CON";
	rename -uid "72B5B3AD-44AA-F538-5001-A0B0DCCD1E04";
	setAttr -k off ".v";
	setAttr ".cc" -type "nurbsCurve" 
		1 4 0 no 3
		5 0 1 2 3 4
		5
		1.125 24.25 4.125
		-1.125 24.25 4.125
		-1.25 24 4.125
		1.25 24 4.125
		1.125 24.25 4.125
		;
createNode transform -n "b_tongueTweak_CNST" -p "b_mouthTweak_CON";
	rename -uid "4E824980-4FC9-46BD-BB18-BFBAE2E034FB";
createNode transform -n "b_tongueTweak_CON" -p "b_tongueTweak_CNST";
	rename -uid "88A8A954-42CD-B8E3-7599-98B4BD8D440A";
	setAttr ".ove" yes;
	setAttr ".ovrgbf" yes;
	setAttr ".ovrgb" -type "float3" 0.23988932 1 0.061915278 ;
	setAttr -l on ".tz";
	setAttr -l on ".rx";
	setAttr -l on ".ry";
	setAttr -l on ".rz";
	setAttr -l on ".sx";
	setAttr -l on ".sy";
	setAttr -l on ".sz";
	setAttr ".rp" -type "double3" -7.1054273576010019e-15 24.5 4 ;
	setAttr ".sp" -type "double3" -7.1054273576010019e-15 24.5 4 ;
	setAttr ".uocol" yes;
	setAttr ".oclr" -type "float3" 0.52302802 1 0.28279793 ;
createNode nurbsCurve -n "b_tongueTweak_CONShape" -p "b_tongueTweak_CON";
	rename -uid "FEC26C45-47CB-2311-F8B5-C78FF9B7693B";
	setAttr -k off ".v";
	setAttr ".cc" -type "nurbsCurve" 
		1 4 0 no 3
		5 0 1 2 3 4
		5
		0.375 24.875 4.125
		-0.375 24.875 4.125
		-0.375 24.125 4.125
		0.375 24.125 4.125
		0.375 24.875 4.125
		;
createNode transform -n "group1" -p "head_CON";
	rename -uid "1A961B10-435D-83E6-18A3-A88F7FA16357";
	setAttr ".v" no;
createNode transform -n "rt_b_eyelid_CON" -p "group1";
	rename -uid "4401ABE3-4B0D-FD5F-47B4-D1871FCD9DCA";
	setAttr ".ove" yes;
	setAttr ".ovrgbf" yes;
	setAttr ".ovrgb" -type "float3" 1 0.13216358 0.084072948 ;
	setAttr ".rp" -type "double3" -3 27 4 ;
	setAttr ".sp" -type "double3" -3 27 4 ;
	setAttr ".uocol" yes;
	setAttr ".oclr" -type "float3" 1 0.3990109 0.32493308 ;
createNode nurbsCurve -n "rt_b_eyelid_CONShape" -p "rt_b_eyelid_CON";
	rename -uid "3469B261-432F-DD26-E33F-11AF340B5779";
	setAttr -k off ".v";
	setAttr ".cc" -type "nurbsCurve" 
		1 4 0 no 3
		5 0 1 2 3 4
		5
		-3 27 4.125
		-3.75 27 4.125
		-3.5 26.5 4.125
		-3 26.5 4.125
		-3 27 4.125
		;
createNode transform -n "rt_t_eyelid_CON" -p "group1";
	rename -uid "F9905C9D-45A0-D1EE-5957-6DA3579A8E05";
	setAttr ".ove" yes;
	setAttr ".ovrgbf" yes;
	setAttr ".ovrgb" -type "float3" 1 0.13216358 0.084072948 ;
	setAttr ".rp" -type "double3" -3 28 4 ;
	setAttr ".sp" -type "double3" -3 28 4 ;
	setAttr ".uocol" yes;
	setAttr ".oclr" -type "float3" 1 0.3990109 0.32493308 ;
createNode nurbsCurve -n "rt_t_eyelid_CONShape" -p "rt_t_eyelid_CON";
	rename -uid "11951668-429C-2C5E-8C04-5C8E9596C0DD";
	setAttr -k off ".v";
	setAttr ".cc" -type "nurbsCurve" 
		1 4 0 no 3
		5 0 1 2 3 4
		5
		-3 28 4.125
		-3.75 28 4.125
		-3.5 28.5 4.125
		-3 28.5 4.125
		-3 28 4.125
		;
createNode transform -n "rt_eyebrow_CON" -p "group1";
	rename -uid "ED672497-42A6-6A3A-B019-C7A865E2701B";
	setAttr ".ove" yes;
	setAttr ".ovrgbf" yes;
	setAttr ".ovrgb" -type "float3" 1 0.13216358 0.084072948 ;
	setAttr ".rp" -type "double3" -2 28.5 4 ;
	setAttr ".sp" -type "double3" -2 28.5 4 ;
	setAttr ".uocol" yes;
	setAttr ".oclr" -type "float3" 1 0.3990109 0.32493308 ;
createNode nurbsCurve -n "rt_eyebrow_CONShape" -p "rt_eyebrow_CON";
	rename -uid "D4F8CFF2-4DEE-0D10-AE59-4ABDC3D920A8";
	setAttr -k off ".v";
	setAttr ".cc" -type "nurbsCurve" 
		1 4 0 no 3
		5 0 1 2 3 4
		5
		-1.125 28.875 4.125
		-2.875 28.875 4.125
		-2.875 28.125 4.125
		-1.125 28.125 4.125
		-1.125 28.875 4.125
		;
createNode transform -n "rt_eyePupil_CON" -p "group1";
	rename -uid "3E00A06C-4FE2-D629-0FCD-E4B702225A3D";
	setAttr ".ove" yes;
	setAttr ".ovrgbf" yes;
	setAttr ".ovrgb" -type "float3" 0.23988932 1 0.061915278 ;
	setAttr ".rp" -type "double3" -1.5 27.5 4 ;
	setAttr ".sp" -type "double3" -1.5 27.5 4 ;
	setAttr ".uocol" yes;
	setAttr ".oclr" -type "float3" 0.52302802 1 0.28279793 ;
createNode nurbsCurve -n "rt_eyePupil_CONShape" -p "rt_eyePupil_CON";
	rename -uid "B4D3CA20-4599-EDDC-AEE0-7E82563F296A";
	setAttr -k off ".v";
	setAttr ".cc" -type "nurbsCurve" 
		1 4 0 no 3
		5 0 1 2 3 4
		5
		-1.125 27.875 4.125
		-1.875 27.875 4.125
		-1.875 27.125 4.125
		-1.125 27.125 4.125
		-1.125 27.875 4.125
		;
createNode transform -n "lf_eyePupil_CON" -p "group1";
	rename -uid "AAC2C180-4CF4-15ED-5852-74AAC1647EA3";
	setAttr ".ove" yes;
	setAttr ".ovrgbf" yes;
	setAttr ".ovrgb" -type "float3" 0.23988932 1 0.061915278 ;
	setAttr ".rp" -type "double3" 1.5 27.5 4 ;
	setAttr ".sp" -type "double3" 1.5 27.5 4 ;
	setAttr ".uocol" yes;
	setAttr ".oclr" -type "float3" 0.52302802 1 0.28279793 ;
createNode nurbsCurve -n "lf_eyePupil_CONShape" -p "lf_eyePupil_CON";
	rename -uid "6DF8CF3B-4D95-A0D1-E819-E9B6FC516B62";
	setAttr -k off ".v";
	setAttr ".cc" -type "nurbsCurve" 
		1 4 0 no 3
		5 0 1 2 3 4
		5
		1.875 27.875 4.125
		1.125 27.875 4.125
		1.125 27.125 4.125
		1.875 27.125 4.125
		1.875 27.875 4.125
		;
createNode transform -n "lf_eyebrow_CON" -p "group1";
	rename -uid "788FAF58-4F90-6BB1-1E14-2BAC3AD85B7A";
	setAttr ".ove" yes;
	setAttr ".ovrgbf" yes;
	setAttr ".ovrgb" -type "float3" 0.053311527 0.35221207 1 ;
	setAttr ".rp" -type "double3" 2 28.5 4 ;
	setAttr ".sp" -type "double3" 2 28.5 4 ;
	setAttr ".uocol" yes;
	setAttr ".oclr" -type "float3" 0.26422676 0.62265736 1 ;
createNode nurbsCurve -n "lf_eyebrow_CONShape" -p "lf_eyebrow_CON";
	rename -uid "AA38D192-481E-B245-5837-59A26112BABA";
	setAttr -k off ".v";
	setAttr ".cc" -type "nurbsCurve" 
		1 4 0 no 3
		5 0 1 2 3 4
		5
		2.875 28.875 4.125
		1.125 28.875 4.125
		1.125 28.125 4.125
		2.875 28.125 4.125
		2.875 28.875 4.125
		;
createNode transform -n "lf_t_eyelid_CON" -p "group1";
	rename -uid "090C40E3-47C4-EB2B-4D41-0D9743DAFF04";
	setAttr ".ove" yes;
	setAttr ".ovrgbf" yes;
	setAttr ".ovrgb" -type "float3" 0.053311527 0.35221207 1 ;
	setAttr ".rp" -type "double3" 3 28 3.9999999999999991 ;
	setAttr ".sp" -type "double3" 3 28 3.9999999999999991 ;
	setAttr ".uocol" yes;
	setAttr ".oclr" -type "float3" 0.26422676 0.62265736 1 ;
createNode nurbsCurve -n "lf_t_eyelid_CONShape" -p "lf_t_eyelid_CON";
	rename -uid "E60573D8-4E09-DCFE-C405-E5859A2A3D84";
	setAttr -k off ".v";
	setAttr ".cc" -type "nurbsCurve" 
		1 4 0 no 3
		5 0 1 2 3 4
		5
		3 28 4.1249999999999991
		3.75 28 4.1249999999999991
		3.5 28.5 4.1249999999999991
		3 28.5 4.1249999999999991
		3 28 4.1249999999999991
		;
createNode transform -n "lf_b_eyelid_CON" -p "group1";
	rename -uid "EBAA2D94-4B82-8A2A-93D7-7D9EC54AF342";
	setAttr ".ove" yes;
	setAttr ".ovrgbf" yes;
	setAttr ".ovrgb" -type "float3" 0.053311527 0.35221207 1 ;
	setAttr ".rp" -type "double3" 3 27 3.9999999999999991 ;
	setAttr ".sp" -type "double3" 3 27 3.9999999999999991 ;
	setAttr ".uocol" yes;
	setAttr ".oclr" -type "float3" 0.26422676 0.62265736 1 ;
createNode nurbsCurve -n "lf_b_eyelid_CONShape" -p "lf_b_eyelid_CON";
	rename -uid "E4A4F73B-4FED-9A99-41C3-5E9E33C35B11";
	setAttr -k off ".v";
	setAttr ".cc" -type "nurbsCurve" 
		1 4 0 no 3
		5 0 1 2 3 4
		5
		3 27 4.1249999999999991
		3.75 27 4.1249999999999991
		3.5 26.5 4.1249999999999991
		3 26.5 4.1249999999999991
		3 27 4.1249999999999991
		;
createNode pointConstraint -n "const_head_pointConstraint1" -p "head_CNST";
	rename -uid "EECDF466-4F12-F3FE-72BF-31BB3C019555";
	addAttr -dcb 0 -ci true -k true -sn "w0" -ln "locator_body_topW0" -dv 1 -min 0 
		-at "double";
	setAttr -k on ".nds";
	setAttr -k off ".v";
	setAttr -k off ".tx";
	setAttr -k off ".ty";
	setAttr -k off ".tz";
	setAttr -k off ".rx";
	setAttr -k off ".ry";
	setAttr -k off ".rz";
	setAttr -k off ".sx";
	setAttr -k off ".sy";
	setAttr -k off ".sz";
	setAttr ".erp" yes;
	setAttr -k on ".w0";
createNode transform -n "const_leg_ik_l" -p "main_CON";
	rename -uid "51C62C50-4302-BE9F-1BE0-CB843A0FA2FD";
	setAttr ".t" -type "double3" 4 0 0 ;
createNode transform -n "anim_leg_ik_l" -p "const_leg_ik_l";
	rename -uid "96B4646B-46E4-C4D3-8F21-909BCCDF868A";
	setAttr ".ove" yes;
	setAttr ".ovrgbf" yes;
	setAttr ".ovrgb" -type "float3" 0.053311527 0.35221207 1 ;
	setAttr ".rp" -type "double3" -2 0 0 ;
	setAttr ".sp" -type "double3" -2 0 0 ;
	setAttr ".uocol" yes;
	setAttr ".oclr" -type "float3" 0.26422676 0.62265736 1 ;
createNode nurbsCurve -n "anim_leg_ik_lShape" -p "anim_leg_ik_l";
	rename -uid "C3E1898C-4DF5-BE0F-C936-DF9DCA3E693C";
	setAttr -k off ".v";
	setAttr -s 4 ".iog[0].og";
	setAttr ".tw" yes;
createNode transform -n "grp_joint_leg_bl" -p "anim_leg_ik_l";
	rename -uid "CA411092-4512-D093-9E31-60B3FA333D1F";
createNode joint -n "joint_leg_bl" -p "grp_joint_leg_bl";
	rename -uid "2BA66391-4C1E-DF4E-42FC-C3BB171103D5";
	addAttr -ci true -sn "liw" -ln "lockInfluenceWeights" -min 0 -max 1 -at "bool";
	setAttr ".v" no;
	setAttr ".t" -type "double3" -2 0 6.1714529672596621e-05 ;
	setAttr ".r" -type "double3" -0.080130587784201748 0.001769936100318742 0.0013910667915187805 ;
	setAttr ".mnrl" -type "double3" -360 -360 -360 ;
	setAttr ".mxrl" -type "double3" 360 360 360 ;
	setAttr ".bps" -type "matrix" 1 0 0 0 0 0.99999902174260602 -0.0013987543855059519 0
		 0 0.0013987543855059519 0.99999902174260602 0 2 0 6.1714529672596621e-05 1;
createNode joint -n "joint_leg_tl" -p "joint_leg_bl";
	rename -uid "CDD87B9C-434D-5321-F4FC-9B96BB131C12";
	setAttr ".v" no;
	setAttr ".oc" 1;
	setAttr ".t" -type "double3" 0 3 0 ;
	setAttr ".mnrl" -type "double3" -360 -360 -360 ;
	setAttr ".mxrl" -type "double3" 360 360 360 ;
createNode ikEffector -n "effector" -p "joint_leg_bl";
	rename -uid "2C061ACC-453F-8CD2-81B9-64BD0EF4FA01";
	setAttr ".v" no;
	setAttr ".hd" yes;
createNode transform -n "locator_leg_pole_l" -p "grp_joint_leg_bl";
	rename -uid "5904BD75-427C-638A-C8F3-C08C75CCCAD4";
	setAttr ".v" no;
	setAttr ".t" -type "double3" -2 0 -3.25 ;
	setAttr ".rp" -type "double3" 2 0 3.25 ;
	setAttr ".sp" -type "double3" 2 0 3.25 ;
createNode locator -n "locator_leg_pole_lShape" -p "locator_leg_pole_l";
	rename -uid "15A5D2C4-4D6F-9A9D-11C7-1280B7542D09";
	setAttr -k off ".v";
	setAttr ".lp" -type "double3" 2 0 3.25 ;
createNode transform -n "locator_leg_bottom_l" -p "anim_leg_ik_l";
	rename -uid "C4BAFB15-42FC-1098-DA89-06855A1EAEA7";
	setAttr ".v" no;
	setAttr ".t" -type "double3" -2 0 0 ;
createNode locator -n "locator_leg_bottom_lShape" -p "locator_leg_bottom_l";
	rename -uid "D6DB24F7-4347-A6FA-F23A-0493FD9B8468";
	setAttr -k off ".v";
createNode nurbsCurve -n "anim_leg_ik_lShapeOrig" -p "anim_leg_ik_l";
	rename -uid "EBA5D45B-4FA4-3693-9BF0-61B8382D0D1E";
	setAttr -k off ".v";
	setAttr ".io" yes;
	setAttr ".cc" -type "nurbsCurve" 
		1 12 0 no 3
		13 0 1 2 3 4 5 6 7 8 9 10 11 12
		13
		-4.25 0 2.25
		-2 0 3.25
		0.25 0 2.25
		0.25 0 -2.25
		0.25 2 -2.25
		0.25 0 2.25
		0.25 0 -2.25
		-4.25 0 -2.25
		-4.25 2 -2.25
		0.25 2 -2.25
		-4.25 2 -2.25
		-4.25 0 2.25
		-4.25 0 -2.25
		;
createNode transform -n "grp_ff_l" -p "anim_leg_ik_l";
	rename -uid "90CDAB44-4B60-9588-FD8B-9F9B843A7C7F";
	setAttr ".t" -type "double3" -4 0 -6.9388939039072284e-18 ;
createNode joint -n "joint_ff_l" -p "grp_ff_l";
	rename -uid "C9249ED8-4F96-CC9F-3CDA-FDAA39E5F869";
	setAttr ".v" no;
	setAttr ".t" -type "double3" 2 0 0 ;
	setAttr ".mnrl" -type "double3" -360 -360 -360 ;
	setAttr ".mxrl" -type "double3" 360 360 360 ;
	setAttr ".radi" 0.5;
createNode joint -n "joint_ff_end_l" -p "joint_ff_l";
	rename -uid "455EF34B-495E-786C-D42E-8F8DB8A0953A";
	setAttr ".v" no;
	setAttr ".t" -type "double3" 0 1 0 ;
	setAttr ".mnrl" -type "double3" -360 -360 -360 ;
	setAttr ".mxrl" -type "double3" 360 360 360 ;
	setAttr ".radi" 0.5;
createNode ikEffector -n "effector3" -p "joint_ff_l";
	rename -uid "8A81D089-42DF-729F-3846-0E97C26E2114";
	setAttr ".v" no;
	setAttr ".hd" yes;
createNode transform -n "const_leg_ik_r" -p "main_CON";
	rename -uid "1594A07B-43F5-741F-1772-A4BB6E9A5D2D";
createNode transform -n "anim_leg_ik_r" -p "const_leg_ik_r";
	rename -uid "CEB9889B-4743-F99B-7D83-699A43FFD526";
	setAttr ".ove" yes;
	setAttr ".ovrgbf" yes;
	setAttr ".ovrgb" -type "float3" 1 0.13216358 0.084072948 ;
	setAttr ".rp" -type "double3" -2 0 0 ;
	setAttr ".sp" -type "double3" -2 0 0 ;
	setAttr ".uocol" yes;
	setAttr ".oclr" -type "float3" 1 0.3990109 0.32493308 ;
createNode nurbsCurve -n "anim_leg_ik_rShape" -p "anim_leg_ik_r";
	rename -uid "81C981A4-4BAA-7621-308D-7D889B0CC8A1";
	setAttr -k off ".v";
	setAttr -s 4 ".iog[0].og";
	setAttr ".tw" yes;
createNode transform -n "grp_joint_leg_br" -p "anim_leg_ik_r";
	rename -uid "79020E58-43C7-5A58-B35F-CF89C7C35B68";
createNode joint -n "joint_leg_br" -p "grp_joint_leg_br";
	rename -uid "00516CBC-41D1-AC62-6E06-639FCF1F4F6C";
	addAttr -ci true -sn "liw" -ln "lockInfluenceWeights" -min 0 -max 1 -at "bool";
	setAttr ".v" no;
	setAttr ".t" -type "double3" -2 0 6.1714529672596621e-05 ;
	setAttr ".r" -type "double3" -0.080130587784201734 0.0017699361003187426 0.0013910667915198402 ;
	setAttr ".mnrl" -type "double3" -360 -360 -360 ;
	setAttr ".mxrl" -type "double3" 360 360 360 ;
	setAttr ".bps" -type "matrix" 0.99999999922813898 2.427869561320537e-05 -3.0891212495557642e-05 0
		 -2.4321874543369485e-05 0.99999902174369748 -0.0013985421318188649 0 3.0857227497313657e-05 0.0013985428820715794 0.99999902156234055 0
		 -2 0 6.1714529672596621e-05 1;
createNode joint -n "joint_leg_tr" -p "joint_leg_br";
	rename -uid "34247EE7-4C5F-376B-A580-37A12058128D";
	setAttr ".v" no;
	setAttr ".oc" 1;
	setAttr ".t" -type "double3" 0 3 0 ;
	setAttr ".mnrl" -type "double3" -360 -360 -360 ;
	setAttr ".mxrl" -type "double3" 360 360 360 ;
createNode ikEffector -n "effector1" -p "joint_leg_br";
	rename -uid "53213BCB-4C68-EC55-CFE0-83943560BDC5";
	setAttr ".v" no;
	setAttr ".hd" yes;
createNode transform -n "locator_leg_pole_r" -p "grp_joint_leg_br";
	rename -uid "EC66C4AB-4F1F-8D37-2E16-E8B30F19C192";
	setAttr ".v" no;
	setAttr ".t" -type "double3" 2 0 -3.25 ;
	setAttr ".rp" -type "double3" -2 0 3.25 ;
	setAttr ".sp" -type "double3" -2 0 3.25 ;
createNode locator -n "locator_leg_pole_rShape" -p "locator_leg_pole_r";
	rename -uid "B327858A-40D6-9373-E42D-1DA0DB2B90F9";
	setAttr -k off ".v";
	setAttr ".lp" -type "double3" -2 0 3.25 ;
createNode transform -n "locator_leg_bottom_r" -p "anim_leg_ik_r";
	rename -uid "34CB7AC6-452B-83A2-C89E-B8B9E0B6C30F";
	setAttr ".v" no;
	setAttr ".t" -type "double3" -2 0 0 ;
createNode locator -n "locator_leg_bottom_rShape" -p "locator_leg_bottom_r";
	rename -uid "CB2E0B12-422B-55D1-8F15-0CBA757F3807";
	setAttr -k off ".v";
createNode transform -n "grp_ff_r" -p "anim_leg_ik_r";
	rename -uid "CFE77532-45EE-79D2-CD73-1B8FB12AEFC0";
	setAttr ".t" -type "double3" -4 0 0 ;
createNode joint -n "joint_ff_r" -p "grp_ff_r";
	rename -uid "28D51F15-4CE2-88C3-C3E2-DE87D32A414F";
	setAttr ".v" no;
	setAttr ".t" -type "double3" 2 0 0 ;
	setAttr -l on ".ry";
	setAttr ".ro" 3;
	setAttr ".mnrl" -type "double3" -360 -360 -360 ;
	setAttr ".mxrl" -type "double3" 360 360 360 ;
	setAttr ".radi" 0.5;
createNode joint -n "joint_ff_end_r" -p "joint_ff_r";
	rename -uid "DDBCF25A-4816-3B0D-A2CC-79AA59991FB3";
	setAttr ".v" no;
	setAttr ".t" -type "double3" 0 1 0 ;
	setAttr ".mnrl" -type "double3" -360 -360 -360 ;
	setAttr ".mxrl" -type "double3" 360 360 360 ;
	setAttr ".radi" 0.5;
createNode ikEffector -n "effector5" -p "joint_ff_r";
	rename -uid "6ED96AEA-4768-3E34-9328-F58113974C34";
	setAttr ".v" no;
	setAttr ".hd" yes;
createNode nurbsCurve -n "anim_leg_ik_rShapeOrig" -p "anim_leg_ik_r";
	rename -uid "058CF753-476B-1233-5E03-599D1D77B230";
	setAttr -k off ".v";
	setAttr ".io" yes;
	setAttr ".cc" -type "nurbsCurve" 
		1 12 0 no 3
		13 0 1 2 3 4 5 6 7 8 9 10 11 12
		13
		-4.25 0 2.25
		-2 0 3.25
		0.25 0 2.25
		0.25 0 -2.25
		0.25 2 -2.25
		0.25 0 2.25
		0.25 0 -2.25
		-4.25 0 -2.25
		-4.25 2 -2.25
		0.25 2 -2.25
		-4.25 2 -2.25
		-4.25 0 2.25
		-4.25 0 -2.25
		;
createNode transform -n "const_body" -p "main_CON";
	rename -uid "01EA6506-4374-1B88-13B4-1CBF44346EF8";
createNode transform -n "anim_body" -p "const_body";
	rename -uid "E1BDDBB4-45F7-1DAF-DD0B-DEB8F73BE8D7";
	setAttr ".ove" yes;
	setAttr ".ovrgbf" yes;
	setAttr ".ovrgb" -type "float3" 1 1 0.12597102 ;
	setAttr ".rp" -type "double3" 0 14 0 ;
	setAttr ".sp" -type "double3" 0 14 0 ;
	setAttr ".uocol" yes;
	setAttr ".oclr" -type "float3" 1 1 0.39041173 ;
createNode nurbsCurve -n "anim_bodyShape" -p "anim_body";
	rename -uid "C01B6FB3-4A74-C940-28B6-77A452E8AF8A";
	setAttr -k off ".v";
	setAttr -s 4 ".iog[0].og";
	setAttr ".tw" yes;
createNode transform -n "const_leg_top_r" -p "anim_body";
	rename -uid "30333A11-4E50-4485-6F85-18BC48CD7DAF";
createNode transform -n "anim_leg_top_r" -p "const_leg_top_r";
	rename -uid "1563A827-4F10-37B8-F936-ABB89E44377C";
	setAttr ".ove" yes;
	setAttr ".ovrgbf" yes;
	setAttr ".ovrgb" -type "float3" 1 0.13216358 0.084072948 ;
	setAttr ".rp" -type "double3" -2 12 0 ;
	setAttr ".sp" -type "double3" -2 12 0 ;
	setAttr ".uocol" yes;
	setAttr ".oclr" -type "float3" 1 0.3990109 0.32493308 ;
createNode nurbsCurve -n "anim_leg_top_rShape" -p "anim_leg_top_r";
	rename -uid "6C174B0C-4A17-F66F-C4CE-2A9365A19370";
	setAttr -k off ".v";
	setAttr -s 4 ".iog[0].og";
	setAttr ".tw" yes;
createNode transform -n "locator_leg_top_r" -p "anim_leg_top_r";
	rename -uid "411D7C59-49E9-0753-B247-F5AA9837302C";
	setAttr ".v" no;
	setAttr ".t" -type "double3" -2 12 0 ;
createNode locator -n "locator_leg_top_rShape" -p "locator_leg_top_r";
	rename -uid "1109A0B1-4839-9CF0-7A2B-02B5DFB9C89D";
	setAttr -k off ".v";
createNode transform -n "const_leg_fk_r" -p "anim_leg_top_r";
	rename -uid "5598A16D-4D22-AD29-2A7D-8FA6D5B5E441";
	setAttr ".t" -type "double3" -2 0 0 ;
createNode transform -n "anim_leg_fk_r" -p "const_leg_fk_r";
	rename -uid "66AF8C05-4B5F-C1B3-24FC-C8B5A869F9FE";
	setAttr ".rp" -type "double3" 0 12 0 ;
	setAttr ".sp" -type "double3" 0 12 0 ;
createNode nurbsCurve -n "anim_leg_fk_rShape" -p "anim_leg_fk_r";
	rename -uid "2CB5A788-4048-7E54-F289-BFB17D074D80";
	setAttr -k off ".v";
	setAttr -s 4 ".iog[0].og";
	setAttr ".tw" yes;
createNode nurbsCurve -n "anim_leg_fk_rShapeOrig" -p "anim_leg_fk_r";
	rename -uid "0463E646-4E0C-E9C9-3107-0DB827FC5081";
	setAttr -k off ".v";
	setAttr ".io" yes;
	setAttr ".cc" -type "nurbsCurve" 
		1 23 0 no 3
		24 0 1 2 3 4 5 6 7 8 9 10 11 12 13 14 15 16 17 18 19 20 21 22 23
		24
		-2.25 9 2.25
		0 9 2.75
		2.25 9 2.25
		2.25 9 -2.25
		0 9 -2.75
		-2.25 9 -2.25
		-2.25 9 2.25
		-2.25 11 2.25
		0 11 2.75
		0 9 2.75
		0 11 2.75
		2.25 11 2.25
		2.25 9 2.25
		2.25 11 2.25
		2.25 11 -2.25
		2.25 9 -2.25
		2.25 11 -2.25
		0 11 -2.75
		0 9 -2.75
		0 11 -2.75
		-2.25 11 -2.25
		-2.25 9 -2.25
		-2.25 11 -2.25
		-2.25 11 2.25
		;
createNode transform -n "const_anim_leg_settings_r" -p "anim_leg_top_r";
	rename -uid "2B38948B-4057-2C20-24DE-BCADBD67B572";
createNode transform -n "anim_leg_settings_r" -p "const_anim_leg_settings_r";
	rename -uid "562B29DE-4E19-F074-5076-30813E91FC65";
	addAttr -ci true -sn "IKBend" -ln "IKBend" -nn "IK Bend" -min 0 -max 1 -at "double";
	addAttr -ci true -sn "IKFKControl" -ln "IKFKControl" -nn "IK/FK Control" -min 0 
		-max 1 -at "double";
	addAttr -ci true -sn "FancyFeet" -ln "FancyFeet" -min 0 -max 1 -at "double";
	setAttr ".ove" yes;
	setAttr ".ovrgbf" yes;
	setAttr ".ovrgb" -type "float3" 1 0.13216358 0.084072948 ;
	setAttr -l on -k off ".tx";
	setAttr -l on -k off ".ty";
	setAttr -l on -k off ".tz";
	setAttr -l on -k off ".rx";
	setAttr -l on -k off ".ry";
	setAttr -l on -k off ".rz";
	setAttr -l on -k off ".sx";
	setAttr -l on -k off ".sy";
	setAttr -l on -k off ".sz";
	setAttr ".rp" -type "double3" -5.5 10 -1.1102230246251565e-16 ;
	setAttr ".sp" -type "double3" -5.5 10 -1.1102230246251565e-16 ;
	setAttr ".uocol" yes;
	setAttr ".oclr" -type "float3" 1 0.3990109 0.32493308 ;
	setAttr -k on ".IKBend";
	setAttr -k on ".IKFKControl";
	setAttr -k on ".FancyFeet" 1;
createNode nurbsCurve -n "anim_leg_settings_rShape" -p "anim_leg_settings_r";
	rename -uid "0BBAF0C7-430B-3A79-F2F7-399D878E993F";
	setAttr -k off ".v";
	setAttr ".cc" -type "nurbsCurve" 
		1 10 0 no 3
		11 0 0.61803398874989479 1.2360679774997898 1.8541019662496847 2.4721359549995796
		 3.0901699437494745 3.7082039324993694 4.3262379212492643 4.9442719099991592 5.5623058987490541
		 6.180339887498949
		11
		-5.5 11 3.9429924734491463e-16
		-5.7310785424358244 10.318052328017499 7.9759308208603444e-17
		-6.4510565162951536 10.309016994374948 4.5130644051866784e-17
		-5.8738929357319485 9.8785148208983458 -1.8389439331043348e-16
		-6.0877852522924734 9.1909830056250534 -5.1983602388061319e-16
		-5.5 9.6068657021683084 -3.4684134210891825e-16
		-4.9122147477075266 9.1909830056250534 -5.1983602388061319e-16
		-5.1261070642680515 9.8785148208983458 -1.8389439331043348e-16
		-4.5489434837048464 10.309016994374947 4.513064405186671e-17
		-5.2689214575641756 10.318052328017499 7.9759308208603444e-17
		-5.5 11 3.9429924734491463e-16
		;
createNode transform -n "grp_joint_leg_rot_tr" -p "anim_leg_top_r";
	rename -uid "4389441F-49E1-C9FC-F80A-6E9C92CAF88A";
	setAttr ".rp" -type "double3" -2 0 0 ;
	setAttr ".sp" -type "double3" -2 0 0 ;
createNode transform -n "grp_joint_leg_tr" -p "grp_joint_leg_rot_tr";
	rename -uid "9E37869F-4B02-7B5C-6C5B-E5B72F08E94F";
createNode joint -n "joint_leg_aim_r" -p "grp_joint_leg_tr";
	rename -uid "4976F9BA-4409-E301-03E8-6FB74B8DEE96";
	setAttr ".v" no;
	setAttr ".t" -type "double3" -2 12 -0.016723354517169331 ;
	setAttr ".mnrl" -type "double3" -360 -360 -360 ;
	setAttr ".mxrl" -type "double3" 360 360 360 ;
createNode transform -n "grp_ikHandle_leg_r" -p "joint_leg_aim_r";
	rename -uid "BCFCB4F3-4682-A2C8-BCFC-91B734B9606A";
	setAttr ".t" -type "double3" 0 9 0 ;
	setAttr ".rp" -type "double3" 0 -9 0 ;
	setAttr ".sp" -type "double3" 0 -9 0 ;
createNode ikHandle -n "ikHandle_leg_r" -p "grp_ikHandle_leg_r";
	rename -uid "112D88E7-46CD-8B48-5B1F-8B9F838B373E";
	setAttr ".v" no;
	setAttr ".t" -type "double3" 0 -9 0 ;
	setAttr ".roc" yes;
createNode poleVectorConstraint -n "ikHandle_leg_r_poleVectorConstraint1" -p "ikHandle_leg_r";
	rename -uid "6BFCF1D9-40F0-30E8-0FAB-A2B5BAE06AB8";
	addAttr -dcb 0 -ci true -k true -sn "w0" -ln "locator_leg_pole_rW0" -dv 1 -min 
		0 -at "double";
	setAttr -k on ".nds";
	setAttr -k off ".v";
	setAttr -k off ".tx";
	setAttr -k off ".ty";
	setAttr -k off ".tz";
	setAttr -k off ".rx";
	setAttr -k off ".ry";
	setAttr -k off ".rz";
	setAttr -k off ".sx";
	setAttr -k off ".sy";
	setAttr -k off ".sz";
	setAttr ".erp" yes;
	setAttr ".rst" -type "double3" 0 0 3.2499382854703271 ;
	setAttr -k on ".w0";
createNode transform -n "grp_ikHandle_ff_rotX_r" -p "grp_joint_leg_tr";
	rename -uid "D113CA4E-41E5-943C-FFC4-06B1672D106A";
	setAttr ".t" -type "double3" -0.00030460968721758238 11 0.034904812874567023 ;
	setAttr ".r" -type "double3" 0 -1 0 ;
createNode transform -n "grp_ikHandle_ff_rotZ_r" -p "grp_joint_leg_tr";
	rename -uid "E7DC682C-4814-ED42-4CFA-58B4C23D9ABE";
	setAttr ".t" -type "double3" -0.00030460968721758238 11 0.034904812874567023 ;
	setAttr ".r" -type "double3" 0 -1 0 ;
createNode ikHandle -n "ikHandle_ff_rotZ_r" -p "grp_ikHandle_ff_rotZ_r";
	rename -uid "359F6D47-4A76-E324-B0D1-1985E178F38A";
	setAttr ".v" no;
	setAttr ".t" -type "double3" -2 1 0 ;
	setAttr ".pv" -type "double3" 2 0 0 ;
	setAttr ".roc" yes;
createNode transform -n "locator_leg_top_bend_r" -p "grp_joint_leg_tr";
	rename -uid "B3BFD414-4803-5127-94BD-B8A0F416A6CC";
	setAttr ".v" no;
	setAttr ".t" -type "double3" -2 12 0 ;
	setAttr ".r" -type "double3" 0 -1 0 ;
createNode locator -n "locator_leg_top_bend_rShape" -p "locator_leg_top_bend_r";
	rename -uid "EDA76F39-4EF4-D9A5-2A90-88BA8DAAE619";
	setAttr -k off ".v";
createNode nurbsCurve -n "anim_leg_top_rShapeOrig" -p "anim_leg_top_r";
	rename -uid "988D048D-4B2B-6B93-72A8-B5B400681911";
	setAttr -k off ".v";
	setAttr ".io" yes;
	setAttr ".cc" -type "nurbsCurve" 
		1 6 0 no 3
		7 0 1 2 3 4 5 6
		7
		-2.5 13.749999999999996 2.2500000000000062
		-1.5 13.749999999999996 2.2500000000000062
		-1 12.999999999999996 2.2500000000000058
		-1.5 12.249999999999996 2.2500000000000053
		-2.5 12.249999999999996 2.2500000000000053
		-3 12.999999999999996 2.2500000000000058
		-2.5 13.749999999999996 2.2500000000000062
		;
createNode transform -n "const_leg_top_l" -p "anim_body";
	rename -uid "045AFF60-43F0-7B07-E759-0F8CE20D6032";
	setAttr ".t" -type "double3" 4 0 0 ;
createNode transform -n "anim_leg_top_l" -p "const_leg_top_l";
	rename -uid "915E4AF2-496B-1757-C8F9-E083E858A701";
	setAttr ".ove" yes;
	setAttr ".ovrgbf" yes;
	setAttr ".ovrgb" -type "float3" 0.053311527 0.35221207 1 ;
	setAttr ".rp" -type "double3" -2 12 0 ;
	setAttr ".sp" -type "double3" -2 12 0 ;
	setAttr ".uocol" yes;
	setAttr ".oclr" -type "float3" 0.26422676 0.62265736 1 ;
createNode nurbsCurve -n "anim_leg_top_lShape" -p "anim_leg_top_l";
	rename -uid "1D145369-4B42-FDB9-317F-F788E12CA7BD";
	setAttr -k off ".v";
	setAttr -s 4 ".iog[0].og";
	setAttr ".tw" yes;
createNode transform -n "locator_leg_top_l" -p "anim_leg_top_l";
	rename -uid "BC594F76-4E0F-D615-163B-FE8CE46FD7B9";
	setAttr ".v" no;
	setAttr ".t" -type "double3" -2 12 0 ;
createNode locator -n "locator_leg_top_lShape" -p "locator_leg_top_l";
	rename -uid "C95E25E3-4565-BD03-DE96-5A9E0253978C";
	setAttr -k off ".v";
createNode transform -n "const_leg_fk_l" -p "anim_leg_top_l";
	rename -uid "686632E8-4DD1-85F2-68D5-AABCC0F8A616";
	setAttr ".t" -type "double3" -2 0 0 ;
createNode transform -n "anim_leg_fk_l" -p "const_leg_fk_l";
	rename -uid "E2448CE1-48B5-8CC4-EAE3-659446D6E8C1";
	setAttr ".r" -type "double3" 9.1633365789695223 8.6053045383350764 22.838193776881376 ;
	setAttr ".rp" -type "double3" 0 12 0 ;
	setAttr ".sp" -type "double3" 0 12 0 ;
createNode nurbsCurve -n "anim_leg_fk_lShape" -p "anim_leg_fk_l";
	rename -uid "DF7E09BE-4355-5C82-5751-77A5717651B3";
	setAttr -k off ".v";
	setAttr -s 4 ".iog[0].og";
	setAttr ".tw" yes;
createNode nurbsCurve -n "anim_leg_fk_lShapeOrig" -p "anim_leg_fk_l";
	rename -uid "430DA529-47B1-7DD1-7FCF-7A8D1545433C";
	setAttr -k off ".v";
	setAttr ".io" yes;
	setAttr ".cc" -type "nurbsCurve" 
		1 23 0 no 3
		24 0 1 2 3 4 5 6 7 8 9 10 11 12 13 14 15 16 17 18 19 20 21 22 23
		24
		-2.25 9 2.25
		0 9 2.75
		2.25 9 2.25
		2.25 9 -2.25
		0 9 -2.75
		-2.25 9 -2.25
		-2.25 9 2.25
		-2.25 11 2.25
		0 11 2.75
		0 9 2.75
		0 11 2.75
		2.25 11 2.25
		2.25 9 2.25
		2.25 11 2.25
		2.25 11 -2.25
		2.25 9 -2.25
		2.25 11 -2.25
		0 11 -2.75
		0 9 -2.75
		0 11 -2.75
		-2.25 11 -2.25
		-2.25 9 -2.25
		-2.25 11 -2.25
		-2.25 11 2.25
		;
createNode transform -n "const_anim_leg_settings_l" -p "anim_leg_top_l";
	rename -uid "69AD592A-4916-0028-9165-0780EB724783";
	setAttr ".t" -type "double3" 7 0 0 ;
createNode transform -n "anim_leg_settings_l" -p "const_anim_leg_settings_l";
	rename -uid "E05A875C-42BC-6B42-FD6C-978267D062BA";
	addAttr -ci true -sn "IKBend" -ln "IKBend" -nn "IK Bend" -min 0 -max 1 -at "double";
	addAttr -ci true -sn "IKFKControl" -ln "IKFKControl" -nn "IK/FK Control" -min 0 
		-max 1 -at "double";
	addAttr -ci true -sn "FancyFeet" -ln "FancyFeet" -nn "Fancy Feet" -min 0 -max 1 
		-at "double";
	setAttr ".ove" yes;
	setAttr ".ovrgbf" yes;
	setAttr ".ovrgb" -type "float3" 0.053311527 0.35221207 1 ;
	setAttr -l on -k off ".tx";
	setAttr -l on -k off ".ty";
	setAttr -l on -k off ".tz";
	setAttr -l on -k off ".rx";
	setAttr -l on -k off ".ry";
	setAttr -l on -k off ".rz";
	setAttr -l on -k off ".sx";
	setAttr -l on -k off ".sy";
	setAttr -l on -k off ".sz";
	setAttr ".rp" -type "double3" -5.5 10 -1.1102230246251565e-16 ;
	setAttr ".sp" -type "double3" -5.5 10 -1.1102230246251565e-16 ;
	setAttr ".uocol" yes;
	setAttr ".oclr" -type "float3" 0.26422676 0.62265736 1 ;
	setAttr -k on ".IKBend" 1;
	setAttr -k on ".IKFKControl";
	setAttr -k on ".FancyFeet" 1;
createNode nurbsCurve -n "anim_leg_settings_lShape" -p "anim_leg_settings_l";
	rename -uid "7BE79342-4984-5EFD-F246-90B16C7FBDDC";
	setAttr -k off ".v";
	setAttr ".cc" -type "nurbsCurve" 
		1 10 0 no 3
		11 0 0.61803398874989479 1.2360679774997898 1.8541019662496847 2.4721359549995796
		 3.0901699437494745 3.7082039324993694 4.3262379212492643 4.9442719099991592 5.5623058987490541
		 6.180339887498949
		11
		-5.5 11 3.9429924734491463e-16
		-5.7310785424358244 10.318052328017499 7.9759308208603444e-17
		-6.4510565162951536 10.309016994374948 4.5130644051866784e-17
		-5.8738929357319485 9.8785148208983458 -1.8389439331043348e-16
		-6.0877852522924734 9.1909830056250534 -5.1983602388061319e-16
		-5.5 9.6068657021683084 -3.4684134210891825e-16
		-4.9122147477075266 9.1909830056250534 -5.1983602388061319e-16
		-5.1261070642680515 9.8785148208983458 -1.8389439331043348e-16
		-4.5489434837048464 10.309016994374947 4.513064405186671e-17
		-5.2689214575641756 10.318052328017499 7.9759308208603444e-17
		-5.5 11 3.9429924734491463e-16
		;
createNode transform -n "grp_joint_leg_rot_tl" -p "anim_leg_top_l";
	rename -uid "ECC1D768-4CFD-18FB-8D7C-E0A138A7DAF3";
	setAttr ".rp" -type "double3" -2 0 0 ;
	setAttr ".sp" -type "double3" -2 0 0 ;
createNode transform -n "grp_joint_leg_tl" -p "grp_joint_leg_rot_tl";
	rename -uid "F4F7BBF5-45C4-953A-AB77-E59AD417F1EC";
createNode joint -n "joint_leg_aim_l" -p "grp_joint_leg_tl";
	rename -uid "31C84B95-4FFC-10BE-1A9D-C38B0736E9BE";
	setAttr ".v" no;
	setAttr ".t" -type "double3" -2 12 -0.016723354517169331 ;
	setAttr ".mnrl" -type "double3" -360 -360 -360 ;
	setAttr ".mxrl" -type "double3" 360 360 360 ;
createNode transform -n "grp_ikHandle_leg_l" -p "joint_leg_aim_l";
	rename -uid "95891AFE-4628-EE38-1244-E384E95E88D9";
	setAttr ".t" -type "double3" 0 9 0 ;
	setAttr ".rp" -type "double3" 0 -9 0 ;
	setAttr ".sp" -type "double3" 0 -9 0 ;
createNode ikHandle -n "ikHandle_leg_l" -p "grp_ikHandle_leg_l";
	rename -uid "29C930B6-42E1-07C1-8CE0-E9A17E640ECB";
	setAttr ".v" no;
	setAttr ".t" -type "double3" 0 -9 0 ;
	setAttr ".roc" yes;
createNode poleVectorConstraint -n "ikHandle_leg_l_poleVectorConstraint1" -p "ikHandle_leg_l";
	rename -uid "A39BB80E-4564-576A-0358-C4A02F2A8DB4";
	addAttr -dcb 0 -ci true -k true -sn "w0" -ln "locator_leg_pole_lW0" -dv 1 -min 
		0 -at "double";
	setAttr -k on ".nds";
	setAttr -k off ".v";
	setAttr -k off ".tx";
	setAttr -k off ".ty";
	setAttr -k off ".tz";
	setAttr -k off ".rx";
	setAttr -k off ".ry";
	setAttr -k off ".rz";
	setAttr -k off ".sx";
	setAttr -k off ".sy";
	setAttr -k off ".sz";
	setAttr ".erp" yes;
	setAttr ".rst" -type "double3" 0 0 3.2499382854703271 ;
	setAttr -k on ".w0";
createNode transform -n "locator_leg_bend_top_l" -p "grp_joint_leg_tl";
	rename -uid "AFE3E7CD-4F4F-D13E-F3A1-6EAE0235FE8C";
	setAttr ".v" no;
	setAttr ".t" -type "double3" -2 12 -6.9388939039072284e-18 ;
	setAttr ".r" -type "double3" 0 -1 0 ;
createNode locator -n "locator_leg_bend_top_lShape" -p "locator_leg_bend_top_l";
	rename -uid "97227A1A-4542-A9F6-14EB-F0A47D57B88A";
	setAttr -k off ".v";
createNode transform -n "grp_ikHandle_ff_rotX_l" -p "grp_joint_leg_tl";
	rename -uid "EFF84ED1-4E9B-08F7-794E-3798C9248A28";
	setAttr ".t" -type "double3" -3.9996953903127825 11 -0.03490481287456703 ;
	setAttr ".r" -type "double3" 0 -1 0 ;
	setAttr ".rp" -type "double3" 2 1 0 ;
	setAttr ".rpt" -type "double3" -0.00030460968721780443 0 0.034904812874567023 ;
	setAttr ".sp" -type "double3" 2 1 0 ;
createNode transform -n "grp_ikHandle_ff_rotZ_l" -p "grp_joint_leg_tl";
	rename -uid "B78FC4ED-408A-A7C0-17A6-04A5AB7FF8A8";
	setAttr ".t" -type "double3" -3.9996953903127825 11 -0.03490481287456703 ;
	setAttr ".r" -type "double3" 0 -1 0 ;
	setAttr ".rp" -type "double3" 2 1 0 ;
	setAttr ".rpt" -type "double3" -0.00030460968721780443 0 0.034904812874567023 ;
	setAttr ".sp" -type "double3" 2 1 0 ;
createNode ikHandle -n "ikHandle_ff_rotZ_l" -p "grp_ikHandle_ff_rotZ_l";
	rename -uid "EC884054-4F04-A02E-0B08-E886BB1CB25B";
	setAttr ".v" no;
	setAttr ".t" -type "double3" 2 1 0 ;
	setAttr ".pv" -type "double3" 2 0 0 ;
	setAttr ".roc" yes;
createNode nurbsCurve -n "anim_leg_top_lShapeOrig" -p "anim_leg_top_l";
	rename -uid "070BC930-4E32-89B1-7C16-119203F70618";
	setAttr -k off ".v";
	setAttr ".io" yes;
	setAttr ".cc" -type "nurbsCurve" 
		1 6 0 no 3
		7 0 1 2 3 4 5 6
		7
		-2.5 13.749999999999996 2.2500000000000062
		-1.5 13.749999999999996 2.2500000000000062
		-1 12.999999999999996 2.2500000000000058
		-1.5 12.249999999999996 2.2500000000000053
		-2.5 12.249999999999996 2.2500000000000053
		-3 12.999999999999996 2.2500000000000058
		-2.5 13.749999999999996 2.2500000000000062
		;
createNode joint -n "joint_body" -p "anim_body";
	rename -uid "7A4C788C-471B-96C4-3ED7-F49496931D92";
	addAttr -ci true -sn "liw" -ln "lockInfluenceWeights" -min 0 -max 1 -at "bool";
	setAttr ".v" no;
	setAttr ".uoc" 1;
	setAttr ".t" -type "double3" 0 14 0 ;
	setAttr ".mnrl" -type "double3" -360 -360 -360 ;
	setAttr ".mxrl" -type "double3" 360 360 360 ;
	setAttr ".bps" -type "matrix" 1 0 0 0 0 1 0 0 0 0 1 0 0 14 0 1;
	setAttr ".radi" 0.5;
createNode transform -n "locator_body_top" -p "anim_body";
	rename -uid "E759D0C1-4576-D591-7668-928144671251";
	setAttr ".v" no;
	setAttr ".rp" -type "double3" 0 24 0 ;
	setAttr ".sp" -type "double3" 0 24 0 ;
createNode locator -n "locator_body_topShape" -p "locator_body_top";
	rename -uid "77AC4A66-4FBF-1F3D-37E3-EBBFCBB75FA7";
	setAttr -k off ".v";
	setAttr ".lp" -type "double3" 0 24 0 ;
createNode transform -n "const_shoulder_r" -p "anim_body";
	rename -uid "E235E2D5-4AE1-7875-E1C1-289A698C3067";
	setAttr ".s" -type "double3" -1 1 1 ;
createNode transform -n "anim_shoulder_r" -p "const_shoulder_r";
	rename -uid "2A7EDC91-4140-9DDF-9110-559DC18A2641";
	setAttr ".ove" yes;
	setAttr ".ovrgbf" yes;
	setAttr ".ovrgb" -type "float3" 1 0.13216358 0.084072948 ;
	setAttr ".rp" -type "double3" 2 22 0 ;
	setAttr ".sp" -type "double3" 2 22 0 ;
	setAttr ".uocol" yes;
	setAttr ".oclr" -type "float3" 1 0.3990109 0.32493308 ;
createNode nurbsCurve -n "anim_shoulder_rShape" -p "anim_shoulder_r";
	rename -uid "40BAF1B9-42B8-EEC8-E1F5-ED8D88EB5999";
	setAttr -k off ".v";
	setAttr -s 4 ".iog[0].og";
	setAttr ".tw" yes;
createNode transform -n "const_arm_settings_r" -p "anim_shoulder_r";
	rename -uid "F311B6D2-4C9B-B1DC-AC41-84BC39F7809E";
createNode transform -n "anim_arm_settings_r" -p "const_arm_settings_r";
	rename -uid "D7006B99-4650-7607-130F-52A18DB7B226";
	addAttr -ci true -sn "IKFKControl" -ln "IKFKControl" -nn "IK/FK Control" -min 0 
		-max 1 -at "double";
	addAttr -ci true -sn "WorldLocalIK" -ln "WorldLocalIK" -nn "World/Local IK" -min 
		0 -max 1 -at "double";
	setAttr ".ove" yes;
	setAttr ".ovrgbf" yes;
	setAttr ".ovrgb" -type "float3" 1 0.13216358 0.084072948 ;
	setAttr -l on -k off ".tx";
	setAttr -l on -k off ".ty";
	setAttr -l on -k off ".tz";
	setAttr -l on -k off ".rx";
	setAttr -l on -k off ".ry";
	setAttr -l on -k off ".rz";
	setAttr -l on -k off ".sx";
	setAttr -l on -k off ".sy";
	setAttr -l on -k off ".sz";
	setAttr ".rp" -type "double3" 6 26 0 ;
	setAttr ".sp" -type "double3" 6 26 0 ;
	setAttr ".uocol" yes;
	setAttr ".oclr" -type "float3" 1 0.3990109 0.32493308 ;
	setAttr -k on ".IKFKControl" 1;
	setAttr -k on ".WorldLocalIK";
createNode nurbsCurve -n "anim_arm_settings_rShape" -p "anim_arm_settings_r";
	rename -uid "182CB345-4FC1-0120-4075-89B3BE016EF9";
	setAttr -k off ".v";
	setAttr ".tw" yes;
createNode nurbsCurve -n "anim_reg_settings_r1Shape" -p "anim_arm_settings_r";
	rename -uid "86FB3C74-4C1C-B6DA-1DE4-B18FDD951F73";
	setAttr -k off ".v";
	setAttr ".cc" -type "nurbsCurve" 
		1 10 0 no 3
		11 0 0.61803398874989479 1.2360679774997898 1.8541019662496847 2.4721359549995796
		 3.0901699437494745 3.7082039324993694 4.3262379212492643 4.9442719099991592 5.5623058987490541
		 6.180339887498949
		11
		6 27 3.9429924734491463e-16
		5.7689214575641756 26.318052328017501 7.9759308208603444e-17
		5.0489434837048464 26.30901699437495 4.5130644051866784e-17
		5.6261070642680515 25.878514820898346 -1.8389439331043348e-16
		5.4122147477075266 25.190983005625053 -5.1983602388061319e-16
		6 25.606865702168307 -3.4684134210891825e-16
		6.5877852522924734 25.190983005625053 -5.1983602388061319e-16
		6.3738929357319485 25.878514820898346 -1.8389439331043348e-16
		6.9510565162951536 26.309016994374947 4.513064405186671e-17
		6.2310785424358244 26.318052328017501 7.9759308208603444e-17
		6 27 3.9429924734491463e-16
		;
createNode nurbsCurve -n "anim_shoulder_rShapeOrig" -p "anim_shoulder_r";
	rename -uid "65B73035-4243-7423-50D5-2190B9E80F62";
	setAttr -k off ".v";
	setAttr ".io" yes;
	setAttr ".cc" -type "nurbsCurve" 
		1 6 0 no 3
		7 0 1 2 3 4 5 6
		7
		1.7939957663596084 23.213977501875174 2.2500000000000062
		3.2060042336403924 23.213977501875174 2.2500000000000062
		3.9120084672807844 21.999999999999996 2.2500000000000058
		3.2060042336403924 20.786022498124801 2.2500000000000053
		2.5 22.000000000000007 2.2500000000000053
		1.0879915327192142 21.999999999999996 2.2500000000000058
		1.7939957663596084 23.213977501875174 2.2500000000000062
		;
createNode transform -n "grp_alex_arm_offset_r" -p "anim_shoulder_r";
	rename -uid "832404E4-46F7-FBA5-E9B4-A096E9E8759A";
createNode transform -n "grp_arm_ik_top_r" -p "grp_alex_arm_offset_r";
	rename -uid "176C3719-4AEB-7957-6D12-4C9655C537BC";
	setAttr ".t" -type "double3" 6 22 0 ;
	setAttr ".rp" -type "double3" 6 22 0 ;
	setAttr ".sp" -type "double3" 6 22 0 ;
createNode joint -n "joint_arm_ik_top_r" -p "grp_arm_ik_top_r";
	rename -uid "6555C23B-4770-3880-2352-24A9CAE1DA2E";
	setAttr ".v" no;
	setAttr ".t" -type "double3" -8.8817841970012523e-16 0 0 ;
	setAttr ".mnrl" -type "double3" -360 -360 -360 ;
	setAttr ".mxrl" -type "double3" 360 360 360 ;
createNode joint -n "joint_arm_ik_end_r" -p "joint_arm_ik_top_r";
	rename -uid "99FC4AF7-4A79-773F-72C7-40904E37F184";
	setAttr ".v" no;
	setAttr ".t" -type "double3" 2 0 0 ;
	setAttr ".mnrl" -type "double3" -360 -360 -360 ;
	setAttr ".mxrl" -type "double3" 360 360 360 ;
createNode ikEffector -n "effector2" -p "joint_arm_ik_top_r";
	rename -uid "139ED716-4A2F-974C-49DB-5AA74F163FE0";
	setAttr ".v" no;
	setAttr ".hd" yes;
createNode transform -n "const_arm_fk_r" -p "grp_alex_arm_offset_r";
	rename -uid "912CBA6D-4893-78AD-9F39-E9B11377899B";
	setAttr ".t" -type "double3" 6 22 9.7971743931788197e-16 ;
	setAttr ".s" -type "double3" 1 1 -1 ;
createNode transform -n "anim_arm_fk_r" -p "const_arm_fk_r";
	rename -uid "D14BA8BE-4E46-248F-543B-08B680B0405B";
	setAttr ".ove" yes;
	setAttr ".ovrgbf" yes;
	setAttr ".ovrgb" -type "float3" 1 0.13216358 0.084072948 ;
	setAttr ".uocol" yes;
	setAttr ".oclr" -type "float3" 1 0.3990109 0.32493308 ;
createNode nurbsCurve -n "anim_arm_fk_rShape" -p "anim_arm_fk_r";
	rename -uid "A08ECDE1-4557-5373-62A8-CD81C5CF0D48";
	setAttr -k off ".v";
	setAttr -s 4 ".iog[0].og";
	setAttr ".tw" yes;
createNode nurbsCurve -n "anim_arm_fk_rShapeOrig" -p "anim_arm_fk_r";
	rename -uid "F0B112A5-42CE-63C8-70E0-B388F17697F9";
	setAttr -k off ".v";
	setAttr ".io" yes;
	setAttr ".cc" -type "nurbsCurve" 
		1 23 0 no 3
		24 0 1 2 3 4 5 6 7 8 9 10 11 12 13 14 15 16 17 18 19 20 21 22 23
		24
		3.0000000000000036 -2.2499999999999929 2.25
		3.0000000000000036 7.1054273576010019e-15 2.75
		3 2.2500000000000071 2.25
		3 2.2500000000000071 -2.25
		3.0000000000000036 7.1054273576010019e-15 -2.75
		3.0000000000000036 -2.2499999999999929 -2.25
		3.0000000000000036 -2.2499999999999929 2.25
		1.0000000000000044 -2.25 2.25
		1.0000000000000027 0 2.75
		3.0000000000000036 7.1054273576010019e-15 2.75
		1.0000000000000027 0 2.75
		1.0000000000000009 2.25 2.25
		3 2.2500000000000071 2.25
		1.0000000000000009 2.25 2.25
		1.0000000000000009 2.25 -2.25
		3 2.2500000000000071 -2.25
		1.0000000000000009 2.25 -2.25
		1.0000000000000027 0 -2.75
		3.0000000000000036 7.1054273576010019e-15 -2.75
		1.0000000000000027 0 -2.75
		1.0000000000000044 -2.25 -2.25
		3.0000000000000036 -2.2499999999999929 -2.25
		1.0000000000000044 -2.25 -2.25
		1.0000000000000044 -2.25 2.25
		;
createNode transform -n "const_shoulder_l" -p "anim_body";
	rename -uid "3B642F5C-4102-C8AB-B14A-5D98EDC14E84";
createNode transform -n "anim_shoulder_l" -p "const_shoulder_l";
	rename -uid "708C7EEB-4D18-FE57-A5A7-828DFC223E1A";
	setAttr ".ove" yes;
	setAttr ".ovrgbf" yes;
	setAttr ".ovrgb" -type "float3" 0.053311527 0.35221207 1 ;
	setAttr ".rp" -type "double3" 2 22 0 ;
	setAttr ".sp" -type "double3" 2 22 0 ;
	setAttr ".uocol" yes;
	setAttr ".oclr" -type "float3" 0.26422676 0.62265736 1 ;
createNode nurbsCurve -n "anim_shoulder_lShape" -p "anim_shoulder_l";
	rename -uid "9007FCE0-4569-9BE6-8A3A-D3B15266CF4A";
	setAttr -k off ".v";
	setAttr -s 4 ".iog[0].og";
	setAttr ".tw" yes;
createNode transform -n "const_arm_settings_l" -p "anim_shoulder_l";
	rename -uid "386A6E43-4DC0-3E04-0221-C8A074738766";
createNode transform -n "anim_arm_settings_l" -p "const_arm_settings_l";
	rename -uid "D05060F2-4597-5659-59FB-B2816840974E";
	addAttr -ci true -sn "IKFKControl" -ln "IKFKControl" -nn "IK/FK Control" -min 0 
		-max 1 -at "double";
	addAttr -ci true -sn "WorldLocalIK" -ln "WorldLocalIK" -nn "World/Local IK" -min 
		0 -max 1 -at "double";
	setAttr ".ove" yes;
	setAttr ".ovrgbf" yes;
	setAttr ".ovrgb" -type "float3" 0.053311527 0.35221207 1 ;
	setAttr -l on -k off ".tx";
	setAttr -l on -k off ".ty";
	setAttr -l on -k off ".tz";
	setAttr -l on -k off ".rx";
	setAttr -l on -k off ".ry";
	setAttr -l on -k off ".rz";
	setAttr -l on -k off ".sx";
	setAttr -l on -k off ".sy";
	setAttr -l on -k off ".sz";
	setAttr ".rp" -type "double3" 6 26 0 ;
	setAttr ".sp" -type "double3" 6 26 0 ;
	setAttr ".uocol" yes;
	setAttr ".oclr" -type "float3" 0.26422676 0.62265736 1 ;
	setAttr -k on ".IKFKControl" 1;
	setAttr -k on ".WorldLocalIK";
createNode nurbsCurve -n "anim_arm_settings_lShape" -p "anim_arm_settings_l";
	rename -uid "D5B4D2D0-454B-7967-DFDA-72852A3057DA";
	setAttr -k off ".v";
	setAttr ".tw" yes;
createNode nurbsCurve -n "anim_leg_settings_l1Shape" -p "anim_arm_settings_l";
	rename -uid "A1227032-4121-9BE9-87EF-229279A742B1";
	setAttr -k off ".v";
	setAttr ".cc" -type "nurbsCurve" 
		1 10 0 no 3
		11 0 0.61803398874989479 1.2360679774997898 1.8541019662496847 2.4721359549995796
		 3.0901699437494745 3.7082039324993694 4.3262379212492643 4.9442719099991592 5.5623058987490541
		 6.180339887498949
		11
		6 27 3.9429924734491463e-16
		5.7689214575641756 26.318052328017501 7.9759308208603444e-17
		5.0489434837048464 26.30901699437495 4.5130644051866784e-17
		5.6261070642680515 25.878514820898346 -1.8389439331043348e-16
		5.4122147477075266 25.190983005625053 -5.1983602388061319e-16
		6 25.606865702168307 -3.4684134210891825e-16
		6.5877852522924734 25.190983005625053 -5.1983602388061319e-16
		6.3738929357319485 25.878514820898346 -1.8389439331043348e-16
		6.9510565162951536 26.309016994374947 4.513064405186671e-17
		6.2310785424358244 26.318052328017501 7.9759308208603444e-17
		6 27 3.9429924734491463e-16
		;
createNode nurbsCurve -n "anim_shoulder_lShapeOrig" -p "anim_shoulder_l";
	rename -uid "26ABDD2F-4F62-E52B-C3A3-708B4C07A941";
	setAttr -k off ".v";
	setAttr ".io" yes;
	setAttr ".cc" -type "nurbsCurve" 
		1 6 0 no 3
		7 0 1 2 3 4 5 6
		7
		1.7939957663596084 23.213977501875174 2.2500000000000062
		3.2060042336403924 23.213977501875174 2.2500000000000062
		3.9120084672807844 21.999999999999996 2.2500000000000058
		3.2060042336403924 20.786022498124801 2.2500000000000053
		2.5 22.000000000000007 2.2500000000000053
		1.0879915327192142 21.999999999999996 2.2500000000000058
		1.7939957663596084 23.213977501875174 2.2500000000000062
		;
createNode transform -n "grp_arm_alex_offset_l" -p "anim_shoulder_l";
	rename -uid "A8F9D447-49F7-CCE6-6F85-2D950AF8CD01";
createNode transform -n "const_arm_fk_l" -p "grp_arm_alex_offset_l";
	rename -uid "A99347AC-462D-F031-4F10-DCA80C937098";
	setAttr ".t" -type "double3" 6 22 0 ;
createNode transform -n "anim_arm_fk_l" -p "const_arm_fk_l";
	rename -uid "D3CC9830-4366-48B3-78FD-F0B9B927CBB3";
	setAttr ".ove" yes;
	setAttr ".ovrgbf" yes;
	setAttr ".ovrgb" -type "float3" 0.053311527 0.35221207 1 ;
	setAttr ".uocol" yes;
	setAttr ".oclr" -type "float3" 0.26422676 0.62265736 1 ;
createNode nurbsCurve -n "anim_arm_fk_lShape" -p "anim_arm_fk_l";
	rename -uid "4369EDA0-4ED5-8040-69A4-D5B6464D4249";
	setAttr -k off ".v";
	setAttr -s 4 ".iog[0].og";
	setAttr ".tw" yes;
createNode nurbsCurve -n "anim_arm_fk_lShapeOrig" -p "anim_arm_fk_l";
	rename -uid "B6CA79E3-4D00-DFCF-71D9-9C88017F6A97";
	setAttr -k off ".v";
	setAttr ".io" yes;
	setAttr ".cc" -type "nurbsCurve" 
		1 23 0 no 3
		24 0 1 2 3 4 5 6 7 8 9 10 11 12 13 14 15 16 17 18 19 20 21 22 23
		24
		3.0000000000000036 -2.2499999999999929 2.25
		3.0000000000000036 7.1054273576010019e-15 2.75
		3 2.2500000000000071 2.25
		3 2.2500000000000071 -2.25
		3.0000000000000036 7.1054273576010019e-15 -2.75
		3.0000000000000036 -2.2499999999999929 -2.25
		3.0000000000000036 -2.2499999999999929 2.25
		1.0000000000000044 -2.25 2.25
		1.0000000000000027 0 2.75
		3.0000000000000036 7.1054273576010019e-15 2.75
		1.0000000000000027 0 2.75
		1.0000000000000009 2.25 2.25
		3 2.2500000000000071 2.25
		1.0000000000000009 2.25 2.25
		1.0000000000000009 2.25 -2.25
		3 2.2500000000000071 -2.25
		1.0000000000000009 2.25 -2.25
		1.0000000000000027 0 -2.75
		3.0000000000000036 7.1054273576010019e-15 -2.75
		1.0000000000000027 0 -2.75
		1.0000000000000044 -2.25 -2.25
		3.0000000000000036 -2.2499999999999929 -2.25
		1.0000000000000044 -2.25 -2.25
		1.0000000000000044 -2.25 2.25
		;
createNode transform -n "grp_arm_ik_top_l" -p "grp_arm_alex_offset_l";
	rename -uid "5FD59250-4CD6-7FE8-A20F-2E89F35ECF4F";
	setAttr ".t" -type "double3" 6 22 0 ;
	setAttr ".rp" -type "double3" 6 22 0 ;
	setAttr ".sp" -type "double3" 6 22 0 ;
createNode joint -n "joint_arm_ik_top_l" -p "grp_arm_ik_top_l";
	rename -uid "6E43562C-4AEC-4F4C-A2B3-BF86F0D20DE6";
	setAttr ".v" no;
	setAttr ".mnrl" -type "double3" -360 -360 -360 ;
	setAttr ".mxrl" -type "double3" 360 360 360 ;
createNode joint -n "joint_arm_ik_end_l" -p "joint_arm_ik_top_l";
	rename -uid "5087F34E-418F-C6E4-CF2C-20AA9F95DA50";
	setAttr ".v" no;
	setAttr ".t" -type "double3" 2 0 0 ;
	setAttr ".mnrl" -type "double3" -360 -360 -360 ;
	setAttr ".mxrl" -type "double3" 360 360 360 ;
createNode ikEffector -n "effector2" -p "joint_arm_ik_top_l";
	rename -uid "08A5FD42-432A-DE0E-670A-16AF3B8203E1";
	setAttr ".v" no;
	setAttr ".hd" yes;
createNode nurbsCurve -n "anim_bodyShapeOrig" -p "anim_body";
	rename -uid "D6073F49-4DBE-5C29-AC45-7DADFD8A7AA3";
	setAttr -k off ".v";
	setAttr ".io" yes;
	setAttr ".cc" -type "nurbsCurve" 
		1 16 0 no 3
		17 0 1 2 3 4 5 6 7 8 9 10 11 12 13 14 15 16
		17
		-4.25 14.25 2.25
		-4.25 19.75 2.25
		-4.25 14.25 2.25
		4.25 14.25 2.25
		4.25 19.75 2.25
		-4.25 19.75 2.25
		-4.25 19.75 -2.25
		4.25 19.75 -2.25
		4.25 19.75 2.25
		4.25 14.25 2.25
		4.25 14.25 -2.25
		4.25 19.75 -2.25
		-4.25 19.75 -2.25
		-4.25 14.25 -2.25
		4.25 14.25 -2.25
		-4.25 14.25 -2.25
		-4.25 14.25 2.25
		;
createNode transform -n "const_arm_ik_l" -p "main_CON";
	rename -uid "DDCCB942-48C2-3C53-E771-7BA19506BF18";
	setAttr ".rp" -type "double3" 0 14 4.4408920985006262e-16 ;
	setAttr ".sp" -type "double3" 0 14 4.4408920985006262e-16 ;
createNode transform -n "anim_arm_ik_l" -p "const_arm_ik_l";
	rename -uid "1D47C1EB-4357-3273-3CB2-0192D3C99093";
	setAttr ".ove" yes;
	setAttr ".ovrgbf" yes;
	setAttr ".ovrgb" -type "float3" 0.053311527 0.35221207 1 ;
	setAttr ".rp" -type "double3" 16 22 2.7755575615628914e-17 ;
	setAttr ".sp" -type "double3" 16 22 2.7755575615628914e-17 ;
	setAttr ".uocol" yes;
	setAttr ".oclr" -type "float3" 0.26422676 0.62265736 1 ;
createNode nurbsCurve -n "anim_arm_ik_lShape" -p "anim_arm_ik_l";
	rename -uid "9C74D494-44F6-C48A-5B74-DA95C3CC066F";
	setAttr -k off ".v";
	setAttr ".cc" -type "nurbsCurve" 
		1 4 0 no 3
		5 0 1 2 3 4
		5
		16 19.626817334234548 -2.3731826657654516
		16 24.373182665765452 -2.3731826657654516
		16 24.373182665765452 2.3731826657654516
		16 19.626817334234548 2.3731826657654516
		16 19.626817334234548 -2.3731826657654516
		;
createNode nurbsCurve -n "anim_arm_ik_lShape1" -p "anim_arm_ik_l";
	rename -uid "18BDD94F-4A15-5C1B-6423-9FB0DCE4D259";
	setAttr -k off ".v";
	setAttr ".cc" -type "nurbsCurve" 
		1 4 0 no 3
		5 0 1 2 3 4
		5
		17.186591332882728 22.000000000000004 -2.3731826657654516
		17.186591332882728 22.000000000000004 2.3731826657654524
		14.813408667117272 21.999999999999996 2.3731826657654507
		14.813408667117276 21.999999999999996 -2.3731826657654516
		17.186591332882728 22.000000000000004 -2.3731826657654516
		;
createNode nurbsCurve -n "anim_arm_ik_lShape2" -p "anim_arm_ik_l";
	rename -uid "A8DBBE8F-4ABD-B393-E9FE-0CA4141497F8";
	setAttr -k off ".v";
	setAttr ".cc" -type "nurbsCurve" 
		1 4 0 no 3
		5 0 1 2 3 4
		5
		17.186591332882728 19.626817334234548 -3.8113475313723173e-17
		17.186591332882728 24.373182665765452 2.2536272840368797e-16
		14.813408667117276 24.373182665765455 9.3624626544982516e-17
		14.813408667117272 19.626817334234548 -1.6985157717242864e-16
		17.186591332882728 19.626817334234548 -3.8113475313723173e-17
		;
createNode joint -n "joint_arm_ik_aim_l" -p "anim_arm_ik_l";
	rename -uid "1FDC969B-42E3-9346-C672-399DD8B1E7C8";
	setAttr ".v" no;
	setAttr ".t" -type "double3" 16 21.999999999999996 2.7755575615628914e-17 ;
	setAttr ".mnrl" -type "double3" -360 -360 -360 ;
	setAttr ".mxrl" -type "double3" 360 360 360 ;
createNode transform -n "grp_ikHandle_l" -p "joint_arm_ik_aim_l";
	rename -uid "0C00F105-4596-8A94-A151-EAAB39AC1CAF";
	setAttr ".t" -type "double3" -16 -22 0 ;
	setAttr ".rp" -type "double3" 16 22 0 ;
	setAttr ".sp" -type "double3" 16 22 0 ;
createNode ikHandle -n "ikHandle_arm_l" -p "grp_ikHandle_l";
	rename -uid "ECBB5D34-4695-8B20-3E8C-20A192BD7D5B";
	setAttr ".v" no;
	setAttr ".rp" -type "double3" 16 22 0 ;
	setAttr ".sp" -type "double3" 16 22 0 ;
	setAttr ".pv" -type "double3" 0 -2 0 ;
	setAttr ".roc" yes;
createNode transform -n "const_arm_ik_r" -p "main_CON";
	rename -uid "C3545385-4E9A-2141-15FB-92A2B940FCA0";
	setAttr ".s" -type "double3" -1 1 1 ;
	setAttr ".rp" -type "double3" 0 14 4.4408920985006262e-16 ;
	setAttr ".sp" -type "double3" 0 14 4.4408920985006262e-16 ;
createNode transform -n "anim_arm_ik_r" -p "const_arm_ik_r";
	rename -uid "24AD0844-4632-73D5-5525-F0BBF8EEA330";
	setAttr ".ove" yes;
	setAttr ".ovrgbf" yes;
	setAttr ".ovrgb" -type "float3" 1 0.13216358 0.084072948 ;
	setAttr ".rp" -type "double3" 16 22 2.7755575615628914e-17 ;
	setAttr ".sp" -type "double3" 16 22 2.7755575615628914e-17 ;
	setAttr ".uocol" yes;
	setAttr ".oclr" -type "float3" 1 0.3990109 0.32493308 ;
createNode nurbsCurve -n "anim_arm_ik_rShape" -p "anim_arm_ik_r";
	rename -uid "754C02E4-4B9B-C35E-822D-B88C8780D4FA";
	setAttr -k off ".v";
	setAttr ".cc" -type "nurbsCurve" 
		1 4 0 no 3
		5 0 1 2 3 4
		5
		16 19.626817334234548 -2.3731826657654516
		16 24.373182665765452 -2.3731826657654516
		16 24.373182665765452 2.3731826657654516
		16 19.626817334234548 2.3731826657654516
		16 19.626817334234548 -2.3731826657654516
		;
createNode nurbsCurve -n "anim_arm_ik_rShape3" -p "anim_arm_ik_r";
	rename -uid "C2D297B1-401A-54EA-9E5B-ED8FA0C01391";
	setAttr -k off ".v";
	setAttr ".cc" -type "nurbsCurve" 
		1 4 0 no 3
		5 0 1 2 3 4
		5
		17.186591332882728 22.000000000000004 -2.3731826657654516
		17.186591332882728 22.000000000000004 2.3731826657654524
		14.813408667117272 21.999999999999996 2.3731826657654507
		14.813408667117276 21.999999999999996 -2.3731826657654516
		17.186591332882728 22.000000000000004 -2.3731826657654516
		;
createNode nurbsCurve -n "anim_arm_ik_rShape4" -p "anim_arm_ik_r";
	rename -uid "5526B47F-48AA-8392-FC9B-AFA001B8469A";
	setAttr -k off ".v";
	setAttr ".cc" -type "nurbsCurve" 
		1 4 0 no 3
		5 0 1 2 3 4
		5
		17.186591332882728 19.626817334234548 -3.8113475313723173e-17
		17.186591332882728 24.373182665765452 2.2536272840368797e-16
		14.813408667117276 24.373182665765455 9.3624626544982516e-17
		14.813408667117272 19.626817334234548 -1.6985157717242864e-16
		17.186591332882728 19.626817334234548 -3.8113475313723173e-17
		;
createNode joint -n "joint_arm_ik_aim_r" -p "anim_arm_ik_r";
	rename -uid "B2BC2418-4031-48FB-9B61-8F98FD796594";
	setAttr ".v" no;
	setAttr ".t" -type "double3" 16 21.999999999999996 2.7755575615628914e-17 ;
	setAttr ".mnrl" -type "double3" -360 -360 -360 ;
	setAttr ".mxrl" -type "double3" 360 360 360 ;
createNode transform -n "grp_ikHandle_r" -p "joint_arm_ik_aim_r";
	rename -uid "8BFD4B43-410F-AE2A-4777-AD82BAB9C690";
	setAttr ".t" -type "double3" -16 -22 0 ;
	setAttr ".rp" -type "double3" 16 22 0 ;
	setAttr ".sp" -type "double3" 16 22 0 ;
createNode ikHandle -n "ikHandle_arm_r" -p "grp_ikHandle_r";
	rename -uid "1230D938-4B5F-CA7E-A6FC-C3A675A19CD6";
	setAttr ".v" no;
	setAttr ".rp" -type "double3" 16 22 0 ;
	setAttr ".sp" -type "double3" 16 22 0 ;
	setAttr ".pv" -type "double3" 0 -2 0 ;
	setAttr ".roc" yes;
createNode transform -n "body_LOC";
	rename -uid "6E9B61A6-404B-33DA-E44B-FBAD5EBB5366";
	setAttr ".rp" -type "double3" 0 24 0 ;
	setAttr ".sp" -type "double3" 0 24 0 ;
createNode locator -n "body_LOCShape" -p "body_LOC";
	rename -uid "00C61624-44AD-D67F-793A-189C9BC01943";
	setAttr -k off ".v";
	setAttr ".lp" -type "double3" 0 24 0 ;
createNode parentConstraint -n "body_LOC_parentConstraint1" -p "body_LOC";
	rename -uid "57F31D5C-459D-DFFC-E605-A6BD99C604F4";
	addAttr -dcb 0 -ci true -k true -sn "w0" -ln "joint_bodyW0" -dv 1 -min 0 -at "double";
	setAttr -k on ".nds";
	setAttr -k off ".v";
	setAttr -k off ".tx";
	setAttr -k off ".ty";
	setAttr -k off ".tz";
	setAttr -k off ".rx";
	setAttr -k off ".ry";
	setAttr -k off ".rz";
	setAttr -k off ".sx";
	setAttr -k off ".sy";
	setAttr -k off ".sz";
	setAttr ".erp" yes;
	setAttr ".tg[0].tot" -type "double3" 0 10 0 ;
	setAttr -k on ".w0";
createNode transform -n "leftArm_LOC";
	rename -uid "BE821952-4B37-F82C-B73C-D580448B9748";
	setAttr ".rp" -type "double3" 6 22 0 ;
	setAttr ".sp" -type "double3" 6 22 0 ;
createNode locator -n "leftArm_LOCShape" -p "leftArm_LOC";
	rename -uid "AA3656B0-4F87-AE47-1380-20BB9FBD9E1F";
	setAttr -k off ".v";
	setAttr ".lp" -type "double3" 6 22 0 ;
createNode parentConstraint -n "leftArm_LOC_parentConstraint1" -p "leftArm_LOC";
	rename -uid "F039C4B4-4D67-B787-1DCF-848CE5C7969C";
	addAttr -dcb 0 -ci true -k true -sn "w0" -ln "joint_arm_bind_lW0" -dv 1 -min 0 
		-at "double";
	setAttr -k on ".nds";
	setAttr -k off ".v";
	setAttr -k off ".tx";
	setAttr -k off ".ty";
	setAttr -k off ".tz";
	setAttr -k off ".rx";
	setAttr -k off ".ry";
	setAttr -k off ".rz";
	setAttr -k off ".sx";
	setAttr -k off ".sy";
	setAttr -k off ".sz";
	setAttr ".erp" yes;
	setAttr ".tg[0].tot" -type "double3" 0 3.5527136788005009e-15 0 ;
	setAttr ".rst" -type "double3" 0 3.5527136788005009e-15 0 ;
	setAttr -k on ".w0";
createNode transform -n "rightArm_LOC";
	rename -uid "9D3A2AE7-422E-6785-5006-5D8894A2A1A3";
	setAttr ".rp" -type "double3" -6 22 0 ;
	setAttr ".sp" -type "double3" -6 22 0 ;
createNode locator -n "rightArm_LOCShape" -p "rightArm_LOC";
	rename -uid "1DCDA49F-405C-3570-47BF-6B9313E3A520";
	setAttr -k off ".v";
	setAttr ".lp" -type "double3" -6 22 0 ;
createNode parentConstraint -n "rightArm_LOC_parentConstraint1" -p "rightArm_LOC";
	rename -uid "4D125752-4572-6937-A108-E0974AF8D651";
	addAttr -dcb 0 -ci true -k true -sn "w0" -ln "joint_arm_bind_rW0" -dv 1 -min 0 
		-at "double";
	setAttr -k on ".nds";
	setAttr -k off ".v";
	setAttr -k off ".tx";
	setAttr -k off ".ty";
	setAttr -k off ".tz";
	setAttr -k off ".rx";
	setAttr -k off ".ry";
	setAttr -k off ".rz";
	setAttr -k off ".sx";
	setAttr -k off ".sy";
	setAttr -k off ".sz";
	setAttr ".erp" yes;
	setAttr ".tg[0].tot" -type "double3" 0 3.5527136788005009e-15 9.7971748206813428e-16 ;
	setAttr ".tg[0].tor" -type "double3" 0 180 0 ;
	setAttr ".lr" -type "double3" 0 360 0 ;
	setAttr ".rst" -type "double3" 0 3.5527136788005009e-15 0 ;
	setAttr ".rsrr" -type "double3" 0 360 0 ;
	setAttr -k on ".w0";
createNode transform -n "leftLeg_LOC";
	rename -uid "5B5536A1-47A0-4F07-A2DF-5B9D799D7FC8";
	setAttr ".rp" -type "double3" 2 12 0 ;
	setAttr ".sp" -type "double3" 2 12 0 ;
createNode locator -n "leftLeg_LOCShape" -p "leftLeg_LOC";
	rename -uid "4E31FCD1-42E3-1E76-B8F4-85B61D84706C";
	setAttr -k off ".v";
	setAttr ".lp" -type "double3" 2 12 0 ;
createNode parentConstraint -n "leftLeg_LOC_parentConstraint1" -p "leftLeg_LOC";
	rename -uid "7C85DCF8-4A75-C5A3-13D0-56A7C3587A67";
	addAttr -dcb 0 -ci true -k true -sn "w0" -ln "joint_leg_bind_lW0" -dv 1 -min 0 
		-at "double";
	setAttr -k on ".nds";
	setAttr -k off ".v";
	setAttr -k off ".tx";
	setAttr -k off ".ty";
	setAttr -k off ".tz";
	setAttr -k off ".rx";
	setAttr -k off ".ry";
	setAttr -k off ".rz";
	setAttr -k off ".sx";
	setAttr -k off ".sy";
	setAttr -k off ".sz";
	setAttr ".erp" yes;
	setAttr ".tg[0].tot" -type "double3" 0.0002913462521658694 11.999988347235432 0.016720799617572911 ;
	setAttr ".tg[0].tor" -type "double3" 0.080130628379778857 -0.0017679889377942878 
		-0.0013935407541761633 ;
	setAttr ".lr" -type "double3" -1.2424038762344487e-17 2.106769418732828e-38 1.9431522777677205e-19 ;
	setAttr ".rst" -type "double3" 4.4408920985006262e-16 -1.7763568394002505e-15 -6.9388939039072284e-18 ;
	setAttr ".rsrr" -type "double3" -1.2424038762344487e-17 2.106769418732828e-38 1.9431522777677205e-19 ;
	setAttr -k on ".w0";
createNode transform -n "rightLeg_LOC";
	rename -uid "4B4DFCD2-4539-4D2A-2BD3-DE80AFA5638B";
	setAttr ".rp" -type "double3" -2 12 0 ;
	setAttr ".sp" -type "double3" -2 12 0 ;
createNode locator -n "rightLeg_LOCShape" -p "rightLeg_LOC";
	rename -uid "E3B1D0A1-4EC9-06EF-B696-A3968FBF6754";
	setAttr -k off ".v";
	setAttr ".lp" -type "double3" -2 12 0 ;
createNode parentConstraint -n "rightLeg_LOC_parentConstraint1" -p "rightLeg_LOC";
	rename -uid "163FAC68-473D-79C0-1CA8-CE925EBD8E35";
	addAttr -dcb 0 -ci true -k true -sn "w0" -ln "joint_leg_bind_rW0" -dv 1 -min 0 
		-at "double";
	setAttr -k on ".nds";
	setAttr -k off ".v";
	setAttr -k off ".tx";
	setAttr -k off ".ty";
	setAttr -k off ".tz";
	setAttr -k off ".rx";
	setAttr -k off ".ry";
	setAttr -k off ".rz";
	setAttr -k off ".sx";
	setAttr -k off ".sy";
	setAttr -k off ".sz";
	setAttr ".erp" yes;
	setAttr ".tg[0].tot" -type "double3" 0.00029134625216542531 11.999988347235432 0.016720799617572921 ;
	setAttr ".tg[0].tor" -type "double3" 0.080130628379778857 -0.0017679889377942878 
		-0.0013935407541761633 ;
	setAttr ".lr" -type "double3" -1.2424038762344487e-17 2.106769418732828e-38 1.9431522777677205e-19 ;
	setAttr ".rst" -type "double3" 0 -1.7763568394002505e-15 3.4694469519536142e-18 ;
	setAttr ".rsrr" -type "double3" -1.2424038762344487e-17 2.106769418732828e-38 1.9431522777677205e-19 ;
	setAttr -k on ".w0";
createNode transform -n "head_LOC";
	rename -uid "3A6BB223-435D-E33B-3652-999AEF6C4E13";
	setAttr ".rp" -type "double3" 0 24 0 ;
	setAttr ".sp" -type "double3" 0 24 0 ;
createNode locator -n "head_LOCShape" -p "head_LOC";
	rename -uid "CB41BDE5-4AF8-35E2-56F3-C9A16E14B9A7";
	setAttr -k off ".v";
	setAttr ".lp" -type "double3" 0 24 0 ;
createNode parentConstraint -n "head_LOC_parentConstraint1" -p "head_LOC";
	rename -uid "6B8CF21D-4205-F48E-C446-D6865A78D49F";
	addAttr -dcb 0 -ci true -k true -sn "w0" -ln "head_CONW0" -dv 1 -min 0 -at "double";
	setAttr -k on ".nds";
	setAttr -k off ".v";
	setAttr -k off ".tx";
	setAttr -k off ".ty";
	setAttr -k off ".tz";
	setAttr -k off ".rx";
	setAttr -k off ".ry";
	setAttr -k off ".rz";
	setAttr -k off ".sx";
	setAttr -k off ".sy";
	setAttr -k off ".sz";
	setAttr ".erp" yes;
	setAttr -k on ".w0";
createNode lightLinker -s -n "lightLinker1";
	rename -uid "18021D2B-4CAD-EFA3-7B44-A386E8AAA0A5";
	setAttr -s 12 ".lnk";
	setAttr -s 12 ".slnk";
createNode RedshiftOptions -s -n "redshiftOptions";
	rename -uid "B649CC59-41ED-5D64-8896-68B0BA70BA83";
	addAttr -s false -ci true -h true -sn "physicalSky" -ln "physicalSky" -at "message";
	setAttr ".version" 1;
	setAttr ".advancedVsBasicSummary" -type "string" "Sampling | Unified Sampling\n\t* Automatic Sampling = off\nSampling | Filter\n\t* Filter Size = 2\nGlobal Illumination\n\t* Primary GI Engine = 3\nSystem | Legacy\n\t* Black-Body And Dispersion Technique = on\n";
	setAttr ".imageFilePrefix" -type "string" "";
	setAttr ".GIEnabled" yes;
	setAttr ".primaryGIEngine" 3;
	setAttr ".secondaryGIEngine" 2;
	setAttr ".renderViewState" -type "string" "AAAA/wAAAA0/8AAAAAAAAAAAAAAAAAAAAAAAAAAAAAgAegBpAHAAcwAAAAAAAAAGAGwAegB3AAAAXwAAAAAAAAAAQFkAAAAAAAAAAAACAAAABgAAAIwARAA6AFwARQBNACAATwBuAGUARAByAGkAdgBlAFwATwBuAGUARAByAGkAdgBlAFwAVwBhAHkAIABEAGUAZQBwAGUAcgAgAEQAbwB3AG4AIABQAHIAbwBqAGUAYwB0AFwARgA6AFwAUgBlAG4AZABlAHIAcwBcAFEAbgBBAFwASQBuAHQAcgBvACAAMgAAAAAAAABAAEMAOgAvAFAAcgBvAGcAcgBhAG0ARABhAHQAYQAvAFIAZQBkAHMAaABpAGYAdAAvAEQAYQB0AGEALwBMAFUAVAAAAEwAQwA6AC8AUAByAG8AZwByAGEAbQAgAEYAaQBsAGUAcwAvAEEAdQB0AG8AZABlAHMAawAvAE0AYQB5AGEAMgAwADEAOAAvAGIAaQBuAAAASABDADoALwBQAHIAbwBnAHIAYQBtAEQAYQB0AGEALwBSAGUAZABzAGgAaQBmAHQALwBEAGEAdABhAC8AUAByAGUAcwBlAHQAcwAAAEwAQwA6AC8AUAByAG8AZwByAGEAbQAgAEYAaQBsAGUAcwAvAEEAdQB0AG8AZABlAHMAawAvAE0AYQB5AGEAMgAwADEAOAAvAGIAaQBuAAAAAQAAAAEAAABUAEYAcgBhAG0AZQAgADwAZgByAGEAbQBlAD4AOgAgADwAZABhAHQAZQA+ACAAPAB0AGkAbQBlAD4AIAAoADwAZgByAGEAbQBlAHQAaQBtAGUAPgApATgDABtAAAAAOA5AGEAAAAA4DUAbwAAAAAAAAQABP/AAAAAAAAA/8AAAAAAAAD/wAAAAAAAA";
createNode RedshiftPostEffects -n "defaultRedshiftPostEffects";
	rename -uid "E0996B33-404D-14C8-1AED-23B7C600F1D7";
	setAttr ".v" 2;
	setAttr ".clrMgmtDisplayMode" -type "string" "RS_COLORMANAGEMENTDISPLAYMODE_SRGB";
	setAttr -s 2 ".cr[1]" -type "float2" 1 1;
	setAttr -s 2 ".cg[1]" -type "float2" 1 1;
	setAttr -s 2 ".cb[1]" -type "float2" 1 1;
	setAttr -s 2 ".cl[1]" -type "float2" 1 1;
createNode shapeEditorManager -n "shapeEditorManager";
	rename -uid "C1DA10D7-46E8-F31E-EFB7-9BBDE8903201";
createNode poseInterpolatorManager -n "poseInterpolatorManager";
	rename -uid "0CE2BF5F-47FA-D080-4265-E09C1DC32304";
createNode displayLayerManager -n "layerManager";
	rename -uid "EF9DA65A-43BD-1726-257F-BBB3E492610C";
	setAttr ".cdl" 1;
	setAttr -s 2 ".dli[1]"  1;
	setAttr -s 2 ".dli";
createNode displayLayer -n "defaultLayer";
	rename -uid "0C96750E-4B38-0A3E-B4A5-40B4E2D1DA67";
createNode renderLayerManager -n "renderLayerManager";
	rename -uid "C6395B33-4219-4333-AD6B-F6A5666014DD";
createNode renderLayer -n "defaultRenderLayer";
	rename -uid "4701FEA7-4A3A-EE1B-68FE-7D9D3A425E56";
	setAttr ".g" yes;
createNode aiOptions -s -n "defaultArnoldRenderOptions";
	rename -uid "7A10A496-4B44-5F59-5945-54BB0E4A51FA";
	addAttr -ci true -sn "ARV_options" -ln "ARV_options" -dt "string";
	setAttr ".version" -type "string" "3.1.2.1";
createNode aiAOVFilter -s -n "defaultArnoldFilter";
	rename -uid "0F6F24E4-4518-14C2-C053-9987231707D1";
	setAttr ".ai_translator" -type "string" "gaussian";
createNode aiAOVDriver -s -n "defaultArnoldDriver";
	rename -uid "A5EE9EF5-449C-EC7B-7A94-F5AD9A748A9C";
	setAttr ".ai_translator" -type "string" "exr";
createNode aiAOVDriver -s -n "defaultArnoldDisplayDriver";
	rename -uid "43849E9C-408D-48BA-E729-A0A0F99F61B5";
	setAttr ".output_mode" 0;
	setAttr ".ai_translator" -type "string" "maya";
createNode shadingEngine -n "steve_set1";
	rename -uid "E4225740-4956-B873-788C-3188934772B0";
	setAttr ".ihi" 0;
	setAttr ".ro" yes;
createNode materialInfo -n "steve_materialInfo1";
	rename -uid "074DDBBC-497E-88AF-C41B-6895BF1DB1A5";
createNode shadingEngine -n "rsMat_skinPromoArtSG";
	rename -uid "2215613F-40B8-0BE9-F49E-04A289AAE89C";
	setAttr ".ihi" 0;
	setAttr ".ro" yes;
createNode materialInfo -n "materialInfo1";
	rename -uid "EFB55388-40EB-8BDE-63A0-4085A2E6EB57";
createNode file -n "skinPromoArt_tex";
	rename -uid "B766AF30-4BF5-3885-CBD9-B3BD79F60EF7";
	setAttr ".ftn" -type "string" "D:/Dropbox/Misc Projects/Minecraft/skins/skin_trig_dungeons.png";
	setAttr ".ft" 0;
	setAttr ".cs" -type "string" "sRGB";
	setAttr ".rsFilterEnable" 0;
	setAttr ".ai_filter" 1;
	setAttr ".ai_ignore_missing_textures" no;
createNode place2dTexture -n "place2dTexture_skinPromoArt";
	rename -uid "14827B04-4E3B-FDB4-9080-598632A46F98";
createNode ikRPsolver -n "ikRPsolver";
	rename -uid "BA2123EF-4EEB-3062-7D96-DC894570A07D";
createNode distanceBetween -n "distanceBetween1";
	rename -uid "239FE069-4FE2-0414-783B-E18C5B575A48";
createNode condition -n "condition1";
	rename -uid "7117DCF9-4653-608E-FAE4-A5A3992B3F6E";
	setAttr ".op" 4;
	setAttr ".cf" -type "float3" 0 0 0 ;
createNode remapValue -n "remapValue1";
	rename -uid "09BDBDD9-42EC-1EC9-EA2F-E1B33D7E2C0F";
	setAttr ".imx" -4;
	setAttr ".omx" 3.5;
	setAttr -s 2 ".vl[0:1]"  0 0 2 1 1 2;
	setAttr -s 2 ".cl";
	setAttr ".cl[0].clp" 0;
	setAttr ".cl[0].clc" -type "float3" 0 0 0 ;
	setAttr ".cl[0].cli" 1;
	setAttr ".cl[1].clp" 1;
	setAttr ".cl[1].clc" -type "float3" 1 1 1 ;
	setAttr ".cl[1].cli" 1;
createNode script -n "uiConfigurationScriptNode";
	rename -uid "AEE66075-442D-57F4-4E45-30B9CD49A779";
	setAttr ".b" -type "string" (
		"// Maya Mel UI Configuration File.\n//\n//  This script is machine generated.  Edit at your own risk.\n//\n//\n\nglobal string $gMainPane;\nif (`paneLayout -exists $gMainPane`) {\n\n\tglobal int $gUseScenePanelConfig;\n\tint    $useSceneConfig = $gUseScenePanelConfig;\n\tint    $nodeEditorPanelVisible = stringArrayContains(\"nodeEditorPanel1\", `getPanel -vis`);\n\tint    $nodeEditorWorkspaceControlOpen = (`workspaceControl -exists nodeEditorPanel1Window` && `workspaceControl -q -visible nodeEditorPanel1Window`);\n\tint    $menusOkayInPanels = `optionVar -q allowMenusInPanels`;\n\tint    $nVisPanes = `paneLayout -q -nvp $gMainPane`;\n\tint    $nPanes = 0;\n\tstring $editorName;\n\tstring $panelName;\n\tstring $itemFilterName;\n\tstring $panelConfig;\n\n\t//\n\t//  get current state of the UI\n\t//\n\tsceneUIReplacement -update $gMainPane;\n\n\t$panelName = `sceneUIReplacement -getNextPanel \"modelPanel\" (localizedPanelLabel(\"Top View\")) `;\n\tif (\"\" != $panelName) {\n\t\t$label = `panel -q -label $panelName`;\n\t\tmodelPanel -edit -l (localizedPanelLabel(\"Top View\")) -mbv $menusOkayInPanels  $panelName;\n"
		+ "\t\t$editorName = $panelName;\n        modelEditor -e \n            -docTag \"RADRENDER\" \n            -camera \"|top|topShape\" \n            -useInteractiveMode 0\n            -displayLights \"default\" \n            -displayAppearance \"smoothShaded\" \n            -activeOnly 0\n            -ignorePanZoom 0\n            -wireframeOnShaded 0\n            -headsUpDisplay 1\n            -holdOuts 1\n            -selectionHiliteDisplay 1\n            -useDefaultMaterial 0\n            -bufferMode \"double\" \n            -twoSidedLighting 0\n            -backfaceCulling 0\n            -xray 0\n            -jointXray 1\n            -activeComponentsXray 0\n            -displayTextures 0\n            -smoothWireframe 0\n            -lineWidth 1\n            -textureAnisotropic 0\n            -textureHilight 1\n            -textureSampling 2\n            -textureDisplay \"modulate\" \n            -textureMaxSize 32768\n            -fogging 0\n            -fogSource \"fragment\" \n            -fogMode \"linear\" \n            -fogStart 0\n            -fogEnd 100\n"
		+ "            -fogDensity 0.1\n            -fogColor 0.5 0.5 0.5 1 \n            -depthOfFieldPreview 1\n            -maxConstantTransparency 1\n            -rendererName \"vp2Renderer\" \n            -objectFilterShowInHUD 1\n            -isFiltered 0\n            -colorResolution 4 4 \n            -bumpResolution 4 4 \n            -textureCompression 0\n            -transparencyAlgorithm \"frontAndBackCull\" \n            -transpInShadows 0\n            -cullingOverride \"none\" \n            -lowQualityLighting 0\n            -maximumNumHardwareLights 1\n            -occlusionCulling 0\n            -shadingModel 0\n            -useBaseRenderer 0\n            -useReducedRenderer 0\n            -smallObjectCulling 0\n            -smallObjectThreshold -1 \n            -interactiveDisableShadows 0\n            -interactiveBackFaceCull 0\n            -sortTransparent 1\n            -controllers 1\n            -nurbsCurves 1\n            -nurbsSurfaces 1\n            -polymeshes 1\n            -subdivSurfaces 1\n            -planes 1\n            -lights 1\n"
		+ "            -cameras 1\n            -controlVertices 1\n            -hulls 1\n            -grid 1\n            -imagePlane 1\n            -joints 1\n            -ikHandles 1\n            -deformers 1\n            -dynamics 1\n            -particleInstancers 1\n            -fluids 1\n            -hairSystems 1\n            -follicles 1\n            -nCloths 1\n            -nParticles 1\n            -nRigids 1\n            -dynamicConstraints 1\n            -locators 1\n            -manipulators 1\n            -pluginShapes 1\n            -dimensions 1\n            -handles 1\n            -pivots 1\n            -textures 1\n            -strokes 1\n            -motionTrails 1\n            -clipGhosts 1\n            -greasePencils 1\n            -shadows 0\n            -captureSequenceNumber -1\n            -width 1\n            -height 1\n            -sceneRenderFilter 0\n            $editorName;\n        modelEditor -e -viewSelected 0 $editorName;\n        modelEditor -e \n            -pluginObjects \"gpuCacheDisplayFilter\" 1 \n            $editorName;\n"
		+ "\t\tif (!$useSceneConfig) {\n\t\t\tpanel -e -l $label $panelName;\n\t\t}\n\t}\n\n\n\t$panelName = `sceneUIReplacement -getNextPanel \"modelPanel\" (localizedPanelLabel(\"Side View\")) `;\n\tif (\"\" != $panelName) {\n\t\t$label = `panel -q -label $panelName`;\n\t\tmodelPanel -edit -l (localizedPanelLabel(\"Side View\")) -mbv $menusOkayInPanels  $panelName;\n\t\t$editorName = $panelName;\n        modelEditor -e \n            -docTag \"RADRENDER\" \n            -camera \"|side|sideShape\" \n            -useInteractiveMode 0\n            -displayLights \"default\" \n            -displayAppearance \"smoothShaded\" \n            -activeOnly 0\n            -ignorePanZoom 0\n            -wireframeOnShaded 0\n            -headsUpDisplay 1\n            -holdOuts 1\n            -selectionHiliteDisplay 1\n            -useDefaultMaterial 0\n            -bufferMode \"double\" \n            -twoSidedLighting 0\n            -backfaceCulling 0\n            -xray 0\n            -jointXray 1\n            -activeComponentsXray 0\n            -displayTextures 0\n            -smoothWireframe 0\n"
		+ "            -lineWidth 1\n            -textureAnisotropic 0\n            -textureHilight 1\n            -textureSampling 2\n            -textureDisplay \"modulate\" \n            -textureMaxSize 32768\n            -fogging 0\n            -fogSource \"fragment\" \n            -fogMode \"linear\" \n            -fogStart 0\n            -fogEnd 100\n            -fogDensity 0.1\n            -fogColor 0.5 0.5 0.5 1 \n            -depthOfFieldPreview 1\n            -maxConstantTransparency 1\n            -rendererName \"vp2Renderer\" \n            -objectFilterShowInHUD 1\n            -isFiltered 0\n            -colorResolution 4 4 \n            -bumpResolution 4 4 \n            -textureCompression 0\n            -transparencyAlgorithm \"frontAndBackCull\" \n            -transpInShadows 0\n            -cullingOverride \"none\" \n            -lowQualityLighting 0\n            -maximumNumHardwareLights 1\n            -occlusionCulling 0\n            -shadingModel 0\n            -useBaseRenderer 0\n            -useReducedRenderer 0\n            -smallObjectCulling 0\n"
		+ "            -smallObjectThreshold -1 \n            -interactiveDisableShadows 0\n            -interactiveBackFaceCull 0\n            -sortTransparent 1\n            -controllers 1\n            -nurbsCurves 1\n            -nurbsSurfaces 1\n            -polymeshes 1\n            -subdivSurfaces 1\n            -planes 1\n            -lights 1\n            -cameras 1\n            -controlVertices 1\n            -hulls 1\n            -grid 0\n            -imagePlane 1\n            -joints 1\n            -ikHandles 1\n            -deformers 1\n            -dynamics 1\n            -particleInstancers 1\n            -fluids 1\n            -hairSystems 1\n            -follicles 1\n            -nCloths 1\n            -nParticles 1\n            -nRigids 1\n            -dynamicConstraints 1\n            -locators 1\n            -manipulators 1\n            -pluginShapes 1\n            -dimensions 1\n            -handles 1\n            -pivots 1\n            -textures 1\n            -strokes 1\n            -motionTrails 1\n            -clipGhosts 1\n            -greasePencils 1\n"
		+ "            -shadows 0\n            -captureSequenceNumber -1\n            -width 1\n            -height 1\n            -sceneRenderFilter 0\n            $editorName;\n        modelEditor -e -viewSelected 0 $editorName;\n        modelEditor -e \n            -pluginObjects \"gpuCacheDisplayFilter\" 1 \n            $editorName;\n\t\tif (!$useSceneConfig) {\n\t\t\tpanel -e -l $label $panelName;\n\t\t}\n\t}\n\n\n\t$panelName = `sceneUIReplacement -getNextPanel \"modelPanel\" (localizedPanelLabel(\"Front View\")) `;\n\tif (\"\" != $panelName) {\n\t\t$label = `panel -q -label $panelName`;\n\t\tmodelPanel -edit -l (localizedPanelLabel(\"Front View\")) -mbv $menusOkayInPanels  $panelName;\n\t\t$editorName = $panelName;\n        modelEditor -e \n            -docTag \"RADRENDER\" \n            -camera \"|front|frontShape\" \n            -useInteractiveMode 0\n            -displayLights \"default\" \n            -displayAppearance \"smoothShaded\" \n            -activeOnly 0\n            -ignorePanZoom 0\n            -wireframeOnShaded 0\n            -headsUpDisplay 1\n            -holdOuts 1\n"
		+ "            -selectionHiliteDisplay 1\n            -useDefaultMaterial 0\n            -bufferMode \"double\" \n            -twoSidedLighting 0\n            -backfaceCulling 0\n            -xray 0\n            -jointXray 1\n            -activeComponentsXray 0\n            -displayTextures 0\n            -smoothWireframe 0\n            -lineWidth 1\n            -textureAnisotropic 0\n            -textureHilight 1\n            -textureSampling 2\n            -textureDisplay \"modulate\" \n            -textureMaxSize 32768\n            -fogging 0\n            -fogSource \"fragment\" \n            -fogMode \"linear\" \n            -fogStart 0\n            -fogEnd 100\n            -fogDensity 0.1\n            -fogColor 0.5 0.5 0.5 1 \n            -depthOfFieldPreview 1\n            -maxConstantTransparency 1\n            -rendererName \"vp2Renderer\" \n            -objectFilterShowInHUD 1\n            -isFiltered 0\n            -colorResolution 4 4 \n            -bumpResolution 4 4 \n            -textureCompression 0\n            -transparencyAlgorithm \"frontAndBackCull\" \n"
		+ "            -transpInShadows 0\n            -cullingOverride \"none\" \n            -lowQualityLighting 0\n            -maximumNumHardwareLights 1\n            -occlusionCulling 0\n            -shadingModel 0\n            -useBaseRenderer 0\n            -useReducedRenderer 0\n            -smallObjectCulling 0\n            -smallObjectThreshold -1 \n            -interactiveDisableShadows 0\n            -interactiveBackFaceCull 0\n            -sortTransparent 1\n            -controllers 1\n            -nurbsCurves 1\n            -nurbsSurfaces 1\n            -polymeshes 1\n            -subdivSurfaces 1\n            -planes 1\n            -lights 1\n            -cameras 1\n            -controlVertices 1\n            -hulls 1\n            -grid 0\n            -imagePlane 1\n            -joints 1\n            -ikHandles 1\n            -deformers 1\n            -dynamics 1\n            -particleInstancers 1\n            -fluids 1\n            -hairSystems 1\n            -follicles 1\n            -nCloths 1\n            -nParticles 1\n            -nRigids 1\n"
		+ "            -dynamicConstraints 1\n            -locators 1\n            -manipulators 1\n            -pluginShapes 1\n            -dimensions 1\n            -handles 1\n            -pivots 1\n            -textures 1\n            -strokes 1\n            -motionTrails 1\n            -clipGhosts 1\n            -greasePencils 1\n            -shadows 0\n            -captureSequenceNumber -1\n            -width 1\n            -height 1\n            -sceneRenderFilter 0\n            -activeShadingGraph \"ballora_animatronic_shadow_rig:rsMaterial1SG,ballora_animatronic_shadow_rig:MAT_ballora,ballora_animatronic_shadow_rig:MAT_ballora\" \n            -activeCustomGeometry \"meshShaderball\" \n            -activeCustomLighSet \"defaultAreaLightSet\" \n            $editorName;\n        modelEditor -e -viewSelected 0 $editorName;\n        modelEditor -e \n            -pluginObjects \"gpuCacheDisplayFilter\" 1 \n            $editorName;\n\t\tif (!$useSceneConfig) {\n\t\t\tpanel -e -l $label $panelName;\n\t\t}\n\t}\n\n\n\t$panelName = `sceneUIReplacement -getNextPanel \"modelPanel\" (localizedPanelLabel(\"Persp View\")) `;\n"
		+ "\tif (\"\" != $panelName) {\n\t\t$label = `panel -q -label $panelName`;\n\t\tmodelPanel -edit -l (localizedPanelLabel(\"Persp View\")) -mbv $menusOkayInPanels  $panelName;\n\t\t$editorName = $panelName;\n        modelEditor -e \n            -docTag \"RADRENDER\" \n            -camera \"|persp|perspShape\" \n            -useInteractiveMode 0\n            -displayLights \"default\" \n            -displayAppearance \"wireframe\" \n            -activeOnly 0\n            -ignorePanZoom 0\n            -wireframeOnShaded 0\n            -headsUpDisplay 1\n            -holdOuts 1\n            -selectionHiliteDisplay 1\n            -useDefaultMaterial 0\n            -bufferMode \"double\" \n            -twoSidedLighting 0\n            -backfaceCulling 0\n            -xray 0\n            -jointXray 1\n            -activeComponentsXray 0\n            -displayTextures 1\n            -smoothWireframe 0\n            -lineWidth 1\n            -textureAnisotropic 0\n            -textureHilight 1\n            -textureSampling 2\n            -textureDisplay \"modulate\" \n            -textureMaxSize 32768\n"
		+ "            -fogging 0\n            -fogSource \"fragment\" \n            -fogMode \"linear\" \n            -fogStart 0\n            -fogEnd 100\n            -fogDensity 0.1\n            -fogColor 0.5 0.5 0.5 1 \n            -depthOfFieldPreview 0\n            -maxConstantTransparency 1\n            -rendererName \"vp2Renderer\" \n            -objectFilterShowInHUD 1\n            -isFiltered 0\n            -colorResolution 4 4 \n            -bumpResolution 4 4 \n            -textureCompression 0\n            -transparencyAlgorithm \"frontAndBackCull\" \n            -transpInShadows 0\n            -cullingOverride \"none\" \n            -lowQualityLighting 0\n            -maximumNumHardwareLights 1\n            -occlusionCulling 0\n            -shadingModel 0\n            -useBaseRenderer 0\n            -useReducedRenderer 0\n            -smallObjectCulling 0\n            -smallObjectThreshold -1 \n            -interactiveDisableShadows 0\n            -interactiveBackFaceCull 0\n            -sortTransparent 1\n            -controllers 1\n            -nurbsCurves 1\n"
		+ "            -nurbsSurfaces 1\n            -polymeshes 1\n            -subdivSurfaces 1\n            -planes 1\n            -lights 1\n            -cameras 1\n            -controlVertices 1\n            -hulls 1\n            -grid 1\n            -imagePlane 1\n            -joints 1\n            -ikHandles 1\n            -deformers 1\n            -dynamics 1\n            -particleInstancers 1\n            -fluids 1\n            -hairSystems 1\n            -follicles 1\n            -nCloths 1\n            -nParticles 1\n            -nRigids 1\n            -dynamicConstraints 1\n            -locators 1\n            -manipulators 1\n            -pluginShapes 1\n            -dimensions 1\n            -handles 1\n            -pivots 1\n            -textures 1\n            -strokes 1\n            -motionTrails 1\n            -clipGhosts 1\n            -greasePencils 1\n            -shadows 0\n            -captureSequenceNumber -1\n            -width 1096\n            -height 708\n            -sceneRenderFilter 0\n            -activeShadingGraph \"ballora_animatronic_shadow_rig:rsMaterial1SG,ballora_animatronic_shadow_rig:MAT_ballora,ballora_animatronic_shadow_rig:MAT_ballora\" \n"
		+ "            -activeCustomGeometry \"meshShaderball\" \n            -activeCustomLighSet \"defaultAreaLightSet\" \n            $editorName;\n        modelEditor -e -viewSelected 0 $editorName;\n        modelEditor -e \n            -pluginObjects \"gpuCacheDisplayFilter\" 1 \n            $editorName;\n\t\tif (!$useSceneConfig) {\n\t\t\tpanel -e -l $label $panelName;\n\t\t}\n\t}\n\n\n\t$panelName = `sceneUIReplacement -getNextPanel \"outlinerPanel\" (localizedPanelLabel(\"Outliner\")) `;\n\tif (\"\" != $panelName) {\n\t\t$label = `panel -q -label $panelName`;\n\t\toutlinerPanel -edit -l (localizedPanelLabel(\"Outliner\")) -mbv $menusOkayInPanels  $panelName;\n\t\t$editorName = $panelName;\n        outlinerEditor -e \n            -docTag \"isolOutln_fromSeln\" \n            -showShapes 0\n            -showAssignedMaterials 0\n            -showTimeEditor 1\n            -showReferenceNodes 0\n            -showReferenceMembers 0\n            -showAttributes 0\n            -showConnected 0\n            -showAnimCurvesOnly 0\n            -showMuteInfo 0\n            -organizeByLayer 1\n"
		+ "            -organizeByClip 1\n            -showAnimLayerWeight 1\n            -autoExpandLayers 1\n            -autoExpand 0\n            -showDagOnly 1\n            -showAssets 1\n            -showContainedOnly 1\n            -showPublishedAsConnected 0\n            -showParentContainers 0\n            -showContainerContents 1\n            -ignoreDagHierarchy 0\n            -expandConnections 0\n            -showUpstreamCurves 1\n            -showUnitlessCurves 1\n            -showCompounds 1\n            -showLeafs 1\n            -showNumericAttrsOnly 0\n            -highlightActive 1\n            -autoSelectNewObjects 0\n            -doNotSelectNewObjects 0\n            -dropIsParent 1\n            -transmitFilters 0\n            -setFilter \"defaultSetFilter\" \n            -showSetMembers 1\n            -allowMultiSelection 1\n            -alwaysToggleSelect 0\n            -directSelect 0\n            -isSet 0\n            -isSetMember 0\n            -displayMode \"DAG\" \n            -expandObjects 0\n            -setsIgnoreFilters 1\n            -containersIgnoreFilters 0\n"
		+ "            -editAttrName 0\n            -showAttrValues 0\n            -highlightSecondary 0\n            -showUVAttrsOnly 0\n            -showTextureNodesOnly 0\n            -attrAlphaOrder \"default\" \n            -animLayerFilterOptions \"allAffecting\" \n            -sortOrder \"none\" \n            -longNames 0\n            -niceNames 1\n            -showNamespace 1\n            -showPinIcons 0\n            -mapMotionTrails 0\n            -ignoreHiddenAttribute 0\n            -ignoreOutlinerColor 0\n            -renderFilterVisible 0\n            -renderFilterIndex 0\n            -selectionOrder \"chronological\" \n            -expandAttribute 0\n            $editorName;\n\t\tif (!$useSceneConfig) {\n\t\t\tpanel -e -l $label $panelName;\n\t\t}\n\t}\n\n\n\t$panelName = `sceneUIReplacement -getNextPanel \"outlinerPanel\" (localizedPanelLabel(\"Outliner\")) `;\n\tif (\"\" != $panelName) {\n\t\t$label = `panel -q -label $panelName`;\n\t\toutlinerPanel -edit -l (localizedPanelLabel(\"Outliner\")) -mbv $menusOkayInPanels  $panelName;\n\t\t$editorName = $panelName;\n        outlinerEditor -e \n"
		+ "            -docTag \"isolOutln_fromSeln\" \n            -showShapes 0\n            -showAssignedMaterials 0\n            -showTimeEditor 1\n            -showReferenceNodes 1\n            -showReferenceMembers 0\n            -showAttributes 0\n            -showConnected 0\n            -showAnimCurvesOnly 0\n            -showMuteInfo 0\n            -organizeByLayer 1\n            -organizeByClip 1\n            -showAnimLayerWeight 1\n            -autoExpandLayers 1\n            -autoExpand 0\n            -showDagOnly 1\n            -showAssets 1\n            -showContainedOnly 1\n            -showPublishedAsConnected 0\n            -showParentContainers 0\n            -showContainerContents 1\n            -ignoreDagHierarchy 0\n            -expandConnections 0\n            -showUpstreamCurves 1\n            -showUnitlessCurves 1\n            -showCompounds 1\n            -showLeafs 1\n            -showNumericAttrsOnly 0\n            -highlightActive 1\n            -autoSelectNewObjects 0\n            -doNotSelectNewObjects 0\n            -dropIsParent 1\n"
		+ "            -transmitFilters 0\n            -setFilter \"defaultSetFilter\" \n            -showSetMembers 1\n            -allowMultiSelection 1\n            -alwaysToggleSelect 0\n            -directSelect 0\n            -displayMode \"DAG\" \n            -expandObjects 0\n            -setsIgnoreFilters 1\n            -containersIgnoreFilters 0\n            -editAttrName 0\n            -showAttrValues 0\n            -highlightSecondary 0\n            -showUVAttrsOnly 0\n            -showTextureNodesOnly 0\n            -attrAlphaOrder \"default\" \n            -animLayerFilterOptions \"allAffecting\" \n            -sortOrder \"none\" \n            -longNames 0\n            -niceNames 1\n            -showNamespace 1\n            -showPinIcons 0\n            -mapMotionTrails 0\n            -ignoreHiddenAttribute 0\n            -ignoreOutlinerColor 0\n            -renderFilterVisible 0\n            $editorName;\n\t\tif (!$useSceneConfig) {\n\t\t\tpanel -e -l $label $panelName;\n\t\t}\n\t}\n\n\n\t$panelName = `sceneUIReplacement -getNextScriptedPanel \"graphEditor\" (localizedPanelLabel(\"Graph Editor\")) `;\n"
		+ "\tif (\"\" != $panelName) {\n\t\t$label = `panel -q -label $panelName`;\n\t\tscriptedPanel -edit -l (localizedPanelLabel(\"Graph Editor\")) -mbv $menusOkayInPanels  $panelName;\n\n\t\t\t$editorName = ($panelName+\"OutlineEd\");\n            outlinerEditor -e \n                -showShapes 1\n                -showAssignedMaterials 0\n                -showTimeEditor 1\n                -showReferenceNodes 0\n                -showReferenceMembers 0\n                -showAttributes 1\n                -showConnected 1\n                -showAnimCurvesOnly 1\n                -showMuteInfo 0\n                -organizeByLayer 1\n                -organizeByClip 1\n                -showAnimLayerWeight 1\n                -autoExpandLayers 1\n                -autoExpand 1\n                -showDagOnly 0\n                -showAssets 1\n                -showContainedOnly 0\n                -showPublishedAsConnected 0\n                -showParentContainers 1\n                -showContainerContents 0\n                -ignoreDagHierarchy 0\n                -expandConnections 1\n"
		+ "                -showUpstreamCurves 1\n                -showUnitlessCurves 1\n                -showCompounds 0\n                -showLeafs 1\n                -showNumericAttrsOnly 1\n                -highlightActive 0\n                -autoSelectNewObjects 1\n                -doNotSelectNewObjects 0\n                -dropIsParent 1\n                -transmitFilters 1\n                -setFilter \"0\" \n                -showSetMembers 0\n                -allowMultiSelection 1\n                -alwaysToggleSelect 0\n                -directSelect 0\n                -displayMode \"DAG\" \n                -expandObjects 0\n                -setsIgnoreFilters 1\n                -containersIgnoreFilters 0\n                -editAttrName 0\n                -showAttrValues 0\n                -highlightSecondary 0\n                -showUVAttrsOnly 0\n                -showTextureNodesOnly 0\n                -attrAlphaOrder \"default\" \n                -animLayerFilterOptions \"allAffecting\" \n                -sortOrder \"none\" \n                -longNames 0\n"
		+ "                -niceNames 1\n                -showNamespace 1\n                -showPinIcons 1\n                -mapMotionTrails 1\n                -ignoreHiddenAttribute 0\n                -ignoreOutlinerColor 0\n                -renderFilterVisible 0\n                $editorName;\n\n\t\t\t$editorName = ($panelName+\"GraphEd\");\n            animCurveEditor -e \n                -displayValues 0\n                -snapTime \"integer\" \n                -snapValue \"none\" \n                -showPlayRangeShades \"on\" \n                -lockPlayRangeShades \"off\" \n                -smoothness \"fine\" \n                -resultSamples 1\n                -resultScreenSamples 0\n                -resultUpdate \"delayed\" \n                -showUpstreamCurves 1\n                -keyMinScale 1\n                -stackedCurvesMin -1\n                -stackedCurvesMax 1\n                -stackedCurvesSpace 0.2\n                -preSelectionHighlight 0\n                -constrainDrag 0\n                -valueLinesToggle 1\n                -outliner \"graphEditor1OutlineEd\" \n"
		+ "                -highlightAffectedCurves 0\n                $editorName;\n\t\tif (!$useSceneConfig) {\n\t\t\tpanel -e -l $label $panelName;\n\t\t}\n\t}\n\n\n\t$panelName = `sceneUIReplacement -getNextScriptedPanel \"dopeSheetPanel\" (localizedPanelLabel(\"Dope Sheet\")) `;\n\tif (\"\" != $panelName) {\n\t\t$label = `panel -q -label $panelName`;\n\t\tscriptedPanel -edit -l (localizedPanelLabel(\"Dope Sheet\")) -mbv $menusOkayInPanels  $panelName;\n\n\t\t\t$editorName = ($panelName+\"OutlineEd\");\n            outlinerEditor -e \n                -showShapes 1\n                -showAssignedMaterials 0\n                -showTimeEditor 1\n                -showReferenceNodes 0\n                -showReferenceMembers 0\n                -showAttributes 1\n                -showConnected 1\n                -showAnimCurvesOnly 1\n                -showMuteInfo 0\n                -organizeByLayer 1\n                -organizeByClip 1\n                -showAnimLayerWeight 1\n                -autoExpandLayers 1\n                -autoExpand 0\n                -showDagOnly 0\n                -showAssets 1\n"
		+ "                -showContainedOnly 0\n                -showPublishedAsConnected 0\n                -showParentContainers 1\n                -showContainerContents 0\n                -ignoreDagHierarchy 0\n                -expandConnections 1\n                -showUpstreamCurves 1\n                -showUnitlessCurves 0\n                -showCompounds 1\n                -showLeafs 1\n                -showNumericAttrsOnly 1\n                -highlightActive 0\n                -autoSelectNewObjects 0\n                -doNotSelectNewObjects 1\n                -dropIsParent 1\n                -transmitFilters 0\n                -setFilter \"0\" \n                -showSetMembers 0\n                -allowMultiSelection 1\n                -alwaysToggleSelect 0\n                -directSelect 0\n                -displayMode \"DAG\" \n                -expandObjects 0\n                -setsIgnoreFilters 1\n                -containersIgnoreFilters 0\n                -editAttrName 0\n                -showAttrValues 0\n                -highlightSecondary 0\n"
		+ "                -showUVAttrsOnly 0\n                -showTextureNodesOnly 0\n                -attrAlphaOrder \"default\" \n                -animLayerFilterOptions \"allAffecting\" \n                -sortOrder \"none\" \n                -longNames 0\n                -niceNames 1\n                -showNamespace 1\n                -showPinIcons 0\n                -mapMotionTrails 1\n                -ignoreHiddenAttribute 0\n                -ignoreOutlinerColor 0\n                -renderFilterVisible 0\n                $editorName;\n\n\t\t\t$editorName = ($panelName+\"DopeSheetEd\");\n            dopeSheetEditor -e \n                -displayValues 0\n                -snapTime \"integer\" \n                -snapValue \"none\" \n                -outliner \"dopeSheetPanel1OutlineEd\" \n                -showSummary 1\n                -showScene 0\n                -hierarchyBelow 0\n                -showTicks 1\n                -selectionWindow 0 0 0 0 \n                $editorName;\n\t\tif (!$useSceneConfig) {\n\t\t\tpanel -e -l $label $panelName;\n\t\t}\n\t}\n\n\n\t$panelName = `sceneUIReplacement -getNextScriptedPanel \"timeEditorPanel\" (localizedPanelLabel(\"Time Editor\")) `;\n"
		+ "\tif (\"\" != $panelName) {\n\t\t$label = `panel -q -label $panelName`;\n\t\tscriptedPanel -edit -l (localizedPanelLabel(\"Time Editor\")) -mbv $menusOkayInPanels  $panelName;\n\t\tif (!$useSceneConfig) {\n\t\t\tpanel -e -l $label $panelName;\n\t\t}\n\t}\n\n\n\t$panelName = `sceneUIReplacement -getNextScriptedPanel \"clipEditorPanel\" (localizedPanelLabel(\"Trax Editor\")) `;\n\tif (\"\" != $panelName) {\n\t\t$label = `panel -q -label $panelName`;\n\t\tscriptedPanel -edit -l (localizedPanelLabel(\"Trax Editor\")) -mbv $menusOkayInPanels  $panelName;\n\n\t\t\t$editorName = clipEditorNameFromPanel($panelName);\n            clipEditor -e \n                -displayValues 0\n                -snapTime \"none\" \n                -snapValue \"none\" \n                -initialized 0\n                -manageSequencer 0 \n                $editorName;\n\t\tif (!$useSceneConfig) {\n\t\t\tpanel -e -l $label $panelName;\n\t\t}\n\t}\n\n\n\t$panelName = `sceneUIReplacement -getNextScriptedPanel \"sequenceEditorPanel\" (localizedPanelLabel(\"Camera Sequencer\")) `;\n\tif (\"\" != $panelName) {\n\t\t$label = `panel -q -label $panelName`;\n"
		+ "\t\tscriptedPanel -edit -l (localizedPanelLabel(\"Camera Sequencer\")) -mbv $menusOkayInPanels  $panelName;\n\n\t\t\t$editorName = sequenceEditorNameFromPanel($panelName);\n            clipEditor -e \n                -displayValues 0\n                -snapTime \"none\" \n                -snapValue \"none\" \n                -initialized 0\n                -manageSequencer 1 \n                $editorName;\n\t\tif (!$useSceneConfig) {\n\t\t\tpanel -e -l $label $panelName;\n\t\t}\n\t}\n\n\n\t$panelName = `sceneUIReplacement -getNextScriptedPanel \"hyperGraphPanel\" (localizedPanelLabel(\"Hypergraph Hierarchy\")) `;\n\tif (\"\" != $panelName) {\n\t\t$label = `panel -q -label $panelName`;\n\t\tscriptedPanel -edit -l (localizedPanelLabel(\"Hypergraph Hierarchy\")) -mbv $menusOkayInPanels  $panelName;\n\n\t\t\t$editorName = ($panelName+\"HyperGraphEd\");\n            hyperGraph -e \n                -graphLayoutStyle \"hierarchicalLayout\" \n                -orientation \"horiz\" \n                -mergeConnections 0\n                -zoom 1\n                -animateTransition 0\n                -showRelationships 1\n"
		+ "                -showShapes 0\n                -showDeformers 0\n                -showExpressions 0\n                -showConstraints 0\n                -showConnectionFromSelected 0\n                -showConnectionToSelected 0\n                -showConstraintLabels 0\n                -showUnderworld 0\n                -showInvisible 0\n                -transitionFrames 1\n                -opaqueContainers 0\n                -freeform 0\n                -image \"xdzvzd\" \n                -imagePosition 0 0 \n                -imageScale 1\n                -imageEnabled 0\n                -graphType \"DAG\" \n                -heatMapDisplay 0\n                -updateSelection 1\n                -updateNodeAdded 1\n                -useDrawOverrideColor 0\n                -limitGraphTraversal -1\n                -range 0 0 \n                -iconSize \"smallIcons\" \n                -showCachedConnections 0\n                $editorName;\n\t\tif (!$useSceneConfig) {\n\t\t\tpanel -e -l $label $panelName;\n\t\t}\n\t}\n\n\n\t$panelName = `sceneUIReplacement -getNextScriptedPanel \"hyperShadePanel\" (localizedPanelLabel(\"Hypershade\")) `;\n"
		+ "\tif (\"\" != $panelName) {\n\t\t$label = `panel -q -label $panelName`;\n\t\tscriptedPanel -edit -l (localizedPanelLabel(\"Hypershade\")) -mbv $menusOkayInPanels  $panelName;\n\t\tif (!$useSceneConfig) {\n\t\t\tpanel -e -l $label $panelName;\n\t\t}\n\t}\n\n\n\t$panelName = `sceneUIReplacement -getNextScriptedPanel \"visorPanel\" (localizedPanelLabel(\"Visor\")) `;\n\tif (\"\" != $panelName) {\n\t\t$label = `panel -q -label $panelName`;\n\t\tscriptedPanel -edit -l (localizedPanelLabel(\"Visor\")) -mbv $menusOkayInPanels  $panelName;\n\t\tif (!$useSceneConfig) {\n\t\t\tpanel -e -l $label $panelName;\n\t\t}\n\t}\n\n\n\t$panelName = `sceneUIReplacement -getNextScriptedPanel \"nodeEditorPanel\" (localizedPanelLabel(\"Node Editor\")) `;\n\tif ($nodeEditorPanelVisible || $nodeEditorWorkspaceControlOpen) {\n\t\tif (\"\" == $panelName) {\n\t\t\tif ($useSceneConfig) {\n\t\t\t\t$panelName = `scriptedPanel -unParent  -type \"nodeEditorPanel\" -l (localizedPanelLabel(\"Node Editor\")) -mbv $menusOkayInPanels `;\n\n\t\t\t$editorName = ($panelName+\"NodeEditorEd\");\n            nodeEditor -e \n                -allAttributes 0\n"
		+ "                -allNodes 0\n                -autoSizeNodes 1\n                -consistentNameSize 1\n                -createNodeCommand \"nodeEdCreateNodeCommand\" \n                -connectNodeOnCreation 0\n                -connectOnDrop 0\n                -copyConnectionsOnPaste 0\n                -connectionStyle \"bezier\" \n                -defaultPinnedState 0\n                -additiveGraphingMode 0\n                -settingsChangedCallback \"nodeEdSyncControls\" \n                -traversalDepthLimit -1\n                -keyPressCommand \"nodeEdKeyPressCommand\" \n                -nodeTitleMode \"name\" \n                -gridSnap 0\n                -gridVisibility 1\n                -crosshairOnEdgeDragging 0\n                -popupMenuScript \"nodeEdBuildPanelMenus\" \n                -showNamespace 1\n                -showShapes 1\n                -showSGShapes 0\n                -showTransforms 1\n                -useAssets 1\n                -syncedSelection 1\n                -extendToShapes 1\n                -editorMode \"default\" \n"
		+ "                -hasWatchpoint 0\n                $editorName;\n\t\t\t}\n\t\t} else {\n\t\t\t$label = `panel -q -label $panelName`;\n\t\t\tscriptedPanel -edit -l (localizedPanelLabel(\"Node Editor\")) -mbv $menusOkayInPanels  $panelName;\n\n\t\t\t$editorName = ($panelName+\"NodeEditorEd\");\n            nodeEditor -e \n                -allAttributes 0\n                -allNodes 0\n                -autoSizeNodes 1\n                -consistentNameSize 1\n                -createNodeCommand \"nodeEdCreateNodeCommand\" \n                -connectNodeOnCreation 0\n                -connectOnDrop 0\n                -copyConnectionsOnPaste 0\n                -connectionStyle \"bezier\" \n                -defaultPinnedState 0\n                -additiveGraphingMode 0\n                -settingsChangedCallback \"nodeEdSyncControls\" \n                -traversalDepthLimit -1\n                -keyPressCommand \"nodeEdKeyPressCommand\" \n                -nodeTitleMode \"name\" \n                -gridSnap 0\n                -gridVisibility 1\n                -crosshairOnEdgeDragging 0\n"
		+ "                -popupMenuScript \"nodeEdBuildPanelMenus\" \n                -showNamespace 1\n                -showShapes 1\n                -showSGShapes 0\n                -showTransforms 1\n                -useAssets 1\n                -syncedSelection 1\n                -extendToShapes 1\n                -editorMode \"default\" \n                -hasWatchpoint 0\n                $editorName;\n\t\t\tif (!$useSceneConfig) {\n\t\t\t\tpanel -e -l $label $panelName;\n\t\t\t}\n\t\t}\n\t}\n\n\n\t$panelName = `sceneUIReplacement -getNextScriptedPanel \"createNodePanel\" (localizedPanelLabel(\"Create Node\")) `;\n\tif (\"\" != $panelName) {\n\t\t$label = `panel -q -label $panelName`;\n\t\tscriptedPanel -edit -l (localizedPanelLabel(\"Create Node\")) -mbv $menusOkayInPanels  $panelName;\n\t\tif (!$useSceneConfig) {\n\t\t\tpanel -e -l $label $panelName;\n\t\t}\n\t}\n\n\n\t$panelName = `sceneUIReplacement -getNextScriptedPanel \"polyTexturePlacementPanel\" (localizedPanelLabel(\"UV Editor\")) `;\n\tif (\"\" != $panelName) {\n\t\t$label = `panel -q -label $panelName`;\n\t\tscriptedPanel -edit -l (localizedPanelLabel(\"UV Editor\")) -mbv $menusOkayInPanels  $panelName;\n"
		+ "\t\tif (!$useSceneConfig) {\n\t\t\tpanel -e -l $label $panelName;\n\t\t}\n\t}\n\n\n\t$panelName = `sceneUIReplacement -getNextScriptedPanel \"renderWindowPanel\" (localizedPanelLabel(\"Render View\")) `;\n\tif (\"\" != $panelName) {\n\t\t$label = `panel -q -label $panelName`;\n\t\tscriptedPanel -edit -l (localizedPanelLabel(\"Render View\")) -mbv $menusOkayInPanels  $panelName;\n\t\tif (!$useSceneConfig) {\n\t\t\tpanel -e -l $label $panelName;\n\t\t}\n\t}\n\n\n\t$panelName = `sceneUIReplacement -getNextPanel \"shapePanel\" (localizedPanelLabel(\"Shape Editor\")) `;\n\tif (\"\" != $panelName) {\n\t\t$label = `panel -q -label $panelName`;\n\t\tshapePanel -edit -l (localizedPanelLabel(\"Shape Editor\")) -mbv $menusOkayInPanels  $panelName;\n\t\tif (!$useSceneConfig) {\n\t\t\tpanel -e -l $label $panelName;\n\t\t}\n\t}\n\n\n\t$panelName = `sceneUIReplacement -getNextPanel \"posePanel\" (localizedPanelLabel(\"Pose Editor\")) `;\n\tif (\"\" != $panelName) {\n\t\t$label = `panel -q -label $panelName`;\n\t\tposePanel -edit -l (localizedPanelLabel(\"Pose Editor\")) -mbv $menusOkayInPanels  $panelName;\n\t\tif (!$useSceneConfig) {\n"
		+ "\t\t\tpanel -e -l $label $panelName;\n\t\t}\n\t}\n\n\n\t$panelName = `sceneUIReplacement -getNextScriptedPanel \"dynRelEdPanel\" (localizedPanelLabel(\"Dynamic Relationships\")) `;\n\tif (\"\" != $panelName) {\n\t\t$label = `panel -q -label $panelName`;\n\t\tscriptedPanel -edit -l (localizedPanelLabel(\"Dynamic Relationships\")) -mbv $menusOkayInPanels  $panelName;\n\t\tif (!$useSceneConfig) {\n\t\t\tpanel -e -l $label $panelName;\n\t\t}\n\t}\n\n\n\t$panelName = `sceneUIReplacement -getNextScriptedPanel \"relationshipPanel\" (localizedPanelLabel(\"Relationship Editor\")) `;\n\tif (\"\" != $panelName) {\n\t\t$label = `panel -q -label $panelName`;\n\t\tscriptedPanel -edit -l (localizedPanelLabel(\"Relationship Editor\")) -mbv $menusOkayInPanels  $panelName;\n\t\tif (!$useSceneConfig) {\n\t\t\tpanel -e -l $label $panelName;\n\t\t}\n\t}\n\n\n\t$panelName = `sceneUIReplacement -getNextScriptedPanel \"referenceEditorPanel\" (localizedPanelLabel(\"Reference Editor\")) `;\n\tif (\"\" != $panelName) {\n\t\t$label = `panel -q -label $panelName`;\n\t\tscriptedPanel -edit -l (localizedPanelLabel(\"Reference Editor\")) -mbv $menusOkayInPanels  $panelName;\n"
		+ "\t\tif (!$useSceneConfig) {\n\t\t\tpanel -e -l $label $panelName;\n\t\t}\n\t}\n\n\n\t$panelName = `sceneUIReplacement -getNextScriptedPanel \"componentEditorPanel\" (localizedPanelLabel(\"Component Editor\")) `;\n\tif (\"\" != $panelName) {\n\t\t$label = `panel -q -label $panelName`;\n\t\tscriptedPanel -edit -l (localizedPanelLabel(\"Component Editor\")) -mbv $menusOkayInPanels  $panelName;\n\t\tif (!$useSceneConfig) {\n\t\t\tpanel -e -l $label $panelName;\n\t\t}\n\t}\n\n\n\t$panelName = `sceneUIReplacement -getNextScriptedPanel \"dynPaintScriptedPanelType\" (localizedPanelLabel(\"Paint Effects\")) `;\n\tif (\"\" != $panelName) {\n\t\t$label = `panel -q -label $panelName`;\n\t\tscriptedPanel -edit -l (localizedPanelLabel(\"Paint Effects\")) -mbv $menusOkayInPanels  $panelName;\n\t\tif (!$useSceneConfig) {\n\t\t\tpanel -e -l $label $panelName;\n\t\t}\n\t}\n\n\n\t$panelName = `sceneUIReplacement -getNextScriptedPanel \"scriptEditorPanel\" (localizedPanelLabel(\"Script Editor\")) `;\n\tif (\"\" != $panelName) {\n\t\t$label = `panel -q -label $panelName`;\n\t\tscriptedPanel -edit -l (localizedPanelLabel(\"Script Editor\")) -mbv $menusOkayInPanels  $panelName;\n"
		+ "\t\tif (!$useSceneConfig) {\n\t\t\tpanel -e -l $label $panelName;\n\t\t}\n\t}\n\n\n\t$panelName = `sceneUIReplacement -getNextScriptedPanel \"profilerPanel\" (localizedPanelLabel(\"Profiler Tool\")) `;\n\tif (\"\" != $panelName) {\n\t\t$label = `panel -q -label $panelName`;\n\t\tscriptedPanel -edit -l (localizedPanelLabel(\"Profiler Tool\")) -mbv $menusOkayInPanels  $panelName;\n\t\tif (!$useSceneConfig) {\n\t\t\tpanel -e -l $label $panelName;\n\t\t}\n\t}\n\n\n\t$panelName = `sceneUIReplacement -getNextScriptedPanel \"contentBrowserPanel\" (localizedPanelLabel(\"Content Browser\")) `;\n\tif (\"\" != $panelName) {\n\t\t$label = `panel -q -label $panelName`;\n\t\tscriptedPanel -edit -l (localizedPanelLabel(\"Content Browser\")) -mbv $menusOkayInPanels  $panelName;\n\t\tif (!$useSceneConfig) {\n\t\t\tpanel -e -l $label $panelName;\n\t\t}\n\t}\n\n\n\t$panelName = `sceneUIReplacement -getNextScriptedPanel \"Stereo\" (localizedPanelLabel(\"Stereo\")) `;\n\tif (\"\" != $panelName) {\n\t\t$label = `panel -q -label $panelName`;\n\t\tscriptedPanel -edit -l (localizedPanelLabel(\"Stereo\")) -mbv $menusOkayInPanels  $panelName;\n"
		+ "{ string $editorName = ($panelName+\"Editor\");\n            stereoCameraView -e \n                -editorChanged \"updateModelPanelBar\" \n                -camera \"|persp|perspShape\" \n                -useInteractiveMode 0\n                -displayLights \"default\" \n                -displayAppearance \"smoothShaded\" \n                -activeOnly 0\n                -ignorePanZoom 0\n                -wireframeOnShaded 0\n                -headsUpDisplay 1\n                -holdOuts 1\n                -selectionHiliteDisplay 1\n                -useDefaultMaterial 0\n                -bufferMode \"double\" \n                -twoSidedLighting 0\n                -backfaceCulling 0\n                -xray 0\n                -jointXray 0\n                -activeComponentsXray 0\n                -displayTextures 0\n                -smoothWireframe 0\n                -lineWidth 1\n                -textureAnisotropic 0\n                -textureHilight 1\n                -textureSampling 2\n                -textureDisplay \"modulate\" \n                -textureMaxSize 32768\n"
		+ "                -fogging 0\n                -fogSource \"fragment\" \n                -fogMode \"linear\" \n                -fogStart 0\n                -fogEnd 100\n                -fogDensity 0.1\n                -fogColor 0.5 0.5 0.5 1 \n                -depthOfFieldPreview 1\n                -maxConstantTransparency 1\n                -rendererOverrideName \"stereoOverrideVP2\" \n                -objectFilterShowInHUD 1\n                -isFiltered 0\n                -colorResolution 4 4 \n                -bumpResolution 4 4 \n                -textureCompression 0\n                -transparencyAlgorithm \"frontAndBackCull\" \n                -transpInShadows 0\n                -cullingOverride \"none\" \n                -lowQualityLighting 0\n                -maximumNumHardwareLights 0\n                -occlusionCulling 0\n                -shadingModel 0\n                -useBaseRenderer 0\n                -useReducedRenderer 0\n                -smallObjectCulling 0\n                -smallObjectThreshold -1 \n                -interactiveDisableShadows 0\n"
		+ "                -interactiveBackFaceCull 0\n                -sortTransparent 1\n                -controllers 1\n                -nurbsCurves 1\n                -nurbsSurfaces 1\n                -polymeshes 1\n                -subdivSurfaces 1\n                -planes 1\n                -lights 1\n                -cameras 1\n                -controlVertices 1\n                -hulls 1\n                -grid 1\n                -imagePlane 1\n                -joints 1\n                -ikHandles 1\n                -deformers 1\n                -dynamics 1\n                -particleInstancers 1\n                -fluids 1\n                -hairSystems 1\n                -follicles 1\n                -nCloths 1\n                -nParticles 1\n                -nRigids 1\n                -dynamicConstraints 1\n                -locators 1\n                -manipulators 1\n                -pluginShapes 1\n                -dimensions 1\n                -handles 1\n                -pivots 1\n                -textures 1\n                -strokes 1\n                -motionTrails 1\n"
		+ "                -clipGhosts 1\n                -greasePencils 1\n                -shadows 0\n                -captureSequenceNumber -1\n                -width 0\n                -height 0\n                -sceneRenderFilter 0\n                -displayMode \"centerEye\" \n                -viewColor 0 0 0 1 \n                -useCustomBackground 1\n                $editorName;\n            stereoCameraView -e -viewSelected 0 $editorName;\n            stereoCameraView -e \n                -pluginObjects \"gpuCacheDisplayFilter\" 1 \n                $editorName; };\n\t\tif (!$useSceneConfig) {\n\t\t\tpanel -e -l $label $panelName;\n\t\t}\n\t}\n\n\n\t$panelName = `sceneUIReplacement -getNextPanel \"modelPanel\" (localizedPanelLabel(\"Perspective\")) `;\n\tif (\"\" != $panelName) {\n\t\t$label = `panel -q -label $panelName`;\n\t\tmodelPanel -edit -l (localizedPanelLabel(\"Perspective\")) -mbv $menusOkayInPanels  $panelName;\n\t\t$editorName = $panelName;\n        modelEditor -e \n            -docTag \"RADRENDER\" \n            -camera \"|side|sideShape\" \n            -useInteractiveMode 0\n"
		+ "            -displayLights \"default\" \n            -displayAppearance \"smoothShaded\" \n            -activeOnly 0\n            -ignorePanZoom 0\n            -wireframeOnShaded 0\n            -headsUpDisplay 1\n            -holdOuts 1\n            -selectionHiliteDisplay 1\n            -useDefaultMaterial 0\n            -bufferMode \"double\" \n            -twoSidedLighting 1\n            -backfaceCulling 0\n            -xray 0\n            -jointXray 1\n            -activeComponentsXray 0\n            -displayTextures 0\n            -smoothWireframe 0\n            -lineWidth 1\n            -textureAnisotropic 0\n            -textureHilight 1\n            -textureSampling 2\n            -textureDisplay \"modulate\" \n            -textureMaxSize 32768\n            -fogging 0\n            -fogSource \"fragment\" \n            -fogMode \"linear\" \n            -fogStart 0\n            -fogEnd 100\n            -fogDensity 0.1\n            -fogColor 0.5 0.5 0.5 1 \n            -depthOfFieldPreview 1\n            -maxConstantTransparency 1\n            -rendererName \"vp2Renderer\" \n"
		+ "            -objectFilterShowInHUD 1\n            -isFiltered 0\n            -colorResolution 4 4 \n            -bumpResolution 4 4 \n            -textureCompression 0\n            -transparencyAlgorithm \"frontAndBackCull\" \n            -transpInShadows 0\n            -cullingOverride \"none\" \n            -lowQualityLighting 0\n            -maximumNumHardwareLights 0\n            -occlusionCulling 0\n            -shadingModel 0\n            -useBaseRenderer 0\n            -useReducedRenderer 0\n            -smallObjectCulling 0\n            -smallObjectThreshold -1 \n            -interactiveDisableShadows 0\n            -interactiveBackFaceCull 0\n            -sortTransparent 1\n            -controllers 1\n            -nurbsCurves 1\n            -nurbsSurfaces 1\n            -polymeshes 1\n            -subdivSurfaces 1\n            -planes 1\n            -lights 1\n            -cameras 1\n            -controlVertices 1\n            -hulls 1\n            -grid 1\n            -imagePlane 1\n            -joints 1\n            -ikHandles 1\n            -deformers 1\n"
		+ "            -dynamics 1\n            -particleInstancers 1\n            -fluids 1\n            -hairSystems 1\n            -follicles 1\n            -nCloths 1\n            -nParticles 1\n            -nRigids 1\n            -dynamicConstraints 1\n            -locators 1\n            -manipulators 1\n            -pluginShapes 1\n            -dimensions 1\n            -handles 1\n            -pivots 1\n            -textures 1\n            -strokes 1\n            -motionTrails 1\n            -clipGhosts 1\n            -greasePencils 1\n            -shadows 0\n            -captureSequenceNumber -1\n            -width 893\n            -height 708\n            -sceneRenderFilter 0\n            -activeShadingGraph \"ballora_animatronic_shadow_rig:rsMaterial1SG,ballora_animatronic_shadow_rig:MAT_ballora,ballora_animatronic_shadow_rig:MAT_ballora\" \n            -activeCustomGeometry \"meshShaderball\" \n            -activeCustomLighSet \"defaultAreaLightSet\" \n            $editorName;\n        modelEditor -e -viewSelected 0 $editorName;\n        modelEditor -e \n"
		+ "            -pluginObjects \"gpuCacheDisplayFilter\" 1 \n            $editorName;\n\t\tif (!$useSceneConfig) {\n\t\t\tpanel -e -l $label $panelName;\n\t\t}\n\t}\n\n\n\t$panelName = `sceneUIReplacement -getNextPanel \"outlinerPanel\" (localizedPanelLabel(\"Outliner\")) `;\n\tif (\"\" != $panelName) {\n\t\t$label = `panel -q -label $panelName`;\n\t\toutlinerPanel -edit -l (localizedPanelLabel(\"Outliner\")) -mbv $menusOkayInPanels  $panelName;\n\t\t$editorName = $panelName;\n        outlinerEditor -e \n            -docTag \"isolOutln_fromSeln\" \n            -showShapes 0\n            -showAssignedMaterials 0\n            -showTimeEditor 1\n            -showReferenceNodes 0\n            -showReferenceMembers 0\n            -showAttributes 0\n            -showConnected 0\n            -showAnimCurvesOnly 0\n            -showMuteInfo 0\n            -organizeByLayer 1\n            -organizeByClip 1\n            -showAnimLayerWeight 1\n            -autoExpandLayers 1\n            -autoExpand 0\n            -showDagOnly 1\n            -showAssets 1\n            -showContainedOnly 1\n"
		+ "            -showPublishedAsConnected 0\n            -showParentContainers 0\n            -showContainerContents 1\n            -ignoreDagHierarchy 0\n            -expandConnections 0\n            -showUpstreamCurves 1\n            -showUnitlessCurves 1\n            -showCompounds 1\n            -showLeafs 1\n            -showNumericAttrsOnly 0\n            -highlightActive 1\n            -autoSelectNewObjects 0\n            -doNotSelectNewObjects 0\n            -dropIsParent 1\n            -transmitFilters 0\n            -setFilter \"defaultSetFilter\" \n            -showSetMembers 1\n            -allowMultiSelection 1\n            -alwaysToggleSelect 0\n            -directSelect 0\n            -isSet 0\n            -isSetMember 0\n            -displayMode \"DAG\" \n            -expandObjects 0\n            -setsIgnoreFilters 1\n            -containersIgnoreFilters 0\n            -editAttrName 0\n            -showAttrValues 0\n            -highlightSecondary 0\n            -showUVAttrsOnly 0\n            -showTextureNodesOnly 0\n            -attrAlphaOrder \"default\" \n"
		+ "            -animLayerFilterOptions \"allAffecting\" \n            -sortOrder \"none\" \n            -longNames 0\n            -niceNames 1\n            -showNamespace 1\n            -showPinIcons 0\n            -mapMotionTrails 0\n            -ignoreHiddenAttribute 0\n            -ignoreOutlinerColor 0\n            -renderFilterVisible 0\n            -renderFilterIndex 0\n            -selectionOrder \"chronological\" \n            -expandAttribute 0\n            $editorName;\n\t\tif (!$useSceneConfig) {\n\t\t\tpanel -e -l $label $panelName;\n\t\t}\n\t}\n\n\n\t$panelName = `sceneUIReplacement -getNextPanel \"outlinerPanel\" (localizedPanelLabel(\"ToggledOutliner\")) `;\n\tif (\"\" != $panelName) {\n\t\t$label = `panel -q -label $panelName`;\n\t\toutlinerPanel -edit -l (localizedPanelLabel(\"ToggledOutliner\")) -mbv $menusOkayInPanels  $panelName;\n\t\t$editorName = $panelName;\n        outlinerEditor -e \n            -showShapes 0\n            -showAssignedMaterials 0\n            -showTimeEditor 1\n            -showReferenceNodes 1\n            -showReferenceMembers 1\n"
		+ "            -showAttributes 0\n            -showConnected 0\n            -showAnimCurvesOnly 0\n            -showMuteInfo 0\n            -organizeByLayer 1\n            -organizeByClip 1\n            -showAnimLayerWeight 1\n            -autoExpandLayers 1\n            -autoExpand 0\n            -showDagOnly 1\n            -showAssets 1\n            -showContainedOnly 1\n            -showPublishedAsConnected 0\n            -showParentContainers 0\n            -showContainerContents 1\n            -ignoreDagHierarchy 0\n            -expandConnections 0\n            -showUpstreamCurves 1\n            -showUnitlessCurves 1\n            -showCompounds 1\n            -showLeafs 1\n            -showNumericAttrsOnly 0\n            -highlightActive 1\n            -autoSelectNewObjects 0\n            -doNotSelectNewObjects 0\n            -dropIsParent 1\n            -transmitFilters 0\n            -setFilter \"defaultSetFilter\" \n            -showSetMembers 1\n            -allowMultiSelection 1\n            -alwaysToggleSelect 0\n            -directSelect 0\n"
		+ "            -isSet 0\n            -isSetMember 0\n            -displayMode \"DAG\" \n            -expandObjects 0\n            -setsIgnoreFilters 1\n            -containersIgnoreFilters 0\n            -editAttrName 0\n            -showAttrValues 0\n            -highlightSecondary 0\n            -showUVAttrsOnly 0\n            -showTextureNodesOnly 0\n            -attrAlphaOrder \"default\" \n            -animLayerFilterOptions \"allAffecting\" \n            -sortOrder \"none\" \n            -longNames 0\n            -niceNames 1\n            -showNamespace 1\n            -showPinIcons 0\n            -mapMotionTrails 0\n            -ignoreHiddenAttribute 0\n            -ignoreOutlinerColor 0\n            -renderFilterVisible 0\n            -renderFilterIndex 0\n            -selectionOrder \"chronological\" \n            -expandAttribute 0\n            $editorName;\n\t\tif (!$useSceneConfig) {\n\t\t\tpanel -e -l $label $panelName;\n\t\t}\n\t}\n\n\n\tif ($useSceneConfig) {\n        string $configName = `getPanel -cwl (localizedPanelLabel(\"Current Layout\"))`;\n        if (\"\" != $configName) {\n"
		+ "\t\t\tpanelConfiguration -edit -label (localizedPanelLabel(\"Current Layout\")) \n\t\t\t\t-userCreated false\n\t\t\t\t-defaultImage \"vacantCell.xP:/\"\n\t\t\t\t-image \"\"\n\t\t\t\t-sc false\n\t\t\t\t-configString \"global string $gMainPane; paneLayout -e -cn \\\"single\\\" -ps 1 100 100 $gMainPane;\"\n\t\t\t\t-removeAllPanels\n\t\t\t\t-ap false\n\t\t\t\t\t(localizedPanelLabel(\"Persp View\")) \n\t\t\t\t\t\"modelPanel\"\n"
		+ "\t\t\t\t\t\"$panelName = `modelPanel -unParent -l (localizedPanelLabel(\\\"Persp View\\\")) -mbv $menusOkayInPanels `;\\n$editorName = $panelName;\\nmodelEditor -e \\n    -docTag \\\"RADRENDER\\\" \\n    -cam `findStartUpCamera persp` \\n    -useInteractiveMode 0\\n    -displayLights \\\"default\\\" \\n    -displayAppearance \\\"wireframe\\\" \\n    -activeOnly 0\\n    -ignorePanZoom 0\\n    -wireframeOnShaded 0\\n    -headsUpDisplay 1\\n    -holdOuts 1\\n    -selectionHiliteDisplay 1\\n    -useDefaultMaterial 0\\n    -bufferMode \\\"double\\\" \\n    -twoSidedLighting 0\\n    -backfaceCulling 0\\n    -xray 0\\n    -jointXray 1\\n    -activeComponentsXray 0\\n    -displayTextures 1\\n    -smoothWireframe 0\\n    -lineWidth 1\\n    -textureAnisotropic 0\\n    -textureHilight 1\\n    -textureSampling 2\\n    -textureDisplay \\\"modulate\\\" \\n    -textureMaxSize 32768\\n    -fogging 0\\n    -fogSource \\\"fragment\\\" \\n    -fogMode \\\"linear\\\" \\n    -fogStart 0\\n    -fogEnd 100\\n    -fogDensity 0.1\\n    -fogColor 0.5 0.5 0.5 1 \\n    -depthOfFieldPreview 0\\n    -maxConstantTransparency 1\\n    -rendererName \\\"vp2Renderer\\\" \\n    -objectFilterShowInHUD 1\\n    -isFiltered 0\\n    -colorResolution 4 4 \\n    -bumpResolution 4 4 \\n    -textureCompression 0\\n    -transparencyAlgorithm \\\"frontAndBackCull\\\" \\n    -transpInShadows 0\\n    -cullingOverride \\\"none\\\" \\n    -lowQualityLighting 0\\n    -maximumNumHardwareLights 1\\n    -occlusionCulling 0\\n    -shadingModel 0\\n    -useBaseRenderer 0\\n    -useReducedRenderer 0\\n    -smallObjectCulling 0\\n    -smallObjectThreshold -1 \\n    -interactiveDisableShadows 0\\n    -interactiveBackFaceCull 0\\n    -sortTransparent 1\\n    -controllers 1\\n    -nurbsCurves 1\\n    -nurbsSurfaces 1\\n    -polymeshes 1\\n    -subdivSurfaces 1\\n    -planes 1\\n    -lights 1\\n    -cameras 1\\n    -controlVertices 1\\n    -hulls 1\\n    -grid 1\\n    -imagePlane 1\\n    -joints 1\\n    -ikHandles 1\\n    -deformers 1\\n    -dynamics 1\\n    -particleInstancers 1\\n    -fluids 1\\n    -hairSystems 1\\n    -follicles 1\\n    -nCloths 1\\n    -nParticles 1\\n    -nRigids 1\\n    -dynamicConstraints 1\\n    -locators 1\\n    -manipulators 1\\n    -pluginShapes 1\\n    -dimensions 1\\n    -handles 1\\n    -pivots 1\\n    -textures 1\\n    -strokes 1\\n    -motionTrails 1\\n    -clipGhosts 1\\n    -greasePencils 1\\n    -shadows 0\\n    -captureSequenceNumber -1\\n    -width 1096\\n    -height 708\\n    -sceneRenderFilter 0\\n    -activeShadingGraph \\\"ballora_animatronic_shadow_rig:rsMaterial1SG,ballora_animatronic_shadow_rig:MAT_ballora,ballora_animatronic_shadow_rig:MAT_ballora\\\" \\n    -activeCustomGeometry \\\"meshShaderball\\\" \\n    -activeCustomLighSet \\\"defaultAreaLightSet\\\" \\n    $editorName;\\nmodelEditor -e -viewSelected 0 $editorName;\\nmodelEditor -e \\n    -pluginObjects \\\"gpuCacheDisplayFilter\\\" 1 \\n    $editorName\"\n"
		+ "\t\t\t\t\t\"modelPanel -edit -l (localizedPanelLabel(\\\"Persp View\\\")) -mbv $menusOkayInPanels  $panelName;\\n$editorName = $panelName;\\nmodelEditor -e \\n    -docTag \\\"RADRENDER\\\" \\n    -cam `findStartUpCamera persp` \\n    -useInteractiveMode 0\\n    -displayLights \\\"default\\\" \\n    -displayAppearance \\\"wireframe\\\" \\n    -activeOnly 0\\n    -ignorePanZoom 0\\n    -wireframeOnShaded 0\\n    -headsUpDisplay 1\\n    -holdOuts 1\\n    -selectionHiliteDisplay 1\\n    -useDefaultMaterial 0\\n    -bufferMode \\\"double\\\" \\n    -twoSidedLighting 0\\n    -backfaceCulling 0\\n    -xray 0\\n    -jointXray 1\\n    -activeComponentsXray 0\\n    -displayTextures 1\\n    -smoothWireframe 0\\n    -lineWidth 1\\n    -textureAnisotropic 0\\n    -textureHilight 1\\n    -textureSampling 2\\n    -textureDisplay \\\"modulate\\\" \\n    -textureMaxSize 32768\\n    -fogging 0\\n    -fogSource \\\"fragment\\\" \\n    -fogMode \\\"linear\\\" \\n    -fogStart 0\\n    -fogEnd 100\\n    -fogDensity 0.1\\n    -fogColor 0.5 0.5 0.5 1 \\n    -depthOfFieldPreview 0\\n    -maxConstantTransparency 1\\n    -rendererName \\\"vp2Renderer\\\" \\n    -objectFilterShowInHUD 1\\n    -isFiltered 0\\n    -colorResolution 4 4 \\n    -bumpResolution 4 4 \\n    -textureCompression 0\\n    -transparencyAlgorithm \\\"frontAndBackCull\\\" \\n    -transpInShadows 0\\n    -cullingOverride \\\"none\\\" \\n    -lowQualityLighting 0\\n    -maximumNumHardwareLights 1\\n    -occlusionCulling 0\\n    -shadingModel 0\\n    -useBaseRenderer 0\\n    -useReducedRenderer 0\\n    -smallObjectCulling 0\\n    -smallObjectThreshold -1 \\n    -interactiveDisableShadows 0\\n    -interactiveBackFaceCull 0\\n    -sortTransparent 1\\n    -controllers 1\\n    -nurbsCurves 1\\n    -nurbsSurfaces 1\\n    -polymeshes 1\\n    -subdivSurfaces 1\\n    -planes 1\\n    -lights 1\\n    -cameras 1\\n    -controlVertices 1\\n    -hulls 1\\n    -grid 1\\n    -imagePlane 1\\n    -joints 1\\n    -ikHandles 1\\n    -deformers 1\\n    -dynamics 1\\n    -particleInstancers 1\\n    -fluids 1\\n    -hairSystems 1\\n    -follicles 1\\n    -nCloths 1\\n    -nParticles 1\\n    -nRigids 1\\n    -dynamicConstraints 1\\n    -locators 1\\n    -manipulators 1\\n    -pluginShapes 1\\n    -dimensions 1\\n    -handles 1\\n    -pivots 1\\n    -textures 1\\n    -strokes 1\\n    -motionTrails 1\\n    -clipGhosts 1\\n    -greasePencils 1\\n    -shadows 0\\n    -captureSequenceNumber -1\\n    -width 1096\\n    -height 708\\n    -sceneRenderFilter 0\\n    -activeShadingGraph \\\"ballora_animatronic_shadow_rig:rsMaterial1SG,ballora_animatronic_shadow_rig:MAT_ballora,ballora_animatronic_shadow_rig:MAT_ballora\\\" \\n    -activeCustomGeometry \\\"meshShaderball\\\" \\n    -activeCustomLighSet \\\"defaultAreaLightSet\\\" \\n    $editorName;\\nmodelEditor -e -viewSelected 0 $editorName;\\nmodelEditor -e \\n    -pluginObjects \\\"gpuCacheDisplayFilter\\\" 1 \\n    $editorName\"\n"
		+ "\t\t\t\t$configName;\n\n            setNamedPanelLayout (localizedPanelLabel(\"Current Layout\"));\n        }\n\n        panelHistory -e -clear mainPanelHistory;\n        sceneUIReplacement -clear;\n\t}\n\n\ngrid -spacing 1 -size 12 -divisions 1 -displayAxes yes -displayGridLines yes -displayDivisionLines yes -displayPerspectiveLabels no -displayOrthographicLabels no -displayAxesBold yes -perspectiveLabelPosition axis -orthographicLabelPosition edge;\nviewManip -drawCompass 0 -compassAngle 0 -frontParameters \"\" -homeParameters \"\" -selectionLockParameters \"\";\n}\n");
	setAttr ".st" 3;
createNode script -n "sceneConfigurationScriptNode";
	rename -uid "45B14D5C-42FF-3868-2D19-A295DD95BCEA";
	setAttr ".b" -type "string" "playbackOptions -min 0 -max 200 -ast 0 -aet 200 ";
	setAttr ".st" 6;
createNode multiplyDivide -n "multiplyDivide1";
	rename -uid "7BC31044-49F5-C949-6C8B-6C948A9E4E39";
createNode decomposeMatrix -n "decomposeMatrix1";
	rename -uid "7DC88F6E-49E9-8561-9D84-3D97C403672C";
createNode blendColors -n "blendColors1";
	rename -uid "6D2A2328-4557-D1BB-4D41-4AAD7F957732";
createNode blendColors -n "blendColors2";
	rename -uid "72EDD2A6-45A8-1930-1D40-C3B050C651C6";
createNode blendColors -n "blendColors3";
	rename -uid "6ECEBBEE-4250-B27D-A1B7-A8828F9C692D";
createNode unitConversion -n "unitConversion2";
	rename -uid "F2768020-43F3-B502-763D-83B3401E23CC";
	setAttr ".cf" 0.017453292519943295;
createNode unitConversion -n "unitConversion3";
	rename -uid "166CA3FA-4100-022E-EEC0-F184E8F4BC7E";
	setAttr ".cf" 57.295779513082323;
createNode decomposeMatrix -n "decomposeMatrix2";
	rename -uid "87C1523A-466F-60F4-3C6B-4982850D759D";
createNode unitConversion -n "unitConversion4";
	rename -uid "FD2B1F6C-4B3A-FFCB-A2BC-1297F2A1A4E0";
	setAttr ".cf" 57.295779513082323;
createNode condition -n "condition2";
	rename -uid "7CE16773-4773-0FAA-097D-079ADE4E9191";
	setAttr ".op" 1;
createNode addDoubleLinear -n "addDoubleLinear2";
	rename -uid "A1FA9920-4219-7FA1-DF51-6E8C6CEFEE48";
	setAttr ".ihi" 2;
	setAttr ".i2" -2;
createNode condition -n "condition3";
	rename -uid "01CFC330-424A-5ECC-8916-6C83DFE78856";
	setAttr ".op" 4;
	setAttr ".cf" -type "float3" 0 0 0 ;
createNode multiplyDivide -n "multiplyDivide2";
	rename -uid "B49DE281-4E32-7133-3DA1-BFBE3A744202";
createNode distanceBetween -n "distanceBetween2";
	rename -uid "5B4D1A93-42D0-82C7-429E-929E4AA91BAD";
createNode remapValue -n "remapValue2";
	rename -uid "19ACE161-402C-2229-09C4-EE8C9639CD04";
	setAttr ".imx" -4;
	setAttr ".omx" 3.5;
	setAttr -s 2 ".vl[0:1]"  0 0 2 1 1 2;
	setAttr -s 2 ".cl";
	setAttr ".cl[0].clp" 0;
	setAttr ".cl[0].clc" -type "float3" 0 0 0 ;
	setAttr ".cl[0].cli" 1;
	setAttr ".cl[1].clp" 1;
	setAttr ".cl[1].clc" -type "float3" 1 1 1 ;
	setAttr ".cl[1].cli" 1;
createNode ikRPsolver -n "ikRPsolver1";
	rename -uid "D5225DB2-4CDB-9E1E-3461-F5905174AD05";
createNode condition -n "condition4";
	rename -uid "FA5BD4BA-420E-9DC5-7C32-BC9300C0D123";
	setAttr ".op" 1;
createNode decomposeMatrix -n "decomposeMatrix3";
	rename -uid "1E3961A9-44D4-B706-0310-219854BA17A5";
createNode blendColors -n "blendColors4";
	rename -uid "42BCA113-49BE-B8FB-4289-61B0E19E29E4";
createNode decomposeMatrix -n "decomposeMatrix4";
	rename -uid "FB8E1E9B-4D9C-86D2-A53D-8AB73B7D9CA7";
createNode unitConversion -n "unitConversion5";
	rename -uid "DAC68075-4F6C-8ED9-74DA-3EA3CFCA449B";
	setAttr ".cf" 0.017453292519943295;
createNode blendColors -n "blendColors5";
	rename -uid "BE4F99DC-4850-DD2A-2E1D-BA827EE8947D";
createNode unitConversion -n "unitConversion6";
	rename -uid "E8F578B4-4EF0-0802-2310-6EB979FB7253";
	setAttr ".cf" 57.295779513082323;
createNode unitConversion -n "unitConversion7";
	rename -uid "C6FFA46D-4FB2-28AB-29CC-2E95093DD257";
	setAttr ".cf" 57.295779513082323;
createNode blendColors -n "blendColors6";
	rename -uid "0989692B-42EF-F1F4-A249-B3803FE94D29";
createNode multiplyDivide -n "multiplyDivide3";
	rename -uid "47D59EB0-4DD7-EC5C-4EAF-AF89BC1EFD2E";
	setAttr ".i1" -type "float3" 0 0 -12 ;
createNode addDoubleLinear -n "addDoubleLinear1";
	rename -uid "9A25ACA9-4F24-7848-1B94-5D9DFB3C456F";
	setAttr ".ihi" 2;
createNode unitConversion -n "unitConversion8";
	rename -uid "10DA8D35-47DB-5C18-5743-5887A40C301F";
	setAttr ".cf" 57.295779513082323;
createNode addDoubleLinear -n "addDoubleLinear4";
	rename -uid "4204D4D1-4616-A034-5359-3EB6463625B9";
	setAttr ".ihi" 2;
createNode unitConversion -n "unitConversion9";
	rename -uid "7A17CAC7-4C32-022F-A0D7-55B11122FBF5";
	setAttr ".cf" 57.295779513082323;
createNode unitConversion -n "unitConversion10";
	rename -uid "05F4B4DD-41EE-5145-E1D7-B8900DBB5DDC";
	setAttr ".cf" 57.295779513082323;
createNode unitConversion -n "unitConversion11";
	rename -uid "DD6E9D0F-4334-392A-C90E-F5ACB7BBBCF7";
	setAttr ".cf" 57.295779513082323;
createNode unitConversion -n "unitConversion12";
	rename -uid "534A5F3C-4C5E-7CC8-D8C3-FFB7B5BB1648";
	setAttr ".cf" 57.295779513082323;
createNode unitConversion -n "unitConversion13";
	rename -uid "F1471E63-4BE4-92D2-B783-06A52DD21855";
	setAttr ".cf" 57.295779513082323;
createNode unitConversion -n "unitConversion14";
	rename -uid "00699B0A-44D9-E0C0-2624-57B7264E0119";
	setAttr ".cf" 0.017453292519943295;
createNode reverse -n "reverse1";
	rename -uid "5F80D782-4DE6-7688-C615-A79A4BC41A1D";
createNode unitConversion -n "unitConversion15";
	rename -uid "901686B5-4E10-146B-FA60-A1ABF1FC525B";
	setAttr ".cf" 57.295779513082323;
createNode transformGeometry -n "transformGeometry1";
	rename -uid "99A0BDC2-4789-0BD5-E219-B1ACE1A50A28";
	setAttr ".txf" -type "matrix" -16 0 -1.9594348786357651e-15 0 0 16 0 0 1.9594348786357651e-15 0 -16 0
		 0 0 0 1;
createNode skinCluster -n "skinCluster3";
	rename -uid "E1E42BA0-4E2C-5C85-052C-BCA1D879A9E3";
	setAttr ".ip[0].gtg" -type "string" "";
	setAttr ".skm" 1;
	setAttr -s 8 ".wl";
	setAttr ".wl[0:7].w"
		1 0 1
		1 0 1
		1 0 1
		1 0 1
		1 0 1
		1 0 1
		1 0 1
		1 0 1;
	setAttr ".pm[0]" -type "matrix" 1 0 0 0 0 1 0 0 0 0 1 0 0 -14 0 1;
	setAttr ".gm" -type "matrix" 1 0 0 0 0 1 0 0 0 0 1 0 0 0 0 1;
	setAttr ".dpf[0]"  10;
	setAttr ".mmi" yes;
	setAttr ".ucm" yes;
createNode tweak -n "tweak3";
	rename -uid "16D35715-470D-A83D-65D5-6288B5D2FA42";
createNode objectSet -n "skinCluster3Set";
	rename -uid "C4529708-4672-824E-2707-CF8CE5DA6FE3";
	setAttr ".ihi" 0;
	setAttr ".vo" yes;
createNode groupId -n "skinCluster3GroupId";
	rename -uid "2AEA797A-40BF-7E84-02F2-A68126C65EDB";
	setAttr ".ihi" 0;
createNode groupParts -n "skinCluster3GroupParts";
	rename -uid "822B49CD-4B4B-CD24-20B2-51AD1EBDBB5B";
	setAttr ".ihi" 0;
	setAttr ".ic" -type "componentList" 1 "vtx[*]";
createNode objectSet -n "tweakSet3";
	rename -uid "3A040A1E-4DEA-9F02-9873-B0A7F839CF29";
	setAttr ".ihi" 0;
	setAttr ".vo" yes;
createNode groupId -n "groupId16";
	rename -uid "09850C1A-4008-4809-614D-E8A1FCFBF2E7";
	setAttr ".ihi" 0;
createNode groupParts -n "groupParts6";
	rename -uid "E13318CE-4388-F4C9-9CCA-1FAA147C718B";
	setAttr ".ihi" 0;
	setAttr ".ic" -type "componentList" 1 "vtx[*]";
createNode dagPose -n "bindPose3";
	rename -uid "8812F96D-4125-2711-7EC0-6DA1DD02CC17";
	setAttr -s 4 ".wm";
	setAttr ".wm[0]" -type "matrix" 1 0 0 0 0 1 0 0 0 0 1 0 0 0 0 1;
	setAttr ".wm[1]" -type "matrix" 1 0 0 0 0 1 0 0 0 0 1 0 0 0 0 1;
	setAttr ".wm[2]" -type "matrix" 1 0 0 0 0 1 0 0 0 0 1 0 0 0 0 1;
	setAttr ".wm[3]" -type "matrix" 1 0 0 0 0 1 0 0 0 0 1 0 0 14 0 1;
	setAttr -s 4 ".xm";
	setAttr ".xm[0]" -type "matrix" "xform" 1 1 1 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0
		 0 0 0 0 0 0 0 0 0 0 1 0 0 0 1 1 1 1 yes;
	setAttr ".xm[1]" -type "matrix" "xform" 1 1 1 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0
		 0 0 0 0 0 0 0 0 0 0 1 0 0 0 1 1 1 1 yes;
	setAttr ".xm[2]" -type "matrix" "xform" 1 1 1 0 0 0 0 0 0 0 0 0 0 0 14 0 0 0
		 0 0 14 0 0 0 0 0 0 0 1 0 0 0 1 1 1 1 yes;
	setAttr ".xm[3]" -type "matrix" "xform" 1 1 1 0 0 0 0 0 14 0 0 0 0 0 0 0 0 0
		 0 0 0 0 0 0 0 0 0 0 1 0 0 0 1 1 1 1 yes;
	setAttr -s 4 ".m";
	setAttr -s 4 ".p";
	setAttr -s 4 ".g[0:3]" yes yes yes no;
	setAttr ".bp" yes;
createNode reverse -n "reverse2";
	rename -uid "99606C4D-4FF8-6088-B9D2-23844F04D17B";
createNode unitConversion -n "unitConversion16";
	rename -uid "A945CDB2-468A-E14A-2701-0F9707DF0D77";
	setAttr ".cf" 57.295779513082323;
createNode unitConversion -n "unitConversion17";
	rename -uid "9A8907D1-4E65-DC14-3A56-689E97D98735";
	setAttr ".cf" 0.017453292519943295;
createNode unitConversion -n "unitConversion18";
	rename -uid "11C2510B-42AD-238B-558F-5FB7B82DA619";
	setAttr ".cf" 0.017453292519943295;
createNode unitConversion -n "unitConversion19";
	rename -uid "18CF2C92-4CF1-D0E0-D614-EDA52387AC3F";
	setAttr ".cf" 57.295779513082323;
createNode addDoubleLinear -n "addDoubleLinear5";
	rename -uid "3081B2B7-4E51-9660-3227-1E9EBB864E8A";
	setAttr ".ihi" 2;
createNode unitConversion -n "unitConversion20";
	rename -uid "948DEA4B-41D1-E863-F01F-6BB2F27204A5";
	setAttr ".cf" 0.017453292519943295;
createNode unitConversion -n "unitConversion21";
	rename -uid "0C92B569-45B0-9BA9-E814-6B8197B97356";
	setAttr ".cf" 57.295779513082323;
createNode polyTweakUV -n "polyTweakUV2";
	rename -uid "CA85D506-4888-ADD1-EF68-75978C914A92";
	setAttr ".uopa" yes;
	setAttr -s 24 ".uvtk[0:23]" -type "float2" 0.00024414063 0.00024414063
		 -0.00024414063 0.00024414063 -0.00024414063 -0.00024414063 0.00024414063 -0.00024414063
		 0.00024414063 0.00024414063 -0.00024414063 0.00024414063 -0.00024414063 -0.00024414063
		 0.00024414063 -0.00024414063 -0.00024414063 -0.00024414063 0.00024414063 -0.00024414063
		 0.00024414063 0.00024414063 -0.00024414063 0.00024414063 -0.00024414063 0.00024414063
		 0.00024414063 0.00024414063 0.00024414063 -0.00024414063 -0.00024414063 -0.00024414063
		 0.00024414063 0.00024414063 -0.00024414063 0.00024414063 -0.00024414063 -0.00024414063
		 0.00024414063 -0.00024414063 0.00024414063 0.00024414063 -0.00024414063 0.00024414063
		 -0.00024414063 -0.00024414063 0.00024414063 -0.00024414063;
createNode decomposeMatrix -n "decomposeMatrix5";
	rename -uid "2401B9DC-4DC1-0431-3A62-68B03735496F";
	setAttr ".imat" -type "matrix" 1 0 0 0 0 1 0 0 0 0 1 0 0 24 0 1;
createNode shadingEngine -n "rsMaterial1SG";
	rename -uid "6982187F-4677-A4F4-BB51-56890538C8D4";
	setAttr ".ihi" 0;
	setAttr ".ro" yes;
createNode materialInfo -n "materialInfo2";
	rename -uid "43B7A8D4-4C5B-D0DF-DF66-588F88EC2783";
createNode blinn -n "typeBlinn";
	rename -uid "A892C62C-4C41-7A2C-5114-45AA3EC5B1A3";
	setAttr ".c" -type "float3" 1 1 1 ;
createNode shadingEngine -n "typeBlinnSG";
	rename -uid "65D4B240-4E41-D2D7-C181-988B17774E69";
	setAttr ".ihi" 0;
	setAttr ".ro" yes;
createNode materialInfo -n "materialInfo3";
	rename -uid "49DF6833-4C99-2707-5BC6-638831400677";
createNode remapValue -n "remapValue3";
	rename -uid "8382907A-4DE0-DBCE-8772-D896FFF709C2";
	setAttr ".imn" -3;
	setAttr ".imx" 0;
	setAttr ".omn" -4;
	setAttr ".omx" 0;
	setAttr -s 5 ".vl[1:5]"  1 1 1 0.25 0.25 0 0.75 0.75 0 0.5
		 0.5 0 0 0 0;
	setAttr -s 2 ".cl";
	setAttr ".cl[0].clp" 0;
	setAttr ".cl[0].clc" -type "float3" 0 0 0 ;
	setAttr ".cl[0].cli" 1;
	setAttr ".cl[1].clp" 1;
	setAttr ".cl[1].clc" -type "float3" 1 1 1 ;
	setAttr ".cl[1].cli" 1;
createNode condition -n "condition5";
	rename -uid "886FB682-46F2-3D87-86B1-3D9B55DABE47";
	setAttr ".op" 3;
	setAttr ".st" -1;
	setAttr ".ct" -type "float3" 1 1 1 ;
	setAttr ".cf" -type "float3" 0 0 0 ;
createNode condition -n "condition6";
	rename -uid "943EC441-4F2D-A771-D7FD-37B908C1D820";
	setAttr ".st" -2;
	setAttr ".ct" -type "float3" 1 1 1 ;
	setAttr ".cf" -type "float3" 0 0 0 ;
createNode condition -n "condition7";
	rename -uid "06F0580A-4EC2-F855-3F10-31B2A687B6EE";
	setAttr ".st" -3;
	setAttr ".ct" -type "float3" 1 1 1 ;
	setAttr ".cf" -type "float3" 0 0 0 ;
createNode condition -n "condition8";
	rename -uid "10F6AB56-4C3F-D4B0-6FDA-FEA75C215D2D";
	setAttr ".st" -4;
	setAttr ".ct" -type "float3" 1 1 1 ;
	setAttr ".cf" -type "float3" 0 0 0 ;
createNode condition -n "condition9";
	rename -uid "4A2906E1-4B5E-C64E-E36C-10BC5CC4ACD2";
	setAttr ".ct" -type "float3" 1 1 1 ;
	setAttr ".cf" -type "float3" 0 0 0 ;
createNode addDoubleLinear -n "addDoubleLinear10";
	rename -uid "963FA776-4A5B-E7E1-6438-A297FA079999";
	setAttr ".ihi" 2;
createNode remapValue -n "remapValue4";
	rename -uid "6B2B06C9-48E1-A34B-827C-D7A330C9EDBF";
	setAttr ".imn" -2;
	setAttr ".imx" 2;
	setAttr ".omn" -0.5;
	setAttr ".omx" 0.5;
	setAttr -s 3 ".vl[3:5]"  0.66666001 1 0 0.33333299 0.5 0 0 0 
		0;
	setAttr -s 2 ".cl";
	setAttr ".cl[0].clp" 0;
	setAttr ".cl[0].clc" -type "float3" 0 0 0 ;
	setAttr ".cl[0].cli" 1;
	setAttr ".cl[1].clp" 1;
	setAttr ".cl[1].clc" -type "float3" 1 1 1 ;
	setAttr ".cl[1].cli" 1;
createNode multiplyDivide -n "multiplyDivide5";
	rename -uid "843F1C30-4EDA-E705-240B-559055FEAD85";
	setAttr ".i2" -type "float3" -1 1 1 ;
createNode condition -n "condition14";
	rename -uid "76E978CD-470E-702C-B432-099BD32F5D56";
	setAttr ".st" 1;
	setAttr ".ct" -type "float3" 1 1 1 ;
	setAttr ".cf" -type "float3" 0 0 0 ;
createNode condition -n "condition15";
	rename -uid "3AB59D78-4C4A-852F-9ED2-7D85DD25F5F5";
	setAttr ".st" 2;
	setAttr ".ct" -type "float3" 1 1 1 ;
	setAttr ".cf" -type "float3" 0 0 0 ;
createNode file -n "tex_promo_art_face";
	rename -uid "1E67891A-4887-E78B-DD0A-4FAE4DD5F33E";
	setAttr ".ftn" -type "string" "D:/Dropbox/Misc Projects/Minecraft/skins/skin_mouth_normal.png";
	setAttr ".ft" 0;
	setAttr ".cs" -type "string" "sRGB";
	setAttr ".rsFilterEnable" 0;
	setAttr ".ai_filter" 0;
createNode place2dTexture -n "place2dTexture_texPromoArtFace";
	rename -uid "FF3E5E53-4EAE-5109-3B75-B19B099C4B85";
createNode remapValue -n "remapValue5";
	rename -uid "87E4F7C1-4105-43E4-3716-4D9B0B28F951";
	setAttr ".imn" -2;
	setAttr ".imx" 2;
	setAttr ".omx" -16;
	setAttr -s 3 ".vl[2:4]"  0.30000001 0.5 0 0.69999999 1 1 0 0 
		0;
	setAttr -s 2 ".cl";
	setAttr ".cl[0].clp" 0;
	setAttr ".cl[0].clc" -type "float3" 0 0 0 ;
	setAttr ".cl[0].cli" 1;
	setAttr ".cl[1].clp" 1;
	setAttr ".cl[1].clc" -type "float3" 1 1 1 ;
	setAttr ".cl[1].cli" 1;
createNode remapValue -n "remapValue6";
	rename -uid "4FC90AA9-4103-EDC3-0F6F-E2B80621688A";
	setAttr ".imx" -3;
	setAttr -s 4 ".vl[1:4]"  0.5 0.69999999 0 0.25 0.30000001 0
		 0.75 1 1 0 0 0;
	setAttr -s 2 ".cl";
	setAttr ".cl[0].clp" 0;
	setAttr ".cl[0].clc" -type "float3" 0 0 0 ;
	setAttr ".cl[0].cli" 1;
	setAttr ".cl[1].clp" 1;
	setAttr ".cl[1].clc" -type "float3" 1 1 1 ;
	setAttr ".cl[1].cli" 1;
createNode condition -n "condition16";
	rename -uid "9F0A8627-4B77-E975-4713-EB80F56D7A2E";
	setAttr ".cf" -type "float3" 0 0 0 ;
createNode condition -n "condition17";
	rename -uid "3574EFB1-453B-F5B0-F0E8-DD8ED209A6E0";
	setAttr ".st" 0.30000001192092896;
	setAttr ".ct" -type "float3" 0 32 0 ;
	setAttr ".cf" -type "float3" 0 0 1 ;
createNode condition -n "condition18";
	rename -uid "9E9F2397-482A-1219-968C-96823A4EF0B5";
	setAttr ".st" 0.69999998807907104;
	setAttr ".ct" -type "float3" -32 0 0 ;
	setAttr ".cf" -type "float3" 0 0 1 ;
createNode condition -n "condition19";
	rename -uid "C8474AFB-4531-B4DC-A7AC-C0BED225789B";
	setAttr ".st" 1;
	setAttr ".ct" -type "float3" -32 32 0 ;
	setAttr ".cf" -type "float3" 0 0 1 ;
createNode skinCluster -n "skinCluster9";
	rename -uid "6E39DEEF-4695-7DDB-80E6-50B7ED86AE5B";
	setAttr ".ip[0].gtg" -type "string" "";
	setAttr ".skm" 1;
	setAttr -s 8 ".wl";
	setAttr ".wl[0:7].w"
		1 0 1
		1 0 1
		1 0 1
		1 0 1
		1 0 1
		1 0 1
		1 0 1
		1 0 1;
	setAttr ".pm[0]" -type "matrix" 0.99999999922813898 -2.4321874407121769e-05 3.0857228098450656e-05 0
		 2.4278695477406022e-05 0.99999902174375588 0.0013985428403461217 0 -3.0891213095490465e-05 -0.0013985420900933965 0.99999902156239906 0
		 2.0000000003627143 -4.8557438450713679e-05 -1.0383636509761658e-11 1;
	setAttr ".gm" -type "matrix" 1 0 0 0 0 1 0 0 0 0 1 0 0 0 0 1;
	setAttr ".dpf[0]"  10;
	setAttr ".mmi" yes;
	setAttr ".mi" 1;
	setAttr ".ucm" yes;
createNode tweak -n "tweak9";
	rename -uid "255BD650-4BDC-52AE-FC85-E780BCE334CD";
createNode objectSet -n "skinCluster9Set";
	rename -uid "6AE62C46-4FDE-0170-28C0-4B965FFA0E62";
	setAttr ".ihi" 0;
	setAttr ".vo" yes;
createNode groupId -n "skinCluster9GroupId";
	rename -uid "95315EF2-4468-B6E5-A07C-6B9E1EA4B0F8";
	setAttr ".ihi" 0;
createNode groupParts -n "skinCluster9GroupParts";
	rename -uid "6B4E92C9-4F13-E6E0-7B56-5A8682A11EA3";
	setAttr ".ihi" 0;
	setAttr ".ic" -type "componentList" 1 "vtx[*]";
createNode objectSet -n "tweakSet9";
	rename -uid "6A4758CB-4DF4-A9D3-7441-7B8BC87901F2";
	setAttr ".ihi" 0;
	setAttr ".vo" yes;
createNode groupId -n "groupId31";
	rename -uid "58D513DD-4516-C8BE-F6A7-849858D2C307";
	setAttr ".ihi" 0;
createNode groupParts -n "groupParts20";
	rename -uid "1AE0AA99-4BC0-5985-150F-508D874F604B";
	setAttr ".ihi" 0;
	setAttr ".ic" -type "componentList" 1 "vtx[*]";
createNode dagPose -n "bindPose8";
	rename -uid "77571806-4F0C-1C7D-2912-AC8F31429E65";
	setAttr -s 3 ".wm";
	setAttr ".wm[0]" -type "matrix" 1 0 0 0 0 1 0 0 0 0 1 0 0 0 0 1;
	setAttr ".wm[1]" -type "matrix" 1 0 0 0 0 1 0 0 0 0 1 0 0 0 0 1;
	setAttr -s 3 ".xm";
	setAttr ".xm[0]" -type "matrix" "xform" 1 1 1 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0
		 0 0 0 0 0 0 0 0 0 0 1 0 0 0 1 1 1 1 yes;
	setAttr ".xm[1]" -type "matrix" "xform" 1 1 1 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0
		 0 0 0 0 0 0 0 0 0 0 1 0 0 0 1 1 1 1 yes;
	setAttr ".xm[2]" -type "matrix" "xform" 1 1 1 -0.0013985425466671586 3.0891213100403547e-05
		 2.4278695491375402e-05 0 -2 0 6.1714526964351535e-05 0 0 0 0 0 0 0 0 0 0 0 0 0
		 0 0 0 0 0 1 0 0 0 1 1 1 1 yes;
	setAttr -s 3 ".m";
	setAttr -s 3 ".p";
	setAttr -s 3 ".g[0:2]" yes yes no;
	setAttr ".bp" yes;
createNode dagPose -n "bindPose9";
	rename -uid "A8BF51B6-47E4-7189-5F63-688F99C12AA3";
	setAttr -s 3 ".wm";
	setAttr ".wm[0]" -type "matrix" 1 0 0 0 0 1 0 0 0 0 1 0 0 0 0 1;
	setAttr ".wm[1]" -type "matrix" 1 0 0 0 0 1 0 0 0 0 1 0 0 0 0 1;
	setAttr -s 3 ".xm";
	setAttr ".xm[0]" -type "matrix" "xform" 1 1 1 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0
		 0 0 0 0 0 0 0 0 0 0 1 0 0 0 1 1 1 1 yes;
	setAttr ".xm[1]" -type "matrix" "xform" 1 1 1 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0
		 0 0 0 0 0 0 0 0 0 0 1 0 0 0 1 1 1 1 yes;
	setAttr ".xm[2]" -type "matrix" "xform" 1 1 1 -0.0013985425466671586 3.0891213100403547e-05
		 2.4278695491375402e-05 0 2 0 6.1714526964351535e-05 0 0 0 0 0 0 0 0 0 0 0 0 0
		 0 0 0 0 0 1 0 0 0 1 1 1 1 yes;
	setAttr -s 3 ".m";
	setAttr -s 3 ".p";
	setAttr -s 3 ".g[0:2]" yes yes no;
	setAttr ".bp" yes;
createNode multiplyDivide -n "multiplyDivide6";
	rename -uid "550B1EFA-4671-8759-81FC-81B568690C62";
	setAttr ".i2" -type "float3" 1 -1 1 ;
createNode decomposeMatrix -n "decomposeMatrix6";
	rename -uid "321043FC-433D-A7AF-84EC-8891B134D10B";
	setAttr ".imat" -type "matrix" 1 0 0 0 0 1 0 0 0 0 1 0 0 24 0 1;
createNode multiplyDivide -n "multiplyDivide7";
	rename -uid "92A433BB-4D7C-CB52-1C81-14A3755F24C6";
	setAttr ".i2" -type "float3" -1 1 1 ;
createNode floatMath -n "floatMath1";
	rename -uid "A1FFC621-415C-2561-2E26-F59A371494F1";
createNode floatMath -n "floatMath2";
	rename -uid "BBCC2B62-43B0-A6EF-CC55-4891545419A9";
	setAttr "._fa" 2;
createNode floatMath -n "floatMath3";
	rename -uid "D5216717-4414-808B-A855-AD8FE77E8DA0";
	setAttr "._cnd" 1;
createNode floatMath -n "floatMath4";
	rename -uid "A9BA9ABA-4CD5-8929-B683-A4AEADD63032";
	setAttr "._cnd" 1;
createNode floatMath -n "floatMath5";
	rename -uid "809371BB-4218-79AA-79B8-FAB8B9ED6B87";
	setAttr "._fb" -1;
createNode floatMath -n "floatMath6";
	rename -uid "A8E4E181-4DEA-80F4-C255-9B971E5B5010";
	setAttr "._fa" 0;
createNode floatMath -n "floatMath7";
	rename -uid "EC275500-4662-C3B1-D1D7-27BB84C4FD7C";
	setAttr "._fa" 0;
createNode floatMath -n "floatMath8";
	rename -uid "109F94C5-4A12-4592-3EE3-D495A4471E6B";
	setAttr "._fa" 24;
	setAttr "._fb" -24;
createNode distanceBetween -n "distanceBetween3";
	rename -uid "42CBD8E6-45EE-3CDF-ADC0-05ABCE2B20B2";
createNode skinCluster -n "skinCluster15";
	rename -uid "04558DC4-4BDF-8ADF-B523-A9A7110BFDC8";
	setAttr ".ip[0].gtg" -type "string" "";
	setAttr ".skm" 1;
	setAttr -s 8 ".wl";
	setAttr ".wl[0:7].w"
		1 0 1
		1 0 1
		1 0 1
		1 0 1
		1 0 1
		1 0 1
		1 0 1
		1 0 1;
	setAttr ".pm[0]" -type "matrix" 1 0 0 0 0 1 0 0 0 0 1 0 -6 -22 0 1;
	setAttr ".gm" -type "matrix" 1 0 0 0 0 1 0 0 0 0 1 0 0 0 0 1;
	setAttr ".dpf[0]"  10;
	setAttr ".mmi" yes;
	setAttr ".mi" 1;
	setAttr ".ucm" yes;
createNode tweak -n "tweak15";
	rename -uid "66F94C1B-4163-8833-2555-8FBE554454C1";
createNode objectSet -n "skinCluster15Set";
	rename -uid "8809414A-4213-EC46-F801-4AA5B1C90AF1";
	setAttr ".ihi" 0;
	setAttr ".vo" yes;
createNode groupId -n "skinCluster15GroupId";
	rename -uid "D2BE0C61-474A-7805-2F67-66BD20463673";
	setAttr ".ihi" 0;
createNode groupParts -n "skinCluster15GroupParts";
	rename -uid "2DAE6E9C-49AC-8B9A-BE4E-B78A2982240B";
	setAttr ".ihi" 0;
	setAttr ".ic" -type "componentList" 1 "vtx[*]";
createNode objectSet -n "tweakSet15";
	rename -uid "1A787BA1-4C72-B293-7445-D1B97B0E7CD6";
	setAttr ".ihi" 0;
	setAttr ".vo" yes;
createNode groupId -n "groupId43";
	rename -uid "AD16AE94-4DF6-419A-2F64-FCB9E2E975B1";
	setAttr ".ihi" 0;
createNode groupParts -n "groupParts32";
	rename -uid "FC7D2540-42CB-36F6-1315-4D8FFA6D477B";
	setAttr ".ihi" 0;
	setAttr ".ic" -type "componentList" 1 "vtx[*]";
createNode dagPose -n "bindPose14";
	rename -uid "664850A1-4025-10D0-9900-02968574CF04";
	setAttr -s 6 ".wm";
	setAttr ".wm[0]" -type "matrix" 1 0 0 0 0 1 0 0 0 0 1 0 0 0 0 1;
	setAttr ".wm[2]" -type "matrix" 1 0 0 0 0 1 0 0 0 0 1 0 0 0 0 1;
	setAttr ".wm[3]" -type "matrix" 1 0 0 0 0 1 0 0 0 0 1 0 0 0 0 1;
	setAttr ".wm[4]" -type "matrix" 1 0 0 0 0 1 0 0 0 0 1 0 0 0 0 1;
	setAttr ".wm[5]" -type "matrix" 1 0 0 0 0 1 0 0 0 0 1 0 0 0 0 1;
	setAttr -s 6 ".xm";
	setAttr ".xm[0]" -type "matrix" "xform" 1 1 1 0 0 0 0 0 0 0 0 0 0 6 22 0 0 0
		 0 6 22 0 0 0 0 0 0 0 1 0 0 0 1 1 1 1 yes;
	setAttr ".xm[1]" -type "matrix" "xform" 1 1 1 0 0 0 0 6 22 0 0 0 0 0 0 0 0 0
		 0 0 0 0 0 0 0 0 0 0 1 0 0 0 1 1 1 1 yes;
	setAttr ".xm[2]" -type "matrix" "xform" 1 1 1 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0
		 0 0 0 0 0 0 0 0 0 0 1 0 0 0 1 1 1 1 yes;
	setAttr ".xm[3]" -type "matrix" "xform" 1 1 1 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0
		 0 0 0 0 0 0 0 0 0 0 1 0 0 0 1 1 1 1 yes;
	setAttr ".xm[4]" -type "matrix" "xform" 1 1 1 0 0 0 0 0 0 0 0 0 0 0 1.0078125
		 0 0 0 0 0 1.0078125 0 0 0 0 0 0 0 1 0 0 0 1 1 1 1 yes;
	setAttr ".xm[5]" -type "matrix" "xform" 1 1 1 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0
		 0 0 0 0 0 0 0 0 0 0 1 0 0 0 1 1 1 1 yes;
	setAttr -s 6 ".m";
	setAttr -s 6 ".p";
	setAttr -s 6 ".g[0:5]" yes no yes yes yes yes;
	setAttr ".bp" yes;
createNode makeNurbCircle -n "makeNurbCircle1";
	rename -uid "E8C89BB0-460F-0A19-4976-C6923E1BD0E4";
	setAttr ".nr" -type "double3" 0 1 0 ;
	setAttr ".d" 1;
	setAttr ".s" 10;
createNode deleteComponent -n "deleteComponent1";
	rename -uid "CCAEF80C-4BF9-8408-85DA-71B43CA551AA";
	setAttr ".dc" -type "componentList" 1 "cv[0:10]";
createNode transformGeometry -n "transformGeometry2";
	rename -uid "BB8A3C8D-42EF-E076-8D06-A3BCD1FFF219";
	setAttr ".txf" -type "matrix" 1 0 0 0 0 1 0 0 0 0 1 0 6 26 0 1;
createNode floatMath -n "floatMath10";
	rename -uid "94D961F7-4907-ED03-2800-6A84FA77F9E0";
createNode floatMath -n "floatMath12";
	rename -uid "B62E23A8-4B0D-3659-3A76-BC8B659F66AD";
	setAttr "._fb" 2;
	setAttr "._cnd" 6;
createNode floatMath -n "floatMath13";
	rename -uid "D8DA97EC-4AF2-DA8A-2B0B-2E820070BFA2";
	setAttr "._fb" 0.5;
	setAttr "._cnd" 6;
createNode unitConversion -n "unitConversion22";
	rename -uid "920A813E-4D53-E6D1-4DF1-1084DF96309C";
	setAttr ".cf" 57.295779513082323;
createNode floatMath -n "floatMath14";
	rename -uid "02ED54D1-4864-C0BD-30D6-CBBAEB8620BE";
	setAttr "._fa" 0;
	setAttr "._fb" 2;
	setAttr "._cnd" 6;
createNode floatMath -n "floatMath15";
	rename -uid "4E89593F-4931-9131-8EA8-628CBA1028BE";
	setAttr "._fb" 0.5;
	setAttr "._cnd" 6;
createNode floatMath -n "floatMath16";
	rename -uid "6C759E6A-4F08-9933-93C3-75BF6F2396CD";
	setAttr "._fb" 30;
	setAttr "._cnd" 3;
createNode floatMath -n "floatMath17";
	rename -uid "755DB610-4ABC-6630-6C57-EEA68C3AFBE8";
	setAttr "._cnd" 2;
createNode multiplyDivide -n "multiplyDivide8";
	rename -uid "C9E8B3F3-4E59-C733-B210-20A08B6A65CC";
	setAttr ".op" 2;
createNode addDoubleLinear -n "addDoubleLinear3";
	rename -uid "317CD9C7-48AA-00BF-8CCF-93A43BF4259B";
	setAttr ".ihi" 2;
createNode multiplyDivide -n "multiplyDivide9";
	rename -uid "14587837-4230-6CFC-3042-CA98F43817B7";
	setAttr ".i1" -type "float3" 0 0 -12 ;
createNode multiplyDivide -n "multiplyDivide10";
	rename -uid "F1401EA6-4A0D-E3B4-010A-2AA518A0D897";
	setAttr ".op" 2;
createNode floatMath -n "floatMath22";
	rename -uid "F7F5AD4F-4443-74CB-1C07-E88A5D3F6EAF";
	setAttr "._fb" 0.5;
	setAttr "._cnd" 6;
createNode floatMath -n "floatMath25";
	rename -uid "0B5087E7-4032-D6C1-E5A2-5B9EB986E7CD";
	setAttr "._fb" 30;
	setAttr "._cnd" 3;
createNode floatMath -n "floatMath26";
	rename -uid "024EBE3A-40DF-7416-4EF5-B6975DC98566";
	setAttr "._cnd" 2;
createNode skinCluster -n "skinCluster10";
	rename -uid "325EED92-4C62-712C-B4EF-068D7A32FE7F";
	setAttr ".ip[0].gtg" -type "string" "";
	setAttr ".skm" 1;
	setAttr -s 8 ".wl";
	setAttr ".wl[0:7].w"
		1 0 1
		1 0 1
		1 0 1
		1 0 1
		1 0 1
		1 0 1
		1 0 1
		1 0 1;
	setAttr ".pm[0]" -type "matrix" 0.99999999922813898 -2.4321874407121769e-05 3.0857228098450656e-05 0
		 2.4278695477406022e-05 0.99999902174375588 0.0013985428403461217 0 -3.0891213095490465e-05 -0.0013985420900933965 0.99999902156239906 0
		 -1.9999999965498416 4.8730059177773389e-05 -0.00012342892277743912 1;
	setAttr ".gm" -type "matrix" 1 0 0 0 0 1 0 0 0 0 1 0 0 0 0 1;
	setAttr ".dpf[0]"  10;
	setAttr ".mmi" yes;
	setAttr ".mi" 1;
	setAttr ".ucm" yes;
createNode objectSet -n "skinCluster10Set";
	rename -uid "6C0C130A-4F98-9DFF-DC05-478C27C43FB0";
	setAttr ".ihi" 0;
	setAttr ".vo" yes;
createNode groupParts -n "groupParts22";
	rename -uid "286BB815-4E64-B019-C2DF-CCA030A052BC";
	setAttr ".ihi" 0;
	setAttr ".ic" -type "componentList" 1 "vtx[*]";
createNode groupId -n "groupId33";
	rename -uid "348F8D9F-4FD1-D16C-400B-B08790A88B81";
	setAttr ".ihi" 0;
createNode tweak -n "tweak10";
	rename -uid "3D7BE5C4-4C1F-4D68-5ADE-2DAB26027398";
createNode objectSet -n "tweakSet10";
	rename -uid "21F447F0-4F42-EF3F-A6EC-71A9B2CDC043";
	setAttr ".ihi" 0;
	setAttr ".vo" yes;
createNode groupParts -n "skinCluster10GroupParts";
	rename -uid "28EA8112-4647-AF80-6D00-66ADE7D4CE4D";
	setAttr ".ihi" 0;
	setAttr ".ic" -type "componentList" 1 "vtx[*]";
createNode groupId -n "skinCluster10GroupId";
	rename -uid "EAF5B1A1-4AA1-C7F9-00CF-10BA6DC4425D";
	setAttr ".ihi" 0;
createNode RedshiftMaterial -n "armorPromoArt_rsMat";
	rename -uid "5312A3B3-4BD7-F725-44BC-9B89AFD932C3";
	setAttr ".v" 1;
	setAttr ".refl_weight" 0;
	setAttr ".refl_roughness" 0.28888890147209167;
	setAttr ".refl_fresnel_mode" 2;
	setAttr ".ms_mode" 1;
createNode shadingEngine -n "rsMat_armorPromoArtSG";
	rename -uid "963E8621-4D1F-516C-AA22-7FB95ED5A8E8";
	setAttr ".ihi" 0;
	setAttr -s 9 ".dsm";
	setAttr ".ro" yes;
createNode materialInfo -n "materialInfo4";
	rename -uid "DAE297F6-4076-1280-F536-27B7B2CF8B93";
createNode file -n "armorPromoArt_tex";
	rename -uid "BB7D584A-4863-4576-8EB3-C9AEC2E89ADA";
	setAttr ".ftn" -type "string" "D:/Dropbox/Misc Projects/Minecraft/mobs/tex_armor.png";
	setAttr ".ft" 0;
	setAttr ".cs" -type "string" "sRGB";
	setAttr ".rsFilterEnable" 0;
	setAttr ".ai_filter" 0;
createNode place2dTexture -n "place2dTexture_armorPromoArt";
	rename -uid "205D592E-487C-AD44-73B3-9B99983E3D47";
createNode shadingEngine -n "aiStandardSurface1SG";
	rename -uid "6FADCA92-452A-91FE-258B-F0A04B326141";
	setAttr ".ihi" 0;
	setAttr ".ro" yes;
createNode materialInfo -n "materialInfo5";
	rename -uid "CFB01094-41E6-9440-4F12-5B99B28105A1";
createNode displayLayer -n "layer_meshExtra";
	rename -uid "8DE6A6DC-42ED-EB80-DCEC-FCB4B32B78E7";
	setAttr ".do" 1;
createNode floatMath -n "floatMath20";
	rename -uid "1B90C578-4EA0-E7B1-C537-6D8EDD50BB38";
	setAttr "._fb" 2;
	setAttr "._cnd" 6;
createNode floatMath -n "floatMath23";
	rename -uid "1F073124-49CB-C464-FB76-4087B19FA74F";
	setAttr "._fb" 0.5;
	setAttr "._cnd" 6;
createNode unitConversion -n "unitConversion26";
	rename -uid "E706A66A-416C-7340-8786-AD8BE87F821A";
	setAttr ".cf" 57.295779513082323;
createNode floatMath -n "floatMath29";
	rename -uid "4CE6F45D-4626-FFF9-A6BB-A4AF2D90A2E4";
createNode floatMath -n "floatMath21";
	rename -uid "9315D4D1-46B0-D0AB-DE5F-548DD52C5CFE";
	setAttr "._fb" 2;
	setAttr "._cnd" 6;
createNode unitConversion -n "unitConversion25";
	rename -uid "4D7CF8BC-4FBF-1342-D860-369881961192";
	setAttr ".cf" 57.295779513082323;
createNode floatMath -n "floatMath30";
	rename -uid "93F19217-407A-90D7-E1C6-96AFF800EEBE";
	setAttr "._fb" 2;
	setAttr "._cnd" 6;
createNode floatMath -n "floatMath31";
	rename -uid "B46D138E-4058-976F-E9BB-FE9BFE8DDD89";
	setAttr "._fb" 0.5;
	setAttr "._cnd" 6;
createNode unitConversion -n "unitConversion27";
	rename -uid "E7AEC4B2-42C1-46DA-4B93-979BB00CA5CE";
	setAttr ".cf" 57.295779513082323;
createNode blendColors -n "blendColors7";
	rename -uid "E367D83F-47F0-1366-3FEC-C7AEB9740C95";
createNode blendColors -n "blendColors8";
	rename -uid "4D05D39E-407F-73B3-F83A-5CB9C0CE354B";
createNode unitConversion -n "unitConversion28";
	rename -uid "15725648-44B3-ED39-D686-BCB2597D0E7F";
	setAttr ".cf" 0.017453292519943295;
createNode unitConversion -n "unitConversion29";
	rename -uid "FE2C3AE8-469F-9D18-F44A-4599B1350573";
	setAttr ".cf" 0.017453292519943295;
createNode unitConversion -n "unitConversion30";
	rename -uid "10608B41-4269-D2ED-69FB-2098BFE5DB2B";
	setAttr ".cf" 0.017453292519943295;
createNode unitConversion -n "unitConversion32";
	rename -uid "B8E50E85-45BE-C87A-89E9-E0B02877CAEF";
	setAttr ".cf" 57.295779513082323;
createNode condition -n "condition23";
	rename -uid "8B13E59C-4D42-E21F-0720-C0BDE6E35634";
	setAttr ".op" 1;
createNode shadingEngine -n "aiStandardSurface2SG";
	rename -uid "E68237CD-4272-A909-1B73-398E19D3D686";
	setAttr ".ihi" 0;
	setAttr -s 17 ".dsm";
	setAttr ".ro" yes;
	setAttr -s 2 ".gn";
createNode materialInfo -n "materialInfo6";
	rename -uid "78F5C0FA-4B46-F77D-D54E-029AF6613695";
createNode decomposeMatrix -n "decomposeMatrix7";
	rename -uid "D03EEE3D-43E3-F209-46F1-C2A265E4A97C";
createNode decomposeMatrix -n "decomposeMatrix8";
	rename -uid "4BEEC4EB-4FD5-486C-6444-45BD3215E9AC";
createNode unitConversion -n "unitConversion33";
	rename -uid "2CA9E9D0-4782-F1CB-4D3D-369CC0497EAA";
	setAttr ".cf" 57.295779513082323;
createNode multiplyDivide -n "multiplyDivide11";
	rename -uid "816F4293-4E75-8ECD-ADC1-80B6FBFDDB8F";
createNode multiplyDivide -n "multiplyDivide12";
	rename -uid "5B649B1E-4E6D-4B33-C434-83BB84BD7871";
createNode unitConversion -n "unitConversion34";
	rename -uid "52B650B5-4617-2CAE-F257-D8B68D124412";
	setAttr ".cf" 57.295779513082323;
createNode unitConversion -n "unitConversion35";
	rename -uid "4CDAC466-46BB-B0B1-68DC-22B1F598D69B";
	setAttr ".cf" 0.017453292519943295;
createNode transformGeometry -n "transformGeometry3";
	rename -uid "8C924E05-42F6-FAFE-D9F8-EBAAE9ED61E3";
	setAttr ".txf" -type "matrix" 1 0 0 0 0 1 0 0 0 0 1 0 6 26 0 1;
createNode deleteComponent -n "deleteComponent2";
	rename -uid "1B57FEB5-4553-8C0C-954D-5DB45CB4D586";
	setAttr ".dc" -type "componentList" 1 "cv[0:10]";
createNode makeNurbCircle -n "makeNurbCircle2";
	rename -uid "AE1C3144-4137-10EC-61D8-359C553C3B7A";
	setAttr ".nr" -type "double3" 0 1 0 ;
	setAttr ".d" 1;
	setAttr ".s" 10;
createNode blendColors -n "blendColors9";
	rename -uid "0989F745-45C2-1A6D-0C81-B993634CA486";
createNode decomposeMatrix -n "decomposeMatrix9";
	rename -uid "24817BBA-48C9-7D8B-3FD7-6D8FDF5A39EB";
createNode decomposeMatrix -n "decomposeMatrix10";
	rename -uid "5CEA3534-4313-0097-5A33-DFB3391388C6";
createNode unitConversion -n "unitConversion36";
	rename -uid "70ABF201-423A-AD7D-8164-799DB3A52B49";
	setAttr ".cf" 0.017453292519943295;
createNode blendColors -n "blendColors10";
	rename -uid "5B7A4B37-4456-B0D2-53EE-5097C4272678";
createNode unitConversion -n "unitConversion37";
	rename -uid "33F8FD55-46FD-DB40-0873-F49B4FF2C477";
	setAttr ".cf" 57.295779513082323;
createNode unitConversion -n "unitConversion39";
	rename -uid "A2C83B9E-41D8-8B34-F35D-E096E31BBFB5";
	setAttr ".cf" 0.017453292519943295;
createNode unitConversion -n "unitConversion40";
	rename -uid "70B1893C-43F6-94F4-A962-A08ECC55725E";
	setAttr ".cf" 0.017453292519943295;
createNode unitConversion -n "unitConversion41";
	rename -uid "C0138FDA-42E8-EDC9-3BA8-0999B6EB5B16";
	setAttr ".cf" 0.017453292519943295;
createNode multiplyDivide -n "multiplyDivide13";
	rename -uid "893C4AED-47A2-5CC7-05C7-17BFDAB8ED75";
createNode unitConversion -n "unitConversion42";
	rename -uid "8A3EBCF1-4885-69C0-AABE-34B038409088";
	setAttr ".cf" 57.295779513082323;
createNode floatMath -n "floatMath32";
	rename -uid "41052581-431B-94A4-F3AA-CCBAE6A3C77D";
	setAttr "._cnd" 2;
createNode floatMath -n "floatMath33";
	rename -uid "DCCF991D-49A5-20D9-CE55-5392655F68A8";
	setAttr "._fb" 30;
	setAttr "._cnd" 3;
createNode floatMath -n "floatMath34";
	rename -uid "43FCDF61-43A3-37EF-2272-25AAE61BB904";
createNode floatMath -n "floatMath35";
	rename -uid "14FB3EAA-41EA-1AB5-C8ED-77895B581ACB";
	setAttr "._fb" 0.5;
	setAttr "._cnd" 6;
createNode floatMath -n "floatMath36";
	rename -uid "FC023C0D-4697-3982-6AF9-129309E90CD7";
	setAttr "._fa" 0;
	setAttr "._fb" 2;
	setAttr "._cnd" 6;
createNode floatMath -n "floatMath37";
	rename -uid "2CDE46F9-46D9-7039-19C0-7A8C02E2D17A";
	setAttr "._fb" 0.5;
	setAttr "._cnd" 6;
createNode floatMath -n "floatMath38";
	rename -uid "CC9D460E-4301-91FE-F986-D5BDE7AB3DC4";
	setAttr "._fa" 0;
	setAttr "._fb" 2;
	setAttr "._cnd" 6;
createNode condition -n "condition25";
	rename -uid "EE016D79-4C6D-BB55-F84D-C9AD27298D02";
	setAttr ".op" 4;
	setAttr ".cf" -type "float3" 0 0 0 ;
createNode multiplyDivide -n "multiplyDivide14";
	rename -uid "84587257-47A2-9F36-7E8F-67A26639A1BC";
createNode multiplyDivide -n "multiplyDivide15";
	rename -uid "C1D08076-46D7-8E7E-8009-2096744BA014";
	setAttr ".op" 2;
createNode condition -n "condition26";
	rename -uid "F8EBEFAF-4027-8BFE-34B6-909A4AE0FF61";
	setAttr ".cf" -type "float3" 0 0 0 ;
createNode remapValue -n "remapValue7";
	rename -uid "203B942E-4245-9D69-B199-A7812F48B73E";
	setAttr ".imx" -3;
	setAttr -s 4 ".vl[1:4]"  0.5 0.69999999 0 0.25 0.30000001 0
		 0.75 1 1 0 0 0;
	setAttr -s 2 ".cl";
	setAttr ".cl[0].clp" 0;
	setAttr ".cl[0].clc" -type "float3" 0 0 0 ;
	setAttr ".cl[0].cli" 1;
	setAttr ".cl[1].clp" 1;
	setAttr ".cl[1].clc" -type "float3" 1 1 1 ;
	setAttr ".cl[1].cli" 1;
createNode condition -n "condition27";
	rename -uid "8C75FFD7-472D-D1E4-E515-059FE2AB4AB9";
	setAttr ".st" 0.69999998807907104;
	setAttr ".ct" -type "float3" -32 0 0 ;
	setAttr ".cf" -type "float3" 0 0 1 ;
createNode condition -n "condition28";
	rename -uid "56EEE2BA-4A40-A394-71F4-87BF3E069419";
	setAttr ".st" 1;
	setAttr ".ct" -type "float3" -32 32 0 ;
	setAttr ".cf" -type "float3" 0 0 1 ;
createNode condition -n "condition29";
	rename -uid "FF8AFE0F-4FD1-FAD7-8C85-51AB6CD6421E";
	setAttr ".st" 0.30000001192092896;
	setAttr ".ct" -type "float3" 0 32 0 ;
	setAttr ".cf" -type "float3" 0 0 1 ;
createNode remapValue -n "remapValue8";
	rename -uid "AFBFDD4F-47A2-B7CE-3C29-35B505388518";
	setAttr ".imn" -2;
	setAttr ".imx" 2;
	setAttr ".omx" -16;
	setAttr -s 3 ".vl[2:4]"  0.30000001 0.5 0 0.69999999 1 1 0 0 
		0;
	setAttr -s 2 ".cl";
	setAttr ".cl[0].clp" 0;
	setAttr ".cl[0].clc" -type "float3" 0 0 0 ;
	setAttr ".cl[0].cli" 1;
	setAttr ".cl[1].clp" 1;
	setAttr ".cl[1].clc" -type "float3" 1 1 1 ;
	setAttr ".cl[1].cli" 1;
createNode floatMath -n "floatMath39";
	rename -uid "D55E7A6C-4C74-295B-47AD-6A9AEF88FEE5";
	setAttr "._fa" 0;
createNode floatMath -n "floatMath40";
	rename -uid "44204EA9-477C-ADCF-3690-D2BED3B3F1A5";
	setAttr "._fa" 0;
createNode multiplyDivide -n "multiplyDivide16";
	rename -uid "FA2EB8A7-4A4D-0D7B-BF3F-9DB933218F9D";
	setAttr ".i2" -type "float3" -1 1 1 ;
createNode floatMath -n "floatMath41";
	rename -uid "1F41028B-4C06-C8EC-FB71-BEB8C1BD22FF";
	setAttr "._fb" -1;
createNode floatMath -n "floatMath42";
	rename -uid "0157B312-41AC-73FD-7146-B6BED69486BD";
	setAttr "._cnd" 1;
createNode floatMath -n "floatMath43";
	rename -uid "48700AEA-4E30-ED6F-B679-67AED8C9B687";
createNode floatMath -n "floatMath44";
	rename -uid "E093F416-43FA-A65F-9468-4A8B73238AE3";
	setAttr "._cnd" 1;
createNode floatMath -n "floatMath45";
	rename -uid "0E5FFCE5-48B1-673D-D504-94AB136BA7B5";
createNode floatMath -n "floatMath46";
	rename -uid "754C027D-43B2-FA47-BCA6-0D9AB1BAE976";
	setAttr "._cnd" 2;
createNode floatMath -n "floatMath47";
	rename -uid "FDFE6466-4498-8080-82AB-44AF3919BCFF";
	setAttr "._fb" 30;
	setAttr "._cnd" 3;
createNode floatMath -n "floatMath48";
	rename -uid "EC43CCD0-40B7-04FD-F90A-8796BC140B45";
createNode floatMath -n "floatMath49";
	rename -uid "5C7BECBF-4C6D-EEBD-4FDD-BF8E17193D46";
	setAttr "._fb" 0.5;
	setAttr "._cnd" 6;
createNode floatMath -n "floatMath50";
	rename -uid "C6273888-4BD4-DD11-F1A1-B8997569272B";
	setAttr "._fa" 0;
	setAttr "._fb" 2;
	setAttr "._cnd" 6;
createNode floatMath -n "floatMath51";
	rename -uid "BC6615CB-4648-5DBF-A208-18B11C9DA134";
	setAttr "._fb" 0.5;
	setAttr "._cnd" 6;
createNode floatMath -n "floatMath52";
	rename -uid "233B1688-4B7A-CFA4-7461-728FCD15EEBB";
	setAttr "._fa" 0;
	setAttr "._fb" 2;
	setAttr "._cnd" 6;
createNode ikRPsolver -n "ikRPsolver2";
	rename -uid "771CD188-4889-9550-6379-75B4E3CDEC8A";
createNode condition -n "condition33";
	rename -uid "E47CE37C-4351-847C-E00A-19BAFC2EA500";
	setAttr ".op" 4;
	setAttr ".cf" -type "float3" 0 0 0 ;
createNode multiplyDivide -n "multiplyDivide17";
	rename -uid "F165FAD4-4DFC-8BB4-1380-15B4DA356E4E";
createNode multiplyDivide -n "multiplyDivide18";
	rename -uid "327316E0-41E7-5F2A-2379-F4A4BB7AF5E4";
	setAttr ".op" 2;
createNode multiplyDivide -n "multiplyDivide19";
	rename -uid "5DB88854-476A-31D0-CE18-5EAF91C05E3C";
	setAttr ".i1" -type "float3" 0 0 -12 ;
createNode remapValue -n "remapValue9";
	rename -uid "F06D22DB-4CA9-02A7-34A4-AEA44D686741";
	setAttr ".imx" -4;
	setAttr ".omx" 3.5;
	setAttr -s 2 ".vl[0:1]"  0 0 2 1 1 2;
	setAttr -s 2 ".cl";
	setAttr ".cl[0].clp" 0;
	setAttr ".cl[0].clc" -type "float3" 0 0 0 ;
	setAttr ".cl[0].cli" 1;
	setAttr ".cl[1].clp" 1;
	setAttr ".cl[1].clc" -type "float3" 1 1 1 ;
	setAttr ".cl[1].cli" 1;
createNode condition -n "condition34";
	rename -uid "C8BC570D-4F2B-322B-3343-D98870147ECA";
	setAttr ".op" 1;
createNode multiplyDivide -n "multiplyDivide20";
	rename -uid "2DF4D00A-4C3E-C768-7A08-5A8D9F737B68";
	setAttr ".i1" -type "float3" 0 0 -12 ;
createNode remapValue -n "remapValue10";
	rename -uid "B48FDCF4-49E6-0AAA-B433-49A2E099A6FD";
	setAttr ".imx" -4;
	setAttr ".omx" 3.5;
	setAttr -s 2 ".vl[0:1]"  0 0 2 1 1 2;
	setAttr -s 2 ".cl";
	setAttr ".cl[0].clp" 0;
	setAttr ".cl[0].clc" -type "float3" 0 0 0 ;
	setAttr ".cl[0].cli" 1;
	setAttr ".cl[1].clp" 1;
	setAttr ".cl[1].clc" -type "float3" 1 1 1 ;
	setAttr ".cl[1].cli" 1;
createNode multiplyDivide -n "multiplyDivide21";
	rename -uid "70D140EA-4209-3AC6-4BD4-B49C20616B8D";
createNode skinCluster -n "skinCluster16";
	rename -uid "C53525C2-4BBC-AEA9-A8E9-0E859F20DB9B";
	setAttr ".ip[0].gtg" -type "string" "";
	setAttr ".skm" 1;
	setAttr -s 8 ".wl";
	setAttr ".wl[0:7].w"
		1 0 1
		1 0 1
		1 0 1
		1 0 1
		1 0 1
		1 0 1
		1 0 1
		1 0 1;
	setAttr ".pm[0]" -type "matrix" -1 0 1.2246467991473532e-16 0 0 1 0 0 -1.2246467991473532e-16 0 -1 0
		 -6 -22 7.3478807948841188e-16 1;
	setAttr ".gm" -type "matrix" 1 0 0 0 0 1 0 0 0 0 1 0 0 2.4868995751603507e-14 0 1;
	setAttr ".dpf[0]"  10;
	setAttr ".mmi" yes;
	setAttr ".mi" 1;
	setAttr ".ucm" yes;
createNode tweak -n "tweak16";
	rename -uid "EF7E4372-4833-1E6F-57D9-0B8581B4F8DE";
createNode objectSet -n "skinCluster16Set";
	rename -uid "9F2FDA15-4C45-8A46-A4BF-57AC0D3664CF";
	setAttr ".ihi" 0;
	setAttr ".vo" yes;
createNode groupId -n "skinCluster16GroupId";
	rename -uid "CC8F693C-478C-0697-D320-059599F69DBF";
	setAttr ".ihi" 0;
createNode groupParts -n "skinCluster16GroupParts";
	rename -uid "CCD9366D-4E4D-7917-4D74-3C9C2016F5AC";
	setAttr ".ihi" 0;
	setAttr ".ic" -type "componentList" 1 "vtx[*]";
createNode objectSet -n "tweakSet16";
	rename -uid "4106CAC4-45A3-7B39-E212-2C8C7293C264";
	setAttr ".ihi" 0;
	setAttr ".vo" yes;
createNode groupId -n "groupId45";
	rename -uid "B6A1533D-45CA-AF0D-8AF7-AABF0C5EB6D6";
	setAttr ".ihi" 0;
createNode groupParts -n "groupParts34";
	rename -uid "0CD4D02D-4EAD-CEDC-C9F3-42BE89614627";
	setAttr ".ihi" 0;
	setAttr ".ic" -type "componentList" 1 "vtx[*]";
createNode dagPose -n "bindPose15";
	rename -uid "0DFC561B-4163-3097-631C-4992F7A0B1AC";
	setAttr -s 6 ".wm";
	setAttr ".wm[0]" -type "matrix" 1 0 0 0 0 1 0 0 0 0 1 0 0 0 0 1;
	setAttr ".wm[1]" -type "matrix" 1 0 0 0 0 1 0 0 0 0 1 0 0 0 0 1;
	setAttr ".wm[3]" -type "matrix" 1 0 0 0 0 1 0 0 0 0 1 0 0 0 0 1;
	setAttr ".wm[4]" -type "matrix" 1 0 0 0 0 1 0 0 0 0 1 0 0 0 0 1;
	setAttr ".wm[5]" -type "matrix" 1 0 0 0 0 1 0 0 0 0 1 0 0 0 0 1;
	setAttr -s 6 ".xm";
	setAttr ".xm[0]" -type "matrix" "xform" 1 1 1 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0
		 0 0 0 0 0 0 0 0 0 0 1 0 0 0 1 1 1 1 yes;
	setAttr ".xm[1]" -type "matrix" "xform" 1 1 1 0 0 0 0 0 0 0 0 0 0 6 22 0 0 0
		 0 6 22 0 0 0 0 0 0 0 1 0 0 0 1 1 1 1 yes;
	setAttr ".xm[2]" -type "matrix" "xform" 1 1 1 0 3.1415926535897931 0 0 -6 22
		 9.7971748206813428e-16 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 1 0 0 0 1 1 1 1 yes;
	setAttr ".xm[3]" -type "matrix" "xform" 1 1 1 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0
		 0 0 0 0 0 0 0 0 0 0 1 0 0 0 1 1 1 1 yes;
	setAttr ".xm[4]" -type "matrix" "xform" 1 1 1 0 0 0 0 0 0 0 0 0 0 0 1.0078125
		 0 0 0 0 0 1.0078125 0 0 0 0 0 0 0 1 0 0 0 1 1 1 1 yes;
	setAttr ".xm[5]" -type "matrix" "xform" 1 1 1 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0
		 0 0 0 0 0 0 0 0 0 0 1 0 0 0 1 1 1 1 yes;
	setAttr -s 6 ".m";
	setAttr -s 6 ".p";
	setAttr -s 6 ".g[0:5]" yes yes no yes yes yes;
	setAttr ".bp" yes;
createNode multiplyDivide -n "multiplyDivide22";
	rename -uid "B200BC4D-43AB-9CE0-0E26-D28FEFA98007";
	setAttr ".i2" -type "float3" -1 -1 -1 ;
createNode unitConversion -n "unitConversion45";
	rename -uid "48FF17E1-4E62-E78B-3C65-1F8BE2E5CB28";
	setAttr ".cf" 57.295779513082323;
createNode floatMath -n "floatMath53";
	rename -uid "DBE32630-4EE8-C325-9165-328155943965";
	setAttr "._fb" 180;
createNode unitConversion -n "unitConversion46";
	rename -uid "6BD2430C-4DE8-E7E1-19FB-C185E257E357";
	setAttr ".cf" 57.295779513082323;
createNode skinCluster -n "skinCluster18";
	rename -uid "A2A4E344-45F3-1179-D8F9-87B9C692F9F4";
	setAttr ".ip[0].gtg" -type "string" "";
	setAttr ".skm" 1;
	setAttr -s 8 ".wl";
	setAttr ".wl[0:7].w"
		1 0 1
		1 0 1
		1 0 1
		1 0 1
		1 0 1
		1 0 1
		1 0 1
		1 0 1;
	setAttr ".pm[0]" -type "matrix" -1 0 1.2246467991473532e-16 0 0 1 0 0 -1.2246467991473532e-16 0 -1 0
		 -6 -22 7.3478807948841188e-16 1;
	setAttr ".gm" -type "matrix" 1 0 0 0 0 1 0 0 0 0 1 0 0 0 0 1;
	setAttr ".dpf[0]"  10;
	setAttr ".mmi" yes;
	setAttr ".mi" 1;
	setAttr ".ucm" yes;
createNode tweak -n "tweak18";
	rename -uid "D913EB4C-40CC-AF30-2D80-C59A5B9E4669";
createNode objectSet -n "skinCluster18Set";
	rename -uid "CE690F74-408A-BF1A-75E2-72BB216AA2B9";
	setAttr ".ihi" 0;
	setAttr ".vo" yes;
createNode groupId -n "skinCluster18GroupId";
	rename -uid "A7CC7274-4397-FBFC-E0DA-C3B09763C33B";
	setAttr ".ihi" 0;
createNode groupParts -n "skinCluster18GroupParts";
	rename -uid "6EA24434-4695-9070-6CF9-D0BCEB3C387D";
	setAttr ".ihi" 0;
	setAttr ".ic" -type "componentList" 1 "vtx[*]";
createNode objectSet -n "tweakSet18";
	rename -uid "C31A8CE9-49CD-F8AA-1C05-F98B8A4A8CA0";
	setAttr ".ihi" 0;
	setAttr ".vo" yes;
createNode groupId -n "groupId49";
	rename -uid "0BB0C3F8-4C8F-13B7-DEE3-3190CDE5C464";
	setAttr ".ihi" 0;
createNode groupParts -n "groupParts38";
	rename -uid "ABB53670-4EBA-F511-778F-F08DBAEB4E83";
	setAttr ".ihi" 0;
	setAttr ".ic" -type "componentList" 1 "vtx[*]";
createNode skinCluster -n "skinCluster19";
	rename -uid "AC5FA1AC-495C-7B74-9545-0F988158A954";
	setAttr ".ip[0].gtg" -type "string" "";
	setAttr ".skm" 1;
	setAttr -s 8 ".wl";
	setAttr ".wl[0:7].w"
		1 0 1
		1 0 1
		1 0 1
		1 0 1
		1 0 1
		1 0 1
		1 0 1
		1 0 1;
	setAttr ".pm[0]" -type "matrix" 1 0 0 0 0 1 0 0 0 0 1 0 0 -14 0 1;
	setAttr ".gm" -type "matrix" 1 0 0 0 0 1 0 0 0 0 1 0 0 0 0 1;
	setAttr ".dpf[0]"  10;
	setAttr ".mmi" yes;
	setAttr ".mi" 1;
	setAttr ".ucm" yes;
createNode tweak -n "tweak19";
	rename -uid "A2D54376-4A51-3890-3C92-8FBF8002E5C6";
	setAttr ".ip[0].gtg" -type "string" "";
	setAttr -s 8 ".vl[0].vt[0:7]" -type "float3"  0 0 -0.14186561 0 0 
		0.14186561 0 0 -0.14186561 0 0 0.14186561 0 0 0.14186561 0 0 -0.14186561 0 0 0.14186561 
		0 0 -0.14186561;
createNode objectSet -n "skinCluster19Set";
	rename -uid "3F93A1B4-4842-D36B-2FAC-0BAB073880B2";
	setAttr ".ihi" 0;
	setAttr ".vo" yes;
createNode groupId -n "skinCluster19GroupId";
	rename -uid "1DB1A66B-4651-A81E-2C15-D38CA1773411";
	setAttr ".ihi" 0;
createNode groupParts -n "skinCluster19GroupParts";
	rename -uid "93E4E127-4087-4983-E274-50BE137C396D";
	setAttr ".ihi" 0;
	setAttr ".ic" -type "componentList" 1 "vtx[*]";
createNode objectSet -n "tweakSet19";
	rename -uid "7D145D94-4FEA-AC41-D40F-5EAC0F9C84C8";
	setAttr ".ihi" 0;
	setAttr ".vo" yes;
createNode groupId -n "groupId51";
	rename -uid "F9224C7C-4BA6-A6A5-2D2D-3288E2946B0A";
	setAttr ".ihi" 0;
createNode groupParts -n "groupParts40";
	rename -uid "CC77030C-43BD-6091-957E-BE82DAA08B97";
	setAttr ".ihi" 0;
	setAttr ".ic" -type "componentList" 1 "vtx[*]";
createNode skinCluster -n "skinCluster20";
	rename -uid "65A14F2C-4CD1-1406-1FD2-9AB1D824B9B7";
	setAttr ".ip[0].gtg" -type "string" "";
	setAttr ".skm" 1;
	setAttr -s 8 ".wl";
	setAttr ".wl[0:7].w"
		1 0 1
		1 0 1
		1 0 1
		1 0 1
		1 0 1
		1 0 1
		1 0 1
		1 0 1;
	setAttr ".pm[0]" -type "matrix" 1 0 0 0 0 1 0 0 0 0 1 0 -6 -22 0 1;
	setAttr ".gm" -type "matrix" 1 0 0 0 0 1 0 0 0 0 1 0 0 0 0 1;
	setAttr ".dpf[0]"  10;
	setAttr ".mmi" yes;
	setAttr ".mi" 1;
	setAttr ".ucm" yes;
createNode tweak -n "tweak20";
	rename -uid "CF4CE643-46AC-257D-1FB5-B19C9CBDE93E";
createNode objectSet -n "skinCluster20Set";
	rename -uid "329BB834-4FF8-023F-AB0F-8F9732307D3C";
	setAttr ".ihi" 0;
	setAttr ".vo" yes;
createNode groupId -n "skinCluster20GroupId";
	rename -uid "AD9034E6-49F7-D4E2-5C54-67BCA886BB77";
	setAttr ".ihi" 0;
createNode groupParts -n "skinCluster20GroupParts";
	rename -uid "2233640C-4B77-6960-7BC7-F7BD67F916B2";
	setAttr ".ihi" 0;
	setAttr ".ic" -type "componentList" 1 "vtx[*]";
createNode objectSet -n "tweakSet20";
	rename -uid "35856D42-4A8E-0846-A346-2290A459C185";
	setAttr ".ihi" 0;
	setAttr ".vo" yes;
createNode groupId -n "groupId53";
	rename -uid "E7EBFBFF-4527-C093-451F-5981926468D3";
	setAttr ".ihi" 0;
createNode groupParts -n "groupParts42";
	rename -uid "31D5869D-4368-34D1-A909-5E962DDE479D";
	setAttr ".ihi" 0;
	setAttr ".ic" -type "componentList" 1 "vtx[*]";
createNode skinCluster -n "skinCluster21";
	rename -uid "85B26A94-421D-C403-43F4-1ABD4A5D2D2B";
	setAttr ".ip[0].gtg" -type "string" "";
	setAttr ".skm" 1;
	setAttr -s 8 ".wl";
	setAttr ".wl[0:7].w"
		1 0 1
		1 0 1
		1 0 1
		1 0 1
		1 0 1
		1 0 1
		1 0 1
		1 0 1;
	setAttr ".pm[0]" -type "matrix" 0.99999999922813898 -2.4321874407121769e-05 3.0857228098450656e-05 0
		 2.4278695477406022e-05 0.99999902174375588 0.0013985428403461217 0 -3.0891213095490465e-05 -0.0013985420900933965 0.99999902156239906 0
		 -1.9999999965498416 4.8730059177773389e-05 -0.00012342892277743912 1;
	setAttr ".gm" -type "matrix" 1 0 0 0 0 1 0 0 0 0 1 0 0 0 0 1;
	setAttr ".dpf[0]"  10;
	setAttr ".mmi" yes;
	setAttr ".mi" 1;
	setAttr ".ucm" yes;
createNode tweak -n "tweak21";
	rename -uid "9FC4F156-4830-5A00-78DF-EC903A580A9A";
createNode objectSet -n "skinCluster21Set";
	rename -uid "176E2E61-41AC-63E8-4641-6E90E0D590C6";
	setAttr ".ihi" 0;
	setAttr ".vo" yes;
createNode groupId -n "skinCluster21GroupId";
	rename -uid "6FA365DD-4577-F66F-6E89-E0A863B09CA0";
	setAttr ".ihi" 0;
createNode groupParts -n "skinCluster21GroupParts";
	rename -uid "204A1A00-48F6-EF31-A6A1-C292E984A421";
	setAttr ".ihi" 0;
	setAttr ".ic" -type "componentList" 1 "vtx[*]";
createNode objectSet -n "tweakSet21";
	rename -uid "5815450B-413E-C486-45BE-1FBCB045E44C";
	setAttr ".ihi" 0;
	setAttr ".vo" yes;
createNode groupId -n "groupId55";
	rename -uid "240321C1-4172-D678-C3FD-51BCCCBA8EC2";
	setAttr ".ihi" 0;
createNode groupParts -n "groupParts44";
	rename -uid "35AC9BDC-4BD8-9EB1-7CDB-78A7050B6851";
	setAttr ".ihi" 0;
	setAttr ".ic" -type "componentList" 1 "vtx[*]";
createNode skinCluster -n "skinCluster22";
	rename -uid "CFE7AF3D-4066-D46A-E4D4-3E98BD1057C5";
	setAttr ".ip[0].gtg" -type "string" "";
	setAttr ".skm" 1;
	setAttr -s 8 ".wl";
	setAttr ".wl[0:7].w"
		1 0 1
		1 0 1
		1 0 1
		1 0 1
		1 0 1
		1 0 1
		1 0 1
		1 0 1;
	setAttr ".pm[0]" -type "matrix" 0.99999999922813898 -2.4321874407121769e-05 3.0857228098450656e-05 0
		 2.4278695477406022e-05 0.99999902174375588 0.0013985428403461217 0 -3.0891213095490465e-05 -0.0013985420900933965 0.99999902156239906 0
		 2.0000000003627143 -4.8557438450713679e-05 -1.0383636509761658e-11 1;
	setAttr ".gm" -type "matrix" 1 0 0 0 0 1 0 0 0 0 1 0 0 0 0 1;
	setAttr ".dpf[0]"  10;
	setAttr ".mmi" yes;
	setAttr ".mi" 1;
	setAttr ".ucm" yes;
createNode tweak -n "tweak22";
	rename -uid "1BDF86D8-4FFE-F941-CCE2-A48B21DB76D1";
createNode objectSet -n "skinCluster22Set";
	rename -uid "F24FE95E-476B-C2E7-B13B-8A8167789A62";
	setAttr ".ihi" 0;
	setAttr ".vo" yes;
createNode groupId -n "skinCluster22GroupId";
	rename -uid "0925EFBA-4CF0-51F5-B4A1-5CBBE6DBD387";
	setAttr ".ihi" 0;
createNode groupParts -n "skinCluster22GroupParts";
	rename -uid "0FD94E33-4D49-0F4B-383F-138742AA0AE6";
	setAttr ".ihi" 0;
	setAttr ".ic" -type "componentList" 1 "vtx[*]";
createNode objectSet -n "tweakSet22";
	rename -uid "9EE8E679-43AA-9307-01BF-BABA33E4FBC9";
	setAttr ".ihi" 0;
	setAttr ".vo" yes;
createNode groupId -n "groupId57";
	rename -uid "5D84605B-40AD-9F04-F5F9-B99AFC0B8BD5";
	setAttr ".ihi" 0;
createNode groupParts -n "groupParts46";
	rename -uid "11BD1BBA-4AF5-F7C5-41A6-32A6E3AEC1EE";
	setAttr ".ihi" 0;
	setAttr ".ic" -type "componentList" 1 "vtx[*]";
createNode skinCluster -n "skinCluster23";
	rename -uid "947B9A78-4566-F268-34FC-0EB2A9468286";
	setAttr ".ip[0].gtg" -type "string" "";
	setAttr ".skm" 1;
	setAttr -s 8 ".wl";
	setAttr ".wl[0:7].w"
		1 0 1
		1 0 1
		1 0 1
		1 0 1
		1 0 1
		1 0 1
		1 0 1
		1 0 1;
	setAttr ".pm[0]" -type "matrix" 1 0 0 0 0 1 0 0 0 0 1 0 -6 -22 0 1;
	setAttr ".gm" -type "matrix" 1 0 0 0 0 1 0 0 0 0 1 0 0 0 0 1;
	setAttr ".dpf[0]"  10;
	setAttr ".mmi" yes;
	setAttr ".mi" 1;
	setAttr ".ucm" yes;
createNode tweak -n "tweak23";
	rename -uid "048E4A3A-40D0-BA3A-96DD-EAAB81E86699";
createNode objectSet -n "skinCluster23Set";
	rename -uid "60B7BB25-48B2-8DA5-DA16-C2844CD6F0E2";
	setAttr ".ihi" 0;
	setAttr ".vo" yes;
createNode groupId -n "skinCluster23GroupId";
	rename -uid "DA4B16AB-4201-508B-B514-FC8DFAF9D194";
	setAttr ".ihi" 0;
createNode groupParts -n "skinCluster23GroupParts";
	rename -uid "98F016CC-4103-6014-BD44-ADAA7D2A32DD";
	setAttr ".ihi" 0;
	setAttr ".ic" -type "componentList" 1 "vtx[*]";
createNode objectSet -n "tweakSet23";
	rename -uid "11543BE2-4932-CA79-3A5F-9C801A1CF152";
	setAttr ".ihi" 0;
	setAttr ".vo" yes;
createNode groupId -n "groupId59";
	rename -uid "33BAE42F-465F-875B-7EE7-E9A54E8F6827";
	setAttr ".ihi" 0;
createNode groupParts -n "groupParts48";
	rename -uid "767B0237-4EBB-4AF8-A178-A0A0EA8ABA2B";
	setAttr ".ihi" 0;
	setAttr ".ic" -type "componentList" 1 "vtx[*]";
createNode skinCluster -n "skinCluster24";
	rename -uid "82197A31-49A9-74BD-50CD-9EB09E918291";
	setAttr ".ip[0].gtg" -type "string" "";
	setAttr ".skm" 1;
	setAttr -s 8 ".wl";
	setAttr ".wl[0:7].w"
		1 0 1
		1 0 1
		1 0 1
		1 0 1
		1 0 1
		1 0 1
		1 0 1
		1 0 1;
	setAttr ".pm[0]" -type "matrix" -1 0 1.2246467991473532e-16 0 0 1 0 0 -1.2246467991473532e-16 0 -1 0
		 -6 -22 7.3478807948841188e-16 1;
	setAttr ".gm" -type "matrix" 1 0 0 0 0 1 0 0 0 0 1 0 0 0 0 1;
	setAttr ".dpf[0]"  10;
	setAttr ".mmi" yes;
	setAttr ".mi" 1;
	setAttr ".ucm" yes;
createNode tweak -n "tweak24";
	rename -uid "8DFBC16C-483E-095C-C7AB-54A63B51E9E8";
createNode objectSet -n "skinCluster24Set";
	rename -uid "FB4DC70E-4FA9-197F-835D-91B890EFE6A1";
	setAttr ".ihi" 0;
	setAttr ".vo" yes;
createNode groupId -n "skinCluster24GroupId";
	rename -uid "0FD095E3-4B6D-1386-C874-84A283B3C8B1";
	setAttr ".ihi" 0;
createNode groupParts -n "skinCluster24GroupParts";
	rename -uid "21CE4079-409D-D98D-6F87-C78DE48ECD21";
	setAttr ".ihi" 0;
	setAttr ".ic" -type "componentList" 1 "vtx[*]";
createNode objectSet -n "tweakSet24";
	rename -uid "11D7B6DE-420A-0E11-8B3C-F7B7AB8F5B44";
	setAttr ".ihi" 0;
	setAttr ".vo" yes;
createNode groupId -n "groupId61";
	rename -uid "BFF0DB32-4072-2107-76AF-01A54778B8F6";
	setAttr ".ihi" 0;
createNode groupParts -n "groupParts50";
	rename -uid "3D683FDE-4720-7694-A921-4585A4EFB83A";
	setAttr ".ihi" 0;
	setAttr ".ic" -type "componentList" 1 "vtx[*]";
createNode tweak -n "tweak25";
	rename -uid "A096BF7D-4214-3F21-39A3-25AC907D8043";
createNode objectSet -n "tweakSet25";
	rename -uid "5EF5BFCD-4DF1-482F-45C4-3A992CB261F8";
	setAttr ".ihi" 0;
	setAttr ".vo" yes;
createNode groupId -n "groupId63";
	rename -uid "1F0F76F8-41AB-CAEF-95B7-0B83D579BFE2";
	setAttr ".ihi" 0;
createNode groupParts -n "groupParts52";
	rename -uid "20E48136-47D1-8B7F-E2BB-68AD9D54854B";
	setAttr ".ihi" 0;
	setAttr ".ic" -type "componentList" 1 "vtx[*]";
createNode skinCluster -n "skinCluster26";
	rename -uid "65C65440-4998-C19D-81BF-129BAC68D449";
	setAttr ".ip[0].gtg" -type "string" "";
	setAttr ".skm" 1;
	setAttr -s 8 ".wl";
	setAttr ".wl[0:7].w"
		1 0 1
		1 0 1
		1 0 1
		1 0 1
		1 0 1
		1 0 1
		1 0 1
		1 0 1;
	setAttr ".pm[0]" -type "matrix" 1 0 0 0 0 1 0 0 0 0 1 0 0 -14 0 1;
	setAttr ".gm" -type "matrix" 1 0 0 0 0 1 0 0 0 0 1 0 2.1316282072803006e-14 0 0 1;
	setAttr ".dpf[0]"  10;
	setAttr ".mmi" yes;
	setAttr ".mi" 1;
	setAttr ".ucm" yes;
createNode tweak -n "tweak26";
	rename -uid "87A40F7D-4A58-7A7E-4993-E18FA7C233A3";
createNode objectSet -n "skinCluster26Set";
	rename -uid "B8BE10D5-407E-F423-2F45-7FB2616D9312";
	setAttr ".ihi" 0;
	setAttr ".vo" yes;
createNode groupId -n "skinCluster26GroupId";
	rename -uid "95B20EF4-40E1-C057-EB6C-F286279D7E4E";
	setAttr ".ihi" 0;
createNode groupParts -n "skinCluster26GroupParts";
	rename -uid "6371E313-43D8-F464-C171-6782329FF9A8";
	setAttr ".ihi" 0;
	setAttr ".ic" -type "componentList" 1 "vtx[*]";
createNode objectSet -n "tweakSet26";
	rename -uid "F420241E-47CD-2296-5927-C6B0DF839EBB";
	setAttr ".ihi" 0;
	setAttr ".vo" yes;
createNode groupId -n "groupId65";
	rename -uid "E07C8A75-4422-50CF-401D-83933899C057";
	setAttr ".ihi" 0;
createNode groupParts -n "groupParts54";
	rename -uid "FCAF8D29-4988-1E8D-3862-13832675B374";
	setAttr ".ihi" 0;
	setAttr ".ic" -type "componentList" 1 "vtx[*]";
createNode skinCluster -n "skinCluster27";
	rename -uid "790C9FC5-4BFB-BAE4-C1D5-618BF8B7F49E";
	setAttr ".ip[0].gtg" -type "string" "";
	setAttr ".skm" 1;
	setAttr -s 8 ".wl";
	setAttr ".wl[0:7].w"
		1 0 1
		1 0 1
		1 0 1
		1 0 1
		1 0 1
		1 0 1
		1 0 1
		1 0 1;
	setAttr ".pm[0]" -type "matrix" 1 0 0 0 0 1 0 0 0 0 1 0 0 -14 0 1;
	setAttr ".gm" -type "matrix" 1 0 0 0 0 1 0 0 0 0 1 0 0 0 0 1;
	setAttr ".dpf[0]"  10;
	setAttr ".mmi" yes;
	setAttr ".mi" 1;
	setAttr ".ucm" yes;
createNode tweak -n "tweak27";
	rename -uid "DFA3CA66-457C-8D0B-B552-CB988D51CE4D";
createNode objectSet -n "skinCluster27Set";
	rename -uid "DB6EE6E9-4781-AFB4-7500-0CB9EB9F5AB2";
	setAttr ".ihi" 0;
	setAttr ".vo" yes;
createNode groupId -n "skinCluster27GroupId";
	rename -uid "745B1585-4EA3-7311-CF5A-DFA661A5F003";
	setAttr ".ihi" 0;
createNode groupParts -n "skinCluster27GroupParts";
	rename -uid "AD1D974D-4ED7-462F-07BD-0AAF4F59018F";
	setAttr ".ihi" 0;
	setAttr ".ic" -type "componentList" 1 "vtx[*]";
createNode objectSet -n "tweakSet27";
	rename -uid "A9E1DFE7-4A10-11E5-C96B-90B6C7ED2062";
	setAttr ".ihi" 0;
	setAttr ".vo" yes;
createNode groupId -n "groupId67";
	rename -uid "09125583-46DD-7081-C8A5-FA9D19B5FD8D";
	setAttr ".ihi" 0;
createNode groupParts -n "groupParts56";
	rename -uid "FAD9A4AF-4144-85C3-4AD5-9E95A6DEEFC6";
	setAttr ".ihi" 0;
	setAttr ".ic" -type "componentList" 1 "vtx[*]";
createNode skinCluster -n "skinCluster28";
	rename -uid "5527CA09-40FD-03BC-9259-D594181A58B8";
	setAttr ".ip[0].gtg" -type "string" "";
	setAttr ".skm" 1;
	setAttr -s 8 ".wl";
	setAttr ".wl[0:7].w"
		1 0 1
		1 0 1
		1 0 1
		1 0 1
		1 0 1
		1 0 1
		1 0 1
		1 0 1;
	setAttr ".pm[0]" -type "matrix" 0.99999999922813898 -2.4321874407121769e-05 3.0857228098450656e-05 0
		 2.4278695477406022e-05 0.99999902174375588 0.0013985428403461217 0 -3.0891213095490465e-05 -0.0013985420900933965 0.99999902156239906 0
		 -1.9999999965498416 4.8730059177773389e-05 -0.00012342892277743912 1;
	setAttr ".gm" -type "matrix" 1 0 0 0 0 1 0 0 0 0 1 0 0 0 0 1;
	setAttr ".dpf[0]"  10;
	setAttr ".mmi" yes;
	setAttr ".mi" 1;
	setAttr ".ucm" yes;
createNode tweak -n "tweak28";
	rename -uid "B5292650-4E59-B9E2-C1DA-D4914914837A";
createNode objectSet -n "skinCluster28Set";
	rename -uid "E7510E2C-4D2E-182E-AF03-38AAFEDD85EC";
	setAttr ".ihi" 0;
	setAttr ".vo" yes;
createNode groupId -n "skinCluster28GroupId";
	rename -uid "1C9959E7-4241-0C38-E7BC-A8902F89BC93";
	setAttr ".ihi" 0;
createNode groupParts -n "skinCluster28GroupParts";
	rename -uid "D9F25EB0-4D73-DF62-5B72-95BFC382DE56";
	setAttr ".ihi" 0;
	setAttr ".ic" -type "componentList" 1 "vtx[*]";
createNode objectSet -n "tweakSet28";
	rename -uid "3F8D6C1C-444F-9F68-90F1-78A07B53A906";
	setAttr ".ihi" 0;
	setAttr ".vo" yes;
createNode groupId -n "groupId69";
	rename -uid "9CAE7E49-48BD-997F-7F82-E990B3C0B04C";
	setAttr ".ihi" 0;
createNode groupParts -n "groupParts58";
	rename -uid "E8AAC7EF-4AE8-FC4E-1785-7CB26C7BDC2C";
	setAttr ".ihi" 0;
	setAttr ".ic" -type "componentList" 1 "vtx[*]";
createNode skinCluster -n "skinCluster29";
	rename -uid "670B165E-4621-8CF4-3C61-C59521C6881D";
	setAttr ".ip[0].gtg" -type "string" "";
	setAttr ".skm" 1;
	setAttr -s 8 ".wl";
	setAttr ".wl[0:7].w"
		1 0 1
		1 0 1
		1 0 1
		1 0 1
		1 0 1
		1 0 1
		1 0 1
		1 0 1;
	setAttr ".pm[0]" -type "matrix" 0.99999999922813898 -2.4321874407121769e-05 3.0857228098450656e-05 0
		 2.4278695477406022e-05 0.99999902174375588 0.0013985428403461217 0 -3.0891213095490465e-05 -0.0013985420900933965 0.99999902156239906 0
		 -1.9999999965498416 4.8730059177773389e-05 -0.00012342892277743912 1;
	setAttr ".gm" -type "matrix" 1 0 0 0 0 1 0 0 0 0 1 0 0 0 0 1;
	setAttr ".dpf[0]"  10;
	setAttr ".mmi" yes;
	setAttr ".mi" 1;
	setAttr ".ucm" yes;
createNode tweak -n "tweak29";
	rename -uid "724EF0BB-4511-78D8-6CFB-93998F82F4AA";
createNode objectSet -n "skinCluster29Set";
	rename -uid "E2805E4E-4167-965F-ABC3-BD849A3AE5FC";
	setAttr ".ihi" 0;
	setAttr ".vo" yes;
createNode groupId -n "skinCluster29GroupId";
	rename -uid "3373D457-4B52-EF2E-6E5E-B2B9BF4F5266";
	setAttr ".ihi" 0;
createNode groupParts -n "skinCluster29GroupParts";
	rename -uid "F6E2032E-441A-72D5-FD17-B3ADA89F92A0";
	setAttr ".ihi" 0;
	setAttr ".ic" -type "componentList" 1 "vtx[*]";
createNode objectSet -n "tweakSet29";
	rename -uid "DDE6CE9A-463F-35B7-B532-15A4B9FCBFCC";
	setAttr ".ihi" 0;
	setAttr ".vo" yes;
createNode groupId -n "groupId71";
	rename -uid "98F9A571-46D9-8731-A7B2-4A9FB7891FDB";
	setAttr ".ihi" 0;
createNode groupParts -n "groupParts60";
	rename -uid "D4FA0B4D-4BD2-0E40-D180-48A2AE0E04CA";
	setAttr ".ihi" 0;
	setAttr ".ic" -type "componentList" 1 "vtx[*]";
createNode skinCluster -n "skinCluster30";
	rename -uid "317615A7-4FCD-BE3C-EE9D-3CB0CAB7C107";
	setAttr ".ip[0].gtg" -type "string" "";
	setAttr ".skm" 1;
	setAttr -s 8 ".wl";
	setAttr ".wl[0:7].w"
		1 0 1
		1 0 1
		1 0 1
		1 0 1
		1 0 1
		1 0 1
		1 0 1
		1 0 1;
	setAttr ".pm[0]" -type "matrix" 0.99999999922813898 -2.4321874407121769e-05 3.0857228098450656e-05 0
		 2.4278695477406022e-05 0.99999902174375588 0.0013985428403461217 0 -3.0891213095490465e-05 -0.0013985420900933965 0.99999902156239906 0
		 2.0000000003627143 -4.8557438450713679e-05 -1.0383636509761658e-11 1;
	setAttr ".gm" -type "matrix" 1 0 0 0 0 1 0 0 0 0 1 0 0 0 0 1;
	setAttr ".dpf[0]"  10;
	setAttr ".mmi" yes;
	setAttr ".mi" 1;
	setAttr ".ucm" yes;
createNode tweak -n "tweak30";
	rename -uid "C503C022-42A0-F244-23B4-4180390C7919";
createNode objectSet -n "skinCluster30Set";
	rename -uid "EF8F43D5-455B-47FA-20EB-5780E0B0D7D4";
	setAttr ".ihi" 0;
	setAttr ".vo" yes;
createNode groupId -n "skinCluster30GroupId";
	rename -uid "3B30D45F-40EC-BDA9-AAD3-768727B1F429";
	setAttr ".ihi" 0;
createNode groupParts -n "skinCluster30GroupParts";
	rename -uid "629E7F5D-4226-034D-441C-69BC5CD1E4D7";
	setAttr ".ihi" 0;
	setAttr ".ic" -type "componentList" 1 "vtx[*]";
createNode objectSet -n "tweakSet30";
	rename -uid "FE89B9F2-49B9-2A5E-62BD-A88D9DC50E3C";
	setAttr ".ihi" 0;
	setAttr ".vo" yes;
createNode groupId -n "groupId73";
	rename -uid "07F6CBE0-4CC4-63B0-CF2D-07BC555CDB62";
	setAttr ".ihi" 0;
createNode groupParts -n "groupParts62";
	rename -uid "285FCAE3-4C6F-8E28-6BB5-7D90FD318069";
	setAttr ".ihi" 0;
	setAttr ".ic" -type "componentList" 1 "vtx[*]";
createNode skinCluster -n "skinCluster31";
	rename -uid "45783FFB-49FB-21F5-42BF-F2BD9C6E0B12";
	setAttr ".ip[0].gtg" -type "string" "";
	setAttr ".skm" 1;
	setAttr -s 8 ".wl";
	setAttr ".wl[0:7].w"
		1 0 1
		1 0 1
		1 0 1
		1 0 1
		1 0 1
		1 0 1
		1 0 1
		1 0 1;
	setAttr ".pm[0]" -type "matrix" 0.99999999922813898 -2.4321874407121769e-05 3.0857228098450656e-05 0
		 2.4278695477406022e-05 0.99999902174375588 0.0013985428403461217 0 -3.0891213095490465e-05 -0.0013985420900933965 0.99999902156239906 0
		 2.0000000003627143 -4.8557438450713679e-05 -1.0383636509761658e-11 1;
	setAttr ".gm" -type "matrix" 1 0 0 0 0 1 0 0 0 0 1 0 0 0 0 1;
	setAttr ".dpf[0]"  10;
	setAttr ".mmi" yes;
	setAttr ".mi" 1;
	setAttr ".ucm" yes;
createNode tweak -n "tweak31";
	rename -uid "039C350E-4EFF-BB9E-DF42-F492219F0E92";
createNode objectSet -n "skinCluster31Set";
	rename -uid "03DD5387-42C6-E443-6AC0-6886D3A9C106";
	setAttr ".ihi" 0;
	setAttr ".vo" yes;
createNode groupId -n "skinCluster31GroupId";
	rename -uid "531AB3DF-49FA-952C-766A-0782E496BD45";
	setAttr ".ihi" 0;
createNode groupParts -n "skinCluster31GroupParts";
	rename -uid "B948CBAE-42A4-DD33-5CDD-4385BD05FE52";
	setAttr ".ihi" 0;
	setAttr ".ic" -type "componentList" 1 "vtx[*]";
createNode objectSet -n "tweakSet31";
	rename -uid "B7ADE1F5-4F39-9138-2E5D-8AB24A0F9771";
	setAttr ".ihi" 0;
	setAttr ".vo" yes;
createNode groupId -n "groupId75";
	rename -uid "E059425A-49D9-0AEB-39A5-1D85B65C041E";
	setAttr ".ihi" 0;
createNode groupParts -n "groupParts64";
	rename -uid "42C27E03-47CC-7B19-F7C1-00A748F9D0B3";
	setAttr ".ihi" 0;
	setAttr ".ic" -type "componentList" 1 "vtx[*]";
createNode cluster -n "cluster_headconCluster";
	rename -uid "78690DF6-401E-C530-24B4-3D80AE00B9BF";
	setAttr ".ip[0].gtg" -type "string" "";
	setAttr ".gm[0]" -type "matrix" 1 0 0 0 0 1 0 0 0 0 1 0 0 0 0 1;
createNode tweak -n "tweak32";
	rename -uid "9D04F37E-47B1-5312-4888-D49F9B974236";
createNode objectSet -n "cluster1Set";
	rename -uid "AA633A54-4C2B-C003-FF65-78B4F0C2657D";
	setAttr ".ihi" 0;
	setAttr ".vo" yes;
createNode groupId -n "cluster1GroupId";
	rename -uid "D542319C-4576-659D-D505-CB925375A693";
	setAttr ".ihi" 0;
createNode groupParts -n "cluster1GroupParts";
	rename -uid "E09F5A05-479B-EC7E-AE8B-528B4AC035ED";
	setAttr ".ihi" 0;
	setAttr ".ic" -type "componentList" 1 "cv[0:16]";
createNode objectSet -n "tweakSet32";
	rename -uid "8E57A785-4522-A9AC-439F-36822FF2D657";
	setAttr ".ihi" 0;
	setAttr ".vo" yes;
createNode groupId -n "groupId77";
	rename -uid "C13CE6F9-4F31-87BE-03D7-BEB8C652AEC3";
	setAttr ".ihi" 0;
createNode groupParts -n "groupParts66";
	rename -uid "9635CE49-4530-FDFF-6EE0-21BBB620DDE5";
	setAttr ".ihi" 0;
	setAttr ".ic" -type "componentList" 1 "cv[*]";
createNode cluster -n "cluster_bodyconCluster";
	rename -uid "15A11182-463A-6B3B-9B48-4D81D22EDBAB";
	setAttr ".ip[0].gtg" -type "string" "";
	setAttr ".gm[0]" -type "matrix" 1 0 0 0 0 1 0 0 0 0 1 0 0 0 0 1;
createNode tweak -n "tweak33";
	rename -uid "84262235-4B5E-C368-B756-84B0B1C2AADD";
createNode objectSet -n "cluster2Set";
	rename -uid "7CA4B8CA-4186-3425-BACA-3E898501B91B";
	setAttr ".ihi" 0;
	setAttr ".vo" yes;
createNode groupId -n "cluster2GroupId";
	rename -uid "B0EA10D8-4C2B-49CE-330F-4CBF60DA1AEE";
	setAttr ".ihi" 0;
createNode groupParts -n "cluster2GroupParts";
	rename -uid "70EE3C07-432D-EF5D-2FAE-4385E35F5D4C";
	setAttr ".ihi" 0;
	setAttr ".ic" -type "componentList" 1 "cv[0:16]";
createNode objectSet -n "tweakSet33";
	rename -uid "7CAE6D9B-4FAE-1A29-E8D4-31B65B35C40C";
	setAttr ".ihi" 0;
	setAttr ".vo" yes;
createNode groupId -n "groupId79";
	rename -uid "95DC37BE-4250-368D-C52D-DA8A55FFD02E";
	setAttr ".ihi" 0;
createNode groupParts -n "groupParts68";
	rename -uid "FC780C22-4835-0565-B5BC-8BB6F651E48A";
	setAttr ".ihi" 0;
	setAttr ".ic" -type "componentList" 1 "cv[*]";
createNode cluster -n "cluster_shoulderconCluster";
	rename -uid "AF3E32B6-42F0-C10B-7194-0DB2516F99A1";
	setAttr -s 2 ".ip";
	setAttr ".ip[0].gtg" -type "string" "";
	setAttr ".ip[1].gtg" -type "string" "";
	setAttr -s 2 ".og";
	setAttr -s 2 ".gm";
	setAttr ".gm[0]" -type "matrix" 1 0 0 0 0 1 0 0 0 0 1 0 0 0 0 1;
	setAttr ".gm[1]" -type "matrix" -1 0 0 0 0 1 0 0 0 0 1 0 0 0 0 1;
createNode tweak -n "tweak34";
	rename -uid "1FA3A23E-499F-8707-08BC-E2A18073FD26";
createNode tweak -n "tweak35";
	rename -uid "92110B6B-418E-A02E-5F90-CDB00F224875";
createNode objectSet -n "cluster3Set";
	rename -uid "8020AD63-464F-5EE1-A5D0-058891D8559A";
	setAttr ".ihi" 0;
	setAttr -s 2 ".dsm";
	setAttr ".vo" yes;
	setAttr -s 2 ".gn";
createNode groupId -n "cluster3GroupId";
	rename -uid "B2CE46BD-4326-C7E6-4AE3-7AA1F7DAF6C2";
	setAttr ".ihi" 0;
createNode groupParts -n "cluster3GroupParts";
	rename -uid "DFFD0E89-4712-B078-A589-1A88D840F75B";
	setAttr ".ihi" 0;
	setAttr ".ic" -type "componentList" 1 "cv[0:6]";
createNode groupId -n "cluster3GroupId1";
	rename -uid "2EDA10BD-4525-2219-21B3-6E8094BB85DB";
	setAttr ".ihi" 0;
createNode groupParts -n "cluster3GroupParts1";
	rename -uid "20C982A7-4925-F96A-2091-0687D3645108";
	setAttr ".ihi" 0;
	setAttr ".ic" -type "componentList" 1 "cv[0:6]";
createNode objectSet -n "tweakSet34";
	rename -uid "50705635-4501-5646-B24B-A381FFFC8F12";
	setAttr ".ihi" 0;
	setAttr ".vo" yes;
createNode groupId -n "groupId82";
	rename -uid "7613C2AB-4BF6-2679-2DA5-1E8A7ECAA27C";
	setAttr ".ihi" 0;
createNode groupParts -n "groupParts71";
	rename -uid "78C6003D-45DA-0219-49AE-24AC908E6303";
	setAttr ".ihi" 0;
	setAttr ".ic" -type "componentList" 1 "cv[*]";
createNode objectSet -n "tweakSet35";
	rename -uid "C07E3520-4C24-EF4D-4771-67BC8FB55AAC";
	setAttr ".ihi" 0;
	setAttr ".vo" yes;
createNode groupId -n "groupId83";
	rename -uid "A509A223-4FF7-9922-C296-2FA66BD3F742";
	setAttr ".ihi" 0;
createNode groupParts -n "groupParts72";
	rename -uid "03989A78-4674-6A1A-22E8-77AF248800B6";
	setAttr ".ihi" 0;
	setAttr ".ic" -type "componentList" 1 "cv[*]";
createNode cluster -n "cluster_legikcon_rCluster";
	rename -uid "DFE46ECE-40B0-8911-12E3-CDAD1E52F896";
	setAttr ".ip[0].gtg" -type "string" "";
	setAttr ".gm[0]" -type "matrix" 1 0 0 0 0 1 0 0 0 0 1 0 0 0 0 1;
createNode tweak -n "tweak36";
	rename -uid "07E789CE-4AD4-6930-3754-6A8FAD246E0A";
createNode objectSet -n "cluster4Set";
	rename -uid "FFAE764E-4B65-8E53-3E7B-3CB678D0551B";
	setAttr ".ihi" 0;
	setAttr ".vo" yes;
createNode groupId -n "cluster4GroupId";
	rename -uid "EFB76B04-4EAA-530A-769D-6BBA2E6A852F";
	setAttr ".ihi" 0;
createNode groupParts -n "cluster4GroupParts";
	rename -uid "6163797A-4A8E-C601-6EE4-71830382370C";
	setAttr ".ihi" 0;
	setAttr ".ic" -type "componentList" 1 "cv[0:12]";
createNode objectSet -n "tweakSet36";
	rename -uid "71A4FC22-44F7-1F10-3F5F-D89D74258A2E";
	setAttr ".ihi" 0;
	setAttr ".vo" yes;
createNode groupId -n "groupId85";
	rename -uid "5B5B6C3D-46B5-0BA6-41F9-DC83111D07DA";
	setAttr ".ihi" 0;
createNode groupParts -n "groupParts74";
	rename -uid "78332637-4E76-D704-68E8-B79E92C45111";
	setAttr ".ihi" 0;
	setAttr ".ic" -type "componentList" 1 "cv[*]";
createNode cluster -n "cluster_legikcon_lCluster";
	rename -uid "F2EBC72E-4BEC-3E20-9E9B-8C98F90D1C61";
	setAttr ".ip[0].gtg" -type "string" "";
	setAttr ".gm[0]" -type "matrix" 1 0 0 0 0 1 0 0 0 0 1 0 4 0 0 1;
createNode tweak -n "tweak37";
	rename -uid "84A02C6B-4319-C5A2-CED9-CA9E49D71AF4";
createNode objectSet -n "cluster5Set";
	rename -uid "8F1D71DD-495A-55BD-D6BF-4DA18F373161";
	setAttr ".ihi" 0;
	setAttr ".vo" yes;
createNode groupId -n "cluster5GroupId";
	rename -uid "1C4EDBEF-49A0-2E85-FC7D-82922092DADC";
	setAttr ".ihi" 0;
createNode groupParts -n "cluster5GroupParts";
	rename -uid "B85AE0F9-44AD-A65B-B04E-9E8E25E2694E";
	setAttr ".ihi" 0;
	setAttr ".ic" -type "componentList" 1 "cv[0:12]";
createNode objectSet -n "tweakSet37";
	rename -uid "7ED76F82-4002-254A-0636-9AB473A1E849";
	setAttr ".ihi" 0;
	setAttr ".vo" yes;
createNode groupId -n "groupId87";
	rename -uid "E6B87A43-4C64-8D70-74D3-B58594FE3033";
	setAttr ".ihi" 0;
createNode groupParts -n "groupParts76";
	rename -uid "93C1B49A-4979-9F89-A5CD-1699F5A78CBF";
	setAttr ".ihi" 0;
	setAttr ".ic" -type "componentList" 1 "cv[*]";
createNode cluster -n "cluster_armfkcon_rCluster";
	rename -uid "E6B586F0-436C-BC1C-364F-18897BB01342";
	setAttr ".ip[0].gtg" -type "string" "";
	setAttr ".gm[0]" -type "matrix" -1 0 -1.2246467991473532e-16 0 0 1 0 0 1.2246467991473532e-16 0 -1 0
		 -6 22 9.7971743931788197e-16 1;
createNode tweak -n "tweak38";
	rename -uid "1457A2E4-4AA8-F128-EA87-C79652DAD2CF";
createNode objectSet -n "cluster6Set";
	rename -uid "844FF0B7-4D31-AE47-5CB8-AEA6827238A8";
	setAttr ".ihi" 0;
	setAttr ".vo" yes;
createNode groupId -n "cluster6GroupId";
	rename -uid "646C8370-468E-E6B8-2E4F-54A862E5F946";
	setAttr ".ihi" 0;
createNode groupParts -n "cluster6GroupParts";
	rename -uid "50E561B8-4B81-F04C-5B00-E8B0C730A457";
	setAttr ".ihi" 0;
	setAttr ".ic" -type "componentList" 1 "cv[0:23]";
createNode objectSet -n "tweakSet38";
	rename -uid "AE98087C-4D54-56CD-D8F9-F8962CAC9FBD";
	setAttr ".ihi" 0;
	setAttr ".vo" yes;
createNode groupId -n "groupId89";
	rename -uid "985D1CC5-4902-5EA8-F75C-60A067C5C8D1";
	setAttr ".ihi" 0;
createNode groupParts -n "groupParts78";
	rename -uid "F198B4E1-4D1B-8B8D-734C-8B96C301F927";
	setAttr ".ihi" 0;
	setAttr ".ic" -type "componentList" 1 "cv[*]";
createNode cluster -n "cluster_armfkcon_lCluster";
	rename -uid "D8803F1F-462B-579B-F762-71AB0F83B83A";
	setAttr ".ip[0].gtg" -type "string" "";
	setAttr ".gm[0]" -type "matrix" 1 0 0 0 0 1 0 0 0 0 1 0 6 22 0 1;
createNode tweak -n "tweak39";
	rename -uid "79F30315-468E-5C1B-8C21-08AE730C9500";
createNode objectSet -n "cluster7Set";
	rename -uid "C3F6EFE7-4AC2-47F2-F05A-FF856BCFBB8D";
	setAttr ".ihi" 0;
	setAttr ".vo" yes;
createNode groupId -n "cluster7GroupId";
	rename -uid "35824CA3-4337-3E0C-CF0B-DCA5CD4EF206";
	setAttr ".ihi" 0;
createNode groupParts -n "cluster7GroupParts";
	rename -uid "B550B388-488A-F4BB-B37A-0ABF91E6F95D";
	setAttr ".ihi" 0;
	setAttr ".ic" -type "componentList" 1 "cv[0:23]";
createNode objectSet -n "tweakSet39";
	rename -uid "94203333-4DA4-5144-46A5-4CAE3EDCBCD4";
	setAttr ".ihi" 0;
	setAttr ".vo" yes;
createNode groupId -n "groupId91";
	rename -uid "48646CC8-4B57-76BD-3EBE-65A9FF70B078";
	setAttr ".ihi" 0;
createNode groupParts -n "groupParts80";
	rename -uid "76E35BDF-4617-F932-6322-38A5CCA2B022";
	setAttr ".ihi" 0;
	setAttr ".ic" -type "componentList" 1 "cv[*]";
createNode cluster -n "cluster_legfkcon_lCluster";
	rename -uid "95498F22-4C98-D895-E7A2-A4A53495BCCD";
	setAttr ".ip[0].gtg" -type "string" "";
	setAttr ".gm[0]" -type "matrix" 1 0 0 0 0 1 0 0 0 0 1 0 2 0 0 1;
createNode tweak -n "tweak40";
	rename -uid "31CDD9B1-4DA8-B153-AE85-868B7CB414C8";
createNode objectSet -n "cluster8Set";
	rename -uid "7F8974CF-4EDE-47D6-31C2-8BACFAE367F1";
	setAttr ".ihi" 0;
	setAttr ".vo" yes;
createNode groupId -n "cluster8GroupId";
	rename -uid "3045D4F9-4885-9F26-0AD2-FF963659F6A0";
	setAttr ".ihi" 0;
createNode groupParts -n "cluster8GroupParts";
	rename -uid "8BED875B-4C21-EDFD-4B75-2787860E3B8E";
	setAttr ".ihi" 0;
	setAttr ".ic" -type "componentList" 1 "cv[0:23]";
createNode objectSet -n "tweakSet40";
	rename -uid "FEE12C3D-4BC4-808E-D797-E4BAB5DBAC32";
	setAttr ".ihi" 0;
	setAttr ".vo" yes;
createNode groupId -n "groupId93";
	rename -uid "1F326FDE-456B-51CE-B0D4-D98C5B36660D";
	setAttr ".ihi" 0;
createNode groupParts -n "groupParts82";
	rename -uid "6D0C4D87-4F44-52E2-DB48-A1993A715AF3";
	setAttr ".ihi" 0;
	setAttr ".ic" -type "componentList" 1 "cv[*]";
createNode cluster -n "cluster_legfkcon_rCluster";
	rename -uid "60F78BFA-4B8E-5AE2-C35A-BCAD3B3AFA46";
	setAttr ".ip[0].gtg" -type "string" "";
	setAttr ".gm[0]" -type "matrix" 1 0 0 0 0 1 0 0 0 0 1 0 -2 0 0 1;
createNode tweak -n "tweak41";
	rename -uid "62F3838A-4AC6-9C4B-C1E3-E6ABC3CD8292";
createNode objectSet -n "cluster9Set";
	rename -uid "904A37FD-433C-74FD-82C7-7C81AA052653";
	setAttr ".ihi" 0;
	setAttr ".vo" yes;
createNode groupId -n "cluster9GroupId";
	rename -uid "B1471D5D-400D-D8F4-B693-E4B369117045";
	setAttr ".ihi" 0;
createNode groupParts -n "cluster9GroupParts";
	rename -uid "1CA34119-4933-E344-BD01-9A9ACF09838E";
	setAttr ".ihi" 0;
	setAttr ".ic" -type "componentList" 1 "cv[0:23]";
createNode objectSet -n "tweakSet41";
	rename -uid "A4392C58-41DC-D2D9-7293-C0BEA7A5D93E";
	setAttr ".ihi" 0;
	setAttr ".vo" yes;
createNode groupId -n "groupId95";
	rename -uid "A899B1A3-4D23-A6E0-1D72-D4923B6C9F8E";
	setAttr ".ihi" 0;
createNode groupParts -n "groupParts84";
	rename -uid "C256F830-424A-3629-1711-33A99F872820";
	setAttr ".ihi" 0;
	setAttr ".ic" -type "componentList" 1 "cv[*]";
createNode polyTweakUV -n "polyTweakUV3";
	rename -uid "E3911426-4742-3CFD-ED2C-0AA481C10A5A";
	setAttr ".uopa" yes;
	setAttr -s 24 ".uvtk[0:23]" -type "float2" 0 0.625 0 0.625 0 0.625 0
		 0.625 0 0.625 0 0.625 0 0.625 0 0.625 0 0.625 0 0.625 0 0.625 0 0.625 0 0.625 0 0.625
		 0 0.625 0 0.625 0 0.625 0 0.625 0 0.625 0 0.625 0 0.625 0 0.625 0 0.625 0 0.625;
createNode polyTweakUV -n "polyTweakUV4";
	rename -uid "0870E853-444A-3F98-89B1-CE929134B56E";
	setAttr ".uopa" yes;
	setAttr -s 24 ".uvtk[0:23]" -type "float2" 0 0.625 0 0.625 0 0.625 0
		 0.625 0 0.625 0 0.625 0 0.625 0 0.625 0 0.625 0 0.625 0 0.625 0 0.625 0 0.625 0 0.625
		 0 0.625 0 0.625 0 0.625 0 0.625 0 0.625 0 0.625 0 0.625 0 0.625 0 0.625 0 0.625;
createNode polyTweakUV -n "polyTweakUV5";
	rename -uid "01C2D097-44B7-4684-F85F-8191FDEBE974";
	setAttr ".uopa" yes;
	setAttr -s 24 ".uvtk[0:23]" -type "float2" 0 0.625 0 0.625 0 0.625 0
		 0.625 0 0.625 0 0.625 0 0.625 0 0.625 0 0.625 0 0.625 0 0.625 0 0.625 0 0.625 0 0.625
		 0 0.625 0 0.625 0 0.625 0 0.625 0 0.625 0 0.625 0 0.625 0 0.625 0 0.625 0 0.625;
createNode polyMoveUV -n "polyMoveUV1";
	rename -uid "984B672D-4134-A321-DA3E-36A75CB45F73";
	setAttr ".uopa" yes;
	setAttr ".ics" -type "componentList" 1 "map[0:23]";
	setAttr ".pvt" -type "double2" 0.25 0.96875 ;
	setAttr ".rs" 2056;
	setAttr ".l" -type "double2" 0.0625 0.0625 ;
createNode cluster -n "uvCluster_chestplate_lCluster";
	rename -uid "0A0DA96B-45C1-B177-F85D-088F4A7D8B8A";
createNode objectSet -n "uvClusterSet";
	rename -uid "B70BD986-44CF-2CF6-E9C5-67BED6D4CCC9";
	setAttr ".ihi" 0;
	setAttr ".vo" yes;
createNode polyMoveUV -n "polyMoveUV2";
	rename -uid "FAA03287-4991-1FE3-2436-58A148A40860";
	setAttr ".uopa" yes;
	setAttr ".ics" -type "componentList" 1 "map[0:23]";
	setAttr ".pvt" -type "double2" 0.25 0.96875 ;
	setAttr ".rs" 26223;
	setAttr ".l" -type "double2" 0.0625 0.0625 ;
createNode cluster -n "uvCluster_chestplate_rCluster";
	rename -uid "E67F2F0C-4CEE-516C-AFD0-58ADAB83D2F2";
createNode objectSet -n "uvCluster1Set";
	rename -uid "71178EAD-41F8-7101-1E4D-F793216E8871";
	setAttr ".ihi" 0;
	setAttr ".vo" yes;
createNode polyMoveUV -n "polyMoveUV3";
	rename -uid "0B56577F-477E-7208-F361-158D4532A1E4";
	setAttr ".uopa" yes;
	setAttr ".ics" -type "componentList" 1 "map[0:23]";
	setAttr ".pvt" -type "double2" 0.171875 0.96875 ;
	setAttr ".rs" 11861;
	setAttr ".l" -type "double2" 0.09375 0.09375 ;
createNode cluster -n "uvCluster_chestplace_mCluster";
	rename -uid "CA9FA10D-4719-A1A1-5B3D-2CB16F500566";
createNode objectSet -n "uvCluster2Set";
	rename -uid "6909731B-4A4E-2B3E-CD15-A7A246794A7B";
	setAttr ".ihi" 0;
	setAttr ".vo" yes;
createNode floatMath -n "floatMath54";
	rename -uid "EF943DB5-4EF6-A86C-CDBE-4A9656E1B441";
	setAttr "._fb" -16;
	setAttr "._cnd" 3;
createNode condition -n "condition35";
	rename -uid "1BA47434-43F3-E9B3-B7E2-68A38BD5F611";
	setAttr ".op" 5;
	setAttr ".st" -1;
	setAttr ".ct" -type "float3" 0.5 0.5 0.5 ;
	setAttr ".cf" -type "float3" 0 0 0 ;
createNode polyTweakUV -n "polyTweakUV6";
	rename -uid "8DD0D38D-42D7-D142-2DF4-DCA1144649BA";
	setAttr ".uopa" yes;
	setAttr -s 24 ".uvtk[0:23]" -type "float2" 0 0.5 0 0.5 0 0.5 0 0.5 0
		 0.5 0 0.5 0 0.5 0 0.5 0 0.5 0 0.5 0 0.5 0 0.5 0 0.5 0 0.5 0 0.5 0 0.5 0 0.5 0 0.5
		 0 0.5 0 0.5 0 0.5 0 0.5 0 0.5 0 0.5;
createNode polyMoveUV -n "polyMoveUV4";
	rename -uid "84E99357-46AC-A8C0-2117-68A0FF4356A3";
	setAttr ".uopa" yes;
	setAttr ".ics" -type "componentList" 1 "map[0:23]";
	setAttr ".pvt" -type "double2" 0.0625 0.96875 ;
	setAttr ".rs" 4224;
	setAttr ".l" -type "double2" 0.125 0.125 ;
createNode cluster -n "uvCluster_helmetCluster";
	rename -uid "F83900B2-4712-663C-D524-3BBDC302C645";
createNode objectSet -n "uvClusterSet1";
	rename -uid "6CB47A7E-445D-FAE0-B828-8188F926D715";
	setAttr ".ihi" 0;
	setAttr ".vo" yes;
createNode floatMath -n "floatMath55";
	rename -uid "13B84BD9-4174-B67B-D4B6-C0938715A670";
	setAttr "._fb" -16;
	setAttr "._cnd" 3;
createNode condition -n "condition36";
	rename -uid "4500D62A-4F90-A419-0B0A-A7BE02D870A0";
	setAttr ".op" 5;
	setAttr ".st" -1;
	setAttr ".ct" -type "float3" 0.5 0.5 0.5 ;
	setAttr ".cf" -type "float3" 0 0 0 ;
createNode floatMath -n "floatMath56";
	rename -uid "76EFE279-4A35-F12A-EB46-5FA9EFC0A132";
	setAttr "._fb" -1;
createNode floatMath -n "floatMath57";
	rename -uid "4EBF42DE-4C0D-8940-0634-05969F5451BE";
	setAttr "._fb" -1;
createNode polyTweakUV -n "polyTweakUV7";
	rename -uid "EF7DF7B2-46CB-B872-E2B3-FA8CD34D1333";
	setAttr ".uopa" yes;
	setAttr -s 24 ".uvtk[0:23]" -type "float2" 0 0.6875 0 0.6875 0 0.6875
		 0 0.6875 0 0.6875 0 0.6875 0 0.6875 0 0.6875 0 0.6875 0 0.6875 0 0.6875 0 0.6875
		 0 0.6875 0 0.6875 0 0.6875 0 0.6875 0 0.6875 0 0.6875 0 0.6875 0 0.6875 0 0.6875
		 0 0.6875 0 0.6875 0 0.6875;
createNode polyTweakUV -n "polyTweakUV8";
	rename -uid "7466DABB-4EE3-954F-59B3-6681AAE36DEE";
	setAttr ".uopa" yes;
	setAttr -s 24 ".uvtk[0:23]" -type "float2" 0 0.6875 0 0.6875 0 0.6875
		 0 0.6875 0 0.6875 0 0.6875 0 0.6875 0 0.6875 0 0.6875 0 0.6875 0 0.6875 0 0.6875
		 0 0.6875 0 0.6875 0 0.6875 0 0.6875 0 0.6875 0 0.6875 0 0.6875 0 0.6875 0 0.6875
		 0 0.6875 0 0.6875 0 0.6875;
createNode polyTweakUV -n "polyTweakUV9";
	rename -uid "F264489F-4EFF-5E5A-C31B-989064AF1559";
	setAttr ".uopa" yes;
	setAttr -s 24 ".uvtk[0:23]" -type "float2" 0 0.6875 0 0.6875 0 0.6875
		 0 0.6875 0 0.6875 0 0.6875 0 0.6875 0 0.6875 0 0.6875 0 0.6875 0 0.6875 0 0.6875
		 0 0.6875 0 0.6875 0 0.6875 0 0.6875 0 0.6875 0 0.6875 0 0.6875 0 0.6875 0 0.6875
		 0 0.6875 0 0.6875 0 0.6875;
createNode polyMoveUV -n "polyMoveUV5";
	rename -uid "81A15F75-42BB-FBA9-606A-9D9ED82F19BD";
	setAttr ".uopa" yes;
	setAttr ".ics" -type "componentList" 1 "map[0:23]";
	setAttr ".pvt" -type "double2" 0.3125 0.96875 ;
	setAttr ".rs" 27922;
	setAttr ".l" -type "double2" 0.0625 0.0625 ;
createNode cluster -n "uvCluster_leggings_rCluster";
	rename -uid "C43CF3EB-438E-59ED-F715-E48DB65F3A59";
createNode objectSet -n "uvClusterSet2";
	rename -uid "6F4D5FD4-4CA8-6EA4-496C-D6922E67F11C";
	setAttr ".ihi" 0;
	setAttr ".vo" yes;
createNode polyMoveUV -n "polyMoveUV6";
	rename -uid "4B013716-4439-CC14-9A8C-03BED40E7A5A";
	setAttr ".uopa" yes;
	setAttr ".ics" -type "componentList" 1 "map[0:23]";
	setAttr ".pvt" -type "double2" 0.3125 0.96875 ;
	setAttr ".rs" 22333;
	setAttr ".l" -type "double2" 0.0625 0.0625 ;
createNode cluster -n "uvCluster_leggings_lCluster";
	rename -uid "F41AFDB1-4CC2-11E7-9A03-928DE111B7D9";
createNode objectSet -n "uvCluster3Set";
	rename -uid "BCB40680-4E84-C8C5-5C3A-9586AE0F2B61";
	setAttr ".ihi" 0;
	setAttr ".vo" yes;
createNode polyMoveUV -n "polyMoveUV7";
	rename -uid "E3D93D80-4BB2-9FD9-0555-DA8348DE1922";
	setAttr ".uopa" yes;
	setAttr ".ics" -type "componentList" 1 "map[0:23]";
	setAttr ".pvt" -type "double2" 0.390625 0.96875 ;
	setAttr ".rs" 26087;
	setAttr ".l" -type "double2" 0.09375 0.09375 ;
createNode cluster -n "uvCluster_leggings_mCluster";
	rename -uid "B9C50C86-4BE5-68EE-6C64-129AF274B8EA";
createNode objectSet -n "uvCluster4Set";
	rename -uid "05B7F230-49EA-3E3F-43AF-54A5F430A661";
	setAttr ".ihi" 0;
	setAttr ".vo" yes;
createNode condition -n "condition37";
	rename -uid "EE87F594-4C9B-57FF-7D6F-2AA68E7A9CF6";
	setAttr ".op" 5;
	setAttr ".st" -1;
	setAttr ".ct" -type "float3" 0.5 0.5 0.5 ;
	setAttr ".cf" -type "float3" 0 0 0 ;
createNode floatMath -n "floatMath58";
	rename -uid "239A1F5D-41EC-DDFF-C50F-8A82BBB1CE37";
	setAttr "._fb" -16;
	setAttr "._cnd" 3;
createNode floatMath -n "floatMath59";
	rename -uid "97C4365B-4994-F436-771A-45A1B513EE46";
	setAttr "._fb" -1;
createNode polyTweakUV -n "polyTweakUV10";
	rename -uid "98D72451-4A2E-D6E7-B189-D7BC2321871E";
	setAttr ".uopa" yes;
	setAttr -s 24 ".uvtk[0:23]" -type "float2" 0 0.625 0 0.625 0 0.625 0
		 0.625 0 0.625 0 0.625 0 0.625 0 0.625 0 0.625 0 0.625 0 0.625 0 0.625 0 0.625 0 0.625
		 0 0.625 0 0.625 0 0.625 0 0.625 0 0.625 0 0.625 0 0.625 0 0.625 0 0.625 0 0.625;
createNode polyTweakUV -n "polyTweakUV11";
	rename -uid "A926828E-4D47-1D12-3EB7-F399E168391E";
	setAttr ".uopa" yes;
	setAttr -s 24 ".uvtk[0:23]" -type "float2" 0 0.625 0 0.625 0 0.625 0
		 0.625 0 0.625 0 0.625 0 0.625 0 0.625 0 0.625 0 0.625 0 0.625 0 0.625 0 0.625 0 0.625
		 0 0.625 0 0.625 0 0.625 0 0.625 0 0.625 0 0.625 0 0.625 0 0.625 0 0.625 0 0.625;
createNode polyMoveUV -n "polyMoveUV8";
	rename -uid "FC1981AE-4CE5-E00D-19A9-D1871696957C";
	setAttr ".uopa" yes;
	setAttr ".ics" -type "componentList" 1 "map[0:23]";
	setAttr ".pvt" -type "double2" 0.46875 0.96875 ;
	setAttr ".rs" 11474;
	setAttr ".l" -type "double2" 0.0625 0.0625 ;
createNode cluster -n "uvCluster_boots_lCluster";
	rename -uid "736769AF-447C-083F-6106-3289BD07D8F3";
createNode objectSet -n "uvClusterSet3";
	rename -uid "1DC8D62F-4DA1-3C6F-3083-01ABE985470F";
	setAttr ".ihi" 0;
	setAttr ".vo" yes;
createNode polyMoveUV -n "polyMoveUV9";
	rename -uid "EEC2DA54-4352-D6C9-5A06-3EADAB69FA78";
	setAttr ".uopa" yes;
	setAttr ".ics" -type "componentList" 1 "map[0:23]";
	setAttr ".pvt" -type "double2" 0.46875 0.96875 ;
	setAttr ".rs" 6229;
	setAttr ".l" -type "double2" 0.0625 0.0625 ;
createNode cluster -n "uvCluster_boots_rCluster";
	rename -uid "596503B7-4498-CCA0-5639-478FD764374B";
createNode objectSet -n "uvCluster5Set";
	rename -uid "9AF00A66-42E0-147F-9C08-489AFC1A2EBF";
	setAttr ".ihi" 0;
	setAttr ".vo" yes;
createNode floatMath -n "floatMath60";
	rename -uid "F98A8BF5-4615-FAF6-7A15-139B4FB68E39";
	setAttr "._fb" -1;
createNode floatMath -n "floatMath61";
	rename -uid "D65BCF7B-4D6D-6BEC-9196-C4973F0B2D3C";
	setAttr "._fb" -16;
	setAttr "._cnd" 3;
createNode condition -n "condition38";
	rename -uid "5BEDC0A9-432B-96BD-0D3B-83A5440D49E4";
	setAttr ".op" 5;
	setAttr ".st" -1;
	setAttr ".ct" -type "float3" 0.5 0.5 0.5 ;
	setAttr ".cf" -type "float3" 0 0 0 ;
createNode condition -n "condition39";
	rename -uid "2499507F-4E53-C476-D281-0A9FF4045A63";
	setAttr ".op" 1;
	setAttr ".ct" -type "float3" 1.4 1.4 1.4 ;
createNode condition -n "condition40";
	rename -uid "CA2BD6C8-4D7B-F397-5908-ECAB1A4E3A3C";
	setAttr ".op" 1;
	setAttr ".ct" -type "float3" 1.2 1.3 1.3 ;
createNode condition -n "condition41";
	rename -uid "50C700BE-4883-A538-EDED-E494E2BBC13C";
	setAttr ".op" 1;
	setAttr ".ct" -type "float3" 0 0 0.80000001 ;
	setAttr ".cf" -type "float3" 0 0 0 ;
createNode condition -n "condition42";
	rename -uid "CE8AD7E2-4E75-4635-2078-C1970422A44A";
	setAttr ".op" 1;
	setAttr ".ct" -type "float3" 0 0 0.80000001 ;
	setAttr ".cf" -type "float3" 0 0 0 ;
createNode cluster -n "cluster_legtopconCluster";
	rename -uid "3E71AE8A-4443-D141-EF8A-9FAC6E42FB27";
	setAttr -s 2 ".ip";
	setAttr ".ip[0].gtg" -type "string" "";
	setAttr ".ip[1].gtg" -type "string" "";
	setAttr -s 2 ".og";
	setAttr -s 2 ".gm";
	setAttr ".gm[0]" -type "matrix" 1 0 0 0 0 1 0 0 0 0 1 0 0 0 0 1;
	setAttr ".gm[1]" -type "matrix" 1 0 0 0 0 1 0 0 0 0 1 0 4 0 0 1;
createNode tweak -n "tweak42";
	rename -uid "8A6941F1-45D9-39CC-BE92-AD849A6320B2";
createNode tweak -n "tweak43";
	rename -uid "CE11DBEA-4C99-7317-2477-18A8B97FBBE2";
createNode objectSet -n "cluster10Set";
	rename -uid "F0C382AA-428D-B9E6-3263-23A58AAC18E9";
	setAttr ".ihi" 0;
	setAttr -s 2 ".dsm";
	setAttr ".vo" yes;
	setAttr -s 2 ".gn";
createNode groupId -n "cluster10GroupId";
	rename -uid "5066FB27-4D76-2AF0-1F00-D889EC3C61B6";
	setAttr ".ihi" 0;
createNode groupParts -n "cluster10GroupParts";
	rename -uid "E297915E-422A-7F97-7198-48A3A8B896A9";
	setAttr ".ihi" 0;
	setAttr ".ic" -type "componentList" 1 "cv[0:6]";
createNode groupId -n "cluster10GroupId1";
	rename -uid "FDE990E1-4F0B-A45D-FBED-30BEAAC051D1";
	setAttr ".ihi" 0;
createNode groupParts -n "cluster10GroupParts1";
	rename -uid "06CD106D-4538-9589-C337-29A6DA94048A";
	setAttr ".ihi" 0;
	setAttr ".ic" -type "componentList" 1 "cv[0:6]";
createNode objectSet -n "tweakSet42";
	rename -uid "96935BE1-4256-C028-5F29-3DADFBECEB13";
	setAttr ".ihi" 0;
	setAttr ".vo" yes;
createNode groupId -n "groupId98";
	rename -uid "E8DAAEF2-4CE2-829A-D53B-2C9AC85FA325";
	setAttr ".ihi" 0;
createNode groupParts -n "groupParts87";
	rename -uid "0AE1470A-4F79-8F4E-9FA1-CE879A3F14D3";
	setAttr ".ihi" 0;
	setAttr ".ic" -type "componentList" 1 "cv[*]";
createNode objectSet -n "tweakSet43";
	rename -uid "C1625C8C-4506-1690-8028-3D923B8E3EFF";
	setAttr ".ihi" 0;
	setAttr ".vo" yes;
createNode groupId -n "groupId99";
	rename -uid "485DC227-486F-FA59-3DE8-66AF0F58CC23";
	setAttr ".ihi" 0;
createNode groupParts -n "groupParts88";
	rename -uid "E1BED685-479A-64E1-3119-9DBDCB34FC28";
	setAttr ".ihi" 0;
	setAttr ".ic" -type "componentList" 1 "cv[*]";
createNode condition -n "condition43";
	rename -uid "E8A8B8FA-4B70-F298-2305-018698F4F9D5";
	setAttr ".op" 1;
	setAttr ".ct" -type "float3" 1.2 1.3 1.3 ;
createNode condition -n "condition44";
	rename -uid "3DBCFC47-40B2-AD7C-3606-389050D6AF8E";
	setAttr ".op" 1;
	setAttr ".ct" -type "float3" 1.15 1.15 1.15 ;
createNode shadingEngine -n "aiStandardSurface3SG";
	rename -uid "F338251B-4BC2-48D5-F6D6-03BB11EFD3AB";
	setAttr ".ihi" 0;
	setAttr ".ro" yes;
createNode materialInfo -n "materialInfo7";
	rename -uid "5C569DED-4CEC-609D-EC2C-9BA4C17E15BD";
createNode ramp -n "ramp1";
	rename -uid "2E012646-441D-D166-8930-D493801C2189";
	setAttr -s 3 ".cel";
	setAttr ".cel[0].ep" 0.67052024602890015;
	setAttr ".cel[0].ec" -type "float3" 0 0 0 ;
	setAttr ".cel[1].ep" 0.29768785834312439;
	setAttr ".cel[1].ec" -type "float3" 0.19580001 0.61750001 1 ;
	setAttr ".cel[2].ep" 0.11560693383216858;
	setAttr ".cel[2].ec" -type "float3" 1 1 1 ;
createNode place2dTexture -n "place2dTexture1";
	rename -uid "11C25A41-472D-A985-66F9-52BC7A850926";
createNode condition -n "condition45";
	rename -uid "C2A9DE53-4A05-CDFC-37F9-119FF5F30E4E";
	setAttr ".op" 1;
createNode skinCluster -n "skinCluster33";
	rename -uid "EC03F6FD-40EA-A141-2513-B7A47DA82E98";
	setAttr ".ip[0].gtg" -type "string" "";
	setAttr ".skm" 1;
	setAttr -s 8 ".wl";
	setAttr ".wl[0:7].w"
		1 0 1
		1 0 1
		1 0 1
		1 0 1
		1 0 1
		1 0 1
		1 0 1
		1 0 1;
	setAttr ".pm[0]" -type "matrix" -1 0 1.2246467991473532e-16 0 0 1 0 0 -1.2246467991473532e-16 0 -1 0
		 -6 -22 1.7145055615565462e-15 1;
	setAttr ".gm" -type "matrix" 1 0 0 0 0 1 0 0 0 0 1 0 0 0 0 1;
	setAttr ".dpf[0]"  10;
	setAttr ".mmi" yes;
	setAttr ".mi" 1;
	setAttr ".ucm" yes;
createNode tweak -n "tweak45";
	rename -uid "460E1D8F-4172-8B45-A330-7CB16F038812";
createNode objectSet -n "skinCluster33Set";
	rename -uid "2F1292E0-493E-2E7F-5CA0-1089A6D037EA";
	setAttr ".ihi" 0;
	setAttr ".vo" yes;
createNode groupId -n "skinCluster33GroupId";
	rename -uid "0553DD93-435E-E76B-D659-968C1296B278";
	setAttr ".ihi" 0;
createNode groupParts -n "skinCluster33GroupParts";
	rename -uid "643CACA4-4E9D-4782-BD1F-DFAF90D5C6D1";
	setAttr ".ihi" 0;
	setAttr ".ic" -type "componentList" 1 "vtx[*]";
createNode objectSet -n "tweakSet45";
	rename -uid "3B39A2C0-46ED-C1AD-2B7A-3CBE6FF0D3C5";
	setAttr ".ihi" 0;
	setAttr ".vo" yes;
createNode groupId -n "groupId103";
	rename -uid "18A4D19B-4535-AF9F-399B-30A497430BAA";
	setAttr ".ihi" 0;
createNode groupParts -n "groupParts92";
	rename -uid "C9A186E7-4C33-689C-94B4-FD8966BAA3AF";
	setAttr ".ihi" 0;
	setAttr ".ic" -type "componentList" 1 "vtx[*]";
createNode skinCluster -n "skinCluster34";
	rename -uid "E5FD2D82-45C2-CD04-FFF4-06ADABF90570";
	setAttr ".ip[0].gtg" -type "string" "";
	setAttr ".skm" 1;
	setAttr -s 8 ".wl";
	setAttr ".wl[0:7].w"
		1 0 1
		1 0 1
		1 0 1
		1 0 1
		1 0 1
		1 0 1
		1 0 1
		1 0 1;
	setAttr ".pm[0]" -type "matrix" -1 0 1.2246467991473532e-16 0 0 1 0 0 -1.2246467991473532e-16 0 -1 0
		 -6 -22 1.7145055615565462e-15 1;
	setAttr ".gm" -type "matrix" 1 0 0 0 0 1 0 0 0 0 1 0 0 0 0 1;
	setAttr ".dpf[0]"  10;
	setAttr ".mmi" yes;
	setAttr ".mi" 1;
	setAttr ".ucm" yes;
createNode tweak -n "tweak46";
	rename -uid "8CFC8888-4207-3551-89EC-3EBA5624D558";
createNode objectSet -n "skinCluster34Set";
	rename -uid "91FCD0E0-45F2-6B84-BCF4-21B8DB4891FF";
	setAttr ".ihi" 0;
	setAttr ".vo" yes;
createNode groupId -n "skinCluster34GroupId";
	rename -uid "FB0D8512-47C0-ED1F-241F-CEB7C99C8FF8";
	setAttr ".ihi" 0;
createNode groupParts -n "skinCluster34GroupParts";
	rename -uid "91A9E6DB-4F8B-E022-9D82-E1AB5DE70481";
	setAttr ".ihi" 0;
	setAttr ".ic" -type "componentList" 1 "vtx[*]";
createNode objectSet -n "tweakSet46";
	rename -uid "F316B5A3-462F-9DCC-4CF2-FE9FEAF7FB28";
	setAttr ".ihi" 0;
	setAttr ".vo" yes;
createNode groupId -n "groupId105";
	rename -uid "570145BE-40F8-7518-9FFF-62970DAA9C68";
	setAttr ".ihi" 0;
createNode groupParts -n "groupParts94";
	rename -uid "770AEB70-4E45-CA3E-4551-E9B2C723E84E";
	setAttr ".ihi" 0;
	setAttr ".ic" -type "componentList" 1 "vtx[*]";
createNode skinCluster -n "skinCluster35";
	rename -uid "B686FF91-478A-469F-F04A-E3A104A3DDB2";
	setAttr ".ip[0].gtg" -type "string" "";
	setAttr ".skm" 1;
	setAttr -s 8 ".wl";
	setAttr ".wl[0:7].w"
		1 0 1
		1 0 1
		1 0 1
		1 0 1
		1 0 1
		1 0 1
		1 0 1
		1 0 1;
	setAttr ".pm[0]" -type "matrix" 1 0 0 0 0 1 0 0 0 0 1 0 -6 -22 0 1;
	setAttr ".gm" -type "matrix" 1 0 0 0 0 1 0 0 0 0 1 0 0 0 0 1;
	setAttr ".dpf[0]"  10;
	setAttr ".mmi" yes;
	setAttr ".mi" 1;
	setAttr ".ucm" yes;
createNode tweak -n "tweak47";
	rename -uid "0EB0CA3B-48D2-D6BD-744D-1E89D16D9977";
createNode objectSet -n "skinCluster35Set";
	rename -uid "3C88F282-4ABE-BF45-3A06-009E118B0F29";
	setAttr ".ihi" 0;
	setAttr ".vo" yes;
createNode groupId -n "skinCluster35GroupId";
	rename -uid "CB9E8665-42D3-FD7D-133F-98B20D63E8C4";
	setAttr ".ihi" 0;
createNode groupParts -n "skinCluster35GroupParts";
	rename -uid "527A6DEF-4045-71C8-8930-C29326CE7511";
	setAttr ".ihi" 0;
	setAttr ".ic" -type "componentList" 1 "vtx[*]";
createNode objectSet -n "tweakSet47";
	rename -uid "4A9D7FAF-434B-FB57-C0E5-74843E6E595C";
	setAttr ".ihi" 0;
	setAttr ".vo" yes;
createNode groupId -n "groupId107";
	rename -uid "4AC75793-40AA-E189-01F8-6889185844FA";
	setAttr ".ihi" 0;
createNode groupParts -n "groupParts96";
	rename -uid "D175F241-4A54-A33F-E09C-B7BC247F9270";
	setAttr ".ihi" 0;
	setAttr ".ic" -type "componentList" 1 "vtx[*]";
createNode skinCluster -n "skinCluster36";
	rename -uid "0BDE57D4-4C77-B41D-2052-E2919D6E6A17";
	setAttr ".ip[0].gtg" -type "string" "";
	setAttr ".skm" 1;
	setAttr -s 8 ".wl";
	setAttr ".wl[0:7].w"
		1 0 1
		1 0 1
		1 0 1
		1 0 1
		1 0 1
		1 0 1
		1 0 1
		1 0 1;
	setAttr ".pm[0]" -type "matrix" 1 0 0 0 0 1 0 0 0 0 1 0 -6 -22 0 1;
	setAttr ".gm" -type "matrix" 1 0 0 0 0 1 0 0 0 0 1 0 0 0 0 1;
	setAttr ".dpf[0]"  10;
	setAttr ".mmi" yes;
	setAttr ".mi" 1;
	setAttr ".ucm" yes;
createNode tweak -n "tweak48";
	rename -uid "7752DCEA-43E6-48EF-0B3E-C1AE832721B0";
createNode objectSet -n "skinCluster36Set";
	rename -uid "A09BED3B-44E1-643D-51E1-FBABA37F5AE0";
	setAttr ".ihi" 0;
	setAttr ".vo" yes;
createNode groupId -n "skinCluster36GroupId";
	rename -uid "ED804AC7-40B4-2DE4-AF78-DFA7785C67C0";
	setAttr ".ihi" 0;
createNode groupParts -n "skinCluster36GroupParts";
	rename -uid "B659C38E-4CE0-33D4-EA0A-469096C4F081";
	setAttr ".ihi" 0;
	setAttr ".ic" -type "componentList" 1 "vtx[*]";
createNode objectSet -n "tweakSet48";
	rename -uid "09A7D3BE-48A4-90E2-573E-40B8B4CCC278";
	setAttr ".ihi" 0;
	setAttr ".vo" yes;
createNode groupId -n "groupId109";
	rename -uid "D81C7784-481A-7CA7-7333-48A9B101FF6C";
	setAttr ".ihi" 0;
createNode groupParts -n "groupParts98";
	rename -uid "59072AC7-44CD-2C3E-244B-A7BA638D3ECE";
	setAttr ".ihi" 0;
	setAttr ".ic" -type "componentList" 1 "vtx[*]";
createNode condition -n "condition46";
	rename -uid "F4C48C86-439F-3D04-0CFF-73AB54FDBABF";
	setAttr ".op" 1;
createNode condition -n "condition47";
	rename -uid "E4B4A571-418E-92B2-2C26-A3B622CAB292";
	setAttr ".st" 1;
	setAttr ".ct" -type "float3" -0.5 0 0 ;
	setAttr ".cf" -type "float3" 0 1 1 ;
createNode RedshiftMaterial -n "skinPromoArt_rsMat";
	rename -uid "25A38486-49FE-6661-1488-BD9CF0CB68BC";
	setAttr ".v" 1;
	setAttr ".refl_color" -type "float3" 0 0 0 ;
	setAttr ".refl_weight" 0;
	setAttr ".ms_radius_scale" 0.125;
	setAttr ".ms_mode" 1;
createNode RedshiftBumpBlender -n "rsBumpBlender1";
	rename -uid "E332A3E3-440E-AD38-15E7-E794F67C52BC";
	setAttr ".bumpWeight0" 1;
	setAttr ".bumpInput1" -type "float3" 1 1 1 ;
	setAttr ".bumpWeight1" 1;
	setAttr ".bumpInput2" -type "float3" 0 1 1 ;
	setAttr ".additive" yes;
createNode RedshiftRoundCorners -n "rsRoundCorners1";
	rename -uid "8DA52D1A-4497-6112-FDEE-32A397F52DA7";
	setAttr ".radius" 0.125;
	setAttr ".sameObjectOnly" yes;
createNode colorMath -n "colorMath1";
	rename -uid "1DDD0D5F-413E-8717-D958-9CB993DAC3F9";
	setAttr "._cb" -type "float3" 0.24516129 0.24516129 0.24516129 ;
	setAttr "._cnd" 3;
createNode colorMath -n "colorMath2";
	rename -uid "BDD817F2-4B04-592F-0604-A58AABB36CA7";
	setAttr "._cb" -type "float3" 0.064516127 0.064516127 0.064516127 ;
	setAttr "._cnd" 2;
createNode RedshiftRoundCorners -n "rsRoundCorners2";
	rename -uid "FAA9B630-477F-B232-A2E5-D4B7E123DAF9";
	setAttr ".radius" 0.125;
	setAttr ".sameObjectOnly" yes;
createNode RedshiftBumpBlender -n "rsBumpBlender2";
	rename -uid "56C18648-4768-AF67-6F3A-B1A294085A9D";
	setAttr ".bumpWeight0" 1;
	setAttr ".bumpWeight1" 1;
	setAttr ".bumpInput2" -type "float3" 1 1 1 ;
	setAttr ".additive" yes;
createNode shadingEngine -n "rsSprite1SG";
	rename -uid "8AF55504-4DEF-208D-80D1-74AFC94EA1A4";
	setAttr ".ihi" 0;
	setAttr ".ro" yes;
createNode materialInfo -n "materialInfo8";
	rename -uid "05A6EBE9-4CF7-F9E3-EE61-DF8BE81E5906";
createNode file -n "armorPromoArt_filtered_tex";
	rename -uid "27528E13-4511-2425-FAAB-BDAF2B66519C";
	setAttr ".ftn" -type "string" "D:/Dropbox/Misc Projects/Minecraft/mobs/tex_armor.png";
	setAttr ".ft" 0;
	setAttr ".cs" -type "string" "sRGB";
	setAttr ".ai_filter" 0;
createNode RedshiftBumpMap -n "rsBumpMap2";
	rename -uid "5A011F81-4F53-A0EC-A985-27829744C13A";
	setAttr ".scale" 0.125;
createNode rgbToHsv -n "rgbToHsv1";
	rename -uid "1DEC922E-47FB-95F7-A8E0-E69F61F008A6";
createNode hsvToRgb -n "hsvToRgb1";
	rename -uid "35897D98-409A-DFFC-8056-C2B11FD2D6FA";
	setAttr ".i" -type "float3" 0 0 0.019354839 ;
createNode dagPose -n "bindPose4";
	rename -uid "0BE776E2-47BC-285B-5621-5A9FD93F4C09";
	setAttr -s 6 ".wm";
	setAttr ".wm[5]" -type "matrix" 1 0 0 0 0 1 0 0 0 0 1 0 0 0 0 1;
	setAttr ".wm[6]" -type "matrix" 1 0 0 0 0 1 0 0 0 0 1 0 0 0 0 1;
	setAttr ".wm[7]" -type "matrix" 1 0 0 0 0 1 0 0 0 0 1 0 0 0 0 1;
	setAttr ".wm[8]" -type "matrix" 1 0 0 0 0 1 0 0 0 0 1 0 0 0 0 1;
	setAttr ".wm[9]" -type "matrix" 1 0 0 0 0 1 0 0 0 0 1 0 0 0 0 1;
	setAttr -s 10 ".xm";
	setAttr ".xm[0]" -type "matrix" "xform" 1 1 1 0 0 0 0 0 24 0 0 0 0 0 0 0 0 0
		 0 0 0 0 0 0 0 0 0 0 1 0 0 0 1 1 1 1 yes;
	setAttr ".xm[1]" -type "matrix" "xform" 1 1 1 0 0 0 0 -2 28 4 0 0 0 0 0 0 0
		 0 0 0 0 0 0 0 0 0 0 0 1 0 0 0 1 1 1 1 yes;
	setAttr ".xm[2]" -type "matrix" "xform" 1 1 1 0 0 0 0 -2 27 4 0 0 0 0 0 0 0
		 0 0 0 0 0 0 0 0 0 0 0 1 0 0 0 1 1 1 1 yes;
	setAttr ".xm[3]" -type "matrix" "xform" 1 1 1 0 0 0 0 2 27 4 0 0 0 0 0 0 0 0
		 0 0 0 0 0 0 0 0 0 0 1 0 0 0 1 1 1 1 yes;
	setAttr ".xm[4]" -type "matrix" "xform" 1 1 1 0 0 0 0 2 28 4 0 0 0 0 0 0 0 0
		 0 0 0 0 0 0 0 0 0 0 1 0 0 0 1 1 1 1 yes;
	setAttr ".xm[5]" -type "matrix" "xform" 1 1 1 0 0 0 0 0 0 0 0 0 0 0 24 0 0 0
		 0 0 24 0 0 0 0 0 0 0 1 0 0 0 1 1 1 1 yes;
	setAttr ".xm[6]" -type "matrix" "xform" 1 1 1 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0
		 0 0 0 0 0 0 0 0 0 0 1 0 0 0 1 1 1 1 yes;
	setAttr ".xm[7]" -type "matrix" "xform" 1 1 1 0 0 0 0 0 0 0 0 0 0 0 24 0 0 0
		 0 0 24 0 0 0 0 0 0 0 1 0 0 0 1 1 1 1 yes;
	setAttr ".xm[8]" -type "matrix" "xform" 1 1 1 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0
		 0 0 0 0 0 0 0 0 0 0 1 0 0 0 1 1 1 1 yes;
	setAttr ".xm[9]" -type "matrix" "xform" 1 1 1 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0
		 0 0 0 0 0 0 0 0 0 0 1 0 0 0 1 1 1 1 yes;
	setAttr -s 6 ".m";
	setAttr -s 10 ".p";
	setAttr -s 5 ".g[5:9]" yes yes yes yes yes;
	setAttr ".bp" yes;
createNode skinCluster -n "skinCluster25";
	rename -uid "18236148-4D2C-D65D-CC8A-47ABCBAE1BB0";
	setAttr ".ip[0].gtg" -type "string" "";
	setAttr ".skm" 1;
	setAttr -s 8 ".wl";
	setAttr ".wl[0:7].w"
		1 0 1
		1 0 1
		1 0 1
		1 0 1
		1 0 1
		1 0 1
		1 0 1
		1 0 1;
	setAttr ".pm[0]" -type "matrix" 1 0 0 0 0 1 0 0 0 0 1 0 0 -24 0 1;
	setAttr ".gm" -type "matrix" 1 0 0 0 0 1 0 0 0 0 1 0 0 0 0 1;
	setAttr ".dpf[0]"  10;
	setAttr ".mmi" yes;
	setAttr ".mi" 1;
	setAttr ".ucm" yes;
createNode objectSet -n "skinCluster25Set";
	rename -uid "0508BB0B-4689-1853-6BD2-D68D1063289C";
	setAttr ".ihi" 0;
	setAttr ".vo" yes;
createNode groupId -n "skinCluster25GroupId";
	rename -uid "D76CA0FD-44A0-02CE-CE38-CC99F57F03CF";
	setAttr ".ihi" 0;
createNode groupParts -n "skinCluster25GroupParts";
	rename -uid "9C19BC10-4707-1E14-61A2-07A40A85427E";
	setAttr ".ihi" 0;
	setAttr ".ic" -type "componentList" 1 "vtx[*]";
createNode skinCluster -n "skinCluster32";
	rename -uid "8C2F4F42-4B8F-C637-67E7-85864D9A69FF";
	setAttr ".ip[0].gtg" -type "string" "";
	setAttr ".skm" 1;
	setAttr -s 8 ".wl";
	setAttr ".wl[0:7].w"
		1 0 1
		1 0 1
		1 0 1
		1 0 1
		1 0 1
		1 0 1
		1 0 1
		1 0 1;
	setAttr ".pm[0]" -type "matrix" 1 0 0 0 0 1 0 0 0 0 1 0 0 -24 0 1;
	setAttr ".gm" -type "matrix" 1 0 0 0 0 1 0 0 0 0 1 0 0 0 0 1;
	setAttr ".dpf[0]"  10;
	setAttr ".mmi" yes;
	setAttr ".mi" 1;
	setAttr ".ucm" yes;
createNode objectSet -n "skinCluster32Set";
	rename -uid "9EBDF606-48E7-CD1C-9BB1-9BAFF0341FFE";
	setAttr ".ihi" 0;
	setAttr ".vo" yes;
createNode groupId -n "skinCluster32GroupId";
	rename -uid "97643570-425F-2A73-84B1-4E8AE04DE58B";
	setAttr ".ihi" 0;
createNode groupParts -n "skinCluster32GroupParts";
	rename -uid "281CED15-4037-4887-FB07-B48725327FEC";
	setAttr ".ihi" 0;
	setAttr ".ic" -type "componentList" 1 "vtx[*]";
createNode tweak -n "tweak44";
	rename -uid "C009D88C-4E7C-9468-4D14-FEAD337F2ABD";
createNode objectSet -n "tweakSet44";
	rename -uid "ECAD2515-44EB-DE66-703A-65A99ADDA4E3";
	setAttr ".ihi" 0;
	setAttr ".vo" yes;
createNode groupId -n "groupId101";
	rename -uid "EF9C6A87-442C-26BB-D8E0-ED883E0B1394";
	setAttr ".ihi" 0;
createNode groupParts -n "groupParts90";
	rename -uid "56B8B1BB-486D-F9DE-824A-22AE6340DEA5";
	setAttr ".ihi" 0;
	setAttr ".ic" -type "componentList" 1 "vtx[*]";
createNode RedshiftMaterial -n "facePromoArt_rsMat";
	rename -uid "667F465D-4470-F8A9-D4E0-E3818B3AC18E";
	setAttr ".v" 1;
	setAttr ".refl_color" -type "float3" 0 0 0 ;
	setAttr ".refl_weight" 0;
	setAttr ".ms_mode" 1;
createNode shadingEngine -n "rsMaterial2SG";
	rename -uid "93C155CE-4EBB-87F7-8A76-CABF0CC863A0";
	setAttr ".ihi" 0;
	setAttr ".ro" yes;
createNode materialInfo -n "materialInfo9";
	rename -uid "312FD0E6-4C8B-BD7F-4758-15AD90F6D295";
createNode groupId -n "groupId110";
	rename -uid "21DC11D0-4EC6-B1F9-453C-188B98F8CEC2";
	setAttr ".ihi" 0;
createNode groupParts -n "groupParts99";
	rename -uid "27E45A8E-42D5-7BF6-319F-B1A8E20EEE4B";
	setAttr ".ihi" 0;
	setAttr ".ic" -type "componentList" 1 "f[0:4]";
	setAttr ".irc" -type "componentList" 1 "f[5]";
createNode groupId -n "groupId111";
	rename -uid "C3FAE45F-4B23-2037-30D1-33823CAE49ED";
	setAttr ".ihi" 0;
createNode groupId -n "groupId112";
	rename -uid "99FAA259-40AA-3563-2DC5-509D81DC31EF";
	setAttr ".ihi" 0;
createNode groupParts -n "groupParts100";
	rename -uid "438E88BE-4323-5502-D27E-7A9EF74C0164";
	setAttr ".ihi" 0;
	setAttr ".ic" -type "componentList" 1 "f[5]";
createNode layeredTexture -n "mainFace_layeredTexture";
	rename -uid "114EABE5-48EE-DF8B-C3D4-22AA36521FDF";
	setAttr -s 7 ".cs";
	setAttr ".cs[7].bm" 1;
	setAttr ".cs[7].iv" yes;
	setAttr ".cs[10].bm" 1;
	setAttr ".cs[10].iv" yes;
	setAttr ".cs[11].bm" 1;
	setAttr ".cs[11].iv" yes;
	setAttr ".cs[12].bm" 1;
	setAttr ".cs[12].iv" yes;
	setAttr ".cs[13].bm" 1;
	setAttr ".cs[13].iv" yes;
	setAttr ".cs[14].bm" 1;
	setAttr ".cs[14].iv" yes;
	setAttr ".cs[15].a" 1;
	setAttr ".cs[15].bm" 0;
	setAttr ".cs[15].iv" yes;
createNode projection -n "rt_eyeWhite_projection";
	rename -uid "A1317565-43E0-B159-5071-4496FC32E875";
	setAttr ".ua" 180.00000500895632;
	setAttr ".va" 90.000002504478161;
	setAttr ".vt1" -type "float2" 0.5 0.5 ;
	setAttr ".vt2" -type "float2" 0.5 0.5 ;
	setAttr ".vt3" -type "float2" 0.5 0.5 ;
createNode colorConstant -n "colorConstant1";
	rename -uid "D9C4DFEE-4825-04CB-5025-FBB8C4BEAA3C";
	setAttr "._c" -type "float3" 0.90322578 0.90322578 0.90322578 ;
createNode ramp -n "mainSquare_ramp";
	rename -uid "1C9FBFF3-47AC-9D91-588C-BEBB80DA55D6";
	setAttr ".dc" -type "float3" 0 0 0 ;
	setAttr ".t" 5;
	setAttr ".in" 0;
	setAttr -s 2 ".cel";
	setAttr ".cel[1].ep" 0;
	setAttr ".cel[1].ec" -type "float3" 1 1 1 ;
	setAttr ".cel[2].ep" 1;
	setAttr ".cel[2].ec" -type "float3" 0 0 0 ;
createNode floatMath -n "floatMath62";
	rename -uid "872F5E97-45A8-1D52-1770-63A8304BC797";
createNode projection -n "lf_eyeWhite_projection";
	rename -uid "CF48853C-40E9-1D08-78EF-B0816942D8BE";
	setAttr ".ua" 180.00000500895632;
	setAttr ".va" 90.000002504478161;
	setAttr ".vt1" -type "float2" 0.5 0.5 ;
	setAttr ".vt2" -type "float2" 0.5 0.5 ;
	setAttr ".vt3" -type "float2" 0.5 0.5 ;
createNode colorConstant -n "rt_eyeWhite_colorConstant";
	rename -uid "9AA186F4-4F21-1355-6F48-83A1CF7ED04B";
	setAttr "._c" -type "float3" 1 1 1 ;
createNode colorConstant -n "lf_eyeWhite_colorConstant";
	rename -uid "9E0A36EE-4274-509E-B913-C29E6DEE33D2";
	setAttr "._c" -type "float3" 1 1 1 ;
createNode colorMath -n "colorMath3";
	rename -uid "74B99274-4969-35FA-C707-A093A1FEE45B";
	setAttr "._cnd" 2;
createNode colorMath -n "colorMath4";
	rename -uid "AFE4290C-4198-ABAC-3CAA-43B464FF905F";
	setAttr "._cnd" 2;
createNode projection -n "lf_eyePupil_projection";
	rename -uid "C92BD5CA-439C-2AD6-34BC-D78D196055FB";
	setAttr ".ua" 180.00000500895632;
	setAttr ".va" 90.000002504478161;
	setAttr ".vt1" -type "float2" 0.5 0.5 ;
	setAttr ".vt2" -type "float2" 0.5 0.5 ;
	setAttr ".vt3" -type "float2" 0.5 0.5 ;
createNode projection -n "rt_eyePupil_projection";
	rename -uid "D14723DA-4564-B498-6AC1-C9952B966AEA";
	setAttr ".ua" 180.00000500895632;
	setAttr ".va" 90.000002504478161;
	setAttr ".vt1" -type "float2" 0.5 0.5 ;
	setAttr ".vt2" -type "float2" 0.5 0.5 ;
	setAttr ".vt3" -type "float2" 0.5 0.5 ;
createNode colorMath -n "rt_eyePupilColoring_colorMath";
	rename -uid "AB94914F-497D-10DD-1C31-16BDC4DEA24F";
	setAttr "._cnd" 2;
createNode colorConstant -n "lf_eyePupil_colorConstant";
	rename -uid "7ED0EBD4-4839-7B42-3F78-47B7D4D9ED09";
	setAttr "._c" -type "float3" 0.013 0.013 0.013 ;
createNode colorConstant -n "rt_eyePupil_colorConstant";
	rename -uid "41EA66A7-404A-F512-F42F-75AB0DA02B20";
	setAttr "._c" -type "float3" 0.013 0.013 0.013 ;
createNode colorMath -n "lf_eyePupilColoring_colorMath";
	rename -uid "1F600CC8-422E-D208-9ABF-01A272598E5D";
	setAttr "._cnd" 2;
createNode floatMath -n "lf_eyePupilAlpha_floatMath";
	rename -uid "85139A68-4AB7-666E-63D1-DBB635A2658F";
	setAttr "._cnd" 2;
createNode floatMath -n "rt_eyePupilAlpha_floatMath";
	rename -uid "C0A8A8C1-4878-45A7-6EB4-E48C4D32735B";
	setAttr "._cnd" 2;
createNode place2dTexture -n "mainSquare_place2dTexture";
	rename -uid "9E97937F-483E-514C-DCFC-F49F41115231";
	setAttr ".wu" no;
	setAttr ".wv" no;
createNode colorConstant -n "eyebrow_colorConstant";
	rename -uid "DF4AABF1-4053-5299-1BCF-A0BF96B75FB1";
	setAttr "._c" -type "float3" 0.032000002 0.016040329 0.005632 ;
createNode colorMath -n "lf_eyelidEyebrowCutout_colorMath";
	rename -uid "4AD3DF39-4176-2C25-E521-1294FE37EB8A";
	setAttr "._cnd" 2;
createNode colorMath -n "rt_eyelidEyebrowCutout_colorMath";
	rename -uid "3C888403-42B7-B613-4C1E-418B905EFA1B";
	setAttr "._cnd" 2;
createNode blendColors -n "blendColors11";
	rename -uid "F4064484-4BAD-8C64-E53A-4BAA3499515D";
createNode polyTweakUV -n "polyTweakUV12";
	rename -uid "6139BC12-4399-4E7D-B599-8AA553AF53F1";
	setAttr ".uopa" yes;
	setAttr -s 24 ".uvtk[0:23]" -type "float2" -1.0032103e-07 -8.243687e-08
		 9.7653746e-08 -8.243687e-08 9.7653746e-08 8.8419966e-08 -1.0032103e-07 8.8419966e-08
		 -8.541987e-08 -8.9771042e-08 8.541987e-08 -8.9771042e-08 8.541987e-08 8.1085801e-08
		 -8.541987e-08 8.1085801e-08 9.8433318e-08 1.3012425e-07 -1.0220874e-07 1.3012425e-07
		 -1.0220874e-07 -1.0031925e-07 9.8433318e-08 -1.0031925e-07 8.5393907e-08 -7.983104e-08
		 -8.5444938e-08 -7.983104e-08 -8.5444938e-08 9.1008701e-08 8.5393907e-08 9.1008701e-08
		 -8.5393907e-08 -8.9770055e-08 8.5445834e-08 -8.9770055e-08 8.5445834e-08 8.1086611e-08
		 -8.5393907e-08 8.1086611e-08 -1.0217964e-07 -8.9770055e-08 9.8461527e-08 -8.9770055e-08
		 9.8461527e-08 8.1086611e-08 -1.0217964e-07 8.1086611e-08;
createNode layeredTexture -n "eyebrows_layeredTexture";
	rename -uid "2DBD805F-4C48-096F-ED1F-67B2025D19B7";
	setAttr -s 2 ".cs";
	setAttr ".cs[0].bm" 1;
	setAttr ".cs[0].iv" yes;
	setAttr ".cs[1].bm" 0;
	setAttr ".cs[1].iv" yes;
createNode file -n "lf_eyebrow_tex";
	rename -uid "CAA88AED-4B0B-AB1A-F514-D3BF643734DB";
	setAttr ".dc" -type "float3" 0.25 0.25 0.25 ;
	setAttr ".ftn" -type "string" "D:/Dropbox/Misc Projects/Public Rig/Promo Art Rig/textures/tex_eyebrowBase_normal.png";
	setAttr ".ft" 0;
	setAttr ".cs" -type "string" "sRGB";
	setAttr ".rsFilterEnable" 0;
createNode place2dTexture -n "place2dTexture2";
	rename -uid "2996348F-435F-7945-E229-DD9A0A6E2385";
	setAttr ".wu" no;
	setAttr ".wv" no;
createNode colorMath -n "lf_eyebrowRecolor_colorMath";
	rename -uid "EE3F4EFC-4FDF-D8D3-BC5B-608225CDF753";
	setAttr "._cnd" 2;
createNode contrast -n "lf_eyebrow_contrast";
	rename -uid "39F52E4F-43BE-D8CA-87E1-5B908BB2C091";
createNode floatConstant -n "eyebrowContrast_floatConstant";
	rename -uid "BB1F66CC-41C6-C657-9617-46BB2C6990E9";
	setAttr "._f" 0.5;
createNode projection -n "lf_eyebrow_projection";
	rename -uid "0C37D428-4D61-F864-5E1A-7BA146E1DB78";
	setAttr ".ua" 180.00000500895632;
	setAttr ".va" 90.000002504478161;
	setAttr ".vt1" -type "float2" 0.5 0.5 ;
	setAttr ".vt2" -type "float2" 0.5 0.5 ;
	setAttr ".vt3" -type "float2" 0.5 0.5 ;
createNode rgbToHsv -n "lf_eyebrowAlpha_rgbToHsv";
	rename -uid "C0FDFEE3-4ECC-E862-7F39-BC9EBF4A4900";
createNode ramp -n "lf_eyebrowAlpha_ramp";
	rename -uid "5F6A599E-4F72-087A-CE77-F1A8720950F2";
	setAttr ".in" 0;
	setAttr -s 2 ".cel";
	setAttr ".cel[0].ep" 0;
	setAttr ".cel[0].ec" -type "float3" 0 0 0 ;
	setAttr ".cel[1].ep" 0.39318886399269104;
	setAttr ".cel[1].ec" -type "float3" 1 1 1 ;
createNode skinCluster -n "skinCluster17";
	rename -uid "25E86314-4943-275C-6421-43AA1CB0718F";
	setAttr ".ip[0].gtg" -type "string" "";
	setAttr ".skm" 1;
	setAttr -s 8 ".wl";
	setAttr ".wl[0:7].w"
		1 0 1
		1 0 1
		1 0 1
		1 0 1
		1 0 1
		1 0 1
		1 0 1
		1 0 1;
	setAttr ".pm[0]" -type "matrix" 1 0 0 0 0 1 0 0 0 0 1 0 0 -24 0 1;
	setAttr ".gm" -type "matrix" 1 0 0 0 0 1 0 0 0 0 1 0 0 0 0 1;
	setAttr ".dpf[0]"  10;
	setAttr ".mmi" yes;
	setAttr ".mi" 1;
	setAttr ".ucm" yes;
createNode objectSet -n "skinCluster17Set";
	rename -uid "08C88C95-4F1C-3C2D-153F-7398D15A0192";
	setAttr ".ihi" 0;
	setAttr ".vo" yes;
createNode groupParts -n "groupParts36";
	rename -uid "BD97D565-4B15-B932-3788-8BADAED09901";
	setAttr ".ihi" 0;
	setAttr ".ic" -type "componentList" 1 "vtx[*]";
createNode groupId -n "groupId47";
	rename -uid "43F8856C-457C-FBA9-BA93-0A99A9CADEC6";
	setAttr ".ihi" 0;
createNode tweak -n "tweak17";
	rename -uid "111177F9-4ADE-CC8B-F5D9-56ABFF013BF9";
createNode groupParts -n "skinCluster17GroupParts";
	rename -uid "A9A17E51-414B-A4AA-C8E1-76AC4E8653A9";
	setAttr ".ihi" 0;
	setAttr ".ic" -type "componentList" 1 "vtx[*]";
createNode groupId -n "skinCluster17GroupId";
	rename -uid "8F9ABB9C-44B7-17DE-39E6-2B819B57C9EC";
	setAttr ".ihi" 0;
createNode objectSet -n "tweakSet17";
	rename -uid "94A33D35-4D2D-EF9F-8919-26BBF2783536";
	setAttr ".ihi" 0;
	setAttr ".vo" yes;
createNode ramp -n "lf_eyebrowCutoutAlpha_ramp";
	rename -uid "BE14AEB5-4D42-CA97-07E9-BCBDB1E68520";
	setAttr ".in" 0;
	setAttr -s 3 ".cel";
	setAttr ".cel[1].ep" 0;
	setAttr ".cel[1].ec" -type "float3" 0 0 0 ;
	setAttr ".cel[2].ep" 0.03405572846531868;
	setAttr ".cel[2].ec" -type "float3" 1 1 1 ;
	setAttr ".cel[3].ep" 0.37151703238487244;
	setAttr ".cel[3].ec" -type "float3" 0 0 0 ;
createNode ramp -n "rt_eyebrowCutoutAlpha_ramp";
	rename -uid "3EB87FBD-4EAE-0149-A26B-D39A897FE040";
	setAttr ".in" 0;
	setAttr -s 3 ".cel";
	setAttr ".cel[1].ep" 0;
	setAttr ".cel[1].ec" -type "float3" 0 0 0 ;
	setAttr ".cel[2].ep" 0.03405572846531868;
	setAttr ".cel[2].ec" -type "float3" 1 1 1 ;
	setAttr ".cel[3].ep" 0.37151703238487244;
	setAttr ".cel[3].ec" -type "float3" 0 0 0 ;
createNode ramp -n "rt_eyebrowAlpha_ramp";
	rename -uid "350F372C-4B2B-68B9-15A1-E39539842719";
	setAttr ".in" 0;
	setAttr -s 2 ".cel";
	setAttr ".cel[0].ep" 0;
	setAttr ".cel[0].ec" -type "float3" 0 0 0 ;
	setAttr ".cel[1].ep" 0.39318886399269104;
	setAttr ".cel[1].ec" -type "float3" 1 1 1 ;
createNode rgbToHsv -n "rt_eyebrowAlpha_rgbToHsv";
	rename -uid "B626563E-4308-64D5-7966-00A3BAF988B2";
createNode projection -n "rt_eyebrow_projection";
	rename -uid "C6FED1E9-4B3F-95CA-78FC-92BD76A52E1A";
	setAttr ".ua" 180.00000500895632;
	setAttr ".va" 90.000002504478161;
	setAttr ".vt1" -type "float2" 0.5 0.5 ;
	setAttr ".vt2" -type "float2" 0.5 0.5 ;
	setAttr ".vt3" -type "float2" 0.5 0.5 ;
createNode colorMath -n "rt_eyebrowRecolor_colorMath";
	rename -uid "031ACDE3-45D0-A46C-6CAF-F087BBD76ECC";
	setAttr "._cnd" 2;
createNode contrast -n "rt_eyebrow_contrast";
	rename -uid "37E19BBB-4228-2FB4-6FBB-6A9B53AA063C";
createNode file -n "rt_eyebrow_tex";
	rename -uid "909AC3BB-4B45-FA9C-4CFC-80982F4C4501";
	setAttr ".dc" -type "float3" 0.25 0.25 0.25 ;
	setAttr ".ftn" -type "string" "D:/Dropbox/Misc Projects/Public Rig/Promo Art Rig/textures/tex_eyebrowBase_normal.png";
	setAttr ".ft" 0;
	setAttr ".wu" no;
	setAttr ".wv" no;
	setAttr ".cs" -type "string" "sRGB";
	setAttr ".rsFilterEnable" 0;
createNode place2dTexture -n "place2dTexture3";
	rename -uid "2AFB7E25-460E-CF0C-7DD1-F691F549E879";
	setAttr ".wu" no;
	setAttr ".wv" no;
createNode layeredTexture -n "mouthClosed_layeredTexture";
	rename -uid "0C8C11F4-4125-A1CB-E22E-0E86C66D2F9F";
	setAttr -s 3 ".cs";
	setAttr ".cs[0].bm" 1;
	setAttr ".cs[0].iv" yes;
	setAttr ".cs[1].bm" 1;
	setAttr ".cs[1].iv" yes;
	setAttr ".cs[2].bm" 1;
	setAttr ".cs[2].iv" yes;
createNode blendColors -n "mouthClosedOpen_blendColors";
	rename -uid "39BB1937-4970-A0D7-715E-C49E5B3D7ED1";
createNode layeredTexture -n "mouthOpen_layeredTexture";
	rename -uid "C538213F-42AD-85E8-C8E0-FABC583D4E43";
	setAttr -s 6 ".cs";
	setAttr ".cs[5].bm" 1;
	setAttr ".cs[5].iv" yes;
	setAttr ".cs[7].bm" 1;
	setAttr ".cs[7].iv" yes;
	setAttr ".cs[8].bm" 1;
	setAttr ".cs[8].iv" yes;
	setAttr ".cs[9].bm" 1;
	setAttr ".cs[9].iv" yes;
	setAttr ".cs[10].bm" 1;
	setAttr ".cs[10].iv" yes;
	setAttr ".cs[11].bm" 1;
	setAttr ".cs[11].iv" yes;
createNode blendColors -n "mouthClosedOpenAlpha_blendColors";
	rename -uid "430D931B-4A1D-2712-D86D-AE8804EBC3A9";
	setAttr ".c1" -type "float3" 0.2 0 0.30000001 ;
	setAttr ".c2" -type "float3" 0.03174603 0 0.047619052 ;
createNode floatConstant -n "mouthClosedOpenBlender_floatConstant";
	rename -uid "93575B92-4612-52F3-EFD9-FB9C028814AE";
createNode colorConstant -n "mouthLip_colorConstant";
	rename -uid "C8E12BBC-497D-7EDE-4387-E88224AFD4DC";
	setAttr "._c" -type "float3" 0.2 0.059387136 0.039800014 ;
createNode colorMath -n "lf_lipRecolor_colorMath";
	rename -uid "05BBCE11-40B5-6978-C7A8-D092BB58E841";
	setAttr "._cnd" 2;
createNode projection -n "m_lip_projection";
	rename -uid "59505BEB-48A4-1401-FE18-84AD452E6541";
	setAttr ".ua" 180.00000500895632;
	setAttr ".va" 90.000002504478161;
	setAttr ".vt1" -type "float2" 0.5 0.5 ;
	setAttr ".vt2" -type "float2" 0.5 0.5 ;
	setAttr ".vt3" -type "float2" 0.5 0.5 ;
createNode projection -n "lf_lip_projection";
	rename -uid "DED5C5BE-4976-C8F2-0413-A7A5A88447A6";
	setAttr ".ua" 180.00000500895632;
	setAttr ".va" 90.000002504478161;
	setAttr ".vt1" -type "float2" 0.5 0.5 ;
	setAttr ".vt2" -type "float2" 0.5 0.5 ;
	setAttr ".vt3" -type "float2" 0.5 0.5 ;
createNode projection -n "rt_lip_projection";
	rename -uid "CC12C913-40C3-9E88-74BA-8DB9B9EAAAD1";
	setAttr ".ua" 180.00000500895632;
	setAttr ".va" 90.000002504478161;
	setAttr ".vt1" -type "float2" 0.5 0.5 ;
	setAttr ".vt2" -type "float2" 0.5 0.5 ;
	setAttr ".vt3" -type "float2" 0.5 0.5 ;
createNode colorMath -n "m_lipRecolor_colorMath";
	rename -uid "1E78A5DC-494C-D81D-C99B-FCA4D72B96A3";
	setAttr "._cnd" 2;
createNode colorMath -n "rt_lipRecolor_colorMath";
	rename -uid "BDE27E94-45CE-C61C-362A-A68C364C8D16";
	setAttr "._cnd" 2;
createNode colorConstant -n "mouthInside_colorConstant";
	rename -uid "FB074AD3-41F8-29BD-A6EB-3EA6291E765D";
	setAttr "._c" -type "float3" 0.052999999 0.007526001 0.007526001 ;
createNode colorConstant -n "mouthTongue_colorConstant";
	rename -uid "30A51549-4F91-EB8A-127C-84BF17C55439";
	setAttr "._c" -type "float3" 0.14399999 0.016847996 0.016847996 ;
createNode colorConstant -n "mouthTeeth_colorConstant";
	rename -uid "00CDD26F-414A-AA08-C315-3D9CE67624CB";
	setAttr "._c" -type "float3" 1 1 1 ;
createNode floatConstant -n "eyebrowOpacity_floatConstant";
	rename -uid "1F7E653D-4859-A777-2847-1A978E6B0B08";
createNode floatMath -n "lf_eyebrowOpacity_floatMath";
	rename -uid "A72B2C67-4C6D-22AA-33C4-FAB77EA80D09";
	setAttr "._cnd" 2;
createNode floatMath -n "rt_eyebrowOpacity_floatMath";
	rename -uid "F3713079-4979-8C05-9591-0B891E69A55F";
	setAttr "._cnd" 2;
createNode projection -n "rt_mouth_projection";
	rename -uid "D6494036-43B8-2A8E-4E9B-249DDC0545B0";
	setAttr ".ua" 180.00000500895632;
	setAttr ".va" 90.000002504478161;
	setAttr ".vt1" -type "float2" 0.5 0.5 ;
	setAttr ".vt2" -type "float2" 0.5 0.5 ;
	setAttr ".vt3" -type "float2" 0.5 0.5 ;
createNode projection -n "m_mouth_projection";
	rename -uid "46AEED8D-4D0A-B14A-59DE-D8907C794625";
	setAttr ".ua" 180.00000500895632;
	setAttr ".va" 90.000002504478161;
	setAttr ".vt1" -type "float2" 0.5 0.5 ;
	setAttr ".vt2" -type "float2" 0.5 0.5 ;
	setAttr ".vt3" -type "float2" 0.5 0.5 ;
createNode projection -n "lf_mouth_projection";
	rename -uid "386D2F05-44B0-B6F7-6F7B-70A15306552C";
	setAttr ".ua" 180.00000500895632;
	setAttr ".va" 90.000002504478161;
	setAttr ".vt1" -type "float2" 0.5 0.5 ;
	setAttr ".vt2" -type "float2" 0.5 0.5 ;
	setAttr ".vt3" -type "float2" 0.5 0.5 ;
createNode colorMath -n "rt_mouthRecolor_colorMath";
	rename -uid "DE9EFC26-4301-85F4-6E42-E08FD1F4BB04";
	setAttr "._cnd" 2;
createNode colorMath -n "m_mouthRecolor_colorMath";
	rename -uid "534D2EBF-410E-6D82-7F76-6F825DABA3C4";
	setAttr "._cnd" 2;
createNode colorMath -n "lf_mouthRecolor_colorMath";
	rename -uid "5C325FCC-4349-F7B0-AC0E-BDA3161BA23F";
	setAttr "._cnd" 2;
createNode projection -n "tongue_projection";
	rename -uid "6D0D1118-4A95-EBDB-4EF4-CEAC2704CFD1";
	setAttr ".ua" 180.00000500895632;
	setAttr ".va" 90.000002504478161;
	setAttr ".vt1" -type "float2" 0.5 0.5 ;
	setAttr ".vt2" -type "float2" 0.5 0.5 ;
	setAttr ".vt3" -type "float2" 0.5 0.5 ;
createNode colorMath -n "tongueRecolor_colorMath";
	rename -uid "1715D636-43CC-50B2-C91D-70B97921E018";
	setAttr "._cnd" 2;
createNode floatMath -n "tongueCutoutAlpha_floatMath";
	rename -uid "1F8B1605-43B4-E034-D2EB-4BB7E41CDABC";
	setAttr "._cnd" 2;
createNode projection -n "b_teeth_projection";
	rename -uid "0F78F239-409C-C021-684D-76801E716ADC";
	setAttr ".ua" 180.00000500895632;
	setAttr ".va" 90.000002504478161;
	setAttr ".vt1" -type "float2" 0.5 0.5 ;
	setAttr ".vt2" -type "float2" 0.5 0.5 ;
	setAttr ".vt3" -type "float2" 0.5 0.5 ;
createNode projection -n "t_teeth_projection";
	rename -uid "C5CFFA6F-479F-28E6-EAF2-49A01624AF3A";
	setAttr ".ua" 180.00000500895632;
	setAttr ".va" 90.000002504478161;
	setAttr ".vt1" -type "float2" 0.5 0.5 ;
	setAttr ".vt2" -type "float2" 0.5 0.5 ;
	setAttr ".vt3" -type "float2" 0.5 0.5 ;
createNode colorMath -n "t_teethRecolor_colorMath";
	rename -uid "4330EA6E-4F4B-EA15-F00A-6D9EDD5D8D9C";
	setAttr "._cnd" 2;
createNode colorMath -n "b_teethRecolor_colorMath";
	rename -uid "59E9D3A3-4A6F-FD01-8185-F8BECEFB0969";
	setAttr "._cnd" 2;
createNode floatMath -n "b_teethCutoutAlpha_floatMath";
	rename -uid "4055EA23-455B-B8A3-921D-CDAB389134B5";
	setAttr "._cnd" 2;
createNode floatMath -n "t_teethCutoutAlpha_floatMath";
	rename -uid "384B5FA6-46BE-BAF4-AA8F-489A2C7A0B9C";
	setAttr "._cnd" 2;
createNode floatMath -n "mouthSidesAlpha_floatMath";
	rename -uid "D39CC407-46AA-4DC2-4B1D-F0B2AC5C949C";
createNode floatMath -n "mouthAlpha_floatMath";
	rename -uid "0EBC4F3B-4AF3-E16F-CBEF-FC97B4255FA3";
createNode distanceBetween -n "mouthOpenY_distanceBetween";
	rename -uid "8AC11244-4538-E0A3-B3A9-A7AA8251BADF";
createNode floatMath -n "mouthOpenY_scaleOffset_floatMath";
	rename -uid "FF8F05D8-4F28-ABC8-4765-B1806CDC5E3D";
	setAttr "._fb" 0.5;
	setAttr "._cnd" 2;
createNode floatMath -n "lf_mouthSmileOffset_floatMath";
	rename -uid "C9CA4861-4E7F-8413-4B93-0DA8F854DFF9";
createNode floatMath -n "rt_mouthSmileOffset_floatMath";
	rename -uid "037E80D5-4C9F-2322-94DC-7CBF99151218";
createNode clamp -n "lf_mouthSmile_clamp";
	rename -uid "91BA68ED-4F6C-E38C-331D-BE9B4BB54F4D";
createNode remapValue -n "lf_mouthSmile_remapValue";
	rename -uid "1B412DA8-4509-428F-D345-CFA700C1CA8A";
	setAttr ".imn" -16;
	setAttr ".imx" 16;
	setAttr ".omn" -8;
	setAttr ".omx" 0;
	setAttr -s 3 ".vl[0:2]"  0 0 1 1 0 1 0.5 1 1;
	setAttr -s 2 ".cl";
	setAttr ".cl[0].clp" 0;
	setAttr ".cl[0].clc" -type "float3" 0 0 0 ;
	setAttr ".cl[0].cli" 1;
	setAttr ".cl[1].clp" 1;
	setAttr ".cl[1].clc" -type "float3" 1 1 1 ;
	setAttr ".cl[1].cli" 1;
createNode remapValue -n "rt_mouthSmile_remapValue";
	rename -uid "C1646F75-46B3-4A12-B3C5-9987359CF6E4";
	setAttr ".imn" -16;
	setAttr ".imx" 16;
	setAttr ".omn" -8;
	setAttr ".omx" 0;
	setAttr -s 3 ".vl[0:2]"  0 0 1 1 0 1 0.5 1 1;
	setAttr -s 2 ".cl";
	setAttr ".cl[0].clp" 0;
	setAttr ".cl[0].clc" -type "float3" 0 0 0 ;
	setAttr ".cl[0].cli" 1;
	setAttr ".cl[1].clp" 1;
	setAttr ".cl[1].clc" -type "float3" 1 1 1 ;
	setAttr ".cl[1].cli" 1;
createNode clamp -n "rt_mouthSmile_clamp";
	rename -uid "F27F1D27-4C9D-A173-1D56-89A3005084D9";
createNode floatMath -n "mouthOpenX_addedTranslate_floatMath";
	rename -uid "73424A0F-4AC7-738E-1D39-2984E10AD4E2";
createNode floatMath -n "mouthOpenX_divideTranslate_floatMath";
	rename -uid "E2C54CB2-437F-56CC-C147-3B9A925026C1";
	setAttr "._fb" 0.5;
	setAttr "._cnd" 2;
createNode floatMath -n "mouthOpenX_scaleOffset_floatMath";
	rename -uid "D5F74C52-4691-1B6F-463F-4D9F4F7D7EBD";
	setAttr "._fb" 1.5;
createNode clamp -n "rt_mouthSmileMin_clamp";
	rename -uid "5058933B-4397-88AF-CD64-1AB72809B2A1";
	setAttr ".mn" -type "float3" 0 0.25 0 ;
	setAttr ".mx" -type "float3" 0 16 0 ;
createNode clamp -n "lf_mouthSmileMin_clamp";
	rename -uid "BA3AFF46-4BB7-DD85-A1C2-519A4FE8B856";
	setAttr ".mn" -type "float3" 0 0.25 0 ;
	setAttr ".mx" -type "float3" 0 16 0 ;
createNode clamp -n "rt_mouthMin_clamp";
	rename -uid "F17CF345-4BE3-E03F-E7E6-8CAF2DD3F44C";
	setAttr ".mn" -type "float3" 0 0.25 0 ;
	setAttr ".mx" -type "float3" 0 16 0 ;
createNode multMatrix -n "multMatrix1";
	rename -uid "1AAFB365-4C9E-822F-C990-6AA70ED648B6";
	setAttr -s 2 ".i";
createNode decomposeMatrix -n "decomposeMatrix11";
	rename -uid "52439408-4DAB-BF13-1B42-9A9501AFD950";
createNode floatMath -n "floatMath63";
	rename -uid "D9B46B7F-4FDF-F8A1-2406-AA972016DF6A";
createNode floatMath -n "mouthOpenXInverse_floatMath";
	rename -uid "F5EB32DD-41FB-132A-358E-C4A2A5F2626F";
	setAttr "._fb" -1;
	setAttr "._cnd" 2;
createNode floatMath -n "mouthOpenX_scaleMultiply_floatMath";
	rename -uid "61086749-4476-0D38-F985-AEA86E269F4E";
	setAttr "._fb" 0.5;
	setAttr "._cnd" 2;
createNode multMatrix -n "multMatrix2";
	rename -uid "3F1BF407-46BD-2453-A083-4B953442AB44";
	setAttr -s 2 ".i";
createNode decomposeMatrix -n "decomposeMatrix12";
	rename -uid "55A27E63-45E8-4A64-7385-3199B67A3118";
createNode multMatrix -n "multMatrix3";
	rename -uid "38F72302-4078-3709-B09D-709B57343A02";
	setAttr -s 2 ".i";
createNode multMatrix -n "multMatrix4";
	rename -uid "3668C6B4-44EB-E20C-B98D-EEAF12CD0762";
	setAttr -s 2 ".i";
createNode decomposeMatrix -n "decomposeMatrix13";
	rename -uid "4F9430C8-4EF7-6A36-0BE8-F2ABD599252D";
createNode decomposeMatrix -n "decomposeMatrix14";
	rename -uid "C3B5DBA3-4F6F-367D-CFF7-10A7F5D851D2";
createNode decomposeMatrix -n "decomposeMatrix15";
	rename -uid "EBAC4BE5-4272-1430-6DE0-298E21472B31";
createNode decomposeMatrix -n "decomposeMatrix16";
	rename -uid "6989EFF7-441B-201C-1CA3-52AB0D0B99FE";
createNode multMatrix -n "multMatrix5";
	rename -uid "4E841A8F-4B81-EE2E-BB0F-65A66813554F";
	setAttr -s 2 ".i";
createNode multMatrix -n "multMatrix6";
	rename -uid "F73AD380-4202-4A45-F000-57889A196503";
	setAttr -s 2 ".i";
createNode multMatrix -n "multMatrix7";
	rename -uid "3E18CAEC-4932-98B4-84EF-AAB721A941BB";
	setAttr -s 2 ".i";
createNode decomposeMatrix -n "decomposeMatrix17";
	rename -uid "3D85C876-4424-E40E-7611-C89FE964C42D";
createNode nodeGraphEditorInfo -n "hyperShadePrimaryNodeEditorSavedTabsInfo";
	rename -uid "950F92CC-4009-B85D-2076-EC8F4759550F";
	setAttr -s 2 ".tgi";
	setAttr ".tgi[0].tn" -type "string" "node_facialShaders";
	setAttr ".tgi[0].vl" -type "double2" 7799.247275802044 -2118.8780185781798 ;
	setAttr ".tgi[0].vh" -type "double2" 9174.2473065356899 -1342.0346832420284 ;
	setAttr -s 79 ".tgi[0].ni";
	setAttr ".tgi[0].ni[0].x" 9519.2373046875;
	setAttr ".tgi[0].ni[0].y" -1421.868408203125;
	setAttr ".tgi[0].ni[0].nvs" 1931;
	setAttr ".tgi[0].ni[1].x" 7654.61767578125;
	setAttr ".tgi[0].ni[1].y" -2020.8004150390625;
	setAttr ".tgi[0].ni[1].nvs" 1931;
	setAttr ".tgi[0].ni[2].x" 7633.142578125;
	setAttr ".tgi[0].ni[2].y" -3430.86181640625;
	setAttr ".tgi[0].ni[2].nvs" 1931;
	setAttr ".tgi[0].ni[3].x" 7651.92919921875;
	setAttr ".tgi[0].ni[3].y" -2238.8759765625;
	setAttr ".tgi[0].ni[3].nvs" 1931;
	setAttr ".tgi[0].ni[4].x" 6837.1884765625;
	setAttr ".tgi[0].ni[4].y" -2422.802978515625;
	setAttr ".tgi[0].ni[4].nvs" 1931;
	setAttr ".tgi[0].ni[5].x" 6997.6904296875;
	setAttr ".tgi[0].ni[5].y" -438.03021240234375;
	setAttr ".tgi[0].ni[5].nvs" 1931;
	setAttr ".tgi[0].ni[6].x" 7330.7568359375;
	setAttr ".tgi[0].ni[6].y" -3646.585693359375;
	setAttr ".tgi[0].ni[6].nvs" 1931;
	setAttr ".tgi[0].ni[7].x" 5822.4501953125;
	setAttr ".tgi[0].ni[7].y" -1516.21484375;
	setAttr ".tgi[0].ni[7].nvs" 1931;
	setAttr ".tgi[0].ni[8].x" 5266.06982421875;
	setAttr ".tgi[0].ni[8].y" -2101.647216796875;
	setAttr ".tgi[0].ni[8].nvs" 1931;
	setAttr ".tgi[0].ni[9].x" 7619.4453125;
	setAttr ".tgi[0].ni[9].y" 124.45181274414062;
	setAttr ".tgi[0].ni[9].nvs" 1931;
	setAttr ".tgi[0].ni[10].x" 6409.06982421875;
	setAttr ".tgi[0].ni[10].y" -1625.1107177734375;
	setAttr ".tgi[0].ni[10].nvs" 1931;
	setAttr ".tgi[0].ni[11].x" 7634.61328125;
	setAttr ".tgi[0].ni[11].y" -3646.995361328125;
	setAttr ".tgi[0].ni[11].nvs" 1931;
	setAttr ".tgi[0].ni[12].x" 9238.4560546875;
	setAttr ".tgi[0].ni[12].y" -1671.3203125;
	setAttr ".tgi[0].ni[12].nvs" 1931;
	setAttr ".tgi[0].ni[13].x" 8190;
	setAttr ".tgi[0].ni[13].y" -3362.857177734375;
	setAttr ".tgi[0].ni[13].nvs" 1923;
	setAttr ".tgi[0].ni[14].x" 6838.8857421875;
	setAttr ".tgi[0].ni[14].y" -2539.390625;
	setAttr ".tgi[0].ni[14].nvs" 1931;
	setAttr ".tgi[0].ni[15].x" 6346.63671875;
	setAttr ".tgi[0].ni[15].y" -1063.69287109375;
	setAttr ".tgi[0].ni[15].nvs" 1931;
	setAttr ".tgi[0].ni[16].x" 6973.55126953125;
	setAttr ".tgi[0].ni[16].y" -1802.6575927734375;
	setAttr ".tgi[0].ni[16].nvs" 1931;
	setAttr ".tgi[0].ni[17].x" 6418.33056640625;
	setAttr ".tgi[0].ni[17].y" -1371.4744873046875;
	setAttr ".tgi[0].ni[17].nvs" 1931;
	setAttr ".tgi[0].ni[18].x" 7798.404296875;
	setAttr ".tgi[0].ni[18].y" -377.59814453125;
	setAttr ".tgi[0].ni[18].nvs" 1931;
	setAttr ".tgi[0].ni[19].x" 6839.8037109375;
	setAttr ".tgi[0].ni[19].y" -2771.804931640625;
	setAttr ".tgi[0].ni[19].nvs" 1931;
	setAttr ".tgi[0].ni[20].x" 7792.48828125;
	setAttr ".tgi[0].ni[20].y" -75.429618835449219;
	setAttr ".tgi[0].ni[20].nvs" 1931;
	setAttr ".tgi[0].ni[21].x" 4939.33837890625;
	setAttr ".tgi[0].ni[21].y" -2112.201416015625;
	setAttr ".tgi[0].ni[21].nvs" 1931;
	setAttr ".tgi[0].ni[22].x" 8121.8720703125;
	setAttr ".tgi[0].ni[22].y" 606.271728515625;
	setAttr ".tgi[0].ni[22].nvs" 1931;
	setAttr ".tgi[0].ni[23].x" 5264.82666015625;
	setAttr ".tgi[0].ni[23].y" -1810.151611328125;
	setAttr ".tgi[0].ni[23].nvs" 1931;
	setAttr ".tgi[0].ni[24].x" 7348.09765625;
	setAttr ".tgi[0].ni[24].y" -2060.945556640625;
	setAttr ".tgi[0].ni[24].nvs" 1931;
	setAttr ".tgi[0].ni[25].x" 7328.45458984375;
	setAttr ".tgi[0].ni[25].y" -3233.749755859375;
	setAttr ".tgi[0].ni[25].nvs" 1931;
	setAttr ".tgi[0].ni[26].x" 6109.2568359375;
	setAttr ".tgi[0].ni[26].y" -1835.840087890625;
	setAttr ".tgi[0].ni[26].nvs" 1931;
	setAttr ".tgi[0].ni[27].x" 7802.47314453125;
	setAttr ".tgi[0].ni[27].y" -895.567626953125;
	setAttr ".tgi[0].ni[27].nvs" 1931;
	setAttr ".tgi[0].ni[28].x" 9499.1337890625;
	setAttr ".tgi[0].ni[28].y" -418.22607421875;
	setAttr ".tgi[0].ni[28].nvs" 1931;
	setAttr ".tgi[0].ni[29].x" 8111.33154296875;
	setAttr ".tgi[0].ni[29].y" 901.49749755859375;
	setAttr ".tgi[0].ni[29].nvs" 1931;
	setAttr ".tgi[0].ni[30].x" 6994.57421875;
	setAttr ".tgi[0].ni[30].y" -648.01507568359375;
	setAttr ".tgi[0].ni[30].nvs" 1931;
	setAttr ".tgi[0].ni[31].x" 8603.876953125;
	setAttr ".tgi[0].ni[31].y" -1844.1790771484375;
	setAttr ".tgi[0].ni[31].nvs" 1931;
	setAttr ".tgi[0].ni[32].x" 6714.90478515625;
	setAttr ".tgi[0].ni[32].y" -1778.005615234375;
	setAttr ".tgi[0].ni[32].nvs" 1931;
	setAttr ".tgi[0].ni[33].x" 6410.14306640625;
	setAttr ".tgi[0].ni[33].y" -2173.21142578125;
	setAttr ".tgi[0].ni[33].nvs" 1931;
	setAttr ".tgi[0].ni[34].x" 7335.87060546875;
	setAttr ".tgi[0].ni[34].y" -2791.13916015625;
	setAttr ".tgi[0].ni[34].nvs" 1923;
	setAttr ".tgi[0].ni[35].x" 7332.91064453125;
	setAttr ".tgi[0].ni[35].y" -3003.315673828125;
	setAttr ".tgi[0].ni[35].nvs" 1923;
	setAttr ".tgi[0].ni[36].x" 8278.099609375;
	setAttr ".tgi[0].ni[36].y" -1631.3111572265625;
	setAttr ".tgi[0].ni[36].nvs" 1931;
	setAttr ".tgi[0].ni[37].x" 8260.0498046875;
	setAttr ".tgi[0].ni[37].y" -2662.89892578125;
	setAttr ".tgi[0].ni[37].nvs" 1931;
	setAttr ".tgi[0].ni[38].x" 7946.17138671875;
	setAttr ".tgi[0].ni[38].y" -2809.207275390625;
	setAttr ".tgi[0].ni[38].nvs" 1923;
	setAttr ".tgi[0].ni[39].x" 4932.76025390625;
	setAttr ".tgi[0].ni[39].y" -1845.923095703125;
	setAttr ".tgi[0].ni[39].nvs" 1931;
	setAttr ".tgi[0].ni[40].x" 5829.28271484375;
	setAttr ".tgi[0].ni[40].y" -2320.220703125;
	setAttr ".tgi[0].ni[40].nvs" 1931;
	setAttr ".tgi[0].ni[41].x" 7615.22509765625;
	setAttr ".tgi[0].ni[41].y" 342.17825317382812;
	setAttr ".tgi[0].ni[41].nvs" 1931;
	setAttr ".tgi[0].ni[42].x" 7347.361328125;
	setAttr ".tgi[0].ni[42].y" -1870.2113037109375;
	setAttr ".tgi[0].ni[42].nvs" 1931;
	setAttr ".tgi[0].ni[43].x" 9517.7470703125;
	setAttr ".tgi[0].ni[43].y" -721.1024169921875;
	setAttr ".tgi[0].ni[43].nvs" 18314;
	setAttr ".tgi[0].ni[44].x" 7635.7060546875;
	setAttr ".tgi[0].ni[44].y" -3207.754150390625;
	setAttr ".tgi[0].ni[44].nvs" 1931;
	setAttr ".tgi[0].ni[45].x" 7939.6103515625;
	setAttr ".tgi[0].ni[45].y" -3524.013916015625;
	setAttr ".tgi[0].ni[45].nvs" 1923;
	setAttr ".tgi[0].ni[46].x" 7943.23095703125;
	setAttr ".tgi[0].ni[46].y" -2592.96923828125;
	setAttr ".tgi[0].ni[46].nvs" 1923;
	setAttr ".tgi[0].ni[47].x" 7645.876953125;
	setAttr ".tgi[0].ni[47].y" -2735.1640625;
	setAttr ".tgi[0].ni[47].nvs" 1923;
	setAttr ".tgi[0].ni[48].x" 7403.99755859375;
	setAttr ".tgi[0].ni[48].y" 651.02301025390625;
	setAttr ".tgi[0].ni[48].nvs" 1931;
	setAttr ".tgi[0].ni[49].x" 6412.04248046875;
	setAttr ".tgi[0].ni[49].y" -2424.419921875;
	setAttr ".tgi[0].ni[49].nvs" 1931;
	setAttr ".tgi[0].ni[50].x" 7339.24755859375;
	setAttr ".tgi[0].ni[50].y" -2574.463623046875;
	setAttr ".tgi[0].ni[50].nvs" 1923;
	setAttr ".tgi[0].ni[51].x" 5827.271484375;
	setAttr ".tgi[0].ni[51].y" -1758.7890625;
	setAttr ".tgi[0].ni[51].nvs" 1931;
	setAttr ".tgi[0].ni[52].x" 6994.66748046875;
	setAttr ".tgi[0].ni[52].y" -229.58934020996094;
	setAttr ".tgi[0].ni[52].nvs" 1931;
	setAttr ".tgi[0].ni[53].x" 7332.23095703125;
	setAttr ".tgi[0].ni[53].y" -3424.087158203125;
	setAttr ".tgi[0].ni[53].nvs" 1931;
	setAttr ".tgi[0].ni[54].x" 8125.11181640625;
	setAttr ".tgi[0].ni[54].y" 312.43618774414062;
	setAttr ".tgi[0].ni[54].nvs" 1931;
	setAttr ".tgi[0].ni[55].x" 6701.30126953125;
	setAttr ".tgi[0].ni[55].y" -2279.535888671875;
	setAttr ".tgi[0].ni[55].nvs" 1931;
	setAttr ".tgi[0].ni[56].x" 8605.306640625;
	setAttr ".tgi[0].ni[56].y" -1674.0845947265625;
	setAttr ".tgi[0].ni[56].nvs" 1931;
	setAttr ".tgi[0].ni[57].x" 5550.69970703125;
	setAttr ".tgi[0].ni[57].y" -1646.4339599609375;
	setAttr ".tgi[0].ni[57].nvs" 1931;
	setAttr ".tgi[0].ni[58].x" 9916.4541015625;
	setAttr ".tgi[0].ni[58].y" -1201.2257080078125;
	setAttr ".tgi[0].ni[58].nvs" 1931;
	setAttr ".tgi[0].ni[59].x" 6120.54052734375;
	setAttr ".tgi[0].ni[59].y" -2101.580810546875;
	setAttr ".tgi[0].ni[59].nvs" 1931;
	setAttr ".tgi[0].ni[60].x" 6409.27099609375;
	setAttr ".tgi[0].ni[60].y" -1899.042236328125;
	setAttr ".tgi[0].ni[60].nvs" 1931;
	setAttr ".tgi[0].ni[61].x" 7644.58544921875;
	setAttr ".tgi[0].ni[61].y" -2506.1513671875;
	setAttr ".tgi[0].ni[61].nvs" 1923;
	setAttr ".tgi[0].ni[62].x" 6608.91162109375;
	setAttr ".tgi[0].ni[62].y" -1043.122314453125;
	setAttr ".tgi[0].ni[62].nvs" 1963;
	setAttr ".tgi[0].ni[63].x" 7657.4619140625;
	setAttr ".tgi[0].ni[63].y" -1793.1334228515625;
	setAttr ".tgi[0].ni[63].nvs" 1931;
	setAttr ".tgi[0].ni[64].x" 5585.962890625;
	setAttr ".tgi[0].ni[64].y" -2150.520751953125;
	setAttr ".tgi[0].ni[64].nvs" 1931;
	setAttr ".tgi[0].ni[65].x" 5280.591796875;
	setAttr ".tgi[0].ni[65].y" -1522.7947998046875;
	setAttr ".tgi[0].ni[65].nvs" 1931;
	setAttr ".tgi[0].ni[66].x" 7405.32666015625;
	setAttr ".tgi[0].ni[66].y" 855.94921875;
	setAttr ".tgi[0].ni[66].nvs" 1931;
	setAttr ".tgi[0].ni[67].x" 8949.3173828125;
	setAttr ".tgi[0].ni[67].y" -1520.3929443359375;
	setAttr ".tgi[0].ni[67].nvs" 1931;
	setAttr ".tgi[0].ni[68].x" 7948.93701171875;
	setAttr ".tgi[0].ni[68].y" -3022.001220703125;
	setAttr ".tgi[0].ni[68].nvs" 1923;
	setAttr ".tgi[0].ni[69].x" 9234.236328125;
	setAttr ".tgi[0].ni[69].y" -1378.6619873046875;
	setAttr ".tgi[0].ni[69].nvs" 1931;
	setAttr ".tgi[0].ni[70].x" 7802.85107421875;
	setAttr ".tgi[0].ni[70].y" -608.6353759765625;
	setAttr ".tgi[0].ni[70].nvs" 1931;
	setAttr ".tgi[0].ni[71].x" 6119.736328125;
	setAttr ".tgi[0].ni[71].y" -1535.839599609375;
	setAttr ".tgi[0].ni[71].nvs" 1931;
	setAttr ".tgi[0].ni[72].x" 6989.2275390625;
	setAttr ".tgi[0].ni[72].y" -13.298201560974121;
	setAttr ".tgi[0].ni[72].nvs" 1931;
	setAttr ".tgi[0].ni[73].x" 6107.69873046875;
	setAttr ".tgi[0].ni[73].y" -2373.693603515625;
	setAttr ".tgi[0].ni[73].nvs" 1931;
	setAttr ".tgi[0].ni[74].x" 8129.0185546875;
	setAttr ".tgi[0].ni[74].y" 80.377365112304688;
	setAttr ".tgi[0].ni[74].nvs" 1931;
	setAttr ".tgi[0].ni[75].x" 7652.46240234375;
	setAttr ".tgi[0].ni[75].y" -2981.854248046875;
	setAttr ".tgi[0].ni[75].nvs" 1923;
	setAttr ".tgi[0].ni[76].x" 7345.578125;
	setAttr ".tgi[0].ni[76].y" -2242.612060546875;
	setAttr ".tgi[0].ni[76].nvs" 1931;
	setAttr ".tgi[0].ni[77].x" 8278.2041015625;
	setAttr ".tgi[0].ni[77].y" -1762.13818359375;
	setAttr ".tgi[0].ni[77].nvs" 1931;
	setAttr ".tgi[0].ni[78].x" 6839.451171875;
	setAttr ".tgi[0].ni[78].y" -2655.14013671875;
	setAttr ".tgi[0].ni[78].nvs" 1931;
	setAttr ".tgi[1].tn" -type "string" "Untitled_1";
	setAttr ".tgi[1].vl" -type "double2" -801.64984455031572 -594.19101225483519 ;
	setAttr ".tgi[1].vh" -type "double2" 1264.2576802792144 572.99913771797765 ;
	setAttr -s 5 ".tgi[1].ni";
	setAttr ".tgi[1].ni[0].x" 228.21731567382812;
	setAttr ".tgi[1].ni[0].y" 195.71427917480469;
	setAttr ".tgi[1].ni[0].nvs" 1923;
	setAttr ".tgi[1].ni[1].x" -82.814353942871094;
	setAttr ".tgi[1].ni[1].y" -90;
	setAttr ".tgi[1].ni[1].nvs" 1923;
	setAttr ".tgi[1].ni[2].x" -410.1046142578125;
	setAttr ".tgi[1].ni[2].y" 128.06427001953125;
	setAttr ".tgi[1].ni[2].nvs" 1923;
	setAttr ".tgi[1].ni[3].x" -404.28570556640625;
	setAttr ".tgi[1].ni[3].y" -134.28572082519531;
	setAttr ".tgi[1].ni[3].nvs" 1923;
	setAttr ".tgi[1].ni[4].x" -85.714286804199219;
	setAttr ".tgi[1].ni[4].y" 108.57142639160156;
	setAttr ".tgi[1].ni[4].nvs" 1923;
createNode condition -n "condition48";
	rename -uid "BA4676FD-4FD2-418F-9876-9BB92FF47450";
	setAttr ".op" 2;
	setAttr ".st" 0.5;
createNode floatMath -n "mouthOpenY_bottomLimitMult_floatMath";
	rename -uid "97C990DD-45E1-E098-23E4-42AEBAEE5C60";
	setAttr "._fb" -1;
	setAttr "._cnd" 2;
createNode floatMath -n "mouthOpenY_bottomLimitAdd_floatMath";
	rename -uid "2138967C-4109-2E42-82D9-2D935A8B4879";
	setAttr "._fb" 0.5;
createNode condition -n "condition49";
	rename -uid "B046CA54-4EB4-FD56-C709-75B7D35CB711";
	setAttr ".cf" -type "float3" 0 -0.5 0 ;
createNode nodeGraphEditorInfo -n "MayaNodeEditorSavedTabsInfo";
	rename -uid "726C775F-463E-32ED-4828-9D88AD128AC1";
	setAttr -s 13 ".tgi";
	setAttr ".tgi[0].tn" -type "string" "nodes_leg_r";
	setAttr ".tgi[0].vl" -type "double2" -1588.0956007374614 -3082.1428499760955 ;
	setAttr ".tgi[0].vh" -type "double2" 12126.190420086434 632.14294733037627 ;
	setAttr -s 9 ".tgi[0].ni";
	setAttr ".tgi[0].ni[0].x" 6177.14306640625;
	setAttr ".tgi[0].ni[0].y" -1260;
	setAttr ".tgi[0].ni[0].nvs" 18304;
	setAttr ".tgi[0].ni[1].x" 4365.1103515625;
	setAttr ".tgi[0].ni[1].y" -1296.7777099609375;
	setAttr ".tgi[0].ni[1].nvs" 1923;
	setAttr ".tgi[0].ni[2].x" 5811.4287109375;
	setAttr ".tgi[0].ni[2].y" -938.5714111328125;
	setAttr ".tgi[0].ni[2].nvs" 18304;
	setAttr ".tgi[0].ni[3].x" 5255.71435546875;
	setAttr ".tgi[0].ni[3].y" -1260;
	setAttr ".tgi[0].ni[3].nvs" 18304;
	setAttr ".tgi[0].ni[4].x" 5562.85693359375;
	setAttr ".tgi[0].ni[4].y" -1288.5714111328125;
	setAttr ".tgi[0].ni[4].nvs" 18304;
	setAttr ".tgi[0].ni[5].x" 4990.2734375;
	setAttr ".tgi[0].ni[5].y" -1367.1910400390625;
	setAttr ".tgi[0].ni[5].nvs" 18304;
	setAttr ".tgi[0].ni[6].x" 4132.0849609375;
	setAttr ".tgi[0].ni[6].y" -970.178955078125;
	setAttr ".tgi[0].ni[6].nvs" 18304;
	setAttr ".tgi[0].ni[7].x" 5870;
	setAttr ".tgi[0].ni[7].y" -1260;
	setAttr ".tgi[0].ni[7].nvs" 18304;
	setAttr ".tgi[0].ni[8].x" 4444.28271484375;
	setAttr ".tgi[0].ni[8].y" -918.75042724609375;
	setAttr ".tgi[0].ni[8].nvs" 1923;
	setAttr ".tgi[1].tn" -type "string" "Untitled_1";
	setAttr ".tgi[1].vl" -type "double2" -7306.5477544589694 -1522.0238643506223 ;
	setAttr ".tgi[1].vh" -type "double2" 6407.7382663649269 2192.2619329558493 ;
	setAttr -s 2 ".tgi[1].ni";
	setAttr ".tgi[1].ni[0].x" -699.6005859375;
	setAttr ".tgi[1].ni[0].y" 387.33819580078125;
	setAttr ".tgi[1].ni[0].nvs" 1923;
	setAttr ".tgi[1].ni[1].x" -992.2510986328125;
	setAttr ".tgi[1].ni[1].y" 401.5927734375;
	setAttr ".tgi[1].ni[1].nvs" 18306;
	setAttr ".tgi[2].tn" -type "string" "Untitled_2";
	setAttr ".tgi[2].vl" -type "double2" -8107.7381988129991 -2010.1190830506989 ;
	setAttr ".tgi[2].vh" -type "double2" 5606.5478220108971 1704.1667142557728 ;
	setAttr ".tgi[3].tn" -type "string" "Untitled_3";
	setAttr ".tgi[3].vl" -type "double2" -6877.9763429174391 -1728.5714751907765 ;
	setAttr ".tgi[3].vh" -type "double2" 6836.3096779064563 1985.7143221156953 ;
	setAttr -s 2 ".tgi[3].ni";
	setAttr ".tgi[3].ni[0].x" -482.53323364257812;
	setAttr ".tgi[3].ni[0].y" -361.22799682617188;
	setAttr ".tgi[3].ni[0].nvs" 18306;
	setAttr ".tgi[3].ni[1].x" -486.13442993164062;
	setAttr ".tgi[3].ni[1].y" 253.19326782226562;
	setAttr ".tgi[3].ni[1].nvs" 18306;
	setAttr ".tgi[4].tn" -type "string" "Untitled_4";
	setAttr ".tgi[4].vl" -type "double2" -6503.5715958901856 -1717.2619518306528 ;
	setAttr ".tgi[4].vh" -type "double2" 7210.7144249337107 1997.0238454758189 ;
	setAttr -s 6 ".tgi[4].ni";
	setAttr ".tgi[4].ni[0].x" 1295.7142333984375;
	setAttr ".tgi[4].ni[0].y" 2908.571533203125;
	setAttr ".tgi[4].ni[0].nvs" 1923;
	setAttr ".tgi[4].ni[1].x" 988.5714111328125;
	setAttr ".tgi[4].ni[1].y" 3595.71435546875;
	setAttr ".tgi[4].ni[1].nvs" 1923;
	setAttr ".tgi[4].ni[2].x" 1602.857177734375;
	setAttr ".tgi[4].ni[2].y" 4888.5712890625;
	setAttr ".tgi[4].ni[2].nvs" 1923;
	setAttr ".tgi[4].ni[3].x" 1602.857177734375;
	setAttr ".tgi[4].ni[3].y" 5328.5712890625;
	setAttr ".tgi[4].ni[3].nvs" 1923;
	setAttr ".tgi[4].ni[4].x" -2014.2857666015625;
	setAttr ".tgi[4].ni[4].y" -478.57144165039062;
	setAttr ".tgi[4].ni[4].nvs" 18304;
	setAttr ".tgi[4].ni[5].x" 1295.7142333984375;
	setAttr ".tgi[4].ni[5].y" 4852.85693359375;
	setAttr ".tgi[4].ni[5].nvs" 1923;
	setAttr ".tgi[5].tn" -type "string" "Untitled_5";
	setAttr ".tgi[5].vl" -type "double2" -6942.2620546486687 -1844.0476610783562 ;
	setAttr ".tgi[5].vh" -type "double2" 6772.0239661752275 1870.2381362281155 ;
	setAttr -s 7 ".tgi[5].ni";
	setAttr ".tgi[5].ni[0].x" -687.09906005859375;
	setAttr ".tgi[5].ni[0].y" -12.651556968688965;
	setAttr ".tgi[5].ni[0].nvs" 18306;
	setAttr ".tgi[5].ni[1].x" 358.57144165039062;
	setAttr ".tgi[5].ni[1].y" 245.71427917480469;
	setAttr ".tgi[5].ni[1].nvs" 18304;
	setAttr ".tgi[5].ni[2].x" 358.57144165039062;
	setAttr ".tgi[5].ni[2].y" -404.28570556640625;
	setAttr ".tgi[5].ni[2].nvs" 18304;
	setAttr ".tgi[5].ni[3].x" 358.57144165039062;
	setAttr ".tgi[5].ni[3].y" -14.285714149475098;
	setAttr ".tgi[5].ni[3].nvs" 18304;
	setAttr ".tgi[5].ni[4].x" 358.57144165039062;
	setAttr ".tgi[5].ni[4].y" -274.28570556640625;
	setAttr ".tgi[5].ni[4].nvs" 18304;
	setAttr ".tgi[5].ni[5].x" 358.57144165039062;
	setAttr ".tgi[5].ni[5].y" 115.71428680419922;
	setAttr ".tgi[5].ni[5].nvs" 18304;
	setAttr ".tgi[5].ni[6].x" 358.57144165039062;
	setAttr ".tgi[5].ni[6].y" -144.28572082519531;
	setAttr ".tgi[5].ni[6].nvs" 18304;
	setAttr ".tgi[6].tn" -type "string" "Untitled_6";
	setAttr ".tgi[6].vl" -type "double2" -6638.690638140084 -1835.1190900045738 ;
	setAttr ".tgi[6].vh" -type "double2" 7075.5953826838122 1879.1667073018978 ;
	setAttr -s 3 ".tgi[6].ni";
	setAttr ".tgi[6].ni[0].x" -215.48788452148438;
	setAttr ".tgi[6].ni[0].y" 157.91050720214844;
	setAttr ".tgi[6].ni[0].nvs" 1923;
	setAttr ".tgi[6].ni[1].x" -529.3731689453125;
	setAttr ".tgi[6].ni[1].y" 266.96307373046875;
	setAttr ".tgi[6].ni[1].nvs" 18306;
	setAttr ".tgi[6].ni[2].x" -228.38653564453125;
	setAttr ".tgi[6].ni[2].y" -126.50729370117188;
	setAttr ".tgi[6].ni[2].nvs" 1923;
	setAttr ".tgi[7].tn" -type "string" "Untitled_7";
	setAttr ".tgi[7].vl" -type "double2" -6839.8811063359699 -1872.6190885144576 ;
	setAttr ".tgi[7].vh" -type "double2" 6874.4049144879255 1841.6667087920139 ;
	setAttr ".tgi[8].tn" -type "string" "mouth_nodes";
	setAttr ".tgi[8].vl" -type "double2" -5571.4287757873562 -2062.5000333502194 ;
	setAttr ".tgi[8].vh" -type "double2" 8142.85724503654 1651.7857639562521 ;
	setAttr ".tgi[8].ni[0].x" 1977.142822265625;
	setAttr ".tgi[8].ni[0].y" 332.85714721679688;
	setAttr ".tgi[8].ni[0].nvs" 18304;
	setAttr ".tgi[9].tn" -type "string" "Untitled_8";
	setAttr ".tgi[9].vl" -type "double2" -5179.1668866125383 -2791.666671042407 ;
	setAttr ".tgi[9].vh" -type "double2" 8535.119134211358 922.61912626406468 ;
	setAttr -s 8 ".tgi[9].ni";
	setAttr ".tgi[9].ni[0].x" -672.944091796875;
	setAttr ".tgi[9].ni[0].y" -170.12294006347656;
	setAttr ".tgi[9].ni[0].nvs" 1923;
	setAttr ".tgi[9].ni[1].x" 542.85711669921875;
	setAttr ".tgi[9].ni[1].y" -171.42857360839844;
	setAttr ".tgi[9].ni[1].nvs" 18304;
	setAttr ".tgi[9].ni[2].x" -74.790763854980469;
	setAttr ".tgi[9].ni[2].y" -425.03399658203125;
	setAttr ".tgi[9].ni[2].nvs" 1923;
	setAttr ".tgi[9].ni[3].x" -319.42526245117188;
	setAttr ".tgi[9].ni[3].y" 248.94038391113281;
	setAttr ".tgi[9].ni[3].nvs" 18306;
	setAttr ".tgi[9].ni[4].x" 251.72549438476562;
	setAttr ".tgi[9].ni[4].y" -414.6982421875;
	setAttr ".tgi[9].ni[4].nvs" 1923;
	setAttr ".tgi[9].ni[5].x" -307.3369140625;
	setAttr ".tgi[9].ni[5].y" -309.24600219726562;
	setAttr ".tgi[9].ni[5].nvs" 18306;
	setAttr ".tgi[9].ni[6].x" 255.49363708496094;
	setAttr ".tgi[9].ni[6].y" -49.189296722412109;
	setAttr ".tgi[9].ni[6].nvs" 1923;
	setAttr ".tgi[9].ni[7].x" -58.152957916259766;
	setAttr ".tgi[9].ni[7].y" -19.321784973144531;
	setAttr ".tgi[9].ni[7].nvs" 1923;
	setAttr ".tgi[10].tn" -type "string" "nodes_armor";
	setAttr ".tgi[10].vl" -type "double2" -5763.3239468102583 264.28570378394397 ;
	setAttr ".tgi[10].vh" -type "double2" 3331.1811863120706 2727.380844004575 ;
	setAttr -s 48 ".tgi[10].ni";
	setAttr ".tgi[10].ni[0].x" -2087.343017578125;
	setAttr ".tgi[10].ni[0].y" 1574.1798095703125;
	setAttr ".tgi[10].ni[0].nvs" 1923;
	setAttr ".tgi[10].ni[1].x" -371.00726318359375;
	setAttr ".tgi[10].ni[1].y" -1914.09912109375;
	setAttr ".tgi[10].ni[1].nvs" 1923;
	setAttr ".tgi[10].ni[2].x" -1161.029052734375;
	setAttr ".tgi[10].ni[2].y" 227.22349548339844;
	setAttr ".tgi[10].ni[2].nvs" 1923;
	setAttr ".tgi[10].ni[3].x" -1080.0106201171875;
	setAttr ".tgi[10].ni[3].y" -1518.4940185546875;
	setAttr ".tgi[10].ni[3].nvs" 1923;
	setAttr ".tgi[10].ni[4].x" -412.63259887695312;
	setAttr ".tgi[10].ni[4].y" 209.58502197265625;
	setAttr ".tgi[10].ni[4].nvs" 1923;
	setAttr ".tgi[10].ni[5].x" -1745.513427734375;
	setAttr ".tgi[10].ni[5].y" 1943.21484375;
	setAttr ".tgi[10].ni[5].nvs" 1923;
	setAttr ".tgi[10].ni[6].x" -1775.589111328125;
	setAttr ".tgi[10].ni[6].y" 1223.2052001953125;
	setAttr ".tgi[10].ni[6].nvs" 1923;
	setAttr ".tgi[10].ni[7].x" -2168.80908203125;
	setAttr ".tgi[10].ni[7].y" 2585.61767578125;
	setAttr ".tgi[10].ni[7].nvs" 1923;
	setAttr ".tgi[10].ni[8].x" -1515.679443359375;
	setAttr ".tgi[10].ni[8].y" -1755.423095703125;
	setAttr ".tgi[10].ni[8].nvs" 1923;
	setAttr ".tgi[10].ni[9].x" -2914.885009765625;
	setAttr ".tgi[10].ni[9].y" 1470.0838623046875;
	setAttr ".tgi[10].ni[9].nvs" 18306;
	setAttr ".tgi[10].ni[10].x" -1748.60888671875;
	setAttr ".tgi[10].ni[10].y" 1755.9652099609375;
	setAttr ".tgi[10].ni[10].nvs" 1923;
	setAttr ".tgi[10].ni[11].x" -2522.7353515625;
	setAttr ".tgi[10].ni[11].y" 2526.276123046875;
	setAttr ".tgi[10].ni[11].nvs" 1923;
	setAttr ".tgi[10].ni[12].x" -2025.0179443359375;
	setAttr ".tgi[10].ni[12].y" -143.8809814453125;
	setAttr ".tgi[10].ni[12].nvs" 1923;
	setAttr ".tgi[10].ni[13].x" -151.42857360839844;
	setAttr ".tgi[10].ni[13].y" 1721.4285888671875;
	setAttr ".tgi[10].ni[13].nvs" 18304;
	setAttr ".tgi[10].ni[14].x" -403.2918701171875;
	setAttr ".tgi[10].ni[14].y" -564.39739990234375;
	setAttr ".tgi[10].ni[14].nvs" 1923;
	setAttr ".tgi[10].ni[15].x" -1144.197509765625;
	setAttr ".tgi[10].ni[15].y" -949.3353271484375;
	setAttr ".tgi[10].ni[15].nvs" 1923;
	setAttr ".tgi[10].ni[16].x" -2097.580078125;
	setAttr ".tgi[10].ni[16].y" 360.41128540039062;
	setAttr ".tgi[10].ni[16].nvs" 18306;
	setAttr ".tgi[10].ni[17].x" -2014.6898193359375;
	setAttr ".tgi[10].ni[17].y" -455.3707275390625;
	setAttr ".tgi[10].ni[17].nvs" 1923;
	setAttr ".tgi[10].ni[18].x" -2098.96533203125;
	setAttr ".tgi[10].ni[18].y" 1373.045166015625;
	setAttr ".tgi[10].ni[18].nvs" 1923;
	setAttr ".tgi[10].ni[19].x" -1457.8756103515625;
	setAttr ".tgi[10].ni[19].y" 901.98785400390625;
	setAttr ".tgi[10].ni[19].nvs" 18306;
	setAttr ".tgi[10].ni[20].x" -1574.400146484375;
	setAttr ".tgi[10].ni[20].y" -1171.783447265625;
	setAttr ".tgi[10].ni[20].nvs" 1923;
	setAttr ".tgi[10].ni[21].x" -412.63259887695312;
	setAttr ".tgi[10].ni[21].y" -282.05825805664062;
	setAttr ".tgi[10].ni[21].nvs" 1923;
	setAttr ".tgi[10].ni[22].x" -402.23388671875;
	setAttr ".tgi[10].ni[22].y" -1331.9654541015625;
	setAttr ".tgi[10].ni[22].nvs" 1923;
	setAttr ".tgi[10].ni[23].x" -2162.3408203125;
	setAttr ".tgi[10].ni[23].y" 2340.181396484375;
	setAttr ".tgi[10].ni[23].nvs" 1923;
	setAttr ".tgi[10].ni[24].x" -415.33230590820312;
	setAttr ".tgi[10].ni[24].y" -22.471473693847656;
	setAttr ".tgi[10].ni[24].nvs" 1923;
	setAttr ".tgi[10].ni[25].x" 845.71429443359375;
	setAttr ".tgi[10].ni[25].y" -1061.4285888671875;
	setAttr ".tgi[10].ni[25].nvs" 18304;
	setAttr ".tgi[10].ni[26].x" -2509.224365234375;
	setAttr ".tgi[10].ni[26].y" 2285.350830078125;
	setAttr ".tgi[10].ni[26].nvs" 1923;
	setAttr ".tgi[10].ni[27].x" 845.71429443359375;
	setAttr ".tgi[10].ni[27].y" -1321.4285888671875;
	setAttr ".tgi[10].ni[27].nvs" 18304;
	setAttr ".tgi[10].ni[28].x" -371.00726318359375;
	setAttr ".tgi[10].ni[28].y" -1654.09912109375;
	setAttr ".tgi[10].ni[28].nvs" 1923;
	setAttr ".tgi[10].ni[29].x" -1689.1397705078125;
	setAttr ".tgi[10].ni[29].y" -102.16388702392578;
	setAttr ".tgi[10].ni[29].nvs" 1923;
	setAttr ".tgi[10].ni[30].x" -2185.71435546875;
	setAttr ".tgi[10].ni[30].y" 905.71429443359375;
	setAttr ".tgi[10].ni[30].nvs" 18304;
	setAttr ".tgi[10].ni[31].x" -2161.025634765625;
	setAttr ".tgi[10].ni[31].y" 2078.278564453125;
	setAttr ".tgi[10].ni[31].nvs" 1923;
	setAttr ".tgi[10].ni[32].x" -2504.204345703125;
	setAttr ".tgi[10].ni[32].y" 1197.728515625;
	setAttr ".tgi[10].ni[32].nvs" 1923;
	setAttr ".tgi[10].ni[33].x" -1669.7515869140625;
	setAttr ".tgi[10].ni[33].y" -587.68817138671875;
	setAttr ".tgi[10].ni[33].nvs" 1923;
	setAttr ".tgi[10].ni[34].x" -2163.67578125;
	setAttr ".tgi[10].ni[34].y" 2817.769287109375;
	setAttr ".tgi[10].ni[34].nvs" 1923;
	setAttr ".tgi[10].ni[35].x" -2498.177734375;
	setAttr ".tgi[10].ni[35].y" 1712.298828125;
	setAttr ".tgi[10].ni[35].nvs" 1923;
	setAttr ".tgi[10].ni[36].x" -2496.595947265625;
	setAttr ".tgi[10].ni[36].y" 1441.7066650390625;
	setAttr ".tgi[10].ni[36].nvs" 18306;
	setAttr ".tgi[10].ni[37].x" -397.16732788085938;
	setAttr ".tgi[10].ni[37].y" -1068.6943359375;
	setAttr ".tgi[10].ni[37].nvs" 1923;
	setAttr ".tgi[10].ni[38].x" -2506.60302734375;
	setAttr ".tgi[10].ni[38].y" 2019.4232177734375;
	setAttr ".tgi[10].ni[38].nvs" 1923;
	setAttr ".tgi[10].ni[39].x" -151.42857360839844;
	setAttr ".tgi[10].ni[39].y" 1591.4285888671875;
	setAttr ".tgi[10].ni[39].nvs" 18304;
	setAttr ".tgi[10].ni[40].x" -1671.1104736328125;
	setAttr ".tgi[10].ni[40].y" 723.175048828125;
	setAttr ".tgi[10].ni[40].nvs" 18306;
	setAttr ".tgi[10].ni[41].x" -393.1181640625;
	setAttr ".tgi[10].ni[41].y" -819.24420166015625;
	setAttr ".tgi[10].ni[41].nvs" 1923;
	setAttr ".tgi[10].ni[42].x" -1918.7177734375;
	setAttr ".tgi[10].ni[42].y" -1417.1259765625;
	setAttr ".tgi[10].ni[42].nvs" 1923;
	setAttr ".tgi[10].ni[43].x" -1736.2271728515625;
	setAttr ".tgi[10].ni[43].y" 2133.56787109375;
	setAttr ".tgi[10].ni[43].nvs" 1923;
	setAttr ".tgi[10].ni[44].x" -1947.348876953125;
	setAttr ".tgi[10].ni[44].y" -932.88482666015625;
	setAttr ".tgi[10].ni[44].nvs" 1923;
	setAttr ".tgi[10].ni[45].x" -258.57144165039062;
	setAttr ".tgi[10].ni[45].y" 2454.28564453125;
	setAttr ".tgi[10].ni[45].nvs" 18304;
	setAttr ".tgi[10].ni[46].x" -1874.0689697265625;
	setAttr ".tgi[10].ni[46].y" 541.79315185546875;
	setAttr ".tgi[10].ni[46].nvs" 18306;
	setAttr ".tgi[10].ni[47].x" -1176.2025146484375;
	setAttr ".tgi[10].ni[47].y" -417.6365966796875;
	setAttr ".tgi[10].ni[47].nvs" 1923;
	setAttr ".tgi[11].tn" -type "string" "Untitled_9";
	setAttr ".tgi[11].vl" -type "double2" -1309.4338903985454 1018.281633640993 ;
	setAttr ".tgi[11].vh" -type "double2" 768.53128757643981 2033.2541207821362 ;
	setAttr -s 37 ".tgi[11].ni";
	setAttr ".tgi[11].ni[0].x" -1613.6673583984375;
	setAttr ".tgi[11].ni[0].y" 1007.9954833984375;
	setAttr ".tgi[11].ni[0].nvs" 1923;
	setAttr ".tgi[11].ni[1].x" -979.70501708984375;
	setAttr ".tgi[11].ni[1].y" 1407.0621337890625;
	setAttr ".tgi[11].ni[1].nvs" 1923;
	setAttr ".tgi[11].ni[2].x" -2419.343017578125;
	setAttr ".tgi[11].ni[2].y" 702.77056884765625;
	setAttr ".tgi[11].ni[2].nvs" 1923;
	setAttr ".tgi[11].ni[3].x" -3096.912841796875;
	setAttr ".tgi[11].ni[3].y" 1283.4703369140625;
	setAttr ".tgi[11].ni[3].nvs" 18314;
	setAttr ".tgi[11].ni[4].x" -1932.76220703125;
	setAttr ".tgi[11].ni[4].y" 106.40505218505859;
	setAttr ".tgi[11].ni[4].nvs" 18306;
	setAttr ".tgi[11].ni[5].x" -2488.54443359375;
	setAttr ".tgi[11].ni[5].y" 1517.589599609375;
	setAttr ".tgi[11].ni[5].nvs" 1923;
	setAttr ".tgi[11].ni[6].x" 195.92857360839844;
	setAttr ".tgi[11].ni[6].y" 1364.91748046875;
	setAttr ".tgi[11].ni[6].nvs" 1923;
	setAttr ".tgi[11].ni[7].x" -1587.64404296875;
	setAttr ".tgi[11].ni[7].y" 1852.75146484375;
	setAttr ".tgi[11].ni[7].nvs" 1931;
	setAttr ".tgi[11].ni[8].x" -1945.765869140625;
	setAttr ".tgi[11].ni[8].y" 331.6412353515625;
	setAttr ".tgi[11].ni[8].nvs" 1923;
	setAttr ".tgi[11].ni[9].x" -193.8173828125;
	setAttr ".tgi[11].ni[9].y" 1069.725830078125;
	setAttr ".tgi[11].ni[9].nvs" 1931;
	setAttr ".tgi[11].ni[10].x" -1919.2685546875;
	setAttr ".tgi[11].ni[10].y" 957.9541015625;
	setAttr ".tgi[11].ni[10].nvs" 1923;
	setAttr ".tgi[11].ni[11].x" -1614.439697265625;
	setAttr ".tgi[11].ni[11].y" 788.04833984375;
	setAttr ".tgi[11].ni[11].nvs" 1923;
	setAttr ".tgi[11].ni[12].x" -1607.8271484375;
	setAttr ".tgi[11].ni[12].y" 259.00283813476562;
	setAttr ".tgi[11].ni[12].nvs" 1923;
	setAttr ".tgi[11].ni[13].x" -984.3916015625;
	setAttr ".tgi[11].ni[13].y" 1097.9208984375;
	setAttr ".tgi[11].ni[13].nvs" 1923;
	setAttr ".tgi[11].ni[14].x" -2221.097900390625;
	setAttr ".tgi[11].ni[14].y" 959.48052978515625;
	setAttr ".tgi[11].ni[14].nvs" 1923;
	setAttr ".tgi[11].ni[15].x" -1888.3345947265625;
	setAttr ".tgi[11].ni[15].y" 796.3853759765625;
	setAttr ".tgi[11].ni[15].nvs" 1923;
	setAttr ".tgi[11].ni[16].x" 187.00146484375;
	setAttr ".tgi[11].ni[16].y" 1074.0250244140625;
	setAttr ".tgi[11].ni[16].nvs" 1923;
	setAttr ".tgi[11].ni[17].x" -1606.5626220703125;
	setAttr ".tgi[11].ni[17].y" 499.71194458007812;
	setAttr ".tgi[11].ni[17].nvs" 1923;
	setAttr ".tgi[11].ni[18].x" -2781.188720703125;
	setAttr ".tgi[11].ni[18].y" 361.83721923828125;
	setAttr ".tgi[11].ni[18].nvs" 1923;
	setAttr ".tgi[11].ni[19].x" -1584.42529296875;
	setAttr ".tgi[11].ni[19].y" 2086.153564453125;
	setAttr ".tgi[11].ni[19].nvs" 1931;
	setAttr ".tgi[11].ni[20].x" 190.70451354980469;
	setAttr ".tgi[11].ni[20].y" 1693.3837890625;
	setAttr ".tgi[11].ni[20].nvs" 1923;
	setAttr ".tgi[11].ni[21].x" -2515.066650390625;
	setAttr ".tgi[11].ni[21].y" 433.26409912109375;
	setAttr ".tgi[11].ni[21].nvs" 1923;
	setAttr ".tgi[11].ni[22].x" -2188.6142578125;
	setAttr ".tgi[11].ni[22].y" 701.77740478515625;
	setAttr ".tgi[11].ni[22].nvs" 1923;
	setAttr ".tgi[11].ni[23].x" -1226.04345703125;
	setAttr ".tgi[11].ni[23].y" 1341.0557861328125;
	setAttr ".tgi[11].ni[23].nvs" 1931;
	setAttr ".tgi[11].ni[24].x" -978.35272216796875;
	setAttr ".tgi[11].ni[24].y" 1721.426025390625;
	setAttr ".tgi[11].ni[24].nvs" 1923;
	setAttr ".tgi[11].ni[25].x" -778.5714111328125;
	setAttr ".tgi[11].ni[25].y" 165.71427917480469;
	setAttr ".tgi[11].ni[25].nvs" 18304;
	setAttr ".tgi[11].ni[26].x" -3083.02490234375;
	setAttr ".tgi[11].ni[26].y" 845.5479736328125;
	setAttr ".tgi[11].ni[26].nvs" 1931;
	setAttr ".tgi[11].ni[27].x" -1590.400390625;
	setAttr ".tgi[11].ni[27].y" 1616.267333984375;
	setAttr ".tgi[11].ni[27].nvs" 1931;
	setAttr ".tgi[11].ni[28].x" -2771.4951171875;
	setAttr ".tgi[11].ni[28].y" 1182.692626953125;
	setAttr ".tgi[11].ni[28].nvs" 1931;
	setAttr ".tgi[11].ni[29].x" -3077.145751953125;
	setAttr ".tgi[11].ni[29].y" 608.94696044921875;
	setAttr ".tgi[11].ni[29].nvs" 1931;
	setAttr ".tgi[11].ni[30].x" -164.86030578613281;
	setAttr ".tgi[11].ni[30].y" 1687.1583251953125;
	setAttr ".tgi[11].ni[30].nvs" 1931;
	setAttr ".tgi[11].ni[31].x" -3443.96142578125;
	setAttr ".tgi[11].ni[31].y" 941.527099609375;
	setAttr ".tgi[11].ni[31].nvs" 1923;
	setAttr ".tgi[11].ni[32].x" -1229.210693359375;
	setAttr ".tgi[11].ni[32].y" 1114.7806396484375;
	setAttr ".tgi[11].ni[32].nvs" 1931;
	setAttr ".tgi[11].ni[33].x" -178.38630676269531;
	setAttr ".tgi[11].ni[33].y" 1368.15380859375;
	setAttr ".tgi[11].ni[33].nvs" 1931;
	setAttr ".tgi[11].ni[34].x" -2239.45263671875;
	setAttr ".tgi[11].ni[34].y" 337.77731323242188;
	setAttr ".tgi[11].ni[34].nvs" 1923;
	setAttr ".tgi[11].ni[35].x" -2283.904296875;
	setAttr ".tgi[11].ni[35].y" 1202.2449951171875;
	setAttr ".tgi[11].ni[35].nvs" 1931;
	setAttr ".tgi[11].ni[36].x" -3097.260009765625;
	setAttr ".tgi[11].ni[36].y" 1769.2861328125;
	setAttr ".tgi[11].ni[36].nvs" 18314;
	setAttr ".tgi[12].tn" -type "string" "Untitled_10";
	setAttr ".tgi[12].vl" -type "double2" -2865.7521169578249 -947.39206195904455 ;
	setAttr ".tgi[12].vh" -type "double2" 5978.8101147935768 3372.6934819111261 ;
	setAttr -s 30 ".tgi[12].ni";
	setAttr ".tgi[12].ni[0].x" -445.68850708007812;
	setAttr ".tgi[12].ni[0].y" 272.73348999023438;
	setAttr ".tgi[12].ni[0].nvs" 18314;
	setAttr ".tgi[12].ni[1].x" 1295.2392578125;
	setAttr ".tgi[12].ni[1].y" 1345.6568603515625;
	setAttr ".tgi[12].ni[1].nvs" 1923;
	setAttr ".tgi[12].ni[2].x" 1557.1697998046875;
	setAttr ".tgi[12].ni[2].y" 1272.3465576171875;
	setAttr ".tgi[12].ni[2].nvs" 1923;
	setAttr ".tgi[12].ni[3].x" 1275.0679931640625;
	setAttr ".tgi[12].ni[3].y" 630.22540283203125;
	setAttr ".tgi[12].ni[3].nvs" 1923;
	setAttr ".tgi[12].ni[4].x" 1603.1131591796875;
	setAttr ".tgi[12].ni[4].y" 674.2344970703125;
	setAttr ".tgi[12].ni[4].nvs" 1923;
	setAttr ".tgi[12].ni[5].x" 641.46807861328125;
	setAttr ".tgi[12].ni[5].y" 1244.5189208984375;
	setAttr ".tgi[12].ni[5].nvs" 18306;
	setAttr ".tgi[12].ni[6].x" 67.140739440917969;
	setAttr ".tgi[12].ni[6].y" 28.781990051269531;
	setAttr ".tgi[12].ni[6].nvs" 1931;
	setAttr ".tgi[12].ni[7].x" -190.49418640136719;
	setAttr ".tgi[12].ni[7].y" 1038.9620361328125;
	setAttr ".tgi[12].ni[7].nvs" 18306;
	setAttr ".tgi[12].ni[8].x" 356.88888549804688;
	setAttr ".tgi[12].ni[8].y" -84.621818542480469;
	setAttr ".tgi[12].ni[8].nvs" 1931;
	setAttr ".tgi[12].ni[9].x" 640.27227783203125;
	setAttr ".tgi[12].ni[9].y" 1769.601806640625;
	setAttr ".tgi[12].ni[9].nvs" 18306;
	setAttr ".tgi[12].ni[10].x" 960.14495849609375;
	setAttr ".tgi[12].ni[10].y" 1630.9832763671875;
	setAttr ".tgi[12].ni[10].nvs" 18306;
	setAttr ".tgi[12].ni[11].x" 1146.9713134765625;
	setAttr ".tgi[12].ni[11].y" 228.80061340332031;
	setAttr ".tgi[12].ni[11].nvs" 1923;
	setAttr ".tgi[12].ni[12].x" 68.535614013671875;
	setAttr ".tgi[12].ni[12].y" 566.9554443359375;
	setAttr ".tgi[12].ni[12].nvs" 1923;
	setAttr ".tgi[12].ni[13].x" 972.89105224609375;
	setAttr ".tgi[12].ni[13].y" 556.4359130859375;
	setAttr ".tgi[12].ni[13].nvs" 18306;
	setAttr ".tgi[12].ni[14].x" 356.78369140625;
	setAttr ".tgi[12].ni[14].y" 416.94674682617188;
	setAttr ".tgi[12].ni[14].nvs" 1931;
	setAttr ".tgi[12].ni[15].x" 361.05047607421875;
	setAttr ".tgi[12].ni[15].y" 1613.85986328125;
	setAttr ".tgi[12].ni[15].nvs" 1923;
	setAttr ".tgi[12].ni[16].x" -448.5347900390625;
	setAttr ".tgi[12].ni[16].y" 1267.148681640625;
	setAttr ".tgi[12].ni[16].nvs" 18306;
	setAttr ".tgi[12].ni[17].x" -198.61836242675781;
	setAttr ".tgi[12].ni[17].y" -119.94354248046875;
	setAttr ".tgi[12].ni[17].nvs" 18314;
	setAttr ".tgi[12].ni[18].x" 357.11965942382812;
	setAttr ".tgi[12].ni[18].y" 1096.39208984375;
	setAttr ".tgi[12].ni[18].nvs" 1923;
	setAttr ".tgi[12].ni[19].x" 1557.1697998046875;
	setAttr ".tgi[12].ni[19].y" 1532.3465576171875;
	setAttr ".tgi[12].ni[19].nvs" 1923;
	setAttr ".tgi[12].ni[20].x" 71.250221252441406;
	setAttr ".tgi[12].ni[20].y" 1714.0439453125;
	setAttr ".tgi[12].ni[20].nvs" 1923;
	setAttr ".tgi[12].ni[21].x" -1038.9407958984375;
	setAttr ".tgi[12].ni[21].y" 308.47943115234375;
	setAttr ".tgi[12].ni[21].nvs" 18314;
	setAttr ".tgi[12].ni[22].x" -446.07073974609375;
	setAttr ".tgi[12].ni[22].y" 758.85748291015625;
	setAttr ".tgi[12].ni[22].nvs" 18306;
	setAttr ".tgi[12].ni[23].x" 78.056053161621094;
	setAttr ".tgi[12].ni[23].y" 1161.443359375;
	setAttr ".tgi[12].ni[23].nvs" 1923;
	setAttr ".tgi[12].ni[24].x" 968.31353759765625;
	setAttr ".tgi[12].ni[24].y" 1156.9351806640625;
	setAttr ".tgi[12].ni[24].nvs" 18306;
	setAttr ".tgi[12].ni[25].x" 632.3118896484375;
	setAttr ".tgi[12].ni[25].y" 622.7598876953125;
	setAttr ".tgi[12].ni[25].nvs" 18306;
	setAttr ".tgi[12].ni[26].x" -189.14533996582031;
	setAttr ".tgi[12].ni[26].y" 1560.9451904296875;
	setAttr ".tgi[12].ni[26].nvs" 18306;
	setAttr ".tgi[12].ni[27].x" 1291.10546875;
	setAttr ".tgi[12].ni[27].y" 1640.6953125;
	setAttr ".tgi[12].ni[27].nvs" 1923;
	setAttr ".tgi[12].ni[28].x" -446.32803344726562;
	setAttr ".tgi[12].ni[28].y" 1752.251953125;
	setAttr ".tgi[12].ni[28].nvs" 18306;
	setAttr ".tgi[12].ni[29].x" -196.08412170410156;
	setAttr ".tgi[12].ni[29].y" 421.3138427734375;
	setAttr ".tgi[12].ni[29].nvs" 18306;
createNode multMatrix -n "multMatrix_matrixConstraint";
	rename -uid "4DC37DD5-487F-2980-7F83-27A93DE4FAB7";
	setAttr -s 3 ".i";
createNode composeMatrix -n "composeMatrix_matrixConstraint";
	rename -uid "2F16609A-4D7E-62A2-CE03-29A5E6013587";
	setAttr ".it" -type "double3" 0 10 0 ;
createNode multMatrix -n "multMatrix_matrixConstraint1";
	rename -uid "C5DEED67-4E57-285E-6030-BBA2F1936BA6";
	setAttr -s 3 ".i";
createNode composeMatrix -n "composeMatrix_matrixConstraint1";
	rename -uid "342D7BD5-447C-A5E0-7176-7C9ED54729FC";
	setAttr ".it" -type "double3" 0 -14 0 ;
select -ne :time1;
	setAttr -av -k on ".cch";
	setAttr -av -cb on ".ihi";
	setAttr -av -k on ".nds";
	setAttr -cb on ".bnm";
	setAttr -k on ".o" 0;
	setAttr -av -k on ".unw";
	setAttr -av -k on ".etw";
	setAttr -av -k on ".tps";
	setAttr -av -k on ".tms";
select -ne :hardwareRenderingGlobals;
	setAttr -av -k on ".ihi";
	setAttr ".otfna" -type "stringArray" 22 "NURBS Curves" "NURBS Surfaces" "Polygons" "Subdiv Surface" "Particles" "Particle Instance" "Fluids" "Strokes" "Image Planes" "UI" "Lights" "Cameras" "Locators" "Joints" "IK Handles" "Deformers" "Motion Trails" "Components" "Hair Systems" "Follicles" "Misc. UI" "Ornaments"  ;
	setAttr ".otfva" -type "Int32Array" 22 0 1 1 1 1 1
		 1 1 1 0 0 0 0 0 0 0 0 0
		 0 0 0 0 ;
	setAttr -k on ".hwi";
	setAttr -av ".ta" 5;
	setAttr -av ".tq";
	setAttr -av ".etmr";
	setAttr -av ".aoon";
	setAttr -av ".aoam";
	setAttr -av ".aora";
	setAttr -k on ".hff";
	setAttr -av -k on ".hfd";
	setAttr -av -k on ".hfs";
	setAttr -av -k on ".hfe";
	setAttr -av -k on ".hfcr";
	setAttr -av -k on ".hfcg";
	setAttr -av -k on ".hfcb";
	setAttr -av -k on ".hfa";
	setAttr -av ".mbe";
	setAttr -av -k on ".mbsof";
	setAttr -k on ".blen";
	setAttr -k on ".blat";
	setAttr ".fprt" yes;
select -ne :renderPartition;
	setAttr -av -k on ".cch";
	setAttr -cb on ".ihi";
	setAttr -av -k on ".nds";
	setAttr -cb on ".bnm";
	setAttr -s 12 ".st";
	setAttr -cb on ".an";
	setAttr -cb on ".pt";
select -ne :renderGlobalsList1;
	setAttr -k on ".cch";
	setAttr -cb on ".ihi";
	setAttr -k on ".nds";
	setAttr -cb on ".bnm";
select -ne :defaultShaderList1;
	setAttr -k on ".cch";
	setAttr -cb on ".ihi";
	setAttr -k on ".nds";
	setAttr -cb on ".bnm";
	setAttr -s 9 ".s";
select -ne :postProcessList1;
	setAttr -k on ".cch";
	setAttr -cb on ".ihi";
	setAttr -k on ".nds";
	setAttr -cb on ".bnm";
	setAttr -s 2 ".p";
select -ne :defaultRenderUtilityList1;
	setAttr -k on ".cch";
	setAttr -cb on ".ihi";
	setAttr -av -k on ".nds";
	setAttr -cb on ".bnm";
	setAttr -s 261 ".u";
select -ne :defaultRenderingList1;
	setAttr -k on ".ihi";
select -ne :defaultTextureList1;
	setAttr -k on ".cch";
	setAttr -cb on ".ihi";
	setAttr -k on ".nds";
	setAttr -cb on ".bnm";
	setAttr -s 21 ".tx";
select -ne :initialShadingGroup;
	setAttr -av -k on ".cch";
	setAttr -k on ".fzn";
	setAttr -cb on ".ihi";
	setAttr -av -k on ".nds";
	setAttr -cb on ".bnm";
	setAttr -k on ".bbx";
	setAttr -k on ".vwm";
	setAttr -k on ".tpv";
	setAttr -k on ".uit";
	setAttr -k on ".mwc";
	setAttr -cb on ".an";
	setAttr -cb on ".il";
	setAttr -cb on ".vo";
	setAttr -cb on ".eo";
	setAttr -cb on ".fo";
	setAttr -cb on ".epo";
	setAttr -k on ".ro" yes;
	setAttr -k on ".ai_surface_shader";
	setAttr -k on ".ai_volume_shader";
select -ne :initialParticleSE;
	setAttr -av -k on ".cch";
	setAttr -cb on ".ihi";
	setAttr -av -k on ".nds";
	setAttr -cb on ".bnm";
	setAttr -k on ".mwc";
	setAttr -cb on ".an";
	setAttr -cb on ".il";
	setAttr -cb on ".vo";
	setAttr -cb on ".eo";
	setAttr -cb on ".fo";
	setAttr -cb on ".epo";
	setAttr -k on ".ro" yes;
	setAttr -k on ".ai_surface_shader";
	setAttr -k on ".ai_volume_shader";
select -ne :defaultRenderGlobals;
	addAttr -ci true -h true -sn "dss" -ln "defaultSurfaceShader" -dt "string";
	setAttr -av -k on ".cch";
	setAttr -cb on ".ihi";
	setAttr -av -k on ".nds";
	setAttr -cb on ".bnm";
	setAttr -av -cb on ".macc";
	setAttr -av -cb on ".macd";
	setAttr -av -cb on ".macq";
	setAttr -av -k on ".mcfr";
	setAttr -cb on ".ifg";
	setAttr -av -k on ".clip";
	setAttr -av -k on ".edm";
	setAttr -av -k on ".edl";
	setAttr -av -cb on ".ren" -type "string" "redshift";
	setAttr -av -k on ".esr";
	setAttr -av -k on ".ors";
	setAttr -cb on ".sdf";
	setAttr -av -k on ".outf" 51;
	setAttr -av -cb on ".imfkey" -type "string" "iff";
	setAttr -av -k on ".gama";
	setAttr -av -cb on ".an";
	setAttr -cb on ".ar";
	setAttr -av -k on ".fs";
	setAttr -av -k on ".ef";
	setAttr -av -k on ".bfs";
	setAttr -cb on ".me";
	setAttr -cb on ".se";
	setAttr -av -k on ".be";
	setAttr -av -cb on ".ep";
	setAttr -av -k on ".fec";
	setAttr -av -k on ".ofc";
	setAttr -cb on ".ofe";
	setAttr -cb on ".efe";
	setAttr -cb on ".oft";
	setAttr -cb on ".umfn";
	setAttr -cb on ".ufe";
	setAttr -av -cb on ".pff" yes;
	setAttr -av -cb on ".peie";
	setAttr -av -cb on ".ifp";
	setAttr -k on ".rv";
	setAttr -av -k on ".comp";
	setAttr -av -k on ".cth";
	setAttr -av -k on ".soll";
	setAttr -cb on ".sosl";
	setAttr -av -k on ".rd";
	setAttr -av -k on ".lp";
	setAttr -av -k on ".sp";
	setAttr -av -k on ".shs";
	setAttr -av -k on ".lpr";
	setAttr -cb on ".gv";
	setAttr -cb on ".sv";
	setAttr -av -k on ".mm";
	setAttr -av -k on ".npu";
	setAttr -av -k on ".itf";
	setAttr -av -k on ".shp";
	setAttr -cb on ".isp";
	setAttr -av -k on ".uf";
	setAttr -av -k on ".oi";
	setAttr -av -k on ".rut";
	setAttr -av -k on ".mot";
	setAttr -av -k on ".mb";
	setAttr -av -k on ".mbf";
	setAttr -av -k on ".mbso";
	setAttr -av -k on ".mbsc";
	setAttr -av -k on ".afp";
	setAttr -av -k on ".pfb";
	setAttr -k on ".pram";
	setAttr -k on ".poam";
	setAttr -k on ".prlm";
	setAttr -k on ".polm";
	setAttr -cb on ".prm";
	setAttr -cb on ".pom";
	setAttr -cb on ".pfrm";
	setAttr -cb on ".pfom";
	setAttr -av -k on ".bll";
	setAttr -av -k on ".bls";
	setAttr -av -k on ".smv";
	setAttr -av -k on ".ubc";
	setAttr -av -k on ".mbc";
	setAttr -cb on ".mbt";
	setAttr -av -k on ".udbx";
	setAttr -av -k on ".smc";
	setAttr -av -k on ".kmv";
	setAttr -cb on ".isl";
	setAttr -cb on ".ism";
	setAttr -cb on ".imb";
	setAttr -av -k on ".rlen";
	setAttr -av -k on ".frts";
	setAttr -av -k on ".tlwd";
	setAttr -av -k on ".tlht";
	setAttr -av -k on ".jfc";
	setAttr -cb on ".rsb";
	setAttr -av -cb on ".ope";
	setAttr -av -cb on ".oppf";
	setAttr -av -k on ".rcp";
	setAttr -av -k on ".icp";
	setAttr -av -k on ".ocp";
	setAttr -cb on ".hbl" -type "string" "";
	setAttr ".dss" -type "string" "lambert1";
select -ne :defaultResolution;
	setAttr -av -k on ".cch";
	setAttr -av -k on ".ihi";
	setAttr -av -k on ".nds";
	setAttr -k on ".bnm";
	setAttr -av -k on ".w";
	setAttr -av -k on ".h";
	setAttr -av -k on ".pa" 1;
	setAttr -av -k on ".al";
	setAttr -av -k on ".dar";
	setAttr -av -k on ".ldar";
	setAttr -av -k on ".dpi";
	setAttr -av -k on ".off";
	setAttr -av -k on ".fld";
	setAttr -av -k on ".zsl";
	setAttr -av -k on ".isu";
	setAttr -av -k on ".pdu";
select -ne :defaultColorMgtGlobals;
	setAttr ".cfe" yes;
	setAttr ".cfp" -type "string" "C:/ProgramData/Redshift/Data/OCIO/config.ocio";
	setAttr ".vtn" -type "string" "Un-tone-mapped (sRGB)";
	setAttr ".vn" -type "string" "Un-tone-mapped";
	setAttr ".dn" -type "string" "sRGB";
	setAttr ".wsn" -type "string" "scene-linear Rec.709-sRGB";
	setAttr ".ovt" no;
	setAttr ".povt" no;
	setAttr ".otn" -type "string" "ACES 1.0 SDR-video (sRGB)";
	setAttr ".potn" -type "string" "ACES 1.0 SDR-video (sRGB)";
select -ne :hardwareRenderGlobals;
	setAttr -av -k on ".cch";
	setAttr -cb on ".ihi";
	setAttr -av -k on ".nds";
	setAttr -cb on ".bnm";
	setAttr -av -k off -cb on ".ctrs" 256;
	setAttr -av -k off -cb on ".btrs" 512;
	setAttr -av -k off -cb on ".fbfm";
	setAttr -av -k off -cb on ".ehql";
	setAttr -av -k off -cb on ".eams";
	setAttr -av -k off -cb on ".eeaa";
	setAttr -av -k off -cb on ".engm";
	setAttr -av -k off -cb on ".mes";
	setAttr -av -k off -cb on ".emb";
	setAttr -av -k off -cb on ".mbbf";
	setAttr -av -k off -cb on ".mbs";
	setAttr -av -k off -cb on ".trm";
	setAttr -av -k off -cb on ".tshc";
	setAttr -av -k off -cb on ".enpt";
	setAttr -av -k off -cb on ".clmt";
	setAttr -av -k off -cb on ".tcov";
	setAttr -av -k off -cb on ".lith";
	setAttr -av -k off -cb on ".sobc";
	setAttr -av -k off -cb on ".cuth";
	setAttr -av -k off -cb on ".hgcd";
	setAttr -av -k off -cb on ".hgci";
	setAttr -av -k off -cb on ".mgcs";
	setAttr -av -k off -cb on ".twa";
	setAttr -av -k off -cb on ".twz";
	setAttr -k on ".hwcc";
	setAttr -k on ".hwdp";
	setAttr -k on ".hwql";
	setAttr -k on ".hwfr";
	setAttr -k on ".soll";
	setAttr -k on ".sosl";
	setAttr -k on ".bswa";
	setAttr -k on ".shml";
	setAttr -k on ".hwel";
select -ne :ikSystem;
	setAttr -k on ".cch";
	setAttr -k on ".ihi";
	setAttr -k on ".nds";
	setAttr -k on ".bnm";
	setAttr -av -k on ".gsn";
	setAttr -k on ".gsv";
	setAttr -s 6 ".sol";
connectAttr "polyTweakUV2.out" "mesh_bodyShape.i";
connectAttr "skinCluster3GroupId.id" "mesh_bodyShape.iog.og[3].gid";
connectAttr "skinCluster3Set.mwc" "mesh_bodyShape.iog.og[3].gco";
connectAttr "groupId16.id" "mesh_bodyShape.iog.og[4].gid";
connectAttr "tweakSet3.mwc" "mesh_bodyShape.iog.og[4].gco";
connectAttr "tweak3.vl[0].vt[0]" "mesh_bodyShape.twl";
connectAttr "polyTweakUV2.uvtk[0]" "mesh_bodyShape.uvst[0].uvtw";
connectAttr "skinCluster9.og[0]" "mesh_leg_rShape.i";
connectAttr "skinCluster9GroupId.id" "mesh_leg_rShape.iog.og[11].gid";
connectAttr "skinCluster9Set.mwc" "mesh_leg_rShape.iog.og[11].gco";
connectAttr "groupId31.id" "mesh_leg_rShape.iog.og[12].gid";
connectAttr "tweakSet9.mwc" "mesh_leg_rShape.iog.og[12].gco";
connectAttr "tweak9.vl[0].vt[0]" "mesh_leg_rShape.twl";
connectAttr "skinCluster10Set.mwc" "mesh_leg_lShape.iog.og[5].gco";
connectAttr "skinCluster10GroupId.id" "mesh_leg_lShape.iog.og[5].gid";
connectAttr "tweakSet10.mwc" "mesh_leg_lShape.iog.og[6].gco";
connectAttr "groupId33.id" "mesh_leg_lShape.iog.og[6].gid";
connectAttr "skinCluster10.og[0]" "mesh_leg_lShape.i";
connectAttr "tweak10.vl[0].vt[0]" "mesh_leg_lShape.twl";
connectAttr "anim_facial_settings.ChestplateMaterial" "grp_mesh_chestplate.v";
connectAttr "layer_meshExtra.di" "mesh_armor_chest_r.do";
connectAttr "skinCluster24GroupId.id" "mesh_armor_chest_rShape.iog.og[0].gid";
connectAttr "skinCluster24Set.mwc" "mesh_armor_chest_rShape.iog.og[0].gco";
connectAttr "groupId61.id" "mesh_armor_chest_rShape.iog.og[1].gid";
connectAttr "tweakSet24.mwc" "mesh_armor_chest_rShape.iog.og[1].gco";
connectAttr "polyMoveUV2.out" "mesh_armor_chest_rShape.i";
connectAttr "tweak24.vl[0].vt[0]" "mesh_armor_chest_rShape.twl";
connectAttr "polyTweakUV4.uvtk[0]" "mesh_armor_chest_rShape.uvst[0].uvtw";
connectAttr "layer_meshExtra.di" "mesh_armor_chest_l.do";
connectAttr "skinCluster23GroupId.id" "mesh_armor_chest_lShape.iog.og[0].gid";
connectAttr "skinCluster23Set.mwc" "mesh_armor_chest_lShape.iog.og[0].gco";
connectAttr "groupId59.id" "mesh_armor_chest_lShape.iog.og[1].gid";
connectAttr "tweakSet23.mwc" "mesh_armor_chest_lShape.iog.og[1].gco";
connectAttr "polyMoveUV1.out" "mesh_armor_chest_lShape.i";
connectAttr "tweak23.vl[0].vt[0]" "mesh_armor_chest_lShape.twl";
connectAttr "polyTweakUV5.uvtk[0]" "mesh_armor_chest_lShape.uvst[0].uvtw";
connectAttr "layer_meshExtra.di" "mesh_armor_chest_m.do";
connectAttr "skinCluster26GroupId.id" "mesh_armor_chest_mShape.iog.og[0].gid";
connectAttr "skinCluster26Set.mwc" "mesh_armor_chest_mShape.iog.og[0].gco";
connectAttr "groupId65.id" "mesh_armor_chest_mShape.iog.og[1].gid";
connectAttr "tweakSet26.mwc" "mesh_armor_chest_mShape.iog.og[1].gco";
connectAttr "polyMoveUV3.out" "mesh_armor_chest_mShape.i";
connectAttr "tweak26.vl[0].vt[0]" "mesh_armor_chest_mShape.twl";
connectAttr "polyTweakUV3.uvtk[0]" "mesh_armor_chest_mShape.uvst[0].uvtw";
connectAttr "anim_facial_settings.LeggingsMaterial" "grp_mesh_leggings.v";
connectAttr "layer_meshExtra.di" "mesh_leggings_armor_r.do";
connectAttr "skinCluster30GroupId.id" "mesh_leggings_armor_rShape.iog.og[0].gid"
		;
connectAttr "skinCluster30Set.mwc" "mesh_leggings_armor_rShape.iog.og[0].gco";
connectAttr "groupId73.id" "mesh_leggings_armor_rShape.iog.og[1].gid";
connectAttr "tweakSet30.mwc" "mesh_leggings_armor_rShape.iog.og[1].gco";
connectAttr "polyMoveUV5.out" "mesh_leggings_armor_rShape.i";
connectAttr "tweak30.vl[0].vt[0]" "mesh_leggings_armor_rShape.twl";
connectAttr "polyTweakUV8.uvtk[0]" "mesh_leggings_armor_rShape.uvst[0].uvtw";
connectAttr "layer_meshExtra.di" "mesh_armor_leggings_m.do";
connectAttr "skinCluster27GroupId.id" "mesh_armor_leggings_mShape.iog.og[0].gid"
		;
connectAttr "skinCluster27Set.mwc" "mesh_armor_leggings_mShape.iog.og[0].gco";
connectAttr "groupId67.id" "mesh_armor_leggings_mShape.iog.og[1].gid";
connectAttr "tweakSet27.mwc" "mesh_armor_leggings_mShape.iog.og[1].gco";
connectAttr "polyMoveUV7.out" "mesh_armor_leggings_mShape.i";
connectAttr "tweak27.vl[0].vt[0]" "mesh_armor_leggings_mShape.twl";
connectAttr "polyTweakUV7.uvtk[0]" "mesh_armor_leggings_mShape.uvst[0].uvtw";
connectAttr "layer_meshExtra.di" "mesh_armor_leggings_l.do";
connectAttr "skinCluster28GroupId.id" "mesh_armor_leggings_lShape.iog.og[0].gid"
		;
connectAttr "skinCluster28Set.mwc" "mesh_armor_leggings_lShape.iog.og[0].gco";
connectAttr "groupId69.id" "mesh_armor_leggings_lShape.iog.og[1].gid";
connectAttr "tweakSet28.mwc" "mesh_armor_leggings_lShape.iog.og[1].gco";
connectAttr "polyMoveUV6.out" "mesh_armor_leggings_lShape.i";
connectAttr "tweak28.vl[0].vt[0]" "mesh_armor_leggings_lShape.twl";
connectAttr "polyTweakUV9.uvtk[0]" "mesh_armor_leggings_lShape.uvst[0].uvtw";
connectAttr "anim_facial_settings.HelmetMaterial" "grp_mesh_helmet.v";
connectAttr "layer_meshExtra.di" "mesh_helmet.do";
connectAttr "skinCluster25GroupId.id" "mesh_helmetShape.iog.og[0].gid";
connectAttr "skinCluster25Set.mwc" "mesh_helmetShape.iog.og[0].gco";
connectAttr "groupId63.id" "mesh_helmetShape.iog.og[1].gid";
connectAttr "tweakSet25.mwc" "mesh_helmetShape.iog.og[1].gco";
connectAttr "polyMoveUV4.out" "mesh_helmetShape.i";
connectAttr "tweak25.vl[0].vt[0]" "mesh_helmetShape.twl";
connectAttr "polyTweakUV6.uvtk[0]" "mesh_helmetShape.uvst[0].uvtw";
connectAttr "anim_facial_settings.BootsMaterial" "grp_mesh_boots.v";
connectAttr "layer_meshExtra.di" "mesh_boots_l.do";
connectAttr "skinCluster29GroupId.id" "mesh_boots_lShape.iog.og[0].gid";
connectAttr "skinCluster29Set.mwc" "mesh_boots_lShape.iog.og[0].gco";
connectAttr "groupId71.id" "mesh_boots_lShape.iog.og[1].gid";
connectAttr "tweakSet29.mwc" "mesh_boots_lShape.iog.og[1].gco";
connectAttr "polyMoveUV8.out" "mesh_boots_lShape.i";
connectAttr "tweak29.vl[0].vt[0]" "mesh_boots_lShape.twl";
connectAttr "polyTweakUV10.uvtk[0]" "mesh_boots_lShape.uvst[0].uvtw";
connectAttr "layer_meshExtra.di" "mesh_boots_r.do";
connectAttr "skinCluster31GroupId.id" "mesh_boots_rShape.iog.og[0].gid";
connectAttr "skinCluster31Set.mwc" "mesh_boots_rShape.iog.og[0].gco";
connectAttr "groupId75.id" "mesh_boots_rShape.iog.og[1].gid";
connectAttr "tweakSet31.mwc" "mesh_boots_rShape.iog.og[1].gco";
connectAttr "polyMoveUV9.out" "mesh_boots_rShape.i";
connectAttr "tweak31.vl[0].vt[0]" "mesh_boots_rShape.twl";
connectAttr "polyTweakUV11.uvtk[0]" "mesh_boots_rShape.uvst[0].uvtw";
connectAttr "skinCluster32GroupId.id" "mesh_head_plainShape.iog.og[2].gid";
connectAttr "skinCluster32Set.mwc" "mesh_head_plainShape.iog.og[2].gco";
connectAttr "groupId101.id" "mesh_head_plainShape.iog.og[3].gid";
connectAttr "tweakSet44.mwc" "mesh_head_plainShape.iog.og[3].gco";
connectAttr "groupId110.id" "mesh_head_plainShape.iog.og[4].gid";
connectAttr "aiStandardSurface2SG.mwc" "mesh_head_plainShape.iog.og[4].gco";
connectAttr "groupId112.id" "mesh_head_plainShape.iog.og[5].gid";
connectAttr "rsMaterial2SG.mwc" "mesh_head_plainShape.iog.og[5].gco";
connectAttr "polyTweakUV12.out" "mesh_head_plainShape.i";
connectAttr "tweak44.vl[0].vt[0]" "mesh_head_plainShape.twl";
connectAttr "groupId111.id" "mesh_head_plainShape.ciog.cog[0].cgid";
connectAttr "polyTweakUV12.uvtk[0]" "mesh_head_plainShape.uvst[0].uvtw";
connectAttr "condition46.ocg" "grp_mesh_arm_steve.v";
connectAttr "skinCluster16GroupId.id" "mesh_arm_rShape.iog.og[0].gid";
connectAttr "skinCluster16Set.mwc" "mesh_arm_rShape.iog.og[0].gco";
connectAttr "groupId45.id" "mesh_arm_rShape.iog.og[1].gid";
connectAttr "tweakSet16.mwc" "mesh_arm_rShape.iog.og[1].gco";
connectAttr "skinCluster16.og[0]" "mesh_arm_rShape.i";
connectAttr "tweak16.vl[0].vt[0]" "mesh_arm_rShape.twl";
connectAttr "skinCluster15GroupId.id" "mesh_arm_lShape.iog.og[0].gid";
connectAttr "skinCluster15Set.mwc" "mesh_arm_lShape.iog.og[0].gco";
connectAttr "groupId43.id" "mesh_arm_lShape.iog.og[1].gid";
connectAttr "tweakSet15.mwc" "mesh_arm_lShape.iog.og[1].gco";
connectAttr "skinCluster15.og[0]" "mesh_arm_lShape.i";
connectAttr "tweak15.vl[0].vt[0]" "mesh_arm_lShape.twl";
connectAttr "skinCluster20GroupId.id" "mesh_arm_second_lShape.iog.og[0].gid";
connectAttr "skinCluster20Set.mwc" "mesh_arm_second_lShape.iog.og[0].gco";
connectAttr "groupId53.id" "mesh_arm_second_lShape.iog.og[1].gid";
connectAttr "tweakSet20.mwc" "mesh_arm_second_lShape.iog.og[1].gco";
connectAttr "skinCluster20.og[0]" "mesh_arm_second_lShape.i";
connectAttr "tweak20.vl[0].vt[0]" "mesh_arm_second_lShape.twl";
connectAttr "skinCluster18GroupId.id" "mesh_arm_second_rShape.iog.og[0].gid";
connectAttr "skinCluster18Set.mwc" "mesh_arm_second_rShape.iog.og[0].gco";
connectAttr "groupId49.id" "mesh_arm_second_rShape.iog.og[1].gid";
connectAttr "tweakSet18.mwc" "mesh_arm_second_rShape.iog.og[1].gco";
connectAttr "skinCluster18.og[0]" "mesh_arm_second_rShape.i";
connectAttr "tweak18.vl[0].vt[0]" "mesh_arm_second_rShape.twl";
connectAttr "anim_facial_settings.ArmType" "grp_mesh_arm_alex.v";
connectAttr "skinCluster35GroupId.id" "mesh_arm_alex_second_lShape.iog.og[2].gid"
		;
connectAttr "skinCluster35Set.mwc" "mesh_arm_alex_second_lShape.iog.og[2].gco";
connectAttr "groupId107.id" "mesh_arm_alex_second_lShape.iog.og[3].gid";
connectAttr "tweakSet47.mwc" "mesh_arm_alex_second_lShape.iog.og[3].gco";
connectAttr "skinCluster35.og[0]" "mesh_arm_alex_second_lShape.i";
connectAttr "tweak47.vl[0].vt[0]" "mesh_arm_alex_second_lShape.twl";
connectAttr "skinCluster36GroupId.id" "mesh_arm_alex_lShape.iog.og[2].gid";
connectAttr "skinCluster36Set.mwc" "mesh_arm_alex_lShape.iog.og[2].gco";
connectAttr "groupId109.id" "mesh_arm_alex_lShape.iog.og[3].gid";
connectAttr "tweakSet48.mwc" "mesh_arm_alex_lShape.iog.og[3].gco";
connectAttr "skinCluster36.og[0]" "mesh_arm_alex_lShape.i";
connectAttr "tweak48.vl[0].vt[0]" "mesh_arm_alex_lShape.twl";
connectAttr "skinCluster33GroupId.id" "mesh_arm_alex_second_rShape.iog.og[2].gid"
		;
connectAttr "skinCluster33Set.mwc" "mesh_arm_alex_second_rShape.iog.og[2].gco";
connectAttr "groupId103.id" "mesh_arm_alex_second_rShape.iog.og[3].gid";
connectAttr "tweakSet45.mwc" "mesh_arm_alex_second_rShape.iog.og[3].gco";
connectAttr "skinCluster33.og[0]" "mesh_arm_alex_second_rShape.i";
connectAttr "tweak45.vl[0].vt[0]" "mesh_arm_alex_second_rShape.twl";
connectAttr "skinCluster34GroupId.id" "mesh_arm_alex_rShape.iog.og[2].gid";
connectAttr "skinCluster34Set.mwc" "mesh_arm_alex_rShape.iog.og[2].gco";
connectAttr "groupId105.id" "mesh_arm_alex_rShape.iog.og[3].gid";
connectAttr "tweakSet46.mwc" "mesh_arm_alex_rShape.iog.og[3].gco";
connectAttr "skinCluster34.og[0]" "mesh_arm_alex_rShape.i";
connectAttr "tweak46.vl[0].vt[0]" "mesh_arm_alex_rShape.twl";
connectAttr "skinCluster17Set.mwc" "mesh_head_secondShape.iog.og[0].gco";
connectAttr "skinCluster17GroupId.id" "mesh_head_secondShape.iog.og[0].gid";
connectAttr "tweakSet17.mwc" "mesh_head_secondShape.iog.og[1].gco";
connectAttr "groupId47.id" "mesh_head_secondShape.iog.og[1].gid";
connectAttr "skinCluster17.og[0]" "mesh_head_secondShape.i";
connectAttr "tweak17.vl[0].vt[0]" "mesh_head_secondShape.twl";
connectAttr "skinCluster19GroupId.id" "mesh_body_secondShape.iog.og[0].gid";
connectAttr "skinCluster19Set.mwc" "mesh_body_secondShape.iog.og[0].gco";
connectAttr "groupId51.id" "mesh_body_secondShape.iog.og[1].gid";
connectAttr "tweakSet19.mwc" "mesh_body_secondShape.iog.og[1].gco";
connectAttr "skinCluster19.og[0]" "mesh_body_secondShape.i";
connectAttr "tweak19.vl[0].vt[0]" "mesh_body_secondShape.twl";
connectAttr "skinCluster21GroupId.id" "mesh_leg_second_lShape.iog.og[0].gid";
connectAttr "skinCluster21Set.mwc" "mesh_leg_second_lShape.iog.og[0].gco";
connectAttr "groupId55.id" "mesh_leg_second_lShape.iog.og[1].gid";
connectAttr "tweakSet21.mwc" "mesh_leg_second_lShape.iog.og[1].gco";
connectAttr "skinCluster21.og[0]" "mesh_leg_second_lShape.i";
connectAttr "tweak21.vl[0].vt[0]" "mesh_leg_second_lShape.twl";
connectAttr "skinCluster22GroupId.id" "mesh_leg_second_rShape.iog.og[0].gid";
connectAttr "skinCluster22Set.mwc" "mesh_leg_second_rShape.iog.og[0].gco";
connectAttr "groupId57.id" "mesh_leg_second_rShape.iog.og[1].gid";
connectAttr "tweakSet22.mwc" "mesh_leg_second_rShape.iog.og[1].gco";
connectAttr "skinCluster22.og[0]" "mesh_leg_second_rShape.i";
connectAttr "tweak22.vl[0].vt[0]" "mesh_leg_second_rShape.twl";
connectAttr "decomposeMatrix1.or" "grp_joint_leg_fk_r.r";
connectAttr "decomposeMatrix1.os" "grp_joint_leg_fk_r.s";
connectAttr "decomposeMatrix1.ot" "grp_joint_leg_fk_r.t";
connectAttr "blendColors1.op" "joint_leg_bind_r.t";
connectAttr "unitConversion2.o" "joint_leg_bind_r.r";
connectAttr "blendColors3.op" "joint_leg_bind_r.s";
connectAttr "decomposeMatrix3.or" "grp_joint_leg_fk_l.r";
connectAttr "decomposeMatrix3.os" "grp_joint_leg_fk_l.s";
connectAttr "decomposeMatrix3.ot" "grp_joint_leg_fk_l.t";
connectAttr "blendColors4.op" "joint_leg_bind_l.t";
connectAttr "unitConversion5.o" "joint_leg_bind_l.r";
connectAttr "blendColors6.op" "joint_leg_bind_l.s";
connectAttr "blendColors7.opr" "joint_arm_bind_l.tx";
connectAttr "blendColors7.opg" "joint_arm_bind_l.ty";
connectAttr "blendColors7.opb" "joint_arm_bind_l.tz";
connectAttr "unitConversion28.o" "joint_arm_bind_l.rx";
connectAttr "unitConversion29.o" "joint_arm_bind_l.ry";
connectAttr "unitConversion30.o" "joint_arm_bind_l.rz";
connectAttr "main_CON.s" "joint_arm_bind_l.s";
connectAttr "decomposeMatrix7.ot" "grp_arm_fk_l.t";
connectAttr "decomposeMatrix7.or" "grp_arm_fk_l.r";
connectAttr "blendColors9.opr" "joint_arm_bind_r.tx";
connectAttr "blendColors9.opg" "joint_arm_bind_r.ty";
connectAttr "blendColors9.opb" "joint_arm_bind_r.tz";
connectAttr "unitConversion36.o" "joint_arm_bind_r.rx";
connectAttr "unitConversion39.o" "joint_arm_bind_r.ry";
connectAttr "unitConversion40.o" "joint_arm_bind_r.rz";
connectAttr "main_CON.s" "joint_arm_bind_r.s";
connectAttr "decomposeMatrix10.ot" "grp_arm_fk_r.t";
connectAttr "decomposeMatrix10.or" "grp_arm_fk_r.r";
connectAttr "condition35.ocg" "uvCluster_chestplate_l.tx";
connectAttr "floatMath54.of" "uvCluster_chestplate_l.ty";
connectAttr "condition35.ocg" "uvCluster_chestplate_r.tx";
connectAttr "floatMath54.of" "uvCluster_chestplate_r.ty";
connectAttr "condition35.ocg" "uvCluster_chestplace_m.tx";
connectAttr "floatMath54.of" "uvCluster_chestplace_m.ty";
connectAttr "condition36.ocg" "uvCluster_helmet.tx";
connectAttr "floatMath55.of" "uvCluster_helmet.ty";
connectAttr "condition37.ocg" "uvCluster_leggings_r.tx";
connectAttr "floatMath58.of" "uvCluster_leggings_r.ty";
connectAttr "condition37.ocg" "uvCluster_leggings_l.tx";
connectAttr "floatMath58.of" "uvCluster_leggings_l.ty";
connectAttr "condition37.ocg" "uvCluster_leggings_m.tx";
connectAttr "floatMath58.of" "uvCluster_leggings_m.ty";
connectAttr "condition38.ocg" "uvCluster_boots_l.tx";
connectAttr "floatMath61.of" "uvCluster_boots_l.ty";
connectAttr "condition38.ocg" "uvCluster_boots_r.tx";
connectAttr "floatMath61.of" "uvCluster_boots_r.ty";
connectAttr "condition44.oc" "cluster_headcon.s";
connectAttr "condition40.oc" "cluster_bodycon.s";
connectAttr "condition41.ocb" "cluster_shouldercon.tz";
connectAttr "condition39.oc" "cluster_legikcon_r.s";
connectAttr "condition39.oc" "cluster_legikcon_l.s";
connectAttr "condition40.oc" "cluster_armfkcon_r.s";
connectAttr "condition40.oc" "cluster_armfkcon_l.s";
connectAttr "condition43.oc" "cluster_legfkcon_l.s";
connectAttr "condition43.oc" "cluster_legfkcon_r.s";
connectAttr "condition42.ocb" "cluster_legtopcon.tz";
connectAttr "const_head_pointConstraint1.ctx" "head_CNST.tx";
connectAttr "const_head_pointConstraint1.cty" "head_CNST.ty";
connectAttr "const_head_pointConstraint1.ctz" "head_CNST.tz";
connectAttr "cluster_headconCluster.og[0]" "head_CONShape.cr";
connectAttr "tweak32.pl[0].cp[0]" "head_CONShape.twl";
connectAttr "cluster1GroupId.id" "head_CONShape.iog.og[0].gid";
connectAttr "cluster1Set.mwc" "head_CONShape.iog.og[0].gco";
connectAttr "groupId77.id" "head_CONShape.iog.og[1].gid";
connectAttr "tweakSet32.mwc" "head_CONShape.iog.og[1].gco";
connectAttr "decomposeMatrix11.ot" "t_mouthOpen_LOC.t";
connectAttr "decomposeMatrix11.or" "t_mouthOpen_LOC.r";
connectAttr "decomposeMatrix11.os" "t_mouthOpen_LOC.s";
connectAttr "mouthOpenX_divideTranslate_floatMath.of" "m_mouth_projectionGRP.tx"
		;
connectAttr "rt_mouthMin_clamp.opg" "m_mouth_projectionGRP.sy";
connectAttr "mouthOpenX_scaleOffset_floatMath.of" "m_mouth_projectionGRP.sx";
connectAttr "rt_mouthSmileMin_clamp.opg" "rt_mouth_projectionGRP.sy";
connectAttr "rt_mouthSmile_clamp.opg" "rt_mouth_projectionGRP.ty";
connectAttr "rt_mouthOpen_LOC.tx" "rt_mouth_projectionGRP.tx";
connectAttr "lf_mouthSmileMin_clamp.opg" "lf_mouth_projectionGRP.sy";
connectAttr "lf_mouthSmile_clamp.opg" "lf_mouth_projectionGRP.ty";
connectAttr "lf_mouthOpen_LOC.tx" "lf_mouth_projectionGRP.tx";
connectAttr "lf_mouth_projectionGRP.s" "lf_lip_projectionGRP.s";
connectAttr "lf_mouth_projectionGRP.t" "lf_lip_projectionGRP.t";
connectAttr "m_mouth_projectionGRP.s" "m_lip_projectionGRP.s";
connectAttr "m_mouth_projectionGRP.t" "m_lip_projectionGRP.t";
connectAttr "rt_mouth_projectionGRP.s" "rt_lip_projectionGRP.s";
connectAttr "rt_mouth_projectionGRP.t" "rt_lip_projectionGRP.t";
connectAttr "decomposeMatrix12.ot" "b_mouthOpen_LOC.t";
connectAttr "decomposeMatrix12.os" "b_mouthOpen_LOC.s";
connectAttr "decomposeMatrix12.or" "b_mouthOpen_LOC.r";
connectAttr "decomposeMatrix15.ot" "lf_mouthOpen_LOC.t";
connectAttr "decomposeMatrix15.or" "lf_mouthOpen_LOC.r";
connectAttr "decomposeMatrix15.os" "lf_mouthOpen_LOC.s";
connectAttr "decomposeMatrix16.ot" "rt_mouthOpen_LOC.t";
connectAttr "decomposeMatrix16.or" "rt_mouthOpen_LOC.r";
connectAttr "decomposeMatrix16.os" "rt_mouthOpen_LOC.s";
connectAttr "decomposeMatrix14.ot" "b_mouthTeeth_LOC.t";
connectAttr "decomposeMatrix14.os" "b_mouthTeeth_LOC.s";
connectAttr "decomposeMatrix14.or" "b_mouthTeeth_LOC.r";
connectAttr "decomposeMatrix13.ot" "t_mouthTeeth_LOC.t";
connectAttr "decomposeMatrix13.os" "t_mouthTeeth_LOC.s";
connectAttr "decomposeMatrix13.or" "t_mouthTeeth_LOC.r";
connectAttr "decomposeMatrix17.ot" "mouthTongue_LOC.t";
connectAttr "decomposeMatrix17.os" "mouthTongue_LOC.s";
connectAttr "decomposeMatrix17.or" "mouthTongue_LOC.r";
connectAttr "head_CNST.pim" "const_head_pointConstraint1.cpim";
connectAttr "head_CNST.rp" "const_head_pointConstraint1.crp";
connectAttr "head_CNST.rpt" "const_head_pointConstraint1.crt";
connectAttr "locator_body_top.t" "const_head_pointConstraint1.tg[0].tt";
connectAttr "locator_body_top.rp" "const_head_pointConstraint1.tg[0].trp";
connectAttr "locator_body_top.rpt" "const_head_pointConstraint1.tg[0].trt";
connectAttr "locator_body_top.pm" "const_head_pointConstraint1.tg[0].tpm";
connectAttr "const_head_pointConstraint1.w0" "const_head_pointConstraint1.tg[0].tw"
		;
connectAttr "condition4.ocg" "anim_leg_ik_l.v";
connectAttr "cluster_legikcon_lCluster.og[0]" "anim_leg_ik_lShape.cr";
connectAttr "tweak37.pl[0].cp[0]" "anim_leg_ik_lShape.twl";
connectAttr "cluster5GroupId.id" "anim_leg_ik_lShape.iog.og[0].gid";
connectAttr "cluster5Set.mwc" "anim_leg_ik_lShape.iog.og[0].gco";
connectAttr "groupId87.id" "anim_leg_ik_lShape.iog.og[1].gid";
connectAttr "tweakSet37.mwc" "anim_leg_ik_lShape.iog.og[1].gco";
connectAttr "floatMath17.of" "grp_joint_leg_bl.ty";
connectAttr "joint_leg_bl.s" "joint_leg_tl.is";
connectAttr "joint_leg_tl.tx" "effector.tx";
connectAttr "joint_leg_tl.ty" "effector.ty";
connectAttr "joint_leg_tl.tz" "effector.tz";
connectAttr "joint_ff_l.s" "joint_ff_end_l.is";
connectAttr "joint_ff_end_l.tx" "effector3.tx";
connectAttr "joint_ff_end_l.ty" "effector3.ty";
connectAttr "joint_ff_end_l.tz" "effector3.tz";
connectAttr "condition2.ocg" "anim_leg_ik_r.v";
connectAttr "cluster_legikcon_rCluster.og[0]" "anim_leg_ik_rShape.cr";
connectAttr "tweak36.pl[0].cp[0]" "anim_leg_ik_rShape.twl";
connectAttr "cluster4GroupId.id" "anim_leg_ik_rShape.iog.og[0].gid";
connectAttr "cluster4Set.mwc" "anim_leg_ik_rShape.iog.og[0].gco";
connectAttr "groupId85.id" "anim_leg_ik_rShape.iog.og[1].gid";
connectAttr "tweakSet36.mwc" "anim_leg_ik_rShape.iog.og[1].gco";
connectAttr "floatMath26.of" "grp_joint_leg_br.ty";
connectAttr "joint_leg_br.s" "joint_leg_tr.is";
connectAttr "joint_leg_tr.tx" "effector1.tx";
connectAttr "joint_leg_tr.ty" "effector1.ty";
connectAttr "joint_leg_tr.tz" "effector1.tz";
connectAttr "joint_ff_r.s" "joint_ff_end_r.is";
connectAttr "joint_ff_end_r.tx" "effector5.tx";
connectAttr "joint_ff_end_r.ty" "effector5.ty";
connectAttr "joint_ff_end_r.tz" "effector5.tz";
connectAttr "cluster_bodyconCluster.og[0]" "anim_bodyShape.cr";
connectAttr "tweak33.pl[0].cp[0]" "anim_bodyShape.twl";
connectAttr "cluster2GroupId.id" "anim_bodyShape.iog.og[0].gid";
connectAttr "cluster2Set.mwc" "anim_bodyShape.iog.og[0].gco";
connectAttr "groupId79.id" "anim_bodyShape.iog.og[1].gid";
connectAttr "tweakSet33.mwc" "anim_bodyShape.iog.og[1].gco";
connectAttr "cluster_legtopconCluster.og[0]" "anim_leg_top_rShape.cr";
connectAttr "tweak42.pl[0].cp[0]" "anim_leg_top_rShape.twl";
connectAttr "cluster10GroupId.id" "anim_leg_top_rShape.iog.og[0].gid";
connectAttr "cluster10Set.mwc" "anim_leg_top_rShape.iog.og[0].gco";
connectAttr "groupId98.id" "anim_leg_top_rShape.iog.og[1].gid";
connectAttr "tweakSet42.mwc" "anim_leg_top_rShape.iog.og[1].gco";
connectAttr "anim_leg_settings_r.IKFKControl" "anim_leg_fk_r.v";
connectAttr "cluster_legfkcon_rCluster.og[0]" "anim_leg_fk_rShape.cr";
connectAttr "tweak41.pl[0].cp[0]" "anim_leg_fk_rShape.twl";
connectAttr "cluster9GroupId.id" "anim_leg_fk_rShape.iog.og[0].gid";
connectAttr "cluster9Set.mwc" "anim_leg_fk_rShape.iog.og[0].gco";
connectAttr "groupId95.id" "anim_leg_fk_rShape.iog.og[1].gid";
connectAttr "tweakSet41.mwc" "anim_leg_fk_rShape.iog.og[1].gco";
connectAttr "unitConversion14.o" "grp_joint_leg_rot_tr.ry";
connectAttr "condition1.ocb" "grp_joint_leg_tr.tz";
connectAttr "joint_leg_br.msg" "ikHandle_leg_r.hsj";
connectAttr "effector1.hp" "ikHandle_leg_r.hee";
connectAttr "ikRPsolver.msg" "ikHandle_leg_r.hsv";
connectAttr "ikHandle_leg_r_poleVectorConstraint1.ctx" "ikHandle_leg_r.pvx";
connectAttr "ikHandle_leg_r_poleVectorConstraint1.cty" "ikHandle_leg_r.pvy";
connectAttr "ikHandle_leg_r_poleVectorConstraint1.ctz" "ikHandle_leg_r.pvz";
connectAttr "ikHandle_leg_r.pim" "ikHandle_leg_r_poleVectorConstraint1.cpim";
connectAttr "joint_leg_br.pm" "ikHandle_leg_r_poleVectorConstraint1.ps";
connectAttr "joint_leg_br.t" "ikHandle_leg_r_poleVectorConstraint1.crp";
connectAttr "locator_leg_pole_r.t" "ikHandle_leg_r_poleVectorConstraint1.tg[0].tt"
		;
connectAttr "locator_leg_pole_r.rp" "ikHandle_leg_r_poleVectorConstraint1.tg[0].trp"
		;
connectAttr "locator_leg_pole_r.rpt" "ikHandle_leg_r_poleVectorConstraint1.tg[0].trt"
		;
connectAttr "locator_leg_pole_r.pm" "ikHandle_leg_r_poleVectorConstraint1.tg[0].tpm"
		;
connectAttr "ikHandle_leg_r_poleVectorConstraint1.w0" "ikHandle_leg_r_poleVectorConstraint1.tg[0].tw"
		;
connectAttr "joint_ff_r.msg" "ikHandle_ff_rotZ_r.hsj";
connectAttr "effector5.hp" "ikHandle_ff_rotZ_r.hee";
connectAttr "ikRPsolver.msg" "ikHandle_ff_rotZ_r.hsv";
connectAttr "cluster_legtopconCluster.og[1]" "anim_leg_top_lShape.cr";
connectAttr "tweak43.pl[0].cp[0]" "anim_leg_top_lShape.twl";
connectAttr "cluster10GroupId1.id" "anim_leg_top_lShape.iog.og[0].gid";
connectAttr "cluster10Set.mwc" "anim_leg_top_lShape.iog.og[0].gco";
connectAttr "groupId99.id" "anim_leg_top_lShape.iog.og[1].gid";
connectAttr "tweakSet43.mwc" "anim_leg_top_lShape.iog.og[1].gco";
connectAttr "anim_leg_settings_l.IKFKControl" "anim_leg_fk_l.v";
connectAttr "cluster_legfkcon_lCluster.og[0]" "anim_leg_fk_lShape.cr";
connectAttr "tweak40.pl[0].cp[0]" "anim_leg_fk_lShape.twl";
connectAttr "cluster8GroupId.id" "anim_leg_fk_lShape.iog.og[0].gid";
connectAttr "cluster8Set.mwc" "anim_leg_fk_lShape.iog.og[0].gco";
connectAttr "groupId93.id" "anim_leg_fk_lShape.iog.og[1].gid";
connectAttr "tweakSet40.mwc" "anim_leg_fk_lShape.iog.og[1].gco";
connectAttr "unitConversion20.o" "grp_joint_leg_rot_tl.ry";
connectAttr "condition3.ocb" "grp_joint_leg_tl.tz";
connectAttr "joint_leg_bl.msg" "ikHandle_leg_l.hsj";
connectAttr "effector.hp" "ikHandle_leg_l.hee";
connectAttr "ikRPsolver1.msg" "ikHandle_leg_l.hsv";
connectAttr "ikHandle_leg_l_poleVectorConstraint1.ctx" "ikHandle_leg_l.pvx";
connectAttr "ikHandle_leg_l_poleVectorConstraint1.cty" "ikHandle_leg_l.pvy";
connectAttr "ikHandle_leg_l_poleVectorConstraint1.ctz" "ikHandle_leg_l.pvz";
connectAttr "ikHandle_leg_l.pim" "ikHandle_leg_l_poleVectorConstraint1.cpim";
connectAttr "joint_leg_bl.pm" "ikHandle_leg_l_poleVectorConstraint1.ps";
connectAttr "joint_leg_bl.t" "ikHandle_leg_l_poleVectorConstraint1.crp";
connectAttr "locator_leg_pole_l.t" "ikHandle_leg_l_poleVectorConstraint1.tg[0].tt"
		;
connectAttr "locator_leg_pole_l.rp" "ikHandle_leg_l_poleVectorConstraint1.tg[0].trp"
		;
connectAttr "locator_leg_pole_l.rpt" "ikHandle_leg_l_poleVectorConstraint1.tg[0].trt"
		;
connectAttr "locator_leg_pole_l.pm" "ikHandle_leg_l_poleVectorConstraint1.tg[0].tpm"
		;
connectAttr "ikHandle_leg_l_poleVectorConstraint1.w0" "ikHandle_leg_l_poleVectorConstraint1.tg[0].tw"
		;
connectAttr "joint_ff_l.msg" "ikHandle_ff_rotZ_l.hsj";
connectAttr "effector3.hp" "ikHandle_ff_rotZ_l.hee";
connectAttr "ikRPsolver.msg" "ikHandle_ff_rotZ_l.hsv";
connectAttr "cluster_shoulderconCluster.og[1]" "anim_shoulder_rShape.cr";
connectAttr "tweak35.pl[0].cp[0]" "anim_shoulder_rShape.twl";
connectAttr "cluster3GroupId1.id" "anim_shoulder_rShape.iog.og[0].gid";
connectAttr "cluster3Set.mwc" "anim_shoulder_rShape.iog.og[0].gco";
connectAttr "groupId83.id" "anim_shoulder_rShape.iog.og[1].gid";
connectAttr "tweakSet35.mwc" "anim_shoulder_rShape.iog.og[1].gco";
connectAttr "transformGeometry3.og" "anim_arm_settings_rShape.cr";
connectAttr "condition47.ocr" "grp_alex_arm_offset_r.tx";
connectAttr "joint_arm_ik_top_r.s" "joint_arm_ik_end_r.is";
connectAttr "joint_arm_ik_end_r.tx" "|player_RIG|main_CNST|main_CON|const_body|anim_body|const_shoulder_r|anim_shoulder_r|grp_alex_arm_offset_r|grp_arm_ik_top_r|joint_arm_ik_top_r|effector2.tx"
		;
connectAttr "joint_arm_ik_end_r.ty" "|player_RIG|main_CNST|main_CON|const_body|anim_body|const_shoulder_r|anim_shoulder_r|grp_alex_arm_offset_r|grp_arm_ik_top_r|joint_arm_ik_top_r|effector2.ty"
		;
connectAttr "joint_arm_ik_end_r.tz" "|player_RIG|main_CNST|main_CON|const_body|anim_body|const_shoulder_r|anim_shoulder_r|grp_alex_arm_offset_r|grp_arm_ik_top_r|joint_arm_ik_top_r|effector2.tz"
		;
connectAttr "anim_arm_settings_r.IKFKControl" "anim_arm_fk_r.v";
connectAttr "cluster_armfkcon_rCluster.og[0]" "anim_arm_fk_rShape.cr";
connectAttr "tweak38.pl[0].cp[0]" "anim_arm_fk_rShape.twl";
connectAttr "cluster6GroupId.id" "anim_arm_fk_rShape.iog.og[0].gid";
connectAttr "cluster6Set.mwc" "anim_arm_fk_rShape.iog.og[0].gco";
connectAttr "groupId89.id" "anim_arm_fk_rShape.iog.og[1].gid";
connectAttr "tweakSet38.mwc" "anim_arm_fk_rShape.iog.og[1].gco";
connectAttr "cluster_shoulderconCluster.og[0]" "anim_shoulder_lShape.cr";
connectAttr "tweak34.pl[0].cp[0]" "anim_shoulder_lShape.twl";
connectAttr "cluster3GroupId.id" "anim_shoulder_lShape.iog.og[0].gid";
connectAttr "cluster3Set.mwc" "anim_shoulder_lShape.iog.og[0].gco";
connectAttr "groupId82.id" "anim_shoulder_lShape.iog.og[1].gid";
connectAttr "tweakSet34.mwc" "anim_shoulder_lShape.iog.og[1].gco";
connectAttr "transformGeometry2.og" "anim_arm_settings_lShape.cr";
connectAttr "condition47.ocr" "grp_arm_alex_offset_l.tx";
connectAttr "anim_arm_settings_l.IKFKControl" "anim_arm_fk_l.v";
connectAttr "cluster_armfkcon_lCluster.og[0]" "anim_arm_fk_lShape.cr";
connectAttr "tweak39.pl[0].cp[0]" "anim_arm_fk_lShape.twl";
connectAttr "cluster7GroupId.id" "anim_arm_fk_lShape.iog.og[0].gid";
connectAttr "cluster7Set.mwc" "anim_arm_fk_lShape.iog.og[0].gco";
connectAttr "groupId91.id" "anim_arm_fk_lShape.iog.og[1].gid";
connectAttr "tweakSet39.mwc" "anim_arm_fk_lShape.iog.og[1].gco";
connectAttr "joint_arm_ik_top_l.s" "joint_arm_ik_end_l.is";
connectAttr "joint_arm_ik_end_l.tx" "|player_RIG|main_CNST|main_CON|const_body|anim_body|const_shoulder_l|anim_shoulder_l|grp_arm_alex_offset_l|grp_arm_ik_top_l|joint_arm_ik_top_l|effector2.tx"
		;
connectAttr "joint_arm_ik_end_l.ty" "|player_RIG|main_CNST|main_CON|const_body|anim_body|const_shoulder_l|anim_shoulder_l|grp_arm_alex_offset_l|grp_arm_ik_top_l|joint_arm_ik_top_l|effector2.ty"
		;
connectAttr "joint_arm_ik_end_l.tz" "|player_RIG|main_CNST|main_CON|const_body|anim_body|const_shoulder_l|anim_shoulder_l|grp_arm_alex_offset_l|grp_arm_ik_top_l|joint_arm_ik_top_l|effector2.tz"
		;
connectAttr "unitConversion35.o" "const_arm_ik_l.r";
connectAttr "multiplyDivide12.o" "const_arm_ik_l.t";
connectAttr "condition23.ocg" "anim_arm_ik_l.v";
connectAttr "joint_arm_ik_top_l.msg" "ikHandle_arm_l.hsj";
connectAttr "|player_RIG|main_CNST|main_CON|const_body|anim_body|const_shoulder_l|anim_shoulder_l|grp_arm_alex_offset_l|grp_arm_ik_top_l|joint_arm_ik_top_l|effector2.hp" "ikHandle_arm_l.hee"
		;
connectAttr "ikRPsolver.msg" "ikHandle_arm_l.hsv";
connectAttr "unitConversion41.o" "const_arm_ik_r.r";
connectAttr "multiplyDivide21.o" "const_arm_ik_r.t";
connectAttr "condition34.ocg" "anim_arm_ik_r.v";
connectAttr "joint_arm_ik_top_r.msg" "ikHandle_arm_r.hsj";
connectAttr "|player_RIG|main_CNST|main_CON|const_body|anim_body|const_shoulder_r|anim_shoulder_r|grp_alex_arm_offset_r|grp_arm_ik_top_r|joint_arm_ik_top_r|effector2.hp" "ikHandle_arm_r.hee"
		;
connectAttr "ikRPsolver2.msg" "ikHandle_arm_r.hsv";
connectAttr "body_LOC_parentConstraint1.ctx" "body_LOC.tx";
connectAttr "body_LOC_parentConstraint1.cty" "body_LOC.ty";
connectAttr "body_LOC_parentConstraint1.ctz" "body_LOC.tz";
connectAttr "body_LOC_parentConstraint1.crx" "body_LOC.rx";
connectAttr "body_LOC_parentConstraint1.cry" "body_LOC.ry";
connectAttr "body_LOC_parentConstraint1.crz" "body_LOC.rz";
connectAttr "body_LOC.ro" "body_LOC_parentConstraint1.cro";
connectAttr "body_LOC.pim" "body_LOC_parentConstraint1.cpim";
connectAttr "body_LOC.rp" "body_LOC_parentConstraint1.crp";
connectAttr "body_LOC.rpt" "body_LOC_parentConstraint1.crt";
connectAttr "joint_body.t" "body_LOC_parentConstraint1.tg[0].tt";
connectAttr "joint_body.rp" "body_LOC_parentConstraint1.tg[0].trp";
connectAttr "joint_body.rpt" "body_LOC_parentConstraint1.tg[0].trt";
connectAttr "joint_body.r" "body_LOC_parentConstraint1.tg[0].tr";
connectAttr "joint_body.ro" "body_LOC_parentConstraint1.tg[0].tro";
connectAttr "joint_body.s" "body_LOC_parentConstraint1.tg[0].ts";
connectAttr "joint_body.pm" "body_LOC_parentConstraint1.tg[0].tpm";
connectAttr "joint_body.jo" "body_LOC_parentConstraint1.tg[0].tjo";
connectAttr "joint_body.ssc" "body_LOC_parentConstraint1.tg[0].tsc";
connectAttr "joint_body.is" "body_LOC_parentConstraint1.tg[0].tis";
connectAttr "body_LOC_parentConstraint1.w0" "body_LOC_parentConstraint1.tg[0].tw"
		;
connectAttr "leftArm_LOC_parentConstraint1.ctx" "leftArm_LOC.tx";
connectAttr "leftArm_LOC_parentConstraint1.cty" "leftArm_LOC.ty";
connectAttr "leftArm_LOC_parentConstraint1.ctz" "leftArm_LOC.tz";
connectAttr "leftArm_LOC_parentConstraint1.crx" "leftArm_LOC.rx";
connectAttr "leftArm_LOC_parentConstraint1.cry" "leftArm_LOC.ry";
connectAttr "leftArm_LOC_parentConstraint1.crz" "leftArm_LOC.rz";
connectAttr "leftArm_LOC.ro" "leftArm_LOC_parentConstraint1.cro";
connectAttr "leftArm_LOC.pim" "leftArm_LOC_parentConstraint1.cpim";
connectAttr "leftArm_LOC.rp" "leftArm_LOC_parentConstraint1.crp";
connectAttr "leftArm_LOC.rpt" "leftArm_LOC_parentConstraint1.crt";
connectAttr "joint_arm_bind_l.t" "leftArm_LOC_parentConstraint1.tg[0].tt";
connectAttr "joint_arm_bind_l.rp" "leftArm_LOC_parentConstraint1.tg[0].trp";
connectAttr "joint_arm_bind_l.rpt" "leftArm_LOC_parentConstraint1.tg[0].trt";
connectAttr "joint_arm_bind_l.r" "leftArm_LOC_parentConstraint1.tg[0].tr";
connectAttr "joint_arm_bind_l.ro" "leftArm_LOC_parentConstraint1.tg[0].tro";
connectAttr "joint_arm_bind_l.s" "leftArm_LOC_parentConstraint1.tg[0].ts";
connectAttr "joint_arm_bind_l.pm" "leftArm_LOC_parentConstraint1.tg[0].tpm";
connectAttr "joint_arm_bind_l.jo" "leftArm_LOC_parentConstraint1.tg[0].tjo";
connectAttr "joint_arm_bind_l.ssc" "leftArm_LOC_parentConstraint1.tg[0].tsc";
connectAttr "joint_arm_bind_l.is" "leftArm_LOC_parentConstraint1.tg[0].tis";
connectAttr "leftArm_LOC_parentConstraint1.w0" "leftArm_LOC_parentConstraint1.tg[0].tw"
		;
connectAttr "rightArm_LOC_parentConstraint1.ctx" "rightArm_LOC.tx";
connectAttr "rightArm_LOC_parentConstraint1.cty" "rightArm_LOC.ty";
connectAttr "rightArm_LOC_parentConstraint1.ctz" "rightArm_LOC.tz";
connectAttr "rightArm_LOC_parentConstraint1.crx" "rightArm_LOC.rx";
connectAttr "rightArm_LOC_parentConstraint1.cry" "rightArm_LOC.ry";
connectAttr "rightArm_LOC_parentConstraint1.crz" "rightArm_LOC.rz";
connectAttr "rightArm_LOC.ro" "rightArm_LOC_parentConstraint1.cro";
connectAttr "rightArm_LOC.pim" "rightArm_LOC_parentConstraint1.cpim";
connectAttr "rightArm_LOC.rp" "rightArm_LOC_parentConstraint1.crp";
connectAttr "rightArm_LOC.rpt" "rightArm_LOC_parentConstraint1.crt";
connectAttr "joint_arm_bind_r.t" "rightArm_LOC_parentConstraint1.tg[0].tt";
connectAttr "joint_arm_bind_r.rp" "rightArm_LOC_parentConstraint1.tg[0].trp";
connectAttr "joint_arm_bind_r.rpt" "rightArm_LOC_parentConstraint1.tg[0].trt";
connectAttr "joint_arm_bind_r.r" "rightArm_LOC_parentConstraint1.tg[0].tr";
connectAttr "joint_arm_bind_r.ro" "rightArm_LOC_parentConstraint1.tg[0].tro";
connectAttr "joint_arm_bind_r.s" "rightArm_LOC_parentConstraint1.tg[0].ts";
connectAttr "joint_arm_bind_r.pm" "rightArm_LOC_parentConstraint1.tg[0].tpm";
connectAttr "joint_arm_bind_r.jo" "rightArm_LOC_parentConstraint1.tg[0].tjo";
connectAttr "joint_arm_bind_r.ssc" "rightArm_LOC_parentConstraint1.tg[0].tsc";
connectAttr "joint_arm_bind_r.is" "rightArm_LOC_parentConstraint1.tg[0].tis";
connectAttr "rightArm_LOC_parentConstraint1.w0" "rightArm_LOC_parentConstraint1.tg[0].tw"
		;
connectAttr "leftLeg_LOC_parentConstraint1.ctx" "leftLeg_LOC.tx";
connectAttr "leftLeg_LOC_parentConstraint1.cty" "leftLeg_LOC.ty";
connectAttr "leftLeg_LOC_parentConstraint1.ctz" "leftLeg_LOC.tz";
connectAttr "leftLeg_LOC_parentConstraint1.crx" "leftLeg_LOC.rx";
connectAttr "leftLeg_LOC_parentConstraint1.cry" "leftLeg_LOC.ry";
connectAttr "leftLeg_LOC_parentConstraint1.crz" "leftLeg_LOC.rz";
connectAttr "leftLeg_LOC.ro" "leftLeg_LOC_parentConstraint1.cro";
connectAttr "leftLeg_LOC.pim" "leftLeg_LOC_parentConstraint1.cpim";
connectAttr "leftLeg_LOC.rp" "leftLeg_LOC_parentConstraint1.crp";
connectAttr "leftLeg_LOC.rpt" "leftLeg_LOC_parentConstraint1.crt";
connectAttr "joint_leg_bind_l.t" "leftLeg_LOC_parentConstraint1.tg[0].tt";
connectAttr "joint_leg_bind_l.rp" "leftLeg_LOC_parentConstraint1.tg[0].trp";
connectAttr "joint_leg_bind_l.rpt" "leftLeg_LOC_parentConstraint1.tg[0].trt";
connectAttr "joint_leg_bind_l.r" "leftLeg_LOC_parentConstraint1.tg[0].tr";
connectAttr "joint_leg_bind_l.ro" "leftLeg_LOC_parentConstraint1.tg[0].tro";
connectAttr "joint_leg_bind_l.s" "leftLeg_LOC_parentConstraint1.tg[0].ts";
connectAttr "joint_leg_bind_l.pm" "leftLeg_LOC_parentConstraint1.tg[0].tpm";
connectAttr "joint_leg_bind_l.jo" "leftLeg_LOC_parentConstraint1.tg[0].tjo";
connectAttr "joint_leg_bind_l.ssc" "leftLeg_LOC_parentConstraint1.tg[0].tsc";
connectAttr "joint_leg_bind_l.is" "leftLeg_LOC_parentConstraint1.tg[0].tis";
connectAttr "leftLeg_LOC_parentConstraint1.w0" "leftLeg_LOC_parentConstraint1.tg[0].tw"
		;
connectAttr "rightLeg_LOC_parentConstraint1.ctx" "rightLeg_LOC.tx";
connectAttr "rightLeg_LOC_parentConstraint1.cty" "rightLeg_LOC.ty";
connectAttr "rightLeg_LOC_parentConstraint1.ctz" "rightLeg_LOC.tz";
connectAttr "rightLeg_LOC_parentConstraint1.crx" "rightLeg_LOC.rx";
connectAttr "rightLeg_LOC_parentConstraint1.cry" "rightLeg_LOC.ry";
connectAttr "rightLeg_LOC_parentConstraint1.crz" "rightLeg_LOC.rz";
connectAttr "rightLeg_LOC.ro" "rightLeg_LOC_parentConstraint1.cro";
connectAttr "rightLeg_LOC.pim" "rightLeg_LOC_parentConstraint1.cpim";
connectAttr "rightLeg_LOC.rp" "rightLeg_LOC_parentConstraint1.crp";
connectAttr "rightLeg_LOC.rpt" "rightLeg_LOC_parentConstraint1.crt";
connectAttr "joint_leg_bind_r.t" "rightLeg_LOC_parentConstraint1.tg[0].tt";
connectAttr "joint_leg_bind_r.rp" "rightLeg_LOC_parentConstraint1.tg[0].trp";
connectAttr "joint_leg_bind_r.rpt" "rightLeg_LOC_parentConstraint1.tg[0].trt";
connectAttr "joint_leg_bind_r.r" "rightLeg_LOC_parentConstraint1.tg[0].tr";
connectAttr "joint_leg_bind_r.ro" "rightLeg_LOC_parentConstraint1.tg[0].tro";
connectAttr "joint_leg_bind_r.s" "rightLeg_LOC_parentConstraint1.tg[0].ts";
connectAttr "joint_leg_bind_r.pm" "rightLeg_LOC_parentConstraint1.tg[0].tpm";
connectAttr "joint_leg_bind_r.jo" "rightLeg_LOC_parentConstraint1.tg[0].tjo";
connectAttr "joint_leg_bind_r.ssc" "rightLeg_LOC_parentConstraint1.tg[0].tsc";
connectAttr "joint_leg_bind_r.is" "rightLeg_LOC_parentConstraint1.tg[0].tis";
connectAttr "rightLeg_LOC_parentConstraint1.w0" "rightLeg_LOC_parentConstraint1.tg[0].tw"
		;
connectAttr "head_LOC_parentConstraint1.ctx" "head_LOC.tx";
connectAttr "head_LOC_parentConstraint1.cty" "head_LOC.ty";
connectAttr "head_LOC_parentConstraint1.ctz" "head_LOC.tz";
connectAttr "head_LOC_parentConstraint1.crx" "head_LOC.rx";
connectAttr "head_LOC_parentConstraint1.cry" "head_LOC.ry";
connectAttr "head_LOC_parentConstraint1.crz" "head_LOC.rz";
connectAttr "head_LOC.ro" "head_LOC_parentConstraint1.cro";
connectAttr "head_LOC.pim" "head_LOC_parentConstraint1.cpim";
connectAttr "head_LOC.rp" "head_LOC_parentConstraint1.crp";
connectAttr "head_LOC.rpt" "head_LOC_parentConstraint1.crt";
connectAttr "head_CON.t" "head_LOC_parentConstraint1.tg[0].tt";
connectAttr "head_CON.rp" "head_LOC_parentConstraint1.tg[0].trp";
connectAttr "head_CON.rpt" "head_LOC_parentConstraint1.tg[0].trt";
connectAttr "head_CON.r" "head_LOC_parentConstraint1.tg[0].tr";
connectAttr "head_CON.ro" "head_LOC_parentConstraint1.tg[0].tro";
connectAttr "head_CON.s" "head_LOC_parentConstraint1.tg[0].ts";
connectAttr "head_CON.pm" "head_LOC_parentConstraint1.tg[0].tpm";
connectAttr "head_LOC_parentConstraint1.w0" "head_LOC_parentConstraint1.tg[0].tw"
		;
relationship "link" ":lightLinker1" ":initialShadingGroup.message" ":defaultLightSet.message";
relationship "link" ":lightLinker1" ":initialParticleSE.message" ":defaultLightSet.message";
relationship "link" ":lightLinker1" "steve_set1.message" ":defaultLightSet.message";
relationship "link" ":lightLinker1" "rsMat_skinPromoArtSG.message" ":defaultLightSet.message";
relationship "link" ":lightLinker1" "rsMaterial1SG.message" ":defaultLightSet.message";
relationship "link" ":lightLinker1" "typeBlinnSG.message" ":defaultLightSet.message";
relationship "link" ":lightLinker1" "rsMat_armorPromoArtSG.message" ":defaultLightSet.message";
relationship "link" ":lightLinker1" "aiStandardSurface1SG.message" ":defaultLightSet.message";
relationship "link" ":lightLinker1" "aiStandardSurface2SG.message" ":defaultLightSet.message";
relationship "link" ":lightLinker1" "aiStandardSurface3SG.message" ":defaultLightSet.message";
relationship "link" ":lightLinker1" "rsSprite1SG.message" ":defaultLightSet.message";
relationship "link" ":lightLinker1" "rsMaterial2SG.message" ":defaultLightSet.message";
relationship "shadowLink" ":lightLinker1" ":initialShadingGroup.message" ":defaultLightSet.message";
relationship "shadowLink" ":lightLinker1" ":initialParticleSE.message" ":defaultLightSet.message";
relationship "shadowLink" ":lightLinker1" "steve_set1.message" ":defaultLightSet.message";
relationship "shadowLink" ":lightLinker1" "rsMat_skinPromoArtSG.message" ":defaultLightSet.message";
relationship "shadowLink" ":lightLinker1" "rsMaterial1SG.message" ":defaultLightSet.message";
relationship "shadowLink" ":lightLinker1" "typeBlinnSG.message" ":defaultLightSet.message";
relationship "shadowLink" ":lightLinker1" "rsMat_armorPromoArtSG.message" ":defaultLightSet.message";
relationship "shadowLink" ":lightLinker1" "aiStandardSurface1SG.message" ":defaultLightSet.message";
relationship "shadowLink" ":lightLinker1" "aiStandardSurface2SG.message" ":defaultLightSet.message";
relationship "shadowLink" ":lightLinker1" "aiStandardSurface3SG.message" ":defaultLightSet.message";
relationship "shadowLink" ":lightLinker1" "rsSprite1SG.message" ":defaultLightSet.message";
relationship "shadowLink" ":lightLinker1" "rsMaterial2SG.message" ":defaultLightSet.message";
connectAttr "defaultRedshiftPostEffects.msg" ":redshiftOptions.postEffects";
connectAttr "layerManager.dli[0]" "defaultLayer.id";
connectAttr "renderLayerManager.rlmi[0]" "defaultRenderLayer.rlid";
connectAttr ":defaultArnoldDisplayDriver.msg" ":defaultArnoldRenderOptions.drivers"
		 -na;
connectAttr ":defaultArnoldFilter.msg" ":defaultArnoldRenderOptions.filt";
connectAttr ":defaultArnoldDriver.msg" ":defaultArnoldRenderOptions.drvr";
connectAttr "steve_set1.msg" "steve_materialInfo1.sg";
connectAttr "rsMat_skinPromoArtSG.msg" "materialInfo1.sg";
connectAttr ":defaultColorMgtGlobals.cme" "skinPromoArt_tex.cme";
connectAttr ":defaultColorMgtGlobals.cfe" "skinPromoArt_tex.cmcf";
connectAttr ":defaultColorMgtGlobals.cfp" "skinPromoArt_tex.cmcp";
connectAttr ":defaultColorMgtGlobals.wsn" "skinPromoArt_tex.ws";
connectAttr "place2dTexture_skinPromoArt.o" "skinPromoArt_tex.uv";
connectAttr "locator_leg_top_rShape.wp" "distanceBetween1.p1";
connectAttr "locator_leg_bottom_rShape.wp" "distanceBetween1.p2";
connectAttr "multiplyDivide1.oz" "condition1.ft";
connectAttr "remapValue1.ov" "condition1.ctb";
connectAttr "multiplyDivide1.oz" "remapValue1.i";
connectAttr "multiplyDivide10.oz" "multiplyDivide1.i1z";
connectAttr "anim_leg_settings_r.IKBend" "multiplyDivide1.i2z";
connectAttr "anim_leg_fk_r.wm" "decomposeMatrix1.imat";
connectAttr "grp_joint_leg_fk_r.t" "blendColors1.c1";
connectAttr "decomposeMatrix2.ot" "blendColors1.c2";
connectAttr "anim_leg_settings_r.IKFKControl" "blendColors1.b";
connectAttr "unitConversion4.o" "blendColors2.c2";
connectAttr "unitConversion3.o" "blendColors2.c1";
connectAttr "anim_leg_settings_r.IKFKControl" "blendColors2.b";
connectAttr "decomposeMatrix2.os" "blendColors3.c2";
connectAttr "grp_joint_leg_fk_r.s" "blendColors3.c1";
connectAttr "anim_leg_settings_r.IKFKControl" "blendColors3.b";
connectAttr "blendColors2.op" "unitConversion2.i";
connectAttr "grp_joint_leg_fk_r.r" "unitConversion3.i";
connectAttr "joint_leg_br.wm" "decomposeMatrix2.imat";
connectAttr "decomposeMatrix2.or" "unitConversion4.i";
connectAttr "anim_leg_settings_r.IKFKControl" "condition2.ft";
connectAttr "grp_joint_leg_fk_r.tx" "addDoubleLinear2.i1";
connectAttr "multiplyDivide2.oz" "condition3.ft";
connectAttr "remapValue2.ov" "condition3.ctb";
connectAttr "multiplyDivide8.oz" "multiplyDivide2.i1z";
connectAttr "anim_leg_settings_l.IKBend" "multiplyDivide2.i2z";
connectAttr "locator_leg_top_lShape.wp" "distanceBetween2.p1";
connectAttr "locator_leg_bottom_lShape.wp" "distanceBetween2.p2";
connectAttr "multiplyDivide2.oz" "remapValue2.i";
connectAttr "anim_leg_settings_l.IKFKControl" "condition4.ft";
connectAttr "anim_leg_fk_l.wm" "decomposeMatrix3.imat";
connectAttr "grp_joint_leg_fk_l.t" "blendColors4.c1";
connectAttr "decomposeMatrix4.ot" "blendColors4.c2";
connectAttr "anim_leg_settings_l.IKFKControl" "blendColors4.b";
connectAttr "joint_leg_bl.wm" "decomposeMatrix4.imat";
connectAttr "blendColors5.op" "unitConversion5.i";
connectAttr "unitConversion6.o" "blendColors5.c2";
connectAttr "unitConversion7.o" "blendColors5.c1";
connectAttr "anim_leg_settings_l.IKFKControl" "blendColors5.b";
connectAttr "decomposeMatrix4.or" "unitConversion6.i";
connectAttr "grp_joint_leg_fk_l.r" "unitConversion7.i";
connectAttr "decomposeMatrix4.os" "blendColors6.c2";
connectAttr "grp_joint_leg_fk_l.s" "blendColors6.c1";
connectAttr "anim_leg_settings_l.IKFKControl" "blendColors6.b";
connectAttr "main_CON.sz" "multiplyDivide3.i2z";
connectAttr "distanceBetween1.d" "addDoubleLinear1.i1";
connectAttr "multiplyDivide3.oz" "addDoubleLinear1.i2";
connectAttr "joint_leg_bl.r" "unitConversion8.i";
connectAttr "reverse1.oy" "addDoubleLinear4.i1";
connectAttr "unitConversion13.o" "addDoubleLinear4.i2";
connectAttr "anim_body.ry" "unitConversion9.i";
connectAttr "anim_body.ry" "unitConversion10.i";
connectAttr "anim_body.ry" "unitConversion11.i";
connectAttr "reverse1.oy" "unitConversion12.i";
connectAttr "anim_leg_ik_r.ry" "unitConversion13.i";
connectAttr "addDoubleLinear4.o" "unitConversion14.i";
connectAttr "unitConversion15.o" "reverse1.iy";
connectAttr "anim_body.ry" "unitConversion15.i";
connectAttr "mesh_bodyShapeOrig.w" "transformGeometry1.ig";
connectAttr "skinCluster3GroupParts.og" "skinCluster3.ip[0].ig";
connectAttr "skinCluster3GroupId.id" "skinCluster3.ip[0].gi";
connectAttr "bindPose3.msg" "skinCluster3.bp";
connectAttr "joint_body.wm" "skinCluster3.ma[0]";
connectAttr "joint_body.liw" "skinCluster3.lw[0]";
connectAttr "joint_body.obcc" "skinCluster3.ifcl[0]";
connectAttr "groupParts6.og" "tweak3.ip[0].ig";
connectAttr "groupId16.id" "tweak3.ip[0].gi";
connectAttr "skinCluster3GroupId.msg" "skinCluster3Set.gn" -na;
connectAttr "mesh_bodyShape.iog.og[3]" "skinCluster3Set.dsm" -na;
connectAttr "skinCluster3.msg" "skinCluster3Set.ub[0]";
connectAttr "tweak3.og[0]" "skinCluster3GroupParts.ig";
connectAttr "skinCluster3GroupId.id" "skinCluster3GroupParts.gi";
connectAttr "groupId16.msg" "tweakSet3.gn" -na;
connectAttr "mesh_bodyShape.iog.og[4]" "tweakSet3.dsm" -na;
connectAttr "tweak3.msg" "tweakSet3.ub[0]";
connectAttr "transformGeometry1.og" "groupParts6.ig";
connectAttr "groupId16.id" "groupParts6.gi";
connectAttr "main_CON.msg" "bindPose3.m[0]";
connectAttr "const_body.msg" "bindPose3.m[1]";
connectAttr "anim_body.msg" "bindPose3.m[2]";
connectAttr "joint_body.msg" "bindPose3.m[3]";
connectAttr "bindPose3.w" "bindPose3.p[0]";
connectAttr "bindPose3.m[0]" "bindPose3.p[1]";
connectAttr "bindPose3.m[1]" "bindPose3.p[2]";
connectAttr "bindPose3.m[2]" "bindPose3.p[3]";
connectAttr "unitConversion19.o" "reverse2.iy";
connectAttr "anim_body.ry" "unitConversion16.i";
connectAttr "reverse2.oy" "unitConversion17.i";
connectAttr "reverse2.oy" "unitConversion18.i";
connectAttr "anim_body.ry" "unitConversion19.i";
connectAttr "reverse2.oy" "addDoubleLinear5.i2";
connectAttr "unitConversion21.o" "addDoubleLinear5.i1";
connectAttr "addDoubleLinear5.o" "unitConversion20.i";
connectAttr "anim_leg_ik_l.ry" "unitConversion21.i";
connectAttr "skinCluster3.og[0]" "polyTweakUV2.ip";
connectAttr "rsMaterial1SG.msg" "materialInfo2.sg";
connectAttr "typeBlinn.oc" "typeBlinnSG.ss";
connectAttr "typeBlinnSG.msg" "materialInfo3.sg";
connectAttr "typeBlinn.msg" "materialInfo3.m";
connectAttr "addDoubleLinear10.o" "condition5.ft";
connectAttr "addDoubleLinear10.o" "condition6.ft";
connectAttr "addDoubleLinear10.o" "condition7.ft";
connectAttr "addDoubleLinear10.o" "condition8.ft";
connectAttr "remapValue3.ov" "addDoubleLinear10.i1";
connectAttr "remapValue4.ov" "multiplyDivide5.i1x";
connectAttr ":defaultColorMgtGlobals.cme" "tex_promo_art_face.cme";
connectAttr ":defaultColorMgtGlobals.cfe" "tex_promo_art_face.cmcf";
connectAttr ":defaultColorMgtGlobals.cfp" "tex_promo_art_face.cmcp";
connectAttr ":defaultColorMgtGlobals.wsn" "tex_promo_art_face.ws";
connectAttr "place2dTexture_texPromoArtFace.c" "tex_promo_art_face.c";
connectAttr "place2dTexture_texPromoArtFace.tf" "tex_promo_art_face.tf";
connectAttr "place2dTexture_texPromoArtFace.rf" "tex_promo_art_face.rf";
connectAttr "place2dTexture_texPromoArtFace.mu" "tex_promo_art_face.mu";
connectAttr "place2dTexture_texPromoArtFace.mv" "tex_promo_art_face.mv";
connectAttr "place2dTexture_texPromoArtFace.s" "tex_promo_art_face.s";
connectAttr "place2dTexture_texPromoArtFace.wu" "tex_promo_art_face.wu";
connectAttr "place2dTexture_texPromoArtFace.wv" "tex_promo_art_face.wv";
connectAttr "place2dTexture_texPromoArtFace.re" "tex_promo_art_face.re";
connectAttr "place2dTexture_texPromoArtFace.of" "tex_promo_art_face.of";
connectAttr "place2dTexture_texPromoArtFace.r" "tex_promo_art_face.ro";
connectAttr "place2dTexture_texPromoArtFace.n" "tex_promo_art_face.n";
connectAttr "place2dTexture_texPromoArtFace.vt1" "tex_promo_art_face.vt1";
connectAttr "place2dTexture_texPromoArtFace.vt2" "tex_promo_art_face.vt2";
connectAttr "place2dTexture_texPromoArtFace.vt3" "tex_promo_art_face.vt3";
connectAttr "place2dTexture_texPromoArtFace.vc1" "tex_promo_art_face.vc1";
connectAttr "place2dTexture_texPromoArtFace.o" "tex_promo_art_face.uv";
connectAttr "place2dTexture_texPromoArtFace.ofs" "tex_promo_art_face.fs";
connectAttr "remapValue6.ov" "condition16.ft";
connectAttr "remapValue6.ov" "condition17.ft";
connectAttr "remapValue6.ov" "condition18.ft";
connectAttr "remapValue6.ov" "condition19.ft";
connectAttr "skinCluster9GroupParts.og" "skinCluster9.ip[0].ig";
connectAttr "skinCluster9GroupId.id" "skinCluster9.ip[0].gi";
connectAttr "bindPose8.msg" "skinCluster9.bp";
connectAttr "joint_leg_bind_r.wm" "skinCluster9.ma[0]";
connectAttr "joint_leg_bind_r.liw" "skinCluster9.lw[0]";
connectAttr "joint_leg_bind_r.obcc" "skinCluster9.ifcl[0]";
connectAttr "groupParts20.og" "tweak9.ip[0].ig";
connectAttr "groupId31.id" "tweak9.ip[0].gi";
connectAttr "skinCluster9GroupId.msg" "skinCluster9Set.gn" -na;
connectAttr "mesh_leg_rShape.iog.og[11]" "skinCluster9Set.dsm" -na;
connectAttr "skinCluster9.msg" "skinCluster9Set.ub[0]";
connectAttr "tweak9.og[0]" "skinCluster9GroupParts.ig";
connectAttr "skinCluster9GroupId.id" "skinCluster9GroupParts.gi";
connectAttr "groupId31.msg" "tweakSet9.gn" -na;
connectAttr "mesh_leg_rShape.iog.og[12]" "tweakSet9.dsm" -na;
connectAttr "tweak9.msg" "tweakSet9.ub[0]";
connectAttr "mesh_leg_rShapeOrig.w" "groupParts20.ig";
connectAttr "groupId31.id" "groupParts20.gi";
connectAttr "grp_leg_rigging_r.msg" "bindPose8.m[0]";
connectAttr "grp_joint_leg_bind_r.msg" "bindPose8.m[1]";
connectAttr "joint_leg_bind_r.msg" "bindPose8.m[2]";
connectAttr "bindPose8.w" "bindPose8.p[0]";
connectAttr "bindPose8.m[0]" "bindPose8.p[1]";
connectAttr "bindPose8.m[1]" "bindPose8.p[2]";
connectAttr "joint_leg_bind_r.bps" "bindPose8.wm[2]";
connectAttr "grp_leg_rigging_l.msg" "bindPose9.m[0]";
connectAttr "grp_joint_leg_bind_l.msg" "bindPose9.m[1]";
connectAttr "joint_leg_bind_l.msg" "bindPose9.m[2]";
connectAttr "bindPose9.w" "bindPose9.p[0]";
connectAttr "bindPose9.m[0]" "bindPose9.p[1]";
connectAttr "bindPose9.m[1]" "bindPose9.p[2]";
connectAttr "joint_leg_bind_l.bps" "bindPose9.wm[2]";
connectAttr "floatMath5.of" "multiplyDivide7.i1x";
connectAttr "floatMath1.of" "floatMath3._fa";
connectAttr "floatMath2.of" "floatMath4._fa";
connectAttr "skinCluster15GroupParts.og" "skinCluster15.ip[0].ig";
connectAttr "skinCluster15GroupId.id" "skinCluster15.ip[0].gi";
connectAttr "bindPose14.msg" "skinCluster15.bp";
connectAttr "joint_arm_bind_l.wm" "skinCluster15.ma[0]";
connectAttr "joint_arm_bind_l.liw" "skinCluster15.lw[0]";
connectAttr "joint_arm_bind_l.obcc" "skinCluster15.ifcl[0]";
connectAttr "groupParts32.og" "tweak15.ip[0].ig";
connectAttr "groupId43.id" "tweak15.ip[0].gi";
connectAttr "skinCluster15GroupId.msg" "skinCluster15Set.gn" -na;
connectAttr "mesh_arm_lShape.iog.og[0]" "skinCluster15Set.dsm" -na;
connectAttr "skinCluster15.msg" "skinCluster15Set.ub[0]";
connectAttr "tweak15.og[0]" "skinCluster15GroupParts.ig";
connectAttr "skinCluster15GroupId.id" "skinCluster15GroupParts.gi";
connectAttr "groupId43.msg" "tweakSet15.gn" -na;
connectAttr "mesh_arm_lShape.iog.og[1]" "tweakSet15.dsm" -na;
connectAttr "tweak15.msg" "tweakSet15.ub[0]";
connectAttr "mesh_arm_lShapeOrig.w" "groupParts32.ig";
connectAttr "groupId43.id" "groupParts32.gi";
connectAttr "grp_arm_bind_l.msg" "bindPose14.m[0]";
connectAttr "joint_arm_bind_l.msg" "bindPose14.m[1]";
connectAttr "grp_arm_rigging_l.msg" "bindPose14.m[2]";
connectAttr "player_RIG.msg" "bindPose14.m[3]";
connectAttr "extraNodes_GRP.msg" "bindPose14.m[4]";
connectAttr "grp_rigging.msg" "bindPose14.m[5]";
connectAttr "bindPose14.m[2]" "bindPose14.p[0]";
connectAttr "bindPose14.m[0]" "bindPose14.p[1]";
connectAttr "bindPose14.m[5]" "bindPose14.p[2]";
connectAttr "bindPose14.w" "bindPose14.p[3]";
connectAttr "player_RIG.msg" "bindPose14.p[4]";
connectAttr "extraNodes_GRP.msg" "bindPose14.p[5]";
connectAttr "joint_arm_bind_l.bps" "bindPose14.wm[1]";
connectAttr "makeNurbCircle1.oc" "deleteComponent1.ig";
connectAttr "deleteComponent1.og" "transformGeometry2.ig";
connectAttr "floatMath13.of" "floatMath10._fa";
connectAttr "floatMath31.of" "floatMath10._fb";
connectAttr "unitConversion22.o" "floatMath12._fa";
connectAttr "floatMath12.of" "floatMath13._fa";
connectAttr "joint_ff_l.rz" "unitConversion22.i";
connectAttr "floatMath14.of" "floatMath15._fa";
connectAttr "floatMath10.of" "floatMath16._fa";
connectAttr "floatMath16.of" "floatMath17._fa";
connectAttr "anim_leg_settings_l.FancyFeet" "floatMath17._fb";
connectAttr "addDoubleLinear3.o" "multiplyDivide8.i1z";
connectAttr "main_CON.sz" "multiplyDivide8.i2z";
connectAttr "distanceBetween2.d" "addDoubleLinear3.i1";
connectAttr "multiplyDivide9.oz" "addDoubleLinear3.i2";
connectAttr "main_CON.sz" "multiplyDivide9.i2z";
connectAttr "main_CON.sz" "multiplyDivide10.i2z";
connectAttr "addDoubleLinear1.o" "multiplyDivide10.i1z";
connectAttr "floatMath21.of" "floatMath22._fa";
connectAttr "floatMath29.of" "floatMath25._fa";
connectAttr "floatMath25.of" "floatMath26._fa";
connectAttr "anim_leg_settings_r.FancyFeet" "floatMath26._fb";
connectAttr "skinCluster10GroupParts.og" "skinCluster10.ip[0].ig";
connectAttr "skinCluster10GroupId.id" "skinCluster10.ip[0].gi";
connectAttr "bindPose9.msg" "skinCluster10.bp";
connectAttr "joint_leg_bind_l.wm" "skinCluster10.ma[0]";
connectAttr "joint_leg_bind_l.liw" "skinCluster10.lw[0]";
connectAttr "joint_leg_bind_l.obcc" "skinCluster10.ifcl[0]";
connectAttr "skinCluster10GroupId.msg" "skinCluster10Set.gn" -na;
connectAttr "mesh_leg_lShape.iog.og[5]" "skinCluster10Set.dsm" -na;
connectAttr "skinCluster10.msg" "skinCluster10Set.ub[0]";
connectAttr "mesh_leg_lShapeOrig.w" "groupParts22.ig";
connectAttr "groupId33.id" "groupParts22.gi";
connectAttr "groupParts22.og" "tweak10.ip[0].ig";
connectAttr "groupId33.id" "tweak10.ip[0].gi";
connectAttr "groupId33.msg" "tweakSet10.gn" -na;
connectAttr "mesh_leg_lShape.iog.og[6]" "tweakSet10.dsm" -na;
connectAttr "tweak10.msg" "tweakSet10.ub[0]";
connectAttr "tweak10.og[0]" "skinCluster10GroupParts.ig";
connectAttr "skinCluster10GroupId.id" "skinCluster10GroupParts.gi";
connectAttr "armorPromoArt_tex.oc" "armorPromoArt_rsMat.diffuse_color";
connectAttr "colorMath2.oc" "armorPromoArt_rsMat.refl_reflectivity";
connectAttr "armorPromoArt_tex.oa" "armorPromoArt_rsMat.opacity_colorR";
connectAttr "armorPromoArt_tex.oa" "armorPromoArt_rsMat.opacity_colorG";
connectAttr "armorPromoArt_tex.oa" "armorPromoArt_rsMat.opacity_colorB";
connectAttr "rsBumpBlender2.oc" "armorPromoArt_rsMat.bump_input";
connectAttr "armorPromoArt_rsMat.oc" "rsMat_armorPromoArtSG.ss";
connectAttr "mesh_boots_lShape.iog" "rsMat_armorPromoArtSG.dsm" -na;
connectAttr "mesh_boots_rShape.iog" "rsMat_armorPromoArtSG.dsm" -na;
connectAttr "mesh_armor_leggings_mShape.iog" "rsMat_armorPromoArtSG.dsm" -na;
connectAttr "mesh_armor_leggings_lShape.iog" "rsMat_armorPromoArtSG.dsm" -na;
connectAttr "mesh_helmetShape.iog" "rsMat_armorPromoArtSG.dsm" -na;
connectAttr "mesh_armor_chest_lShape.iog" "rsMat_armorPromoArtSG.dsm" -na;
connectAttr "mesh_armor_chest_mShape.iog" "rsMat_armorPromoArtSG.dsm" -na;
connectAttr "mesh_armor_chest_rShape.iog" "rsMat_armorPromoArtSG.dsm" -na;
connectAttr "mesh_leggings_armor_rShape.iog" "rsMat_armorPromoArtSG.dsm" -na;
connectAttr "rsMat_armorPromoArtSG.msg" "materialInfo4.sg";
connectAttr "armorPromoArt_rsMat.msg" "materialInfo4.m";
connectAttr "armorPromoArt_tex.msg" "materialInfo4.t" -na;
connectAttr ":defaultColorMgtGlobals.cme" "armorPromoArt_tex.cme";
connectAttr ":defaultColorMgtGlobals.cfe" "armorPromoArt_tex.cmcf";
connectAttr ":defaultColorMgtGlobals.cfp" "armorPromoArt_tex.cmcp";
connectAttr ":defaultColorMgtGlobals.wsn" "armorPromoArt_tex.ws";
connectAttr "place2dTexture_armorPromoArt.o" "armorPromoArt_tex.uv";
connectAttr "aiStandardSurface1SG.msg" "materialInfo5.sg";
connectAttr "layerManager.dli[1]" "layer_meshExtra.id";
connectAttr "unitConversion26.o" "floatMath20._fa";
connectAttr "floatMath20.of" "floatMath23._fa";
connectAttr "joint_ff_r.rx" "unitConversion26.i";
connectAttr "floatMath22.of" "floatMath29._fa";
connectAttr "floatMath23.of" "floatMath29._fb";
connectAttr "unitConversion25.o" "floatMath21._fa";
connectAttr "joint_ff_r.rz" "unitConversion25.i";
connectAttr "unitConversion27.o" "floatMath30._fa";
connectAttr "floatMath30.of" "floatMath31._fa";
connectAttr "joint_ff_l.rx" "unitConversion27.i";
connectAttr "anim_arm_settings_l.IKFKControl" "blendColors7.b";
connectAttr "decomposeMatrix8.ot" "blendColors7.c2";
connectAttr "grp_arm_fk_l.t" "blendColors7.c1";
connectAttr "anim_arm_settings_l.IKFKControl" "blendColors8.b";
connectAttr "unitConversion33.o" "blendColors8.c1";
connectAttr "unitConversion32.o" "blendColors8.c2";
connectAttr "blendColors8.opr" "unitConversion28.i";
connectAttr "blendColors8.opg" "unitConversion29.i";
connectAttr "blendColors8.opb" "unitConversion30.i";
connectAttr "decomposeMatrix8.or" "unitConversion32.i";
connectAttr "anim_arm_settings_l.IKFKControl" "condition23.ft";
connectAttr "skinPromoArt_rsMat.oc" "aiStandardSurface2SG.ss";
connectAttr "mesh_leg_lShape.iog" "aiStandardSurface2SG.dsm" -na;
connectAttr "mesh_arm_lShape.iog" "aiStandardSurface2SG.dsm" -na;
connectAttr "mesh_bodyShape.iog" "aiStandardSurface2SG.dsm" -na;
connectAttr "mesh_arm_rShape.iog" "aiStandardSurface2SG.dsm" -na;
connectAttr "mesh_leg_rShape.iog" "aiStandardSurface2SG.dsm" -na;
connectAttr "mesh_head_secondShape.iog" "aiStandardSurface2SG.dsm" -na;
connectAttr "mesh_arm_second_rShape.iog" "aiStandardSurface2SG.dsm" -na;
connectAttr "mesh_arm_second_lShape.iog" "aiStandardSurface2SG.dsm" -na;
connectAttr "mesh_body_secondShape.iog" "aiStandardSurface2SG.dsm" -na;
connectAttr "mesh_leg_second_rShape.iog" "aiStandardSurface2SG.dsm" -na;
connectAttr "mesh_leg_second_lShape.iog" "aiStandardSurface2SG.dsm" -na;
connectAttr "mesh_arm_alex_second_lShape.iog" "aiStandardSurface2SG.dsm" -na;
connectAttr "mesh_arm_alex_lShape.iog" "aiStandardSurface2SG.dsm" -na;
connectAttr "mesh_arm_alex_second_rShape.iog" "aiStandardSurface2SG.dsm" -na;
connectAttr "mesh_arm_alex_rShape.iog" "aiStandardSurface2SG.dsm" -na;
connectAttr "mesh_head_plainShape.iog.og[4]" "aiStandardSurface2SG.dsm" -na;
connectAttr "mesh_head_plainShape.ciog.cog[0]" "aiStandardSurface2SG.dsm" -na;
connectAttr "groupId110.msg" "aiStandardSurface2SG.gn" -na;
connectAttr "groupId111.msg" "aiStandardSurface2SG.gn" -na;
connectAttr "aiStandardSurface2SG.msg" "materialInfo6.sg";
connectAttr "skinPromoArt_rsMat.msg" "materialInfo6.m";
connectAttr "skinPromoArt_tex.msg" "materialInfo6.t" -na;
connectAttr "anim_arm_fk_l.wm" "decomposeMatrix7.imat";
connectAttr "joint_arm_ik_top_l.wm" "decomposeMatrix8.imat";
connectAttr "grp_arm_fk_l.r" "unitConversion33.i";
connectAttr "anim_arm_settings_l.WorldLocalIK" "multiplyDivide11.i2x";
connectAttr "anim_arm_settings_l.WorldLocalIK" "multiplyDivide11.i2y";
connectAttr "anim_arm_settings_l.WorldLocalIK" "multiplyDivide11.i2z";
connectAttr "unitConversion34.o" "multiplyDivide11.i1";
connectAttr "anim_arm_settings_l.WorldLocalIK" "multiplyDivide12.i2x";
connectAttr "anim_arm_settings_l.WorldLocalIK" "multiplyDivide12.i2y";
connectAttr "anim_arm_settings_l.WorldLocalIK" "multiplyDivide12.i2z";
connectAttr "anim_body.t" "multiplyDivide12.i1";
connectAttr "anim_body.r" "unitConversion34.i";
connectAttr "multiplyDivide11.o" "unitConversion35.i";
connectAttr "deleteComponent2.og" "transformGeometry3.ig";
connectAttr "makeNurbCircle2.oc" "deleteComponent2.ig";
connectAttr "anim_arm_settings_r.IKFKControl" "blendColors9.b";
connectAttr "decomposeMatrix9.ot" "blendColors9.c2";
connectAttr "grp_arm_fk_r.t" "blendColors9.c1";
connectAttr "joint_arm_ik_top_r.wm" "decomposeMatrix9.imat";
connectAttr "anim_arm_fk_r.wm" "decomposeMatrix10.imat";
connectAttr "blendColors10.opr" "unitConversion36.i";
connectAttr "anim_arm_settings_r.IKFKControl" "blendColors10.b";
connectAttr "unitConversion37.o" "blendColors10.c1";
connectAttr "unitConversion46.o" "blendColors10.c2";
connectAttr "grp_arm_fk_r.r" "unitConversion37.i";
connectAttr "blendColors10.opg" "unitConversion39.i";
connectAttr "blendColors10.opb" "unitConversion40.i";
connectAttr "multiplyDivide13.o" "unitConversion41.i";
connectAttr "anim_arm_settings_r.WorldLocalIK" "multiplyDivide13.i2x";
connectAttr "anim_arm_settings_r.WorldLocalIK" "multiplyDivide13.i2y";
connectAttr "anim_arm_settings_r.WorldLocalIK" "multiplyDivide13.i2z";
connectAttr "unitConversion42.o" "multiplyDivide13.i1";
connectAttr "anim_body.r" "unitConversion42.i";
connectAttr "floatMath33.of" "floatMath32._fa";
connectAttr "floatMath34.of" "floatMath33._fa";
connectAttr "floatMath35.of" "floatMath34._fa";
connectAttr "floatMath37.of" "floatMath34._fb";
connectAttr "floatMath36.of" "floatMath35._fa";
connectAttr "floatMath38.of" "floatMath37._fa";
connectAttr "multiplyDivide14.oz" "condition25.ft";
connectAttr "remapValue10.ov" "condition25.ctb";
connectAttr "multiplyDivide15.oz" "multiplyDivide14.i1z";
connectAttr "remapValue7.ov" "condition26.ft";
connectAttr "remapValue7.ov" "condition27.ft";
connectAttr "remapValue7.ov" "condition28.ft";
connectAttr "remapValue7.ov" "condition29.ft";
connectAttr "floatMath41.of" "multiplyDivide16.i1x";
connectAttr "floatMath43.of" "floatMath42._fa";
connectAttr "floatMath45.of" "floatMath44._fa";
connectAttr "floatMath47.of" "floatMath46._fa";
connectAttr "floatMath48.of" "floatMath47._fa";
connectAttr "floatMath49.of" "floatMath48._fa";
connectAttr "floatMath51.of" "floatMath48._fb";
connectAttr "floatMath50.of" "floatMath49._fa";
connectAttr "floatMath52.of" "floatMath51._fa";
connectAttr "multiplyDivide17.oz" "condition33.ft";
connectAttr "remapValue9.ov" "condition33.ctb";
connectAttr "multiplyDivide18.oz" "multiplyDivide17.i1z";
connectAttr "multiplyDivide17.oz" "remapValue9.i";
connectAttr "anim_arm_settings_r.IKFKControl" "condition34.ft";
connectAttr "multiplyDivide14.oz" "remapValue10.i";
connectAttr "anim_arm_settings_r.WorldLocalIK" "multiplyDivide21.i2x";
connectAttr "anim_arm_settings_r.WorldLocalIK" "multiplyDivide21.i2y";
connectAttr "anim_arm_settings_r.WorldLocalIK" "multiplyDivide21.i2z";
connectAttr "anim_body.t" "multiplyDivide21.i1";
connectAttr "skinCluster16GroupParts.og" "skinCluster16.ip[0].ig";
connectAttr "skinCluster16GroupId.id" "skinCluster16.ip[0].gi";
connectAttr "bindPose15.msg" "skinCluster16.bp";
connectAttr "joint_arm_bind_r.wm" "skinCluster16.ma[0]";
connectAttr "joint_arm_bind_r.liw" "skinCluster16.lw[0]";
connectAttr "joint_arm_bind_r.obcc" "skinCluster16.ifcl[0]";
connectAttr "groupParts34.og" "tweak16.ip[0].ig";
connectAttr "groupId45.id" "tweak16.ip[0].gi";
connectAttr "skinCluster16GroupId.msg" "skinCluster16Set.gn" -na;
connectAttr "mesh_arm_rShape.iog.og[0]" "skinCluster16Set.dsm" -na;
connectAttr "skinCluster16.msg" "skinCluster16Set.ub[0]";
connectAttr "tweak16.og[0]" "skinCluster16GroupParts.ig";
connectAttr "skinCluster16GroupId.id" "skinCluster16GroupParts.gi";
connectAttr "groupId45.msg" "tweakSet16.gn" -na;
connectAttr "mesh_arm_rShape.iog.og[1]" "tweakSet16.dsm" -na;
connectAttr "tweak16.msg" "tweakSet16.ub[0]";
connectAttr "mesh_arm_rShapeOrig.w" "groupParts34.ig";
connectAttr "groupId45.id" "groupParts34.gi";
connectAttr "grp_arm_rigging_r.msg" "bindPose15.m[0]";
connectAttr "grp_arm_bind_r.msg" "bindPose15.m[1]";
connectAttr "joint_arm_bind_r.msg" "bindPose15.m[2]";
connectAttr "player_RIG.msg" "bindPose15.m[3]";
connectAttr "extraNodes_GRP.msg" "bindPose15.m[4]";
connectAttr "grp_rigging.msg" "bindPose15.m[5]";
connectAttr "bindPose15.m[5]" "bindPose15.p[0]";
connectAttr "bindPose15.m[0]" "bindPose15.p[1]";
connectAttr "bindPose15.m[1]" "bindPose15.p[2]";
connectAttr "bindPose15.w" "bindPose15.p[3]";
connectAttr "player_RIG.msg" "bindPose15.p[4]";
connectAttr "extraNodes_GRP.msg" "bindPose15.p[5]";
connectAttr "joint_arm_bind_r.bps" "bindPose15.wm[2]";
connectAttr "unitConversion45.o" "multiplyDivide22.i1";
connectAttr "joint_arm_ik_top_r.r" "unitConversion45.i";
connectAttr "multiplyDivide22.oy" "floatMath53._fa";
connectAttr "decomposeMatrix9.or" "unitConversion46.i";
connectAttr "skinCluster18GroupParts.og" "skinCluster18.ip[0].ig";
connectAttr "skinCluster18GroupId.id" "skinCluster18.ip[0].gi";
connectAttr "joint_arm_bind_r.wm" "skinCluster18.ma[0]";
connectAttr "joint_arm_bind_r.liw" "skinCluster18.lw[0]";
connectAttr "joint_arm_bind_r.obcc" "skinCluster18.ifcl[0]";
connectAttr "bindPose15.msg" "skinCluster18.bp";
connectAttr "groupParts38.og" "tweak18.ip[0].ig";
connectAttr "groupId49.id" "tweak18.ip[0].gi";
connectAttr "skinCluster18GroupId.msg" "skinCluster18Set.gn" -na;
connectAttr "mesh_arm_second_rShape.iog.og[0]" "skinCluster18Set.dsm" -na;
connectAttr "skinCluster18.msg" "skinCluster18Set.ub[0]";
connectAttr "tweak18.og[0]" "skinCluster18GroupParts.ig";
connectAttr "skinCluster18GroupId.id" "skinCluster18GroupParts.gi";
connectAttr "groupId49.msg" "tweakSet18.gn" -na;
connectAttr "mesh_arm_second_rShape.iog.og[1]" "tweakSet18.dsm" -na;
connectAttr "tweak18.msg" "tweakSet18.ub[0]";
connectAttr "mesh_arm_second_rShapeOrig.w" "groupParts38.ig";
connectAttr "groupId49.id" "groupParts38.gi";
connectAttr "skinCluster19GroupParts.og" "skinCluster19.ip[0].ig";
connectAttr "skinCluster19GroupId.id" "skinCluster19.ip[0].gi";
connectAttr "joint_body.wm" "skinCluster19.ma[0]";
connectAttr "joint_body.liw" "skinCluster19.lw[0]";
connectAttr "joint_body.obcc" "skinCluster19.ifcl[0]";
connectAttr "bindPose3.msg" "skinCluster19.bp";
connectAttr "groupParts40.og" "tweak19.ip[0].ig";
connectAttr "groupId51.id" "tweak19.ip[0].gi";
connectAttr "skinCluster19GroupId.msg" "skinCluster19Set.gn" -na;
connectAttr "mesh_body_secondShape.iog.og[0]" "skinCluster19Set.dsm" -na;
connectAttr "skinCluster19.msg" "skinCluster19Set.ub[0]";
connectAttr "tweak19.og[0]" "skinCluster19GroupParts.ig";
connectAttr "skinCluster19GroupId.id" "skinCluster19GroupParts.gi";
connectAttr "groupId51.msg" "tweakSet19.gn" -na;
connectAttr "mesh_body_secondShape.iog.og[1]" "tweakSet19.dsm" -na;
connectAttr "tweak19.msg" "tweakSet19.ub[0]";
connectAttr "mesh_body_secondShapeOrig1.w" "groupParts40.ig";
connectAttr "groupId51.id" "groupParts40.gi";
connectAttr "skinCluster20GroupParts.og" "skinCluster20.ip[0].ig";
connectAttr "skinCluster20GroupId.id" "skinCluster20.ip[0].gi";
connectAttr "joint_arm_bind_l.wm" "skinCluster20.ma[0]";
connectAttr "joint_arm_bind_l.liw" "skinCluster20.lw[0]";
connectAttr "joint_arm_bind_l.obcc" "skinCluster20.ifcl[0]";
connectAttr "bindPose14.msg" "skinCluster20.bp";
connectAttr "groupParts42.og" "tweak20.ip[0].ig";
connectAttr "groupId53.id" "tweak20.ip[0].gi";
connectAttr "skinCluster20GroupId.msg" "skinCluster20Set.gn" -na;
connectAttr "mesh_arm_second_lShape.iog.og[0]" "skinCluster20Set.dsm" -na;
connectAttr "skinCluster20.msg" "skinCluster20Set.ub[0]";
connectAttr "tweak20.og[0]" "skinCluster20GroupParts.ig";
connectAttr "skinCluster20GroupId.id" "skinCluster20GroupParts.gi";
connectAttr "groupId53.msg" "tweakSet20.gn" -na;
connectAttr "mesh_arm_second_lShape.iog.og[1]" "tweakSet20.dsm" -na;
connectAttr "tweak20.msg" "tweakSet20.ub[0]";
connectAttr "mesh_arm_second_lShapeOrig1.w" "groupParts42.ig";
connectAttr "groupId53.id" "groupParts42.gi";
connectAttr "skinCluster21GroupParts.og" "skinCluster21.ip[0].ig";
connectAttr "skinCluster21GroupId.id" "skinCluster21.ip[0].gi";
connectAttr "joint_leg_bind_l.wm" "skinCluster21.ma[0]";
connectAttr "joint_leg_bind_l.liw" "skinCluster21.lw[0]";
connectAttr "joint_leg_bind_l.obcc" "skinCluster21.ifcl[0]";
connectAttr "bindPose9.msg" "skinCluster21.bp";
connectAttr "groupParts44.og" "tweak21.ip[0].ig";
connectAttr "groupId55.id" "tweak21.ip[0].gi";
connectAttr "skinCluster21GroupId.msg" "skinCluster21Set.gn" -na;
connectAttr "mesh_leg_second_lShape.iog.og[0]" "skinCluster21Set.dsm" -na;
connectAttr "skinCluster21.msg" "skinCluster21Set.ub[0]";
connectAttr "tweak21.og[0]" "skinCluster21GroupParts.ig";
connectAttr "skinCluster21GroupId.id" "skinCluster21GroupParts.gi";
connectAttr "groupId55.msg" "tweakSet21.gn" -na;
connectAttr "mesh_leg_second_lShape.iog.og[1]" "tweakSet21.dsm" -na;
connectAttr "tweak21.msg" "tweakSet21.ub[0]";
connectAttr "mesh_leg_second_lShapeOrig1.w" "groupParts44.ig";
connectAttr "groupId55.id" "groupParts44.gi";
connectAttr "skinCluster22GroupParts.og" "skinCluster22.ip[0].ig";
connectAttr "skinCluster22GroupId.id" "skinCluster22.ip[0].gi";
connectAttr "joint_leg_bind_r.wm" "skinCluster22.ma[0]";
connectAttr "joint_leg_bind_r.liw" "skinCluster22.lw[0]";
connectAttr "joint_leg_bind_r.obcc" "skinCluster22.ifcl[0]";
connectAttr "bindPose8.msg" "skinCluster22.bp";
connectAttr "groupParts46.og" "tweak22.ip[0].ig";
connectAttr "groupId57.id" "tweak22.ip[0].gi";
connectAttr "skinCluster22GroupId.msg" "skinCluster22Set.gn" -na;
connectAttr "mesh_leg_second_rShape.iog.og[0]" "skinCluster22Set.dsm" -na;
connectAttr "skinCluster22.msg" "skinCluster22Set.ub[0]";
connectAttr "tweak22.og[0]" "skinCluster22GroupParts.ig";
connectAttr "skinCluster22GroupId.id" "skinCluster22GroupParts.gi";
connectAttr "groupId57.msg" "tweakSet22.gn" -na;
connectAttr "mesh_leg_second_rShape.iog.og[1]" "tweakSet22.dsm" -na;
connectAttr "tweak22.msg" "tweakSet22.ub[0]";
connectAttr "mesh_leg_second_rShapeOrig1.w" "groupParts46.ig";
connectAttr "groupId57.id" "groupParts46.gi";
connectAttr "skinCluster23GroupParts.og" "skinCluster23.ip[0].ig";
connectAttr "skinCluster23GroupId.id" "skinCluster23.ip[0].gi";
connectAttr "joint_arm_bind_l.wm" "skinCluster23.ma[0]";
connectAttr "joint_arm_bind_l.liw" "skinCluster23.lw[0]";
connectAttr "joint_arm_bind_l.obcc" "skinCluster23.ifcl[0]";
connectAttr "bindPose14.msg" "skinCluster23.bp";
connectAttr "groupParts48.og" "tweak23.ip[0].ig";
connectAttr "groupId59.id" "tweak23.ip[0].gi";
connectAttr "skinCluster23GroupId.msg" "skinCluster23Set.gn" -na;
connectAttr "mesh_armor_chest_lShape.iog.og[0]" "skinCluster23Set.dsm" -na;
connectAttr "skinCluster23.msg" "skinCluster23Set.ub[0]";
connectAttr "tweak23.og[0]" "skinCluster23GroupParts.ig";
connectAttr "skinCluster23GroupId.id" "skinCluster23GroupParts.gi";
connectAttr "groupId59.msg" "tweakSet23.gn" -na;
connectAttr "mesh_armor_chest_lShape.iog.og[1]" "tweakSet23.dsm" -na;
connectAttr "tweak23.msg" "tweakSet23.ub[0]";
connectAttr "mesh_armor_chest_lShapeOrig.w" "groupParts48.ig";
connectAttr "groupId59.id" "groupParts48.gi";
connectAttr "skinCluster24GroupParts.og" "skinCluster24.ip[0].ig";
connectAttr "skinCluster24GroupId.id" "skinCluster24.ip[0].gi";
connectAttr "joint_arm_bind_r.wm" "skinCluster24.ma[0]";
connectAttr "joint_arm_bind_r.liw" "skinCluster24.lw[0]";
connectAttr "joint_arm_bind_r.obcc" "skinCluster24.ifcl[0]";
connectAttr "bindPose15.msg" "skinCluster24.bp";
connectAttr "groupParts50.og" "tweak24.ip[0].ig";
connectAttr "groupId61.id" "tweak24.ip[0].gi";
connectAttr "skinCluster24GroupId.msg" "skinCluster24Set.gn" -na;
connectAttr "mesh_armor_chest_rShape.iog.og[0]" "skinCluster24Set.dsm" -na;
connectAttr "skinCluster24.msg" "skinCluster24Set.ub[0]";
connectAttr "tweak24.og[0]" "skinCluster24GroupParts.ig";
connectAttr "skinCluster24GroupId.id" "skinCluster24GroupParts.gi";
connectAttr "groupId61.msg" "tweakSet24.gn" -na;
connectAttr "mesh_armor_chest_rShape.iog.og[1]" "tweakSet24.dsm" -na;
connectAttr "tweak24.msg" "tweakSet24.ub[0]";
connectAttr "mesh_armor_chest_rShapeOrig.w" "groupParts50.ig";
connectAttr "groupId61.id" "groupParts50.gi";
connectAttr "groupParts52.og" "tweak25.ip[0].ig";
connectAttr "groupId63.id" "tweak25.ip[0].gi";
connectAttr "groupId63.msg" "tweakSet25.gn" -na;
connectAttr "mesh_helmetShape.iog.og[1]" "tweakSet25.dsm" -na;
connectAttr "tweak25.msg" "tweakSet25.ub[0]";
connectAttr "mesh_helmetShapeOrig.w" "groupParts52.ig";
connectAttr "groupId63.id" "groupParts52.gi";
connectAttr "skinCluster26GroupParts.og" "skinCluster26.ip[0].ig";
connectAttr "skinCluster26GroupId.id" "skinCluster26.ip[0].gi";
connectAttr "joint_body.wm" "skinCluster26.ma[0]";
connectAttr "joint_body.liw" "skinCluster26.lw[0]";
connectAttr "joint_body.obcc" "skinCluster26.ifcl[0]";
connectAttr "bindPose3.msg" "skinCluster26.bp";
connectAttr "groupParts54.og" "tweak26.ip[0].ig";
connectAttr "groupId65.id" "tweak26.ip[0].gi";
connectAttr "skinCluster26GroupId.msg" "skinCluster26Set.gn" -na;
connectAttr "mesh_armor_chest_mShape.iog.og[0]" "skinCluster26Set.dsm" -na;
connectAttr "skinCluster26.msg" "skinCluster26Set.ub[0]";
connectAttr "tweak26.og[0]" "skinCluster26GroupParts.ig";
connectAttr "skinCluster26GroupId.id" "skinCluster26GroupParts.gi";
connectAttr "groupId65.msg" "tweakSet26.gn" -na;
connectAttr "mesh_armor_chest_mShape.iog.og[1]" "tweakSet26.dsm" -na;
connectAttr "tweak26.msg" "tweakSet26.ub[0]";
connectAttr "mesh_armor_chest_mShapeOrig1.w" "groupParts54.ig";
connectAttr "groupId65.id" "groupParts54.gi";
connectAttr "skinCluster27GroupParts.og" "skinCluster27.ip[0].ig";
connectAttr "skinCluster27GroupId.id" "skinCluster27.ip[0].gi";
connectAttr "joint_body.wm" "skinCluster27.ma[0]";
connectAttr "joint_body.liw" "skinCluster27.lw[0]";
connectAttr "joint_body.obcc" "skinCluster27.ifcl[0]";
connectAttr "bindPose3.msg" "skinCluster27.bp";
connectAttr "groupParts56.og" "tweak27.ip[0].ig";
connectAttr "groupId67.id" "tweak27.ip[0].gi";
connectAttr "skinCluster27GroupId.msg" "skinCluster27Set.gn" -na;
connectAttr "mesh_armor_leggings_mShape.iog.og[0]" "skinCluster27Set.dsm" -na;
connectAttr "skinCluster27.msg" "skinCluster27Set.ub[0]";
connectAttr "tweak27.og[0]" "skinCluster27GroupParts.ig";
connectAttr "skinCluster27GroupId.id" "skinCluster27GroupParts.gi";
connectAttr "groupId67.msg" "tweakSet27.gn" -na;
connectAttr "mesh_armor_leggings_mShape.iog.og[1]" "tweakSet27.dsm" -na;
connectAttr "tweak27.msg" "tweakSet27.ub[0]";
connectAttr "mesh_armor_leggings_mShapeOrig1.w" "groupParts56.ig";
connectAttr "groupId67.id" "groupParts56.gi";
connectAttr "skinCluster28GroupParts.og" "skinCluster28.ip[0].ig";
connectAttr "skinCluster28GroupId.id" "skinCluster28.ip[0].gi";
connectAttr "joint_leg_bind_l.wm" "skinCluster28.ma[0]";
connectAttr "joint_leg_bind_l.liw" "skinCluster28.lw[0]";
connectAttr "joint_leg_bind_l.obcc" "skinCluster28.ifcl[0]";
connectAttr "bindPose9.msg" "skinCluster28.bp";
connectAttr "groupParts58.og" "tweak28.ip[0].ig";
connectAttr "groupId69.id" "tweak28.ip[0].gi";
connectAttr "skinCluster28GroupId.msg" "skinCluster28Set.gn" -na;
connectAttr "mesh_armor_leggings_lShape.iog.og[0]" "skinCluster28Set.dsm" -na;
connectAttr "skinCluster28.msg" "skinCluster28Set.ub[0]";
connectAttr "tweak28.og[0]" "skinCluster28GroupParts.ig";
connectAttr "skinCluster28GroupId.id" "skinCluster28GroupParts.gi";
connectAttr "groupId69.msg" "tweakSet28.gn" -na;
connectAttr "mesh_armor_leggings_lShape.iog.og[1]" "tweakSet28.dsm" -na;
connectAttr "tweak28.msg" "tweakSet28.ub[0]";
connectAttr "mesh_armor_leggings_lShapeOrig1.w" "groupParts58.ig";
connectAttr "groupId69.id" "groupParts58.gi";
connectAttr "skinCluster29GroupParts.og" "skinCluster29.ip[0].ig";
connectAttr "skinCluster29GroupId.id" "skinCluster29.ip[0].gi";
connectAttr "joint_leg_bind_l.wm" "skinCluster29.ma[0]";
connectAttr "joint_leg_bind_l.liw" "skinCluster29.lw[0]";
connectAttr "joint_leg_bind_l.obcc" "skinCluster29.ifcl[0]";
connectAttr "bindPose9.msg" "skinCluster29.bp";
connectAttr "groupParts60.og" "tweak29.ip[0].ig";
connectAttr "groupId71.id" "tweak29.ip[0].gi";
connectAttr "skinCluster29GroupId.msg" "skinCluster29Set.gn" -na;
connectAttr "mesh_boots_lShape.iog.og[0]" "skinCluster29Set.dsm" -na;
connectAttr "skinCluster29.msg" "skinCluster29Set.ub[0]";
connectAttr "tweak29.og[0]" "skinCluster29GroupParts.ig";
connectAttr "skinCluster29GroupId.id" "skinCluster29GroupParts.gi";
connectAttr "groupId71.msg" "tweakSet29.gn" -na;
connectAttr "mesh_boots_lShape.iog.og[1]" "tweakSet29.dsm" -na;
connectAttr "tweak29.msg" "tweakSet29.ub[0]";
connectAttr "mesh_boots_lShapeOrig1.w" "groupParts60.ig";
connectAttr "groupId71.id" "groupParts60.gi";
connectAttr "skinCluster30GroupParts.og" "skinCluster30.ip[0].ig";
connectAttr "skinCluster30GroupId.id" "skinCluster30.ip[0].gi";
connectAttr "joint_leg_bind_r.wm" "skinCluster30.ma[0]";
connectAttr "joint_leg_bind_r.liw" "skinCluster30.lw[0]";
connectAttr "joint_leg_bind_r.obcc" "skinCluster30.ifcl[0]";
connectAttr "bindPose8.msg" "skinCluster30.bp";
connectAttr "groupParts62.og" "tweak30.ip[0].ig";
connectAttr "groupId73.id" "tweak30.ip[0].gi";
connectAttr "skinCluster30GroupId.msg" "skinCluster30Set.gn" -na;
connectAttr "mesh_leggings_armor_rShape.iog.og[0]" "skinCluster30Set.dsm" -na;
connectAttr "skinCluster30.msg" "skinCluster30Set.ub[0]";
connectAttr "tweak30.og[0]" "skinCluster30GroupParts.ig";
connectAttr "skinCluster30GroupId.id" "skinCluster30GroupParts.gi";
connectAttr "groupId73.msg" "tweakSet30.gn" -na;
connectAttr "mesh_leggings_armor_rShape.iog.og[1]" "tweakSet30.dsm" -na;
connectAttr "tweak30.msg" "tweakSet30.ub[0]";
connectAttr "mesh_leggings_armor_rShapeOrig1.w" "groupParts62.ig";
connectAttr "groupId73.id" "groupParts62.gi";
connectAttr "skinCluster31GroupParts.og" "skinCluster31.ip[0].ig";
connectAttr "skinCluster31GroupId.id" "skinCluster31.ip[0].gi";
connectAttr "joint_leg_bind_r.wm" "skinCluster31.ma[0]";
connectAttr "joint_leg_bind_r.liw" "skinCluster31.lw[0]";
connectAttr "joint_leg_bind_r.obcc" "skinCluster31.ifcl[0]";
connectAttr "bindPose8.msg" "skinCluster31.bp";
connectAttr "groupParts64.og" "tweak31.ip[0].ig";
connectAttr "groupId75.id" "tweak31.ip[0].gi";
connectAttr "skinCluster31GroupId.msg" "skinCluster31Set.gn" -na;
connectAttr "mesh_boots_rShape.iog.og[0]" "skinCluster31Set.dsm" -na;
connectAttr "skinCluster31.msg" "skinCluster31Set.ub[0]";
connectAttr "tweak31.og[0]" "skinCluster31GroupParts.ig";
connectAttr "skinCluster31GroupId.id" "skinCluster31GroupParts.gi";
connectAttr "groupId75.msg" "tweakSet31.gn" -na;
connectAttr "mesh_boots_rShape.iog.og[1]" "tweakSet31.dsm" -na;
connectAttr "tweak31.msg" "tweakSet31.ub[0]";
connectAttr "mesh_boots_rShapeOrig1.w" "groupParts64.ig";
connectAttr "groupId75.id" "groupParts64.gi";
connectAttr "cluster1GroupParts.og" "cluster_headconCluster.ip[0].ig";
connectAttr "cluster1GroupId.id" "cluster_headconCluster.ip[0].gi";
connectAttr "cluster_headcon.wm" "cluster_headconCluster.ma";
connectAttr "cluster_headconShape.x" "cluster_headconCluster.x";
connectAttr "groupParts66.og" "tweak32.ip[0].ig";
connectAttr "groupId77.id" "tweak32.ip[0].gi";
connectAttr "cluster1GroupId.msg" "cluster1Set.gn" -na;
connectAttr "head_CONShape.iog.og[0]" "cluster1Set.dsm" -na;
connectAttr "cluster_headconCluster.msg" "cluster1Set.ub[0]";
connectAttr "tweak32.og[0]" "cluster1GroupParts.ig";
connectAttr "cluster1GroupId.id" "cluster1GroupParts.gi";
connectAttr "groupId77.msg" "tweakSet32.gn" -na;
connectAttr "head_CONShape.iog.og[1]" "tweakSet32.dsm" -na;
connectAttr "tweak32.msg" "tweakSet32.ub[0]";
connectAttr "head_CONShapeOrig.ws" "groupParts66.ig";
connectAttr "groupId77.id" "groupParts66.gi";
connectAttr "cluster2GroupParts.og" "cluster_bodyconCluster.ip[0].ig";
connectAttr "cluster2GroupId.id" "cluster_bodyconCluster.ip[0].gi";
connectAttr "cluster_bodycon.wm" "cluster_bodyconCluster.ma";
connectAttr "cluster_bodyconShape.x" "cluster_bodyconCluster.x";
connectAttr "groupParts68.og" "tweak33.ip[0].ig";
connectAttr "groupId79.id" "tweak33.ip[0].gi";
connectAttr "cluster2GroupId.msg" "cluster2Set.gn" -na;
connectAttr "anim_bodyShape.iog.og[0]" "cluster2Set.dsm" -na;
connectAttr "cluster_bodyconCluster.msg" "cluster2Set.ub[0]";
connectAttr "tweak33.og[0]" "cluster2GroupParts.ig";
connectAttr "cluster2GroupId.id" "cluster2GroupParts.gi";
connectAttr "groupId79.msg" "tweakSet33.gn" -na;
connectAttr "anim_bodyShape.iog.og[1]" "tweakSet33.dsm" -na;
connectAttr "tweak33.msg" "tweakSet33.ub[0]";
connectAttr "anim_bodyShapeOrig.ws" "groupParts68.ig";
connectAttr "groupId79.id" "groupParts68.gi";
connectAttr "cluster3GroupParts.og" "cluster_shoulderconCluster.ip[0].ig";
connectAttr "cluster3GroupId.id" "cluster_shoulderconCluster.ip[0].gi";
connectAttr "cluster3GroupParts1.og" "cluster_shoulderconCluster.ip[1].ig";
connectAttr "cluster3GroupId1.id" "cluster_shoulderconCluster.ip[1].gi";
connectAttr "cluster_shouldercon.wm" "cluster_shoulderconCluster.ma";
connectAttr "cluster_shoulderconShape.x" "cluster_shoulderconCluster.x";
connectAttr "groupParts71.og" "tweak34.ip[0].ig";
connectAttr "groupId82.id" "tweak34.ip[0].gi";
connectAttr "groupParts72.og" "tweak35.ip[0].ig";
connectAttr "groupId83.id" "tweak35.ip[0].gi";
connectAttr "cluster3GroupId.msg" "cluster3Set.gn" -na;
connectAttr "cluster3GroupId1.msg" "cluster3Set.gn" -na;
connectAttr "anim_shoulder_lShape.iog.og[0]" "cluster3Set.dsm" -na;
connectAttr "anim_shoulder_rShape.iog.og[0]" "cluster3Set.dsm" -na;
connectAttr "cluster_shoulderconCluster.msg" "cluster3Set.ub[0]";
connectAttr "tweak34.og[0]" "cluster3GroupParts.ig";
connectAttr "cluster3GroupId.id" "cluster3GroupParts.gi";
connectAttr "tweak35.og[0]" "cluster3GroupParts1.ig";
connectAttr "cluster3GroupId1.id" "cluster3GroupParts1.gi";
connectAttr "groupId82.msg" "tweakSet34.gn" -na;
connectAttr "anim_shoulder_lShape.iog.og[1]" "tweakSet34.dsm" -na;
connectAttr "tweak34.msg" "tweakSet34.ub[0]";
connectAttr "anim_shoulder_lShapeOrig.ws" "groupParts71.ig";
connectAttr "groupId82.id" "groupParts71.gi";
connectAttr "groupId83.msg" "tweakSet35.gn" -na;
connectAttr "anim_shoulder_rShape.iog.og[1]" "tweakSet35.dsm" -na;
connectAttr "tweak35.msg" "tweakSet35.ub[0]";
connectAttr "anim_shoulder_rShapeOrig.ws" "groupParts72.ig";
connectAttr "groupId83.id" "groupParts72.gi";
connectAttr "cluster4GroupParts.og" "cluster_legikcon_rCluster.ip[0].ig";
connectAttr "cluster4GroupId.id" "cluster_legikcon_rCluster.ip[0].gi";
connectAttr "cluster_legikcon_r.wm" "cluster_legikcon_rCluster.ma";
connectAttr "cluster_legikcon_rShape.x" "cluster_legikcon_rCluster.x";
connectAttr "groupParts74.og" "tweak36.ip[0].ig";
connectAttr "groupId85.id" "tweak36.ip[0].gi";
connectAttr "cluster4GroupId.msg" "cluster4Set.gn" -na;
connectAttr "anim_leg_ik_rShape.iog.og[0]" "cluster4Set.dsm" -na;
connectAttr "cluster_legikcon_rCluster.msg" "cluster4Set.ub[0]";
connectAttr "tweak36.og[0]" "cluster4GroupParts.ig";
connectAttr "cluster4GroupId.id" "cluster4GroupParts.gi";
connectAttr "groupId85.msg" "tweakSet36.gn" -na;
connectAttr "anim_leg_ik_rShape.iog.og[1]" "tweakSet36.dsm" -na;
connectAttr "tweak36.msg" "tweakSet36.ub[0]";
connectAttr "anim_leg_ik_rShapeOrig.ws" "groupParts74.ig";
connectAttr "groupId85.id" "groupParts74.gi";
connectAttr "cluster5GroupParts.og" "cluster_legikcon_lCluster.ip[0].ig";
connectAttr "cluster5GroupId.id" "cluster_legikcon_lCluster.ip[0].gi";
connectAttr "cluster_legikcon_l.wm" "cluster_legikcon_lCluster.ma";
connectAttr "cluster_legikcon_lShape.x" "cluster_legikcon_lCluster.x";
connectAttr "groupParts76.og" "tweak37.ip[0].ig";
connectAttr "groupId87.id" "tweak37.ip[0].gi";
connectAttr "cluster5GroupId.msg" "cluster5Set.gn" -na;
connectAttr "anim_leg_ik_lShape.iog.og[0]" "cluster5Set.dsm" -na;
connectAttr "cluster_legikcon_lCluster.msg" "cluster5Set.ub[0]";
connectAttr "tweak37.og[0]" "cluster5GroupParts.ig";
connectAttr "cluster5GroupId.id" "cluster5GroupParts.gi";
connectAttr "groupId87.msg" "tweakSet37.gn" -na;
connectAttr "anim_leg_ik_lShape.iog.og[1]" "tweakSet37.dsm" -na;
connectAttr "tweak37.msg" "tweakSet37.ub[0]";
connectAttr "anim_leg_ik_lShapeOrig.ws" "groupParts76.ig";
connectAttr "groupId87.id" "groupParts76.gi";
connectAttr "cluster6GroupParts.og" "cluster_armfkcon_rCluster.ip[0].ig";
connectAttr "cluster6GroupId.id" "cluster_armfkcon_rCluster.ip[0].gi";
connectAttr "cluster_armfkcon_r.wm" "cluster_armfkcon_rCluster.ma";
connectAttr "cluster_armfkcon_rShape.x" "cluster_armfkcon_rCluster.x";
connectAttr "groupParts78.og" "tweak38.ip[0].ig";
connectAttr "groupId89.id" "tweak38.ip[0].gi";
connectAttr "cluster6GroupId.msg" "cluster6Set.gn" -na;
connectAttr "anim_arm_fk_rShape.iog.og[0]" "cluster6Set.dsm" -na;
connectAttr "cluster_armfkcon_rCluster.msg" "cluster6Set.ub[0]";
connectAttr "tweak38.og[0]" "cluster6GroupParts.ig";
connectAttr "cluster6GroupId.id" "cluster6GroupParts.gi";
connectAttr "groupId89.msg" "tweakSet38.gn" -na;
connectAttr "anim_arm_fk_rShape.iog.og[1]" "tweakSet38.dsm" -na;
connectAttr "tweak38.msg" "tweakSet38.ub[0]";
connectAttr "anim_arm_fk_rShapeOrig.ws" "groupParts78.ig";
connectAttr "groupId89.id" "groupParts78.gi";
connectAttr "cluster7GroupParts.og" "cluster_armfkcon_lCluster.ip[0].ig";
connectAttr "cluster7GroupId.id" "cluster_armfkcon_lCluster.ip[0].gi";
connectAttr "cluster_armfkcon_l.wm" "cluster_armfkcon_lCluster.ma";
connectAttr "cluster_armfkcon_lShape.x" "cluster_armfkcon_lCluster.x";
connectAttr "groupParts80.og" "tweak39.ip[0].ig";
connectAttr "groupId91.id" "tweak39.ip[0].gi";
connectAttr "cluster7GroupId.msg" "cluster7Set.gn" -na;
connectAttr "anim_arm_fk_lShape.iog.og[0]" "cluster7Set.dsm" -na;
connectAttr "cluster_armfkcon_lCluster.msg" "cluster7Set.ub[0]";
connectAttr "tweak39.og[0]" "cluster7GroupParts.ig";
connectAttr "cluster7GroupId.id" "cluster7GroupParts.gi";
connectAttr "groupId91.msg" "tweakSet39.gn" -na;
connectAttr "anim_arm_fk_lShape.iog.og[1]" "tweakSet39.dsm" -na;
connectAttr "tweak39.msg" "tweakSet39.ub[0]";
connectAttr "anim_arm_fk_lShapeOrig.ws" "groupParts80.ig";
connectAttr "groupId91.id" "groupParts80.gi";
connectAttr "cluster8GroupParts.og" "cluster_legfkcon_lCluster.ip[0].ig";
connectAttr "cluster8GroupId.id" "cluster_legfkcon_lCluster.ip[0].gi";
connectAttr "cluster_legfkcon_l.wm" "cluster_legfkcon_lCluster.ma";
connectAttr "cluster_legfkcon_lShape.x" "cluster_legfkcon_lCluster.x";
connectAttr "groupParts82.og" "tweak40.ip[0].ig";
connectAttr "groupId93.id" "tweak40.ip[0].gi";
connectAttr "cluster8GroupId.msg" "cluster8Set.gn" -na;
connectAttr "anim_leg_fk_lShape.iog.og[0]" "cluster8Set.dsm" -na;
connectAttr "cluster_legfkcon_lCluster.msg" "cluster8Set.ub[0]";
connectAttr "tweak40.og[0]" "cluster8GroupParts.ig";
connectAttr "cluster8GroupId.id" "cluster8GroupParts.gi";
connectAttr "groupId93.msg" "tweakSet40.gn" -na;
connectAttr "anim_leg_fk_lShape.iog.og[1]" "tweakSet40.dsm" -na;
connectAttr "tweak40.msg" "tweakSet40.ub[0]";
connectAttr "anim_leg_fk_lShapeOrig.ws" "groupParts82.ig";
connectAttr "groupId93.id" "groupParts82.gi";
connectAttr "cluster9GroupParts.og" "cluster_legfkcon_rCluster.ip[0].ig";
connectAttr "cluster9GroupId.id" "cluster_legfkcon_rCluster.ip[0].gi";
connectAttr "cluster_legfkcon_r.wm" "cluster_legfkcon_rCluster.ma";
connectAttr "cluster_legfkcon_rShape.x" "cluster_legfkcon_rCluster.x";
connectAttr "groupParts84.og" "tweak41.ip[0].ig";
connectAttr "groupId95.id" "tweak41.ip[0].gi";
connectAttr "cluster9GroupId.msg" "cluster9Set.gn" -na;
connectAttr "anim_leg_fk_rShape.iog.og[0]" "cluster9Set.dsm" -na;
connectAttr "cluster_legfkcon_rCluster.msg" "cluster9Set.ub[0]";
connectAttr "tweak41.og[0]" "cluster9GroupParts.ig";
connectAttr "cluster9GroupId.id" "cluster9GroupParts.gi";
connectAttr "groupId95.msg" "tweakSet41.gn" -na;
connectAttr "anim_leg_fk_rShape.iog.og[1]" "tweakSet41.dsm" -na;
connectAttr "tweak41.msg" "tweakSet41.ub[0]";
connectAttr "anim_leg_fk_rShapeOrig.ws" "groupParts84.ig";
connectAttr "groupId95.id" "groupParts84.gi";
connectAttr "skinCluster26.og[0]" "polyTweakUV3.ip";
connectAttr "skinCluster24.og[0]" "polyTweakUV4.ip";
connectAttr "skinCluster23.og[0]" "polyTweakUV5.ip";
connectAttr "polyTweakUV5.out" "polyMoveUV1.ip";
connectAttr "uvCluster_chestplate_l.tx" "polyMoveUV1.tu" -l on;
connectAttr "uvCluster_chestplate_l.ty" "polyMoveUV1.tv" -l on;
connectAttr "uvCluster_chestplate_l.rx" "polyMoveUV1.ra" -l on;
connectAttr "uvCluster_chestplate_l.sx" "polyMoveUV1.su" -l on;
connectAttr "uvCluster_chestplate_l.sy" "polyMoveUV1.sv" -l on;
connectAttr "uvCluster_chestplate_l.wm" "uvCluster_chestplate_lCluster.ma";
connectAttr "uvCluster_chestplate_lShape.x" "uvCluster_chestplate_lCluster.x";
connectAttr "uvCluster_chestplate_lCluster.msg" "uvClusterSet.ub[0]";
connectAttr "polyTweakUV4.out" "polyMoveUV2.ip";
connectAttr "uvCluster_chestplate_r.tx" "polyMoveUV2.tu" -l on;
connectAttr "uvCluster_chestplate_r.ty" "polyMoveUV2.tv" -l on;
connectAttr "uvCluster_chestplate_r.rx" "polyMoveUV2.ra" -l on;
connectAttr "uvCluster_chestplate_r.sx" "polyMoveUV2.su" -l on;
connectAttr "uvCluster_chestplate_r.sy" "polyMoveUV2.sv" -l on;
connectAttr "uvCluster_chestplate_r.wm" "uvCluster_chestplate_rCluster.ma";
connectAttr "uvCluster_chestplate_rShape.x" "uvCluster_chestplate_rCluster.x";
connectAttr "uvCluster_chestplate_rCluster.msg" "uvCluster1Set.ub[0]";
connectAttr "polyTweakUV3.out" "polyMoveUV3.ip";
connectAttr "uvCluster_chestplace_m.tx" "polyMoveUV3.tu" -l on;
connectAttr "uvCluster_chestplace_m.ty" "polyMoveUV3.tv" -l on;
connectAttr "uvCluster_chestplace_m.rx" "polyMoveUV3.ra" -l on;
connectAttr "uvCluster_chestplace_m.sx" "polyMoveUV3.su" -l on;
connectAttr "uvCluster_chestplace_m.sy" "polyMoveUV3.sv" -l on;
connectAttr "uvCluster_chestplace_m.wm" "uvCluster_chestplace_mCluster.ma";
connectAttr "uvCluster_chestplace_mShape.x" "uvCluster_chestplace_mCluster.x";
connectAttr "uvCluster_chestplace_mCluster.msg" "uvCluster2Set.ub[0]";
connectAttr "floatMath56.of" "floatMath54._fa";
connectAttr "floatMath54.of" "condition35.ft";
connectAttr "skinCluster25.og[0]" "polyTweakUV6.ip";
connectAttr "polyTweakUV6.out" "polyMoveUV4.ip";
connectAttr "uvCluster_helmet.tx" "polyMoveUV4.tu" -l on;
connectAttr "uvCluster_helmet.ty" "polyMoveUV4.tv" -l on;
connectAttr "uvCluster_helmet.rx" "polyMoveUV4.ra" -l on;
connectAttr "uvCluster_helmet.sx" "polyMoveUV4.su" -l on;
connectAttr "uvCluster_helmet.sy" "polyMoveUV4.sv" -l on;
connectAttr "uvCluster_helmet.wm" "uvCluster_helmetCluster.ma";
connectAttr "uvCluster_helmetShape.x" "uvCluster_helmetCluster.x";
connectAttr "uvCluster_helmetCluster.msg" "uvClusterSet1.ub[0]";
connectAttr "floatMath57.of" "floatMath55._fa";
connectAttr "floatMath55.of" "condition36.ft";
connectAttr "anim_facial_settings.ChestplateMaterial" "floatMath56._fa";
connectAttr "anim_facial_settings.HelmetMaterial" "floatMath57._fa";
connectAttr "skinCluster27.og[0]" "polyTweakUV7.ip";
connectAttr "skinCluster30.og[0]" "polyTweakUV8.ip";
connectAttr "skinCluster28.og[0]" "polyTweakUV9.ip";
connectAttr "polyTweakUV8.out" "polyMoveUV5.ip";
connectAttr "uvCluster_leggings_r.tx" "polyMoveUV5.tu" -l on;
connectAttr "uvCluster_leggings_r.ty" "polyMoveUV5.tv" -l on;
connectAttr "uvCluster_leggings_r.rx" "polyMoveUV5.ra" -l on;
connectAttr "uvCluster_leggings_r.sx" "polyMoveUV5.su" -l on;
connectAttr "uvCluster_leggings_r.sy" "polyMoveUV5.sv" -l on;
connectAttr "uvCluster_leggings_r.wm" "uvCluster_leggings_rCluster.ma";
connectAttr "uvCluster_leggings_rShape.x" "uvCluster_leggings_rCluster.x";
connectAttr "uvCluster_leggings_rCluster.msg" "uvClusterSet2.ub[0]";
connectAttr "polyTweakUV9.out" "polyMoveUV6.ip";
connectAttr "uvCluster_leggings_l.tx" "polyMoveUV6.tu" -l on;
connectAttr "uvCluster_leggings_l.ty" "polyMoveUV6.tv" -l on;
connectAttr "uvCluster_leggings_l.rx" "polyMoveUV6.ra" -l on;
connectAttr "uvCluster_leggings_l.sx" "polyMoveUV6.su" -l on;
connectAttr "uvCluster_leggings_l.sy" "polyMoveUV6.sv" -l on;
connectAttr "uvCluster_leggings_l.wm" "uvCluster_leggings_lCluster.ma";
connectAttr "uvCluster_leggings_lShape.x" "uvCluster_leggings_lCluster.x";
connectAttr "uvCluster_leggings_lCluster.msg" "uvCluster3Set.ub[0]";
connectAttr "polyTweakUV7.out" "polyMoveUV7.ip";
connectAttr "uvCluster_leggings_m.tx" "polyMoveUV7.tu" -l on;
connectAttr "uvCluster_leggings_m.ty" "polyMoveUV7.tv" -l on;
connectAttr "uvCluster_leggings_m.rx" "polyMoveUV7.ra" -l on;
connectAttr "uvCluster_leggings_m.sx" "polyMoveUV7.su" -l on;
connectAttr "uvCluster_leggings_m.sy" "polyMoveUV7.sv" -l on;
connectAttr "uvCluster_leggings_m.wm" "uvCluster_leggings_mCluster.ma";
connectAttr "uvCluster_leggings_mShape.x" "uvCluster_leggings_mCluster.x";
connectAttr "uvCluster_leggings_mCluster.msg" "uvCluster4Set.ub[0]";
connectAttr "floatMath58.of" "condition37.ft";
connectAttr "floatMath59.of" "floatMath58._fa";
connectAttr "anim_facial_settings.LeggingsMaterial" "floatMath59._fa";
connectAttr "skinCluster29.og[0]" "polyTweakUV10.ip";
connectAttr "skinCluster31.og[0]" "polyTweakUV11.ip";
connectAttr "polyTweakUV10.out" "polyMoveUV8.ip";
connectAttr "uvCluster_boots_l.tx" "polyMoveUV8.tu" -l on;
connectAttr "uvCluster_boots_l.ty" "polyMoveUV8.tv" -l on;
connectAttr "uvCluster_boots_l.rx" "polyMoveUV8.ra" -l on;
connectAttr "uvCluster_boots_l.sx" "polyMoveUV8.su" -l on;
connectAttr "uvCluster_boots_l.sy" "polyMoveUV8.sv" -l on;
connectAttr "uvCluster_boots_l.wm" "uvCluster_boots_lCluster.ma";
connectAttr "uvCluster_boots_lShape.x" "uvCluster_boots_lCluster.x";
connectAttr "uvCluster_boots_lCluster.msg" "uvClusterSet3.ub[0]";
connectAttr "polyTweakUV11.out" "polyMoveUV9.ip";
connectAttr "uvCluster_boots_r.tx" "polyMoveUV9.tu" -l on;
connectAttr "uvCluster_boots_r.ty" "polyMoveUV9.tv" -l on;
connectAttr "uvCluster_boots_r.rx" "polyMoveUV9.ra" -l on;
connectAttr "uvCluster_boots_r.sx" "polyMoveUV9.su" -l on;
connectAttr "uvCluster_boots_r.sy" "polyMoveUV9.sv" -l on;
connectAttr "uvCluster_boots_r.wm" "uvCluster_boots_rCluster.ma";
connectAttr "uvCluster_boots_rShape.x" "uvCluster_boots_rCluster.x";
connectAttr "uvCluster_boots_rCluster.msg" "uvCluster5Set.ub[0]";
connectAttr "anim_facial_settings.BootsMaterial" "floatMath60._fa";
connectAttr "floatMath60.of" "floatMath61._fa";
connectAttr "floatMath61.of" "condition38.ft";
connectAttr "anim_facial_settings.BootsMaterial" "condition39.ft";
connectAttr "anim_facial_settings.ChestplateMaterial" "condition40.ft";
connectAttr "anim_facial_settings.ChestplateMaterial" "condition41.ft";
connectAttr "anim_facial_settings.LeggingsMaterial" "condition42.ft";
connectAttr "cluster10GroupParts.og" "cluster_legtopconCluster.ip[0].ig";
connectAttr "cluster10GroupId.id" "cluster_legtopconCluster.ip[0].gi";
connectAttr "cluster10GroupParts1.og" "cluster_legtopconCluster.ip[1].ig";
connectAttr "cluster10GroupId1.id" "cluster_legtopconCluster.ip[1].gi";
connectAttr "cluster_legtopcon.wm" "cluster_legtopconCluster.ma";
connectAttr "cluster_legtopconShape.x" "cluster_legtopconCluster.x";
connectAttr "groupParts87.og" "tweak42.ip[0].ig";
connectAttr "groupId98.id" "tweak42.ip[0].gi";
connectAttr "groupParts88.og" "tweak43.ip[0].ig";
connectAttr "groupId99.id" "tweak43.ip[0].gi";
connectAttr "cluster10GroupId.msg" "cluster10Set.gn" -na;
connectAttr "cluster10GroupId1.msg" "cluster10Set.gn" -na;
connectAttr "anim_leg_top_rShape.iog.og[0]" "cluster10Set.dsm" -na;
connectAttr "anim_leg_top_lShape.iog.og[0]" "cluster10Set.dsm" -na;
connectAttr "cluster_legtopconCluster.msg" "cluster10Set.ub[0]";
connectAttr "tweak42.og[0]" "cluster10GroupParts.ig";
connectAttr "cluster10GroupId.id" "cluster10GroupParts.gi";
connectAttr "tweak43.og[0]" "cluster10GroupParts1.ig";
connectAttr "cluster10GroupId1.id" "cluster10GroupParts1.gi";
connectAttr "groupId98.msg" "tweakSet42.gn" -na;
connectAttr "anim_leg_top_rShape.iog.og[1]" "tweakSet42.dsm" -na;
connectAttr "tweak42.msg" "tweakSet42.ub[0]";
connectAttr "anim_leg_top_rShapeOrig.ws" "groupParts87.ig";
connectAttr "groupId98.id" "groupParts87.gi";
connectAttr "groupId99.msg" "tweakSet43.gn" -na;
connectAttr "anim_leg_top_lShape.iog.og[1]" "tweakSet43.dsm" -na;
connectAttr "tweak43.msg" "tweakSet43.ub[0]";
connectAttr "anim_leg_top_lShapeOrig.ws" "groupParts88.ig";
connectAttr "groupId99.id" "groupParts88.gi";
connectAttr "anim_facial_settings.LeggingsMaterial" "condition43.ft";
connectAttr "anim_facial_settings.HelmetMaterial" "condition44.ft";
connectAttr "aiStandardSurface3SG.msg" "materialInfo7.sg";
connectAttr "place2dTexture1.o" "ramp1.uv";
connectAttr "place2dTexture1.ofs" "ramp1.fs";
connectAttr "skinCluster33GroupParts.og" "skinCluster33.ip[0].ig";
connectAttr "skinCluster33GroupId.id" "skinCluster33.ip[0].gi";
connectAttr "joint_arm_bind_r.wm" "skinCluster33.ma[0]";
connectAttr "joint_arm_bind_r.liw" "skinCluster33.lw[0]";
connectAttr "joint_arm_bind_r.obcc" "skinCluster33.ifcl[0]";
connectAttr "bindPose15.msg" "skinCluster33.bp";
connectAttr "groupParts92.og" "tweak45.ip[0].ig";
connectAttr "groupId103.id" "tweak45.ip[0].gi";
connectAttr "skinCluster33GroupId.msg" "skinCluster33Set.gn" -na;
connectAttr "mesh_arm_alex_second_rShape.iog.og[2]" "skinCluster33Set.dsm" -na;
connectAttr "skinCluster33.msg" "skinCluster33Set.ub[0]";
connectAttr "tweak45.og[0]" "skinCluster33GroupParts.ig";
connectAttr "skinCluster33GroupId.id" "skinCluster33GroupParts.gi";
connectAttr "groupId103.msg" "tweakSet45.gn" -na;
connectAttr "mesh_arm_alex_second_rShape.iog.og[3]" "tweakSet45.dsm" -na;
connectAttr "tweak45.msg" "tweakSet45.ub[0]";
connectAttr "mesh_arm_alex_second_rShapeOrig1.w" "groupParts92.ig";
connectAttr "groupId103.id" "groupParts92.gi";
connectAttr "skinCluster34GroupParts.og" "skinCluster34.ip[0].ig";
connectAttr "skinCluster34GroupId.id" "skinCluster34.ip[0].gi";
connectAttr "joint_arm_bind_r.wm" "skinCluster34.ma[0]";
connectAttr "joint_arm_bind_r.liw" "skinCluster34.lw[0]";
connectAttr "joint_arm_bind_r.obcc" "skinCluster34.ifcl[0]";
connectAttr "bindPose15.msg" "skinCluster34.bp";
connectAttr "groupParts94.og" "tweak46.ip[0].ig";
connectAttr "groupId105.id" "tweak46.ip[0].gi";
connectAttr "skinCluster34GroupId.msg" "skinCluster34Set.gn" -na;
connectAttr "mesh_arm_alex_rShape.iog.og[2]" "skinCluster34Set.dsm" -na;
connectAttr "skinCluster34.msg" "skinCluster34Set.ub[0]";
connectAttr "tweak46.og[0]" "skinCluster34GroupParts.ig";
connectAttr "skinCluster34GroupId.id" "skinCluster34GroupParts.gi";
connectAttr "groupId105.msg" "tweakSet46.gn" -na;
connectAttr "mesh_arm_alex_rShape.iog.og[3]" "tweakSet46.dsm" -na;
connectAttr "tweak46.msg" "tweakSet46.ub[0]";
connectAttr "mesh_arm_alex_rShapeOrig1.w" "groupParts94.ig";
connectAttr "groupId105.id" "groupParts94.gi";
connectAttr "skinCluster35GroupParts.og" "skinCluster35.ip[0].ig";
connectAttr "skinCluster35GroupId.id" "skinCluster35.ip[0].gi";
connectAttr "joint_arm_bind_l.wm" "skinCluster35.ma[0]";
connectAttr "joint_arm_bind_l.liw" "skinCluster35.lw[0]";
connectAttr "joint_arm_bind_l.obcc" "skinCluster35.ifcl[0]";
connectAttr "bindPose14.msg" "skinCluster35.bp";
connectAttr "groupParts96.og" "tweak47.ip[0].ig";
connectAttr "groupId107.id" "tweak47.ip[0].gi";
connectAttr "skinCluster35GroupId.msg" "skinCluster35Set.gn" -na;
connectAttr "mesh_arm_alex_second_lShape.iog.og[2]" "skinCluster35Set.dsm" -na;
connectAttr "skinCluster35.msg" "skinCluster35Set.ub[0]";
connectAttr "tweak47.og[0]" "skinCluster35GroupParts.ig";
connectAttr "skinCluster35GroupId.id" "skinCluster35GroupParts.gi";
connectAttr "groupId107.msg" "tweakSet47.gn" -na;
connectAttr "mesh_arm_alex_second_lShape.iog.og[3]" "tweakSet47.dsm" -na;
connectAttr "tweak47.msg" "tweakSet47.ub[0]";
connectAttr "mesh_arm_alex_second_lShapeOrig2.w" "groupParts96.ig";
connectAttr "groupId107.id" "groupParts96.gi";
connectAttr "skinCluster36GroupParts.og" "skinCluster36.ip[0].ig";
connectAttr "skinCluster36GroupId.id" "skinCluster36.ip[0].gi";
connectAttr "joint_arm_bind_l.wm" "skinCluster36.ma[0]";
connectAttr "joint_arm_bind_l.liw" "skinCluster36.lw[0]";
connectAttr "joint_arm_bind_l.obcc" "skinCluster36.ifcl[0]";
connectAttr "bindPose14.msg" "skinCluster36.bp";
connectAttr "groupParts98.og" "tweak48.ip[0].ig";
connectAttr "groupId109.id" "tweak48.ip[0].gi";
connectAttr "skinCluster36GroupId.msg" "skinCluster36Set.gn" -na;
connectAttr "mesh_arm_alex_lShape.iog.og[2]" "skinCluster36Set.dsm" -na;
connectAttr "skinCluster36.msg" "skinCluster36Set.ub[0]";
connectAttr "tweak48.og[0]" "skinCluster36GroupParts.ig";
connectAttr "skinCluster36GroupId.id" "skinCluster36GroupParts.gi";
connectAttr "groupId109.msg" "tweakSet48.gn" -na;
connectAttr "mesh_arm_alex_lShape.iog.og[3]" "tweakSet48.dsm" -na;
connectAttr "tweak48.msg" "tweakSet48.ub[0]";
connectAttr "mesh_arm_alex_lShapeOrig1.w" "groupParts98.ig";
connectAttr "groupId109.id" "groupParts98.gi";
connectAttr "anim_facial_settings.ArmType" "condition46.ft";
connectAttr "anim_facial_settings.ArmType" "condition47.ft";
connectAttr "skinPromoArt_tex.oc" "skinPromoArt_rsMat.diffuse_color";
connectAttr "skinPromoArt_tex.oa" "skinPromoArt_rsMat.opacity_colorB";
connectAttr "skinPromoArt_tex.oa" "skinPromoArt_rsMat.opacity_colorG";
connectAttr "skinPromoArt_tex.oa" "skinPromoArt_rsMat.opacity_colorR";
connectAttr "rsBumpBlender1.oc" "skinPromoArt_rsMat.bump_input";
connectAttr "rsRoundCorners1.oc" "rsBumpBlender1.bumpInput0";
connectAttr "armorPromoArt_tex.oc" "colorMath1._ca";
connectAttr "hsvToRgb1.o" "colorMath2._ca";
connectAttr "rsRoundCorners2.oc" "rsBumpBlender2.bumpInput0";
connectAttr "rsBumpMap2.oc" "rsBumpBlender2.bumpInput1";
connectAttr "rsSprite1SG.msg" "materialInfo8.sg";
connectAttr ":defaultColorMgtGlobals.cme" "armorPromoArt_filtered_tex.cme";
connectAttr ":defaultColorMgtGlobals.cfe" "armorPromoArt_filtered_tex.cmcf";
connectAttr ":defaultColorMgtGlobals.cfp" "armorPromoArt_filtered_tex.cmcp";
connectAttr ":defaultColorMgtGlobals.wsn" "armorPromoArt_filtered_tex.ws";
connectAttr "place2dTexture_armorPromoArt.o" "armorPromoArt_filtered_tex.uv";
connectAttr "armorPromoArt_filtered_tex.oa" "rsBumpMap2.inputG";
connectAttr "armorPromoArt_filtered_tex.oa" "rsBumpMap2.inputB";
connectAttr "armorPromoArt_filtered_tex.oa" "rsBumpMap2.inputR";
connectAttr "armorPromoArt_tex.oc" "rgbToHsv1.i";
connectAttr "rgbToHsv1.oh" "hsvToRgb1.ir";
connectAttr "rgbToHsv1.os" "hsvToRgb1.ig";
connectAttr "head_JNT.msg" "bindPose4.m[0]";
connectAttr "head_CON.msg" "bindPose4.m[5]";
connectAttr "main_CON.msg" "bindPose4.m[6]";
connectAttr "head_CNST.msg" "bindPose4.m[7]";
connectAttr "player_RIG.msg" "bindPose4.m[8]";
connectAttr "main_CNST.msg" "bindPose4.m[9]";
connectAttr "bindPose4.m[5]" "bindPose4.p[0]";
connectAttr "bindPose4.w" "bindPose4.p[1]";
connectAttr "bindPose4.w" "bindPose4.p[2]";
connectAttr "bindPose4.w" "bindPose4.p[3]";
connectAttr "bindPose4.w" "bindPose4.p[4]";
connectAttr "bindPose4.m[7]" "bindPose4.p[5]";
connectAttr "bindPose4.m[9]" "bindPose4.p[6]";
connectAttr "main_CON.msg" "bindPose4.p[7]";
connectAttr "bindPose4.w" "bindPose4.p[8]";
connectAttr "player_RIG.msg" "bindPose4.p[9]";
connectAttr "head_JNT.bps" "bindPose4.wm[0]";
connectAttr "skinCluster25GroupParts.og" "skinCluster25.ip[0].ig";
connectAttr "skinCluster25GroupId.id" "skinCluster25.ip[0].gi";
connectAttr "head_JNT.wm" "skinCluster25.ma[0]";
connectAttr "head_JNT.liw" "skinCluster25.lw[0]";
connectAttr "head_JNT.obcc" "skinCluster25.ifcl[0]";
connectAttr "bindPose4.msg" "skinCluster25.bp";
connectAttr "skinCluster25GroupId.msg" "skinCluster25Set.gn" -na;
connectAttr "mesh_helmetShape.iog.og[0]" "skinCluster25Set.dsm" -na;
connectAttr "skinCluster25.msg" "skinCluster25Set.ub[0]";
connectAttr "tweak25.og[0]" "skinCluster25GroupParts.ig";
connectAttr "skinCluster25GroupId.id" "skinCluster25GroupParts.gi";
connectAttr "skinCluster32GroupParts.og" "skinCluster32.ip[0].ig";
connectAttr "skinCluster32GroupId.id" "skinCluster32.ip[0].gi";
connectAttr "head_JNT.wm" "skinCluster32.ma[0]";
connectAttr "head_JNT.liw" "skinCluster32.lw[0]";
connectAttr "head_JNT.obcc" "skinCluster32.ifcl[0]";
connectAttr "bindPose4.msg" "skinCluster32.bp";
connectAttr "skinCluster32GroupId.msg" "skinCluster32Set.gn" -na;
connectAttr "mesh_head_plainShape.iog.og[2]" "skinCluster32Set.dsm" -na;
connectAttr "skinCluster32.msg" "skinCluster32Set.ub[0]";
connectAttr "tweak44.og[0]" "skinCluster32GroupParts.ig";
connectAttr "skinCluster32GroupId.id" "skinCluster32GroupParts.gi";
connectAttr "groupParts90.og" "tweak44.ip[0].ig";
connectAttr "groupId101.id" "tweak44.ip[0].gi";
connectAttr "groupId101.msg" "tweakSet44.gn" -na;
connectAttr "mesh_head_plainShape.iog.og[3]" "tweakSet44.dsm" -na;
connectAttr "tweak44.msg" "tweakSet44.ub[0]";
connectAttr "mesh_head_plainShapeOrig1.w" "groupParts90.ig";
connectAttr "groupId101.id" "groupParts90.gi";
connectAttr "rsBumpBlender1.oc" "facePromoArt_rsMat.bump_input";
connectAttr "mainFace_layeredTexture.oc" "facePromoArt_rsMat.diffuse_color";
connectAttr "facePromoArt_rsMat.oc" "rsMaterial2SG.ss";
connectAttr "groupId112.msg" "rsMaterial2SG.gn" -na;
connectAttr "mesh_head_plainShape.iog.og[5]" "rsMaterial2SG.dsm" -na;
connectAttr "rsMaterial2SG.msg" "materialInfo9.sg";
connectAttr "facePromoArt_rsMat.msg" "materialInfo9.m";
connectAttr "lf_eyebrowCutoutAlpha_ramp.msg" "materialInfo9.t" -na;
connectAttr "skinCluster32.og[0]" "groupParts99.ig";
connectAttr "groupId110.id" "groupParts99.gi";
connectAttr "groupParts99.og" "groupParts100.ig";
connectAttr "groupId112.id" "groupParts100.gi";
connectAttr "mouthClosedOpen_blendColors.op" "mainFace_layeredTexture.cs[7].c";
connectAttr "mouthClosedOpenAlpha_blendColors.opg" "mainFace_layeredTexture.cs[7].a"
		;
connectAttr "eyebrows_layeredTexture.oc" "mainFace_layeredTexture.cs[10].c";
connectAttr "eyebrows_layeredTexture.oa" "mainFace_layeredTexture.cs[10].a";
connectAttr "rt_eyePupilColoring_colorMath.oc" "mainFace_layeredTexture.cs[11].c"
		;
connectAttr "rt_eyePupilAlpha_floatMath.of" "mainFace_layeredTexture.cs[11].a";
connectAttr "lf_eyePupilColoring_colorMath.oc" "mainFace_layeredTexture.cs[12].c"
		;
connectAttr "lf_eyePupilAlpha_floatMath.of" "mainFace_layeredTexture.cs[12].a";
connectAttr "colorMath4.oc" "mainFace_layeredTexture.cs[13].c";
connectAttr "lf_eyelidEyebrowCutout_colorMath.ocg" "mainFace_layeredTexture.cs[13].a"
		;
connectAttr "colorMath3.oc" "mainFace_layeredTexture.cs[14].c";
connectAttr "rt_eyelidEyebrowCutout_colorMath.ocg" "mainFace_layeredTexture.cs[14].a"
		;
connectAttr "skinPromoArt_tex.oc" "mainFace_layeredTexture.cs[15].c";
connectAttr "mainSquare_ramp.oc" "rt_eyeWhite_projection.im";
connectAttr "rt_eyeWhite_projectionHandle.wim" "rt_eyeWhite_projection.pm";
connectAttr "mainSquare_place2dTexture.o" "mainSquare_ramp.uv";
connectAttr "mainSquare_place2dTexture.ofs" "mainSquare_ramp.fs";
connectAttr "mainSquare_ramp.oc" "lf_eyeWhite_projection.im";
connectAttr "lf_eyeWhite_projectionHandle.wim" "lf_eyeWhite_projection.pm";
connectAttr "rt_eyeWhite_projection.oc" "colorMath3._ca";
connectAttr "rt_eyeWhite_colorConstant.oc" "colorMath3._cb";
connectAttr "lf_eyeWhite_colorConstant.oc" "colorMath4._ca";
connectAttr "lf_eyeWhite_projection.oc" "colorMath4._cb";
connectAttr "mainSquare_ramp.oc" "lf_eyePupil_projection.im";
connectAttr "lf_eyePupil_projectionHandle.wim" "lf_eyePupil_projection.pm";
connectAttr "mainSquare_ramp.oc" "rt_eyePupil_projection.im";
connectAttr "rt_eyePupil_projectionHandle.wim" "rt_eyePupil_projection.pm";
connectAttr "rt_eyePupil_projection.oc" "rt_eyePupilColoring_colorMath._ca";
connectAttr "rt_eyePupil_colorConstant.oc" "rt_eyePupilColoring_colorMath._cb";
connectAttr "lf_eyePupil_projection.oc" "lf_eyePupilColoring_colorMath._ca";
connectAttr "lf_eyePupil_colorConstant.oc" "lf_eyePupilColoring_colorMath._cb";
connectAttr "lf_eyelidEyebrowCutout_colorMath.ocg" "lf_eyePupilAlpha_floatMath._fa"
		;
connectAttr "lf_eyePupil_projection.ocg" "lf_eyePupilAlpha_floatMath._fb";
connectAttr "rt_eyePupil_projection.ocg" "rt_eyePupilAlpha_floatMath._fa";
connectAttr "rt_eyelidEyebrowCutout_colorMath.ocg" "rt_eyePupilAlpha_floatMath._fb"
		;
connectAttr "lf_eyeWhite_projection.oc" "lf_eyelidEyebrowCutout_colorMath._ca";
connectAttr "lf_eyebrowCutoutAlpha_ramp.oc" "lf_eyelidEyebrowCutout_colorMath._cb"
		;
connectAttr "rt_eyeWhite_projection.oc" "rt_eyelidEyebrowCutout_colorMath._ca";
connectAttr "rt_eyebrowCutoutAlpha_ramp.oc" "rt_eyelidEyebrowCutout_colorMath._cb"
		;
connectAttr "groupParts100.og" "polyTweakUV12.ip";
connectAttr "lf_eyebrowRecolor_colorMath.oc" "eyebrows_layeredTexture.cs[0].c";
connectAttr "lf_eyebrowOpacity_floatMath.of" "eyebrows_layeredTexture.cs[0].a";
connectAttr "rt_eyebrowOpacity_floatMath.of" "eyebrows_layeredTexture.cs[1].a";
connectAttr "rt_eyebrowRecolor_colorMath.oc" "eyebrows_layeredTexture.cs[1].c";
connectAttr ":defaultColorMgtGlobals.cme" "lf_eyebrow_tex.cme";
connectAttr ":defaultColorMgtGlobals.cfe" "lf_eyebrow_tex.cmcf";
connectAttr ":defaultColorMgtGlobals.cfp" "lf_eyebrow_tex.cmcp";
connectAttr ":defaultColorMgtGlobals.wsn" "lf_eyebrow_tex.ws";
connectAttr "place2dTexture2.c" "lf_eyebrow_tex.c";
connectAttr "place2dTexture2.tf" "lf_eyebrow_tex.tf";
connectAttr "place2dTexture2.rf" "lf_eyebrow_tex.rf";
connectAttr "place2dTexture2.mu" "lf_eyebrow_tex.mu";
connectAttr "place2dTexture2.mv" "lf_eyebrow_tex.mv";
connectAttr "place2dTexture2.s" "lf_eyebrow_tex.s";
connectAttr "place2dTexture2.wu" "lf_eyebrow_tex.wu";
connectAttr "place2dTexture2.wv" "lf_eyebrow_tex.wv";
connectAttr "place2dTexture2.re" "lf_eyebrow_tex.re";
connectAttr "place2dTexture2.of" "lf_eyebrow_tex.of";
connectAttr "place2dTexture2.r" "lf_eyebrow_tex.ro";
connectAttr "place2dTexture2.n" "lf_eyebrow_tex.n";
connectAttr "place2dTexture2.vt1" "lf_eyebrow_tex.vt1";
connectAttr "place2dTexture2.vt2" "lf_eyebrow_tex.vt2";
connectAttr "place2dTexture2.vt3" "lf_eyebrow_tex.vt3";
connectAttr "place2dTexture2.vc1" "lf_eyebrow_tex.vc1";
connectAttr "place2dTexture2.o" "lf_eyebrow_tex.uv";
connectAttr "place2dTexture2.ofs" "lf_eyebrow_tex.fs";
connectAttr "eyebrow_colorConstant.oc" "lf_eyebrowRecolor_colorMath._ca";
connectAttr "lf_eyebrow_projection.oc" "lf_eyebrowRecolor_colorMath._cb";
connectAttr "lf_eyebrow_tex.oc" "lf_eyebrow_contrast.v";
connectAttr "eyebrowContrast_floatConstant.of" "lf_eyebrow_contrast.cy";
connectAttr "eyebrowContrast_floatConstant.of" "lf_eyebrow_contrast.cx";
connectAttr "eyebrowContrast_floatConstant.of" "lf_eyebrow_contrast.cz";
connectAttr "lf_eyebrow_contrast.o" "lf_eyebrow_projection.im";
connectAttr "lf_eyebrow_projectionHandle.wim" "lf_eyebrow_projection.pm";
connectAttr "lf_eyebrow_projection.oc" "lf_eyebrowAlpha_rgbToHsv.i";
connectAttr "lf_eyebrowAlpha_rgbToHsv.oh" "lf_eyebrowAlpha_ramp.u";
connectAttr "lf_eyebrowAlpha_rgbToHsv.ov" "lf_eyebrowAlpha_ramp.v";
connectAttr "skinCluster17GroupParts.og" "skinCluster17.ip[0].ig";
connectAttr "skinCluster17GroupId.id" "skinCluster17.ip[0].gi";
connectAttr "head_JNT.wm" "skinCluster17.ma[0]";
connectAttr "head_JNT.liw" "skinCluster17.lw[0]";
connectAttr "head_JNT.obcc" "skinCluster17.ifcl[0]";
connectAttr "bindPose4.msg" "skinCluster17.bp";
connectAttr "skinCluster17GroupId.msg" "skinCluster17Set.gn" -na;
connectAttr "mesh_head_secondShape.iog.og[0]" "skinCluster17Set.dsm" -na;
connectAttr "skinCluster17.msg" "skinCluster17Set.ub[0]";
connectAttr "mesh_head_secondShapeOrig.w" "groupParts36.ig";
connectAttr "groupId47.id" "groupParts36.gi";
connectAttr "groupParts36.og" "tweak17.ip[0].ig";
connectAttr "groupId47.id" "tweak17.ip[0].gi";
connectAttr "tweak17.og[0]" "skinCluster17GroupParts.ig";
connectAttr "skinCluster17GroupId.id" "skinCluster17GroupParts.gi";
connectAttr "groupId47.msg" "tweakSet17.gn" -na;
connectAttr "mesh_head_secondShape.iog.og[1]" "tweakSet17.dsm" -na;
connectAttr "tweak17.msg" "tweakSet17.ub[0]";
connectAttr "lf_eyebrowAlpha_rgbToHsv.oh" "lf_eyebrowCutoutAlpha_ramp.u";
connectAttr "lf_eyebrowAlpha_rgbToHsv.ov" "lf_eyebrowCutoutAlpha_ramp.v";
connectAttr "rt_eyebrowAlpha_rgbToHsv.oh" "rt_eyebrowCutoutAlpha_ramp.u";
connectAttr "rt_eyebrowAlpha_rgbToHsv.ov" "rt_eyebrowCutoutAlpha_ramp.v";
connectAttr "rt_eyebrowAlpha_rgbToHsv.oh" "rt_eyebrowAlpha_ramp.u";
connectAttr "rt_eyebrowAlpha_rgbToHsv.ov" "rt_eyebrowAlpha_ramp.v";
connectAttr "rt_eyebrow_projection.oc" "rt_eyebrowAlpha_rgbToHsv.i";
connectAttr "rt_eyebrow_contrast.o" "rt_eyebrow_projection.im";
connectAttr "rt_eyebrow_projectionHandle.wim" "rt_eyebrow_projection.pm";
connectAttr "eyebrow_colorConstant.oc" "rt_eyebrowRecolor_colorMath._ca";
connectAttr "rt_eyebrow_projection.oc" "rt_eyebrowRecolor_colorMath._cb";
connectAttr "rt_eyebrow_tex.oc" "rt_eyebrow_contrast.v";
connectAttr "eyebrowContrast_floatConstant.of" "rt_eyebrow_contrast.cy";
connectAttr "eyebrowContrast_floatConstant.of" "rt_eyebrow_contrast.cx";
connectAttr "eyebrowContrast_floatConstant.of" "rt_eyebrow_contrast.cz";
connectAttr ":defaultColorMgtGlobals.cme" "rt_eyebrow_tex.cme";
connectAttr ":defaultColorMgtGlobals.cfe" "rt_eyebrow_tex.cmcf";
connectAttr ":defaultColorMgtGlobals.cfp" "rt_eyebrow_tex.cmcp";
connectAttr ":defaultColorMgtGlobals.wsn" "rt_eyebrow_tex.ws";
connectAttr "place2dTexture3.o" "rt_eyebrow_tex.uv";
connectAttr "lf_lipRecolor_colorMath.oc" "mouthClosed_layeredTexture.cs[0].c";
connectAttr "lf_lip_projection.ocg" "mouthClosed_layeredTexture.cs[0].a";
connectAttr "m_lipRecolor_colorMath.oc" "mouthClosed_layeredTexture.cs[1].c";
connectAttr "m_lip_projection.ocg" "mouthClosed_layeredTexture.cs[1].a";
connectAttr "rt_lipRecolor_colorMath.oc" "mouthClosed_layeredTexture.cs[2].c";
connectAttr "rt_lip_projection.ocg" "mouthClosed_layeredTexture.cs[2].a";
connectAttr "mouthClosed_layeredTexture.oc" "mouthClosedOpen_blendColors.c1";
connectAttr "mouthOpen_layeredTexture.oc" "mouthClosedOpen_blendColors.c2";
connectAttr "mouthClosedOpenBlender_floatConstant.of" "mouthClosedOpen_blendColors.b"
		;
connectAttr "t_teethRecolor_colorMath.oc" "mouthOpen_layeredTexture.cs[5].c";
connectAttr "t_teethCutoutAlpha_floatMath.of" "mouthOpen_layeredTexture.cs[5].a"
		;
connectAttr "b_teethRecolor_colorMath.oc" "mouthOpen_layeredTexture.cs[7].c";
connectAttr "b_teethCutoutAlpha_floatMath.of" "mouthOpen_layeredTexture.cs[7].a"
		;
connectAttr "tongueRecolor_colorMath.oc" "mouthOpen_layeredTexture.cs[8].c";
connectAttr "tongueCutoutAlpha_floatMath.of" "mouthOpen_layeredTexture.cs[8].a";
connectAttr "rt_mouthRecolor_colorMath.oc" "mouthOpen_layeredTexture.cs[9].c";
connectAttr "rt_mouth_projection.ocg" "mouthOpen_layeredTexture.cs[9].a";
connectAttr "m_mouthRecolor_colorMath.oc" "mouthOpen_layeredTexture.cs[10].c";
connectAttr "m_mouth_projection.ocg" "mouthOpen_layeredTexture.cs[10].a";
connectAttr "lf_mouthRecolor_colorMath.oc" "mouthOpen_layeredTexture.cs[11].c";
connectAttr "lf_mouth_projection.ocg" "mouthOpen_layeredTexture.cs[11].a";
connectAttr "mouthClosed_layeredTexture.oa" "mouthClosedOpenAlpha_blendColors.c1g"
		;
connectAttr "mouthOpen_layeredTexture.oa" "mouthClosedOpenAlpha_blendColors.c2g"
		;
connectAttr "mouthClosedOpenBlender_floatConstant.of" "mouthClosedOpenAlpha_blendColors.b"
		;
connectAttr "condition48.ocg" "mouthClosedOpenBlender_floatConstant._f";
connectAttr "mouthLip_colorConstant.oc" "lf_lipRecolor_colorMath._cb";
connectAttr "lf_lip_projection.oc" "lf_lipRecolor_colorMath._ca";
connectAttr "mainSquare_ramp.oc" "m_lip_projection.im";
connectAttr "m_lip_projectionHandle.wim" "m_lip_projection.pm";
connectAttr "mainSquare_ramp.oc" "lf_lip_projection.im";
connectAttr "rt_lip_projectionHandle.wim" "lf_lip_projection.pm";
connectAttr "mainSquare_ramp.oc" "rt_lip_projection.im";
connectAttr "lf_lip_projectionHandle.wim" "rt_lip_projection.pm";
connectAttr "mouthLip_colorConstant.oc" "m_lipRecolor_colorMath._cb";
connectAttr "m_lip_projection.oc" "m_lipRecolor_colorMath._ca";
connectAttr "mouthLip_colorConstant.oc" "rt_lipRecolor_colorMath._cb";
connectAttr "rt_lip_projection.oc" "rt_lipRecolor_colorMath._ca";
connectAttr "lf_eyebrowAlpha_ramp.ocg" "lf_eyebrowOpacity_floatMath._fa";
connectAttr "eyebrowOpacity_floatConstant.of" "lf_eyebrowOpacity_floatMath._fb";
connectAttr "rt_eyebrowAlpha_ramp.ocg" "rt_eyebrowOpacity_floatMath._fa";
connectAttr "eyebrowOpacity_floatConstant.of" "rt_eyebrowOpacity_floatMath._fb";
connectAttr "mainSquare_ramp.oc" "rt_mouth_projection.im";
connectAttr "rt_mouth_projectionHandle.wim" "rt_mouth_projection.pm";
connectAttr "mainSquare_ramp.oc" "m_mouth_projection.im";
connectAttr "m_mouth_projectionHandle.wim" "m_mouth_projection.pm";
connectAttr "mainSquare_ramp.oc" "lf_mouth_projection.im";
connectAttr "lf_mouth_projectionHandle.wim" "lf_mouth_projection.pm";
connectAttr "mouthInside_colorConstant.oc" "rt_mouthRecolor_colorMath._cb";
connectAttr "rt_mouth_projection.oc" "rt_mouthRecolor_colorMath._ca";
connectAttr "mouthInside_colorConstant.oc" "m_mouthRecolor_colorMath._cb";
connectAttr "m_mouth_projection.oc" "m_mouthRecolor_colorMath._ca";
connectAttr "mouthInside_colorConstant.oc" "lf_mouthRecolor_colorMath._cb";
connectAttr "lf_mouth_projection.oc" "lf_mouthRecolor_colorMath._ca";
connectAttr "mainSquare_ramp.oc" "tongue_projection.im";
connectAttr "tongue_projectionHandle.wim" "tongue_projection.pm";
connectAttr "mouthTongue_colorConstant.oc" "tongueRecolor_colorMath._cb";
connectAttr "tongue_projection.oc" "tongueRecolor_colorMath._ca";
connectAttr "tongue_projection.ocg" "tongueCutoutAlpha_floatMath._fa";
connectAttr "mouthAlpha_floatMath.of" "tongueCutoutAlpha_floatMath._fb";
connectAttr "mainSquare_ramp.oc" "b_teeth_projection.im";
connectAttr "b_teeth_projectionHandle.wim" "b_teeth_projection.pm";
connectAttr "mainSquare_ramp.oc" "t_teeth_projection.im";
connectAttr "t_teeth_projectionHandle.wim" "t_teeth_projection.pm";
connectAttr "mouthTeeth_colorConstant.oc" "t_teethRecolor_colorMath._cb";
connectAttr "t_teeth_projection.oc" "t_teethRecolor_colorMath._ca";
connectAttr "mouthTeeth_colorConstant.oc" "b_teethRecolor_colorMath._cb";
connectAttr "b_teeth_projection.oc" "b_teethRecolor_colorMath._ca";
connectAttr "b_teeth_projection.ocg" "b_teethCutoutAlpha_floatMath._fa";
connectAttr "mouthAlpha_floatMath.of" "b_teethCutoutAlpha_floatMath._fb";
connectAttr "t_teeth_projection.ocg" "t_teethCutoutAlpha_floatMath._fa";
connectAttr "mouthAlpha_floatMath.of" "t_teethCutoutAlpha_floatMath._fb";
connectAttr "rt_mouth_projection.ocg" "mouthSidesAlpha_floatMath._fa";
connectAttr "lf_mouth_projection.ocg" "mouthSidesAlpha_floatMath._fb";
connectAttr "m_mouth_projection.ocg" "mouthAlpha_floatMath._fa";
connectAttr "mouthSidesAlpha_floatMath.of" "mouthAlpha_floatMath._fb";
connectAttr "t_mouthOpen_LOCShape.wp" "mouthOpenY_distanceBetween.p1";
connectAttr "b_mouthOpen_LOCShape.wp" "mouthOpenY_distanceBetween.p2";
connectAttr "mouthOpenY_distanceBetween.d" "mouthOpenY_scaleOffset_floatMath._fa"
		;
connectAttr "mouthOpenY_scaleOffset_floatMath.of" "lf_mouthSmileOffset_floatMath._fa"
		;
connectAttr "lf_mouthSmile_remapValue.ov" "lf_mouthSmileOffset_floatMath._fb";
connectAttr "mouthOpenY_scaleOffset_floatMath.of" "rt_mouthSmileOffset_floatMath._fa"
		;
connectAttr "rt_mouthSmile_remapValue.ov" "rt_mouthSmileOffset_floatMath._fb";
connectAttr "lf_mouthOpen_LOC.ty" "lf_mouthSmile_clamp.ipg";
connectAttr "mouthClosedOpenBlender_floatConstant.of" "lf_mouthSmile_clamp.mxg";
connectAttr "condition49.ocg" "lf_mouthSmile_clamp.mng";
connectAttr "lf_mouthOpen_LOC.ty" "lf_mouthSmile_remapValue.i";
connectAttr "rt_mouthOpen_LOC.ty" "rt_mouthSmile_remapValue.i";
connectAttr "rt_mouthOpen_LOC.ty" "rt_mouthSmile_clamp.ipg";
connectAttr "mouthClosedOpenBlender_floatConstant.of" "rt_mouthSmile_clamp.mxg";
connectAttr "condition49.ocg" "rt_mouthSmile_clamp.mng";
connectAttr "rt_mouthOpen_LOC.tx" "mouthOpenX_addedTranslate_floatMath._fa";
connectAttr "lf_mouthOpen_LOC.tx" "mouthOpenX_addedTranslate_floatMath._fb";
connectAttr "mouthOpenX_addedTranslate_floatMath.of" "mouthOpenX_divideTranslate_floatMath._fa"
		;
connectAttr "mouthOpenX_scaleMultiply_floatMath.of" "mouthOpenX_scaleOffset_floatMath._fa"
		;
connectAttr "rt_mouthSmileOffset_floatMath.of" "rt_mouthSmileMin_clamp.ipg";
connectAttr "lf_mouthSmileOffset_floatMath.of" "lf_mouthSmileMin_clamp.ipg";
connectAttr "mouthOpenY_scaleOffset_floatMath.of" "rt_mouthMin_clamp.ipg";
connectAttr "t_mouthTweak_CON.wm" "multMatrix1.i[0]";
connectAttr "faceLocators_GRP.wim" "multMatrix1.i[1]";
connectAttr "multMatrix1.o" "decomposeMatrix11.imat";
connectAttr "lf_mouthOpen_LOC.tx" "floatMath63._fa";
connectAttr "mouthOpenXInverse_floatMath.of" "floatMath63._fb";
connectAttr "rt_mouthOpen_LOC.tx" "mouthOpenXInverse_floatMath._fa";
connectAttr "floatMath63.of" "mouthOpenX_scaleMultiply_floatMath._fa";
connectAttr "b_mouthTweak_CON.wm" "multMatrix2.i[0]";
connectAttr "faceLocators_GRP.wim" "multMatrix2.i[1]";
connectAttr "multMatrix2.o" "decomposeMatrix12.imat";
connectAttr "t_teethTweak_CON.wm" "multMatrix3.i[0]";
connectAttr "faceLocators_GRP.wim" "multMatrix3.i[1]";
connectAttr "b_teethTweak_CON.wm" "multMatrix4.i[0]";
connectAttr "faceLocators_GRP.wim" "multMatrix4.i[1]";
connectAttr "multMatrix3.o" "decomposeMatrix13.imat";
connectAttr "multMatrix4.o" "decomposeMatrix14.imat";
connectAttr "multMatrix5.o" "decomposeMatrix15.imat";
connectAttr "multMatrix6.o" "decomposeMatrix16.imat";
connectAttr "lf_mouthTweak_CON.wm" "multMatrix5.i[0]";
connectAttr "faceLocators_GRP.wim" "multMatrix5.i[1]";
connectAttr "rt_mouthTweak_CON.wm" "multMatrix6.i[0]";
connectAttr "faceLocators_GRP.wim" "multMatrix6.i[1]";
connectAttr "b_tongueTweak_CON.wm" "multMatrix7.i[0]";
connectAttr "faceLocators_GRP.wim" "multMatrix7.i[1]";
connectAttr "multMatrix7.o" "decomposeMatrix17.imat";
connectAttr "rsBumpBlender1.msg" "hyperShadePrimaryNodeEditorSavedTabsInfo.tgi[0].ni[0].dn"
		;
connectAttr "m_lipRecolor_colorMath.msg" "hyperShadePrimaryNodeEditorSavedTabsInfo.tgi[0].ni[1].dn"
		;
connectAttr "m_mouthRecolor_colorMath.msg" "hyperShadePrimaryNodeEditorSavedTabsInfo.tgi[0].ni[2].dn"
		;
connectAttr "rt_lipRecolor_colorMath.msg" "hyperShadePrimaryNodeEditorSavedTabsInfo.tgi[0].ni[3].dn"
		;
connectAttr "mouthLip_colorConstant.msg" "hyperShadePrimaryNodeEditorSavedTabsInfo.tgi[0].ni[4].dn"
		;
connectAttr "lf_eyeWhite_colorConstant.msg" "hyperShadePrimaryNodeEditorSavedTabsInfo.tgi[0].ni[5].dn"
		;
connectAttr "lf_mouth_projection.msg" "hyperShadePrimaryNodeEditorSavedTabsInfo.tgi[0].ni[6].dn"
		;
connectAttr "eyebrow_colorConstant.msg" "hyperShadePrimaryNodeEditorSavedTabsInfo.tgi[0].ni[7].dn"
		;
connectAttr "rt_eyebrow_tex.msg" "hyperShadePrimaryNodeEditorSavedTabsInfo.tgi[0].ni[8].dn"
		;
connectAttr "lf_eyePupil_projection.msg" "hyperShadePrimaryNodeEditorSavedTabsInfo.tgi[0].ni[9].dn"
		;
connectAttr "lf_eyebrowCutoutAlpha_ramp.msg" "hyperShadePrimaryNodeEditorSavedTabsInfo.tgi[0].ni[10].dn"
		;
connectAttr "lf_mouthRecolor_colorMath.msg" "hyperShadePrimaryNodeEditorSavedTabsInfo.tgi[0].ni[11].dn"
		;
connectAttr "rsRoundCorners1.msg" "hyperShadePrimaryNodeEditorSavedTabsInfo.tgi[0].ni[12].dn"
		;
connectAttr "mouthAlpha_floatMath.msg" "hyperShadePrimaryNodeEditorSavedTabsInfo.tgi[0].ni[13].dn"
		;
connectAttr "mouthInside_colorConstant.msg" "hyperShadePrimaryNodeEditorSavedTabsInfo.tgi[0].ni[14].dn"
		;
connectAttr "mainSquare_place2dTexture.msg" "hyperShadePrimaryNodeEditorSavedTabsInfo.tgi[0].ni[15].dn"
		;
connectAttr "eyebrows_layeredTexture.msg" "hyperShadePrimaryNodeEditorSavedTabsInfo.tgi[0].ni[16].dn"
		;
connectAttr "eyebrowOpacity_floatConstant.msg" "hyperShadePrimaryNodeEditorSavedTabsInfo.tgi[0].ni[17].dn"
		;
connectAttr "rt_eyelidEyebrowCutout_colorMath.msg" "hyperShadePrimaryNodeEditorSavedTabsInfo.tgi[0].ni[18].dn"
		;
connectAttr "mouthTongue_colorConstant.msg" "hyperShadePrimaryNodeEditorSavedTabsInfo.tgi[0].ni[19].dn"
		;
connectAttr "lf_eyelidEyebrowCutout_colorMath.msg" "hyperShadePrimaryNodeEditorSavedTabsInfo.tgi[0].ni[20].dn"
		;
connectAttr "place2dTexture3.msg" "hyperShadePrimaryNodeEditorSavedTabsInfo.tgi[0].ni[21].dn"
		;
connectAttr "lf_eyePupilColoring_colorMath.msg" "hyperShadePrimaryNodeEditorSavedTabsInfo.tgi[0].ni[22].dn"
		;
connectAttr "lf_eyebrow_tex.msg" "hyperShadePrimaryNodeEditorSavedTabsInfo.tgi[0].ni[23].dn"
		;
connectAttr "m_lip_projection.msg" "hyperShadePrimaryNodeEditorSavedTabsInfo.tgi[0].ni[24].dn"
		;
connectAttr "rt_mouth_projection.msg" "hyperShadePrimaryNodeEditorSavedTabsInfo.tgi[0].ni[25].dn"
		;
connectAttr "lf_eyebrowAlpha_rgbToHsv.msg" "hyperShadePrimaryNodeEditorSavedTabsInfo.tgi[0].ni[26].dn"
		;
connectAttr "colorMath3.msg" "hyperShadePrimaryNodeEditorSavedTabsInfo.tgi[0].ni[27].dn"
		;
connectAttr "blendColors11.msg" "hyperShadePrimaryNodeEditorSavedTabsInfo.tgi[0].ni[28].dn"
		;
connectAttr "rt_eyePupilColoring_colorMath.msg" "hyperShadePrimaryNodeEditorSavedTabsInfo.tgi[0].ni[29].dn"
		;
connectAttr "rt_eyeWhite_colorConstant.msg" "hyperShadePrimaryNodeEditorSavedTabsInfo.tgi[0].ni[30].dn"
		;
connectAttr "mouthClosedOpenAlpha_blendColors.msg" "hyperShadePrimaryNodeEditorSavedTabsInfo.tgi[0].ni[31].dn"
		;
connectAttr "lf_eyebrowOpacity_floatMath.msg" "hyperShadePrimaryNodeEditorSavedTabsInfo.tgi[0].ni[32].dn"
		;
connectAttr "rt_eyebrowCutoutAlpha_ramp.msg" "hyperShadePrimaryNodeEditorSavedTabsInfo.tgi[0].ni[33].dn"
		;
connectAttr "b_teeth_projection.msg" "hyperShadePrimaryNodeEditorSavedTabsInfo.tgi[0].ni[34].dn"
		;
connectAttr "tongue_projection.msg" "hyperShadePrimaryNodeEditorSavedTabsInfo.tgi[0].ni[35].dn"
		;
connectAttr "mouthClosedOpenBlender_floatConstant.msg" "hyperShadePrimaryNodeEditorSavedTabsInfo.tgi[0].ni[36].dn"
		;
connectAttr "mouthOpen_layeredTexture.msg" "hyperShadePrimaryNodeEditorSavedTabsInfo.tgi[0].ni[37].dn"
		;
connectAttr "b_teethCutoutAlpha_floatMath.msg" "hyperShadePrimaryNodeEditorSavedTabsInfo.tgi[0].ni[38].dn"
		;
connectAttr "place2dTexture2.msg" "hyperShadePrimaryNodeEditorSavedTabsInfo.tgi[0].ni[39].dn"
		;
connectAttr "rt_eyebrow_projection.msg" "hyperShadePrimaryNodeEditorSavedTabsInfo.tgi[0].ni[40].dn"
		;
connectAttr "rt_eyePupil_projection.msg" "hyperShadePrimaryNodeEditorSavedTabsInfo.tgi[0].ni[41].dn"
		;
connectAttr "lf_lip_projection.msg" "hyperShadePrimaryNodeEditorSavedTabsInfo.tgi[0].ni[42].dn"
		;
connectAttr "mainFace_layeredTexture.msg" "hyperShadePrimaryNodeEditorSavedTabsInfo.tgi[0].ni[43].dn"
		;
connectAttr "rt_mouthRecolor_colorMath.msg" "hyperShadePrimaryNodeEditorSavedTabsInfo.tgi[0].ni[44].dn"
		;
connectAttr "mouthSidesAlpha_floatMath.msg" "hyperShadePrimaryNodeEditorSavedTabsInfo.tgi[0].ni[45].dn"
		;
connectAttr "t_teethCutoutAlpha_floatMath.msg" "hyperShadePrimaryNodeEditorSavedTabsInfo.tgi[0].ni[46].dn"
		;
connectAttr "b_teethRecolor_colorMath.msg" "hyperShadePrimaryNodeEditorSavedTabsInfo.tgi[0].ni[47].dn"
		;
connectAttr "lf_eyePupil_colorConstant.msg" "hyperShadePrimaryNodeEditorSavedTabsInfo.tgi[0].ni[48].dn"
		;
connectAttr "rt_eyebrowAlpha_ramp.msg" "hyperShadePrimaryNodeEditorSavedTabsInfo.tgi[0].ni[49].dn"
		;
connectAttr "t_teeth_projection.msg" "hyperShadePrimaryNodeEditorSavedTabsInfo.tgi[0].ni[50].dn"
		;
connectAttr "lf_eyebrow_projection.msg" "hyperShadePrimaryNodeEditorSavedTabsInfo.tgi[0].ni[51].dn"
		;
connectAttr "rt_eyeWhite_projection.msg" "hyperShadePrimaryNodeEditorSavedTabsInfo.tgi[0].ni[52].dn"
		;
connectAttr "m_mouth_projection.msg" "hyperShadePrimaryNodeEditorSavedTabsInfo.tgi[0].ni[53].dn"
		;
connectAttr "lf_eyePupilAlpha_floatMath.msg" "hyperShadePrimaryNodeEditorSavedTabsInfo.tgi[0].ni[54].dn"
		;
connectAttr "rt_eyebrowOpacity_floatMath.msg" "hyperShadePrimaryNodeEditorSavedTabsInfo.tgi[0].ni[55].dn"
		;
connectAttr "mouthClosedOpen_blendColors.msg" "hyperShadePrimaryNodeEditorSavedTabsInfo.tgi[0].ni[56].dn"
		;
connectAttr "lf_eyebrow_contrast.msg" "hyperShadePrimaryNodeEditorSavedTabsInfo.tgi[0].ni[57].dn"
		;
connectAttr "facePromoArt_rsMat.msg" "hyperShadePrimaryNodeEditorSavedTabsInfo.tgi[0].ni[58].dn"
		;
connectAttr "rt_eyebrowRecolor_colorMath.msg" "hyperShadePrimaryNodeEditorSavedTabsInfo.tgi[0].ni[59].dn"
		;
connectAttr "lf_eyebrowAlpha_ramp.msg" "hyperShadePrimaryNodeEditorSavedTabsInfo.tgi[0].ni[60].dn"
		;
connectAttr "t_teethRecolor_colorMath.msg" "hyperShadePrimaryNodeEditorSavedTabsInfo.tgi[0].ni[61].dn"
		;
connectAttr "mainSquare_ramp.msg" "hyperShadePrimaryNodeEditorSavedTabsInfo.tgi[0].ni[62].dn"
		;
connectAttr "lf_lipRecolor_colorMath.msg" "hyperShadePrimaryNodeEditorSavedTabsInfo.tgi[0].ni[63].dn"
		;
connectAttr "rt_eyebrow_contrast.msg" "hyperShadePrimaryNodeEditorSavedTabsInfo.tgi[0].ni[64].dn"
		;
connectAttr "eyebrowContrast_floatConstant.msg" "hyperShadePrimaryNodeEditorSavedTabsInfo.tgi[0].ni[65].dn"
		;
connectAttr "rt_eyePupil_colorConstant.msg" "hyperShadePrimaryNodeEditorSavedTabsInfo.tgi[0].ni[66].dn"
		;
connectAttr "place2dTexture_skinPromoArt.msg" "hyperShadePrimaryNodeEditorSavedTabsInfo.tgi[0].ni[67].dn"
		;
connectAttr "tongueCutoutAlpha_floatMath.msg" "hyperShadePrimaryNodeEditorSavedTabsInfo.tgi[0].ni[68].dn"
		;
connectAttr "skinPromoArt_tex.msg" "hyperShadePrimaryNodeEditorSavedTabsInfo.tgi[0].ni[69].dn"
		;
connectAttr "colorMath4.msg" "hyperShadePrimaryNodeEditorSavedTabsInfo.tgi[0].ni[70].dn"
		;
connectAttr "lf_eyebrowRecolor_colorMath.msg" "hyperShadePrimaryNodeEditorSavedTabsInfo.tgi[0].ni[71].dn"
		;
connectAttr "lf_eyeWhite_projection.msg" "hyperShadePrimaryNodeEditorSavedTabsInfo.tgi[0].ni[72].dn"
		;
connectAttr "rt_eyebrowAlpha_rgbToHsv.msg" "hyperShadePrimaryNodeEditorSavedTabsInfo.tgi[0].ni[73].dn"
		;
connectAttr "rt_eyePupilAlpha_floatMath.msg" "hyperShadePrimaryNodeEditorSavedTabsInfo.tgi[0].ni[74].dn"
		;
connectAttr "tongueRecolor_colorMath.msg" "hyperShadePrimaryNodeEditorSavedTabsInfo.tgi[0].ni[75].dn"
		;
connectAttr "rt_lip_projection.msg" "hyperShadePrimaryNodeEditorSavedTabsInfo.tgi[0].ni[76].dn"
		;
connectAttr "mouthClosed_layeredTexture.msg" "hyperShadePrimaryNodeEditorSavedTabsInfo.tgi[0].ni[77].dn"
		;
connectAttr "mouthTeeth_colorConstant.msg" "hyperShadePrimaryNodeEditorSavedTabsInfo.tgi[0].ni[78].dn"
		;
connectAttr "skinPromoArt_rsMat.msg" "hyperShadePrimaryNodeEditorSavedTabsInfo.tgi[1].ni[0].dn"
		;
connectAttr "skinPromoArt_tex.msg" "hyperShadePrimaryNodeEditorSavedTabsInfo.tgi[1].ni[1].dn"
		;
connectAttr "rsRoundCorners1.msg" "hyperShadePrimaryNodeEditorSavedTabsInfo.tgi[1].ni[2].dn"
		;
connectAttr "place2dTexture_skinPromoArt.msg" "hyperShadePrimaryNodeEditorSavedTabsInfo.tgi[1].ni[3].dn"
		;
connectAttr "rsBumpBlender1.msg" "hyperShadePrimaryNodeEditorSavedTabsInfo.tgi[1].ni[4].dn"
		;
connectAttr "mouthOpenY_distanceBetween.d" "condition48.ft";
connectAttr "mouthOpenY_distanceBetween.d" "mouthOpenY_bottomLimitMult_floatMath._fa"
		;
connectAttr "mouthOpenY_bottomLimitMult_floatMath.of" "mouthOpenY_bottomLimitAdd_floatMath._fa"
		;
connectAttr "mouthClosedOpenBlender_floatConstant.of" "condition49.ft";
connectAttr "mouthOpenY_bottomLimitAdd_floatMath.of" "condition49.ctg";
connectAttr "grp_joint_leg_tl.msg" "MayaNodeEditorSavedTabsInfo.tgi[0].ni[0].dn"
		;
connectAttr "main_CON.msg" "MayaNodeEditorSavedTabsInfo.tgi[0].ni[1].dn";
connectAttr "main_CONShape.msg" "MayaNodeEditorSavedTabsInfo.tgi[0].ni[2].dn";
connectAttr "multiplyDivide2.msg" "MayaNodeEditorSavedTabsInfo.tgi[0].ni[3].dn";
connectAttr "remapValue2.msg" "MayaNodeEditorSavedTabsInfo.tgi[0].ni[4].dn";
connectAttr "anim_leg_settings_l.msg" "MayaNodeEditorSavedTabsInfo.tgi[0].ni[5].dn"
		;
connectAttr "locator_leg_bottom_lShape.msg" "MayaNodeEditorSavedTabsInfo.tgi[0].ni[6].dn"
		;
connectAttr "condition3.msg" "MayaNodeEditorSavedTabsInfo.tgi[0].ni[7].dn";
connectAttr "distanceBetween2.msg" "MayaNodeEditorSavedTabsInfo.tgi[0].ni[8].dn"
		;
connectAttr "decomposeMatrix5.msg" "MayaNodeEditorSavedTabsInfo.tgi[1].ni[0].dn"
		;
connectAttr "head_JNT.msg" "MayaNodeEditorSavedTabsInfo.tgi[1].ni[1].dn";
connectAttr "joint_leg_bl.msg" "MayaNodeEditorSavedTabsInfo.tgi[3].ni[0].dn";
connectAttr "anim_leg_settings_l.msg" "MayaNodeEditorSavedTabsInfo.tgi[3].ni[1].dn"
		;
connectAttr "multiplyDivide5.msg" "MayaNodeEditorSavedTabsInfo.tgi[4].ni[0].dn";
connectAttr "remapValue4.msg" "MayaNodeEditorSavedTabsInfo.tgi[4].ni[1].dn";
connectAttr "condition7.msg" "MayaNodeEditorSavedTabsInfo.tgi[4].ni[2].dn";
connectAttr "condition5.msg" "MayaNodeEditorSavedTabsInfo.tgi[4].ni[3].dn";
connectAttr "const_facial_settings.msg" "MayaNodeEditorSavedTabsInfo.tgi[4].ni[4].dn"
		;
connectAttr "addDoubleLinear10.msg" "MayaNodeEditorSavedTabsInfo.tgi[4].ni[5].dn"
		;
connectAttr "anim_facial_settings.msg" "MayaNodeEditorSavedTabsInfo.tgi[5].ni[0].dn"
		;
connectAttr "anim_facial_settingsShape5.msg" "MayaNodeEditorSavedTabsInfo.tgi[5].ni[1].dn"
		;
connectAttr "anim_facial_settingsShape2.msg" "MayaNodeEditorSavedTabsInfo.tgi[5].ni[2].dn"
		;
connectAttr "anim_facial_settingsShape4.msg" "MayaNodeEditorSavedTabsInfo.tgi[5].ni[3].dn"
		;
connectAttr "anim_facial_settingsShape3.msg" "MayaNodeEditorSavedTabsInfo.tgi[5].ni[4].dn"
		;
connectAttr "anim_facial_settingsShape1.msg" "MayaNodeEditorSavedTabsInfo.tgi[5].ni[5].dn"
		;
connectAttr "anim_facial_settingsShape.msg" "MayaNodeEditorSavedTabsInfo.tgi[5].ni[6].dn"
		;
connectAttr "condition14.msg" "MayaNodeEditorSavedTabsInfo.tgi[6].ni[0].dn";
connectAttr "anim_facial_settings.msg" "MayaNodeEditorSavedTabsInfo.tgi[6].ni[1].dn"
		;
connectAttr "condition15.msg" "MayaNodeEditorSavedTabsInfo.tgi[6].ni[2].dn";
connectAttr "outline_CNST.msg" "MayaNodeEditorSavedTabsInfo.tgi[8].ni[0].dn";
connectAttr "main_CON.msg" "MayaNodeEditorSavedTabsInfo.tgi[9].ni[0].dn";
connectAttr "main_CONShape.msg" "MayaNodeEditorSavedTabsInfo.tgi[9].ni[1].dn";
connectAttr "grp_arm_bind_r.msg" "MayaNodeEditorSavedTabsInfo.tgi[9].ni[2].dn";
connectAttr "joint_arm_bind_l.msg" "MayaNodeEditorSavedTabsInfo.tgi[9].ni[3].dn"
		;
connectAttr "grp_arm_rigging_r.msg" "MayaNodeEditorSavedTabsInfo.tgi[9].ni[4].dn"
		;
connectAttr "joint_arm_bind_r.msg" "MayaNodeEditorSavedTabsInfo.tgi[9].ni[5].dn"
		;
connectAttr "grp_arm_rigging_l.msg" "MayaNodeEditorSavedTabsInfo.tgi[9].ni[6].dn"
		;
connectAttr "grp_arm_bind_l.msg" "MayaNodeEditorSavedTabsInfo.tgi[9].ni[7].dn";
connectAttr "cluster_legikcon_r.msg" "MayaNodeEditorSavedTabsInfo.tgi[10].ni[0].dn"
		;
connectAttr "uvCluster_boots_r.msg" "MayaNodeEditorSavedTabsInfo.tgi[10].ni[1].dn"
		;
connectAttr "condition35.msg" "MayaNodeEditorSavedTabsInfo.tgi[10].ni[2].dn";
connectAttr "condition38.msg" "MayaNodeEditorSavedTabsInfo.tgi[10].ni[3].dn";
connectAttr "uvCluster_chestplace_m.msg" "MayaNodeEditorSavedTabsInfo.tgi[10].ni[4].dn"
		;
connectAttr "cluster_armfkcon_r.msg" "MayaNodeEditorSavedTabsInfo.tgi[10].ni[5].dn"
		;
connectAttr "cluster_headcon.msg" "MayaNodeEditorSavedTabsInfo.tgi[10].ni[6].dn"
		;
connectAttr "cluster_legfkcon_l.msg" "MayaNodeEditorSavedTabsInfo.tgi[10].ni[7].dn"
		;
connectAttr "floatMath61.msg" "MayaNodeEditorSavedTabsInfo.tgi[10].ni[8].dn";
connectAttr "anim_facial_settings.msg" "MayaNodeEditorSavedTabsInfo.tgi[10].ni[9].dn"
		;
connectAttr "cluster_armfkcon_l.msg" "MayaNodeEditorSavedTabsInfo.tgi[10].ni[10].dn"
		;
connectAttr "condition43.msg" "MayaNodeEditorSavedTabsInfo.tgi[10].ni[11].dn";
connectAttr "floatMath56.msg" "MayaNodeEditorSavedTabsInfo.tgi[10].ni[12].dn";
connectAttr "cluster_legfkcon_rShape.msg" "MayaNodeEditorSavedTabsInfo.tgi[10].ni[13].dn"
		;
connectAttr "uvCluster_helmet.msg" "MayaNodeEditorSavedTabsInfo.tgi[10].ni[14].dn"
		;
connectAttr "condition37.msg" "MayaNodeEditorSavedTabsInfo.tgi[10].ni[15].dn";
connectAttr "grp_mesh_boots.msg" "MayaNodeEditorSavedTabsInfo.tgi[10].ni[16].dn"
		;
connectAttr "floatMath57.msg" "MayaNodeEditorSavedTabsInfo.tgi[10].ni[17].dn";
connectAttr "cluster_legikcon_l.msg" "MayaNodeEditorSavedTabsInfo.tgi[10].ni[18].dn"
		;
connectAttr "grp_mesh_helmet.msg" "MayaNodeEditorSavedTabsInfo.tgi[10].ni[19].dn"
		;
connectAttr "floatMath58.msg" "MayaNodeEditorSavedTabsInfo.tgi[10].ni[20].dn";
connectAttr "uvCluster_chestplate_l.msg" "MayaNodeEditorSavedTabsInfo.tgi[10].ni[21].dn"
		;
connectAttr "uvCluster_leggings_r.msg" "MayaNodeEditorSavedTabsInfo.tgi[10].ni[22].dn"
		;
connectAttr "cluster_legtopcon.msg" "MayaNodeEditorSavedTabsInfo.tgi[10].ni[23].dn"
		;
connectAttr "uvCluster_chestplate_r.msg" "MayaNodeEditorSavedTabsInfo.tgi[10].ni[24].dn"
		;
connectAttr "uvCluster_boots_lShape.msg" "MayaNodeEditorSavedTabsInfo.tgi[10].ni[25].dn"
		;
connectAttr "condition42.msg" "MayaNodeEditorSavedTabsInfo.tgi[10].ni[26].dn";
connectAttr "uvCluster_boots_rShape.msg" "MayaNodeEditorSavedTabsInfo.tgi[10].ni[27].dn"
		;
connectAttr "uvCluster_boots_l.msg" "MayaNodeEditorSavedTabsInfo.tgi[10].ni[28].dn"
		;
connectAttr "floatMath54.msg" "MayaNodeEditorSavedTabsInfo.tgi[10].ni[29].dn";
connectAttr "grp_armor_con_clusters.msg" "MayaNodeEditorSavedTabsInfo.tgi[10].ni[30].dn"
		;
connectAttr "cluster_shouldercon.msg" "MayaNodeEditorSavedTabsInfo.tgi[10].ni[31].dn"
		;
connectAttr "condition44.msg" "MayaNodeEditorSavedTabsInfo.tgi[10].ni[32].dn";
connectAttr "floatMath55.msg" "MayaNodeEditorSavedTabsInfo.tgi[10].ni[33].dn";
connectAttr "cluster_legfkcon_r.msg" "MayaNodeEditorSavedTabsInfo.tgi[10].ni[34].dn"
		;
connectAttr "condition40.msg" "MayaNodeEditorSavedTabsInfo.tgi[10].ni[35].dn";
connectAttr "condition39.msg" "MayaNodeEditorSavedTabsInfo.tgi[10].ni[36].dn";
connectAttr "uvCluster_leggings_m.msg" "MayaNodeEditorSavedTabsInfo.tgi[10].ni[37].dn"
		;
connectAttr "condition41.msg" "MayaNodeEditorSavedTabsInfo.tgi[10].ni[38].dn";
connectAttr "cluster_legfkcon_lShape.msg" "MayaNodeEditorSavedTabsInfo.tgi[10].ni[39].dn"
		;
connectAttr "grp_mesh_chestplate.msg" "MayaNodeEditorSavedTabsInfo.tgi[10].ni[40].dn"
		;
connectAttr "uvCluster_leggings_l.msg" "MayaNodeEditorSavedTabsInfo.tgi[10].ni[41].dn"
		;
connectAttr "floatMath60.msg" "MayaNodeEditorSavedTabsInfo.tgi[10].ni[42].dn";
connectAttr "cluster_bodycon.msg" "MayaNodeEditorSavedTabsInfo.tgi[10].ni[43].dn"
		;
connectAttr "floatMath59.msg" "MayaNodeEditorSavedTabsInfo.tgi[10].ni[44].dn";
connectAttr "cluster_headconShape.msg" "MayaNodeEditorSavedTabsInfo.tgi[10].ni[45].dn"
		;
connectAttr "grp_mesh_leggings.msg" "MayaNodeEditorSavedTabsInfo.tgi[10].ni[46].dn"
		;
connectAttr "condition36.msg" "MayaNodeEditorSavedTabsInfo.tgi[10].ni[47].dn";
connectAttr "rt_mouthSmile_remapValue.msg" "MayaNodeEditorSavedTabsInfo.tgi[11].ni[0].dn"
		;
connectAttr "rt_mouthSmileMin_clamp.msg" "MayaNodeEditorSavedTabsInfo.tgi[11].ni[1].dn"
		;
connectAttr "mouthOpenY_bottomLimitMult_floatMath.msg" "MayaNodeEditorSavedTabsInfo.tgi[11].ni[2].dn"
		;
connectAttr "b_mouthOpen_LOCShape.msg" "MayaNodeEditorSavedTabsInfo.tgi[11].ni[3].dn"
		;
connectAttr "mouthClosedOpenBlender_floatConstant.msg" "MayaNodeEditorSavedTabsInfo.tgi[11].ni[4].dn"
		;
connectAttr "condition48.msg" "MayaNodeEditorSavedTabsInfo.tgi[11].ni[5].dn";
connectAttr "rt_lip_projectionGRP.msg" "MayaNodeEditorSavedTabsInfo.tgi[11].ni[6].dn"
		;
connectAttr "b_mouthTeeth_LOC.msg" "MayaNodeEditorSavedTabsInfo.tgi[11].ni[7].dn"
		;
connectAttr "mouthOpenX_scaleOffset_floatMath.msg" "MayaNodeEditorSavedTabsInfo.tgi[11].ni[8].dn"
		;
connectAttr "lf_mouth_projectionGRP.msg" "MayaNodeEditorSavedTabsInfo.tgi[11].ni[9].dn"
		;
connectAttr "mouthOpenX_divideTranslate_floatMath.msg" "MayaNodeEditorSavedTabsInfo.tgi[11].ni[10].dn"
		;
connectAttr "rt_mouthSmile_clamp.msg" "MayaNodeEditorSavedTabsInfo.tgi[11].ni[11].dn"
		;
connectAttr "lf_mouthSmile_clamp.msg" "MayaNodeEditorSavedTabsInfo.tgi[11].ni[12].dn"
		;
connectAttr "lf_mouthSmileMin_clamp.msg" "MayaNodeEditorSavedTabsInfo.tgi[11].ni[13].dn"
		;
connectAttr "mouthOpenX_addedTranslate_floatMath.msg" "MayaNodeEditorSavedTabsInfo.tgi[11].ni[14].dn"
		;
connectAttr "condition49.msg" "MayaNodeEditorSavedTabsInfo.tgi[11].ni[15].dn";
connectAttr "lf_lip_projectionGRP.msg" "MayaNodeEditorSavedTabsInfo.tgi[11].ni[16].dn"
		;
connectAttr "lf_mouthSmile_remapValue.msg" "MayaNodeEditorSavedTabsInfo.tgi[11].ni[17].dn"
		;
connectAttr "mouthOpenXInverse_floatMath.msg" "MayaNodeEditorSavedTabsInfo.tgi[11].ni[18].dn"
		;
connectAttr "t_mouthTeeth_LOC.msg" "MayaNodeEditorSavedTabsInfo.tgi[11].ni[19].dn"
		;
connectAttr "m_lip_projectionGRP.msg" "MayaNodeEditorSavedTabsInfo.tgi[11].ni[20].dn"
		;
connectAttr "floatMath63.msg" "MayaNodeEditorSavedTabsInfo.tgi[11].ni[21].dn";
connectAttr "mouthOpenY_bottomLimitAdd_floatMath.msg" "MayaNodeEditorSavedTabsInfo.tgi[11].ni[22].dn"
		;
connectAttr "rt_mouthSmileOffset_floatMath.msg" "MayaNodeEditorSavedTabsInfo.tgi[11].ni[23].dn"
		;
connectAttr "rt_mouthMin_clamp.msg" "MayaNodeEditorSavedTabsInfo.tgi[11].ni[24].dn"
		;
connectAttr "b_mouthTweak_CONShape.msg" "MayaNodeEditorSavedTabsInfo.tgi[11].ni[25].dn"
		;
connectAttr "rt_mouthOpen_LOC.msg" "MayaNodeEditorSavedTabsInfo.tgi[11].ni[26].dn"
		;
connectAttr "t_mouthOpen_LOC.msg" "MayaNodeEditorSavedTabsInfo.tgi[11].ni[27].dn"
		;
connectAttr "mouthOpenY_distanceBetween.msg" "MayaNodeEditorSavedTabsInfo.tgi[11].ni[28].dn"
		;
connectAttr "lf_mouthOpen_LOC.msg" "MayaNodeEditorSavedTabsInfo.tgi[11].ni[29].dn"
		;
connectAttr "m_mouth_projectionGRP.msg" "MayaNodeEditorSavedTabsInfo.tgi[11].ni[30].dn"
		;
connectAttr "b_mouthTweak_CON.msg" "MayaNodeEditorSavedTabsInfo.tgi[11].ni[31].dn"
		;
connectAttr "lf_mouthSmileOffset_floatMath.msg" "MayaNodeEditorSavedTabsInfo.tgi[11].ni[32].dn"
		;
connectAttr "rt_mouth_projectionGRP.msg" "MayaNodeEditorSavedTabsInfo.tgi[11].ni[33].dn"
		;
connectAttr "mouthOpenX_scaleMultiply_floatMath.msg" "MayaNodeEditorSavedTabsInfo.tgi[11].ni[34].dn"
		;
connectAttr "mouthOpenY_scaleOffset_floatMath.msg" "MayaNodeEditorSavedTabsInfo.tgi[11].ni[35].dn"
		;
connectAttr "t_mouthOpen_LOCShape.msg" "MayaNodeEditorSavedTabsInfo.tgi[11].ni[36].dn"
		;
connectAttr "t_mouthTweak_CON.msg" "MayaNodeEditorSavedTabsInfo.tgi[12].ni[0].dn"
		;
connectAttr "decomposeMatrix16.msg" "MayaNodeEditorSavedTabsInfo.tgi[12].ni[1].dn"
		;
connectAttr "rt_mouthOpen_LOC.msg" "MayaNodeEditorSavedTabsInfo.tgi[12].ni[2].dn"
		;
connectAttr "decomposeMatrix17.msg" "MayaNodeEditorSavedTabsInfo.tgi[12].ni[3].dn"
		;
connectAttr "mouthTongue_LOC.msg" "MayaNodeEditorSavedTabsInfo.tgi[12].ni[4].dn"
		;
connectAttr "rt_mouthTweak_CON.msg" "MayaNodeEditorSavedTabsInfo.tgi[12].ni[5].dn"
		;
connectAttr "decomposeMatrix11.msg" "MayaNodeEditorSavedTabsInfo.tgi[12].ni[6].dn"
		;
connectAttr "multMatrix3.msg" "MayaNodeEditorSavedTabsInfo.tgi[12].ni[7].dn";
connectAttr "t_mouthOpen_LOC.msg" "MayaNodeEditorSavedTabsInfo.tgi[12].ni[8].dn"
		;
connectAttr "lf_mouthTweak_CON.msg" "MayaNodeEditorSavedTabsInfo.tgi[12].ni[9].dn"
		;
connectAttr "multMatrix5.msg" "MayaNodeEditorSavedTabsInfo.tgi[12].ni[10].dn";
connectAttr "mouthClosedOpenBlender_floatConstant.msg" "MayaNodeEditorSavedTabsInfo.tgi[12].ni[11].dn"
		;
connectAttr "decomposeMatrix12.msg" "MayaNodeEditorSavedTabsInfo.tgi[12].ni[12].dn"
		;
connectAttr "multMatrix7.msg" "MayaNodeEditorSavedTabsInfo.tgi[12].ni[13].dn";
connectAttr "b_mouthOpen_LOC.msg" "MayaNodeEditorSavedTabsInfo.tgi[12].ni[14].dn"
		;
connectAttr "b_mouthTeeth_LOC.msg" "MayaNodeEditorSavedTabsInfo.tgi[12].ni[15].dn"
		;
connectAttr "t_teethTweak_CON.msg" "MayaNodeEditorSavedTabsInfo.tgi[12].ni[16].dn"
		;
connectAttr "multMatrix1.msg" "MayaNodeEditorSavedTabsInfo.tgi[12].ni[17].dn";
connectAttr "t_mouthTeeth_LOC.msg" "MayaNodeEditorSavedTabsInfo.tgi[12].ni[18].dn"
		;
connectAttr "lf_mouthOpen_LOC.msg" "MayaNodeEditorSavedTabsInfo.tgi[12].ni[19].dn"
		;
connectAttr "decomposeMatrix14.msg" "MayaNodeEditorSavedTabsInfo.tgi[12].ni[20].dn"
		;
connectAttr "faceLocators_GRP.msg" "MayaNodeEditorSavedTabsInfo.tgi[12].ni[21].dn"
		;
connectAttr "b_mouthTweak_CON.msg" "MayaNodeEditorSavedTabsInfo.tgi[12].ni[22].dn"
		;
connectAttr "decomposeMatrix13.msg" "MayaNodeEditorSavedTabsInfo.tgi[12].ni[23].dn"
		;
connectAttr "multMatrix6.msg" "MayaNodeEditorSavedTabsInfo.tgi[12].ni[24].dn";
connectAttr "b_tongueTweak_CON.msg" "MayaNodeEditorSavedTabsInfo.tgi[12].ni[25].dn"
		;
connectAttr "multMatrix4.msg" "MayaNodeEditorSavedTabsInfo.tgi[12].ni[26].dn";
connectAttr "decomposeMatrix15.msg" "MayaNodeEditorSavedTabsInfo.tgi[12].ni[27].dn"
		;
connectAttr "b_teethTweak_CON.msg" "MayaNodeEditorSavedTabsInfo.tgi[12].ni[28].dn"
		;
connectAttr "multMatrix2.msg" "MayaNodeEditorSavedTabsInfo.tgi[12].ni[29].dn";
connectAttr "composeMatrix_matrixConstraint.omat" "multMatrix_matrixConstraint.i[0]"
		;
connectAttr "joint_body.wm" "multMatrix_matrixConstraint.i[1]";
connectAttr "body_LOC.pim" "multMatrix_matrixConstraint.i[2]";
connectAttr "composeMatrix_matrixConstraint1.omat" "multMatrix_matrixConstraint1.i[0]"
		;
connectAttr "joint_body.wm" "multMatrix_matrixConstraint1.i[1]";
connectAttr "body_LOC.pim" "multMatrix_matrixConstraint1.i[2]";
connectAttr "steve_set1.pa" ":renderPartition.st" -na;
connectAttr "rsMat_skinPromoArtSG.pa" ":renderPartition.st" -na;
connectAttr "rsMaterial1SG.pa" ":renderPartition.st" -na;
connectAttr "typeBlinnSG.pa" ":renderPartition.st" -na;
connectAttr "rsMat_armorPromoArtSG.pa" ":renderPartition.st" -na;
connectAttr "aiStandardSurface1SG.pa" ":renderPartition.st" -na;
connectAttr "aiStandardSurface2SG.pa" ":renderPartition.st" -na;
connectAttr "aiStandardSurface3SG.pa" ":renderPartition.st" -na;
connectAttr "rsSprite1SG.pa" ":renderPartition.st" -na;
connectAttr "rsMaterial2SG.pa" ":renderPartition.st" -na;
connectAttr "typeBlinn.msg" ":defaultShaderList1.s" -na;
connectAttr "armorPromoArt_rsMat.msg" ":defaultShaderList1.s" -na;
connectAttr "skinPromoArt_rsMat.msg" ":defaultShaderList1.s" -na;
connectAttr "facePromoArt_rsMat.msg" ":defaultShaderList1.s" -na;
connectAttr "defaultRedshiftPostEffects.msg" ":defaultRenderUtilityList1.u" -na;
connectAttr "place2dTexture_skinPromoArt.msg" ":defaultRenderUtilityList1.u" -na
		;
connectAttr "distanceBetween1.msg" ":defaultRenderUtilityList1.u" -na;
connectAttr "addDoubleLinear1.msg" ":defaultRenderUtilityList1.u" -na;
connectAttr "condition1.msg" ":defaultRenderUtilityList1.u" -na;
connectAttr "remapValue1.msg" ":defaultRenderUtilityList1.u" -na;
connectAttr "multiplyDivide1.msg" ":defaultRenderUtilityList1.u" -na;
connectAttr "decomposeMatrix1.msg" ":defaultRenderUtilityList1.u" -na;
connectAttr "blendColors1.msg" ":defaultRenderUtilityList1.u" -na;
connectAttr "blendColors2.msg" ":defaultRenderUtilityList1.u" -na;
connectAttr "blendColors3.msg" ":defaultRenderUtilityList1.u" -na;
connectAttr "decomposeMatrix2.msg" ":defaultRenderUtilityList1.u" -na;
connectAttr "addDoubleLinear2.msg" ":defaultRenderUtilityList1.u" -na;
connectAttr "condition2.msg" ":defaultRenderUtilityList1.u" -na;
connectAttr "condition3.msg" ":defaultRenderUtilityList1.u" -na;
connectAttr "multiplyDivide2.msg" ":defaultRenderUtilityList1.u" -na;
connectAttr "addDoubleLinear3.msg" ":defaultRenderUtilityList1.u" -na;
connectAttr "distanceBetween2.msg" ":defaultRenderUtilityList1.u" -na;
connectAttr "remapValue2.msg" ":defaultRenderUtilityList1.u" -na;
connectAttr "condition4.msg" ":defaultRenderUtilityList1.u" -na;
connectAttr "decomposeMatrix3.msg" ":defaultRenderUtilityList1.u" -na;
connectAttr "blendColors4.msg" ":defaultRenderUtilityList1.u" -na;
connectAttr "decomposeMatrix4.msg" ":defaultRenderUtilityList1.u" -na;
connectAttr "blendColors5.msg" ":defaultRenderUtilityList1.u" -na;
connectAttr "blendColors6.msg" ":defaultRenderUtilityList1.u" -na;
connectAttr "multiplyDivide3.msg" ":defaultRenderUtilityList1.u" -na;
connectAttr "addDoubleLinear4.msg" ":defaultRenderUtilityList1.u" -na;
connectAttr "reverse1.msg" ":defaultRenderUtilityList1.u" -na;
connectAttr "reverse2.msg" ":defaultRenderUtilityList1.u" -na;
connectAttr "addDoubleLinear5.msg" ":defaultRenderUtilityList1.u" -na;
connectAttr "decomposeMatrix5.msg" ":defaultRenderUtilityList1.u" -na;
connectAttr "remapValue3.msg" ":defaultRenderUtilityList1.u" -na;
connectAttr "condition5.msg" ":defaultRenderUtilityList1.u" -na;
connectAttr "condition6.msg" ":defaultRenderUtilityList1.u" -na;
connectAttr "condition7.msg" ":defaultRenderUtilityList1.u" -na;
connectAttr "condition8.msg" ":defaultRenderUtilityList1.u" -na;
connectAttr "condition9.msg" ":defaultRenderUtilityList1.u" -na;
connectAttr "addDoubleLinear10.msg" ":defaultRenderUtilityList1.u" -na;
connectAttr "remapValue4.msg" ":defaultRenderUtilityList1.u" -na;
connectAttr "multiplyDivide5.msg" ":defaultRenderUtilityList1.u" -na;
connectAttr "condition14.msg" ":defaultRenderUtilityList1.u" -na;
connectAttr "condition15.msg" ":defaultRenderUtilityList1.u" -na;
connectAttr "place2dTexture_texPromoArtFace.msg" ":defaultRenderUtilityList1.u" 
		-na;
connectAttr "remapValue5.msg" ":defaultRenderUtilityList1.u" -na;
connectAttr "remapValue6.msg" ":defaultRenderUtilityList1.u" -na;
connectAttr "condition16.msg" ":defaultRenderUtilityList1.u" -na;
connectAttr "condition17.msg" ":defaultRenderUtilityList1.u" -na;
connectAttr "condition18.msg" ":defaultRenderUtilityList1.u" -na;
connectAttr "condition19.msg" ":defaultRenderUtilityList1.u" -na;
connectAttr "multiplyDivide6.msg" ":defaultRenderUtilityList1.u" -na;
connectAttr "decomposeMatrix6.msg" ":defaultRenderUtilityList1.u" -na;
connectAttr "multiplyDivide7.msg" ":defaultRenderUtilityList1.u" -na;
connectAttr "floatMath1.msg" ":defaultRenderUtilityList1.u" -na;
connectAttr "floatMath2.msg" ":defaultRenderUtilityList1.u" -na;
connectAttr "floatMath3.msg" ":defaultRenderUtilityList1.u" -na;
connectAttr "floatMath4.msg" ":defaultRenderUtilityList1.u" -na;
connectAttr "floatMath5.msg" ":defaultRenderUtilityList1.u" -na;
connectAttr "floatMath6.msg" ":defaultRenderUtilityList1.u" -na;
connectAttr "floatMath7.msg" ":defaultRenderUtilityList1.u" -na;
connectAttr "floatMath8.msg" ":defaultRenderUtilityList1.u" -na;
connectAttr "distanceBetween3.msg" ":defaultRenderUtilityList1.u" -na;
connectAttr "floatMath10.msg" ":defaultRenderUtilityList1.u" -na;
connectAttr "floatMath12.msg" ":defaultRenderUtilityList1.u" -na;
connectAttr "floatMath13.msg" ":defaultRenderUtilityList1.u" -na;
connectAttr "floatMath14.msg" ":defaultRenderUtilityList1.u" -na;
connectAttr "floatMath15.msg" ":defaultRenderUtilityList1.u" -na;
connectAttr "floatMath16.msg" ":defaultRenderUtilityList1.u" -na;
connectAttr "floatMath17.msg" ":defaultRenderUtilityList1.u" -na;
connectAttr "multiplyDivide8.msg" ":defaultRenderUtilityList1.u" -na;
connectAttr "multiplyDivide9.msg" ":defaultRenderUtilityList1.u" -na;
connectAttr "multiplyDivide10.msg" ":defaultRenderUtilityList1.u" -na;
connectAttr "floatMath20.msg" ":defaultRenderUtilityList1.u" -na;
connectAttr "floatMath21.msg" ":defaultRenderUtilityList1.u" -na;
connectAttr "floatMath22.msg" ":defaultRenderUtilityList1.u" -na;
connectAttr "floatMath23.msg" ":defaultRenderUtilityList1.u" -na;
connectAttr "floatMath25.msg" ":defaultRenderUtilityList1.u" -na;
connectAttr "floatMath26.msg" ":defaultRenderUtilityList1.u" -na;
connectAttr "place2dTexture_armorPromoArt.msg" ":defaultRenderUtilityList1.u" -na
		;
connectAttr "floatMath29.msg" ":defaultRenderUtilityList1.u" -na;
connectAttr "floatMath30.msg" ":defaultRenderUtilityList1.u" -na;
connectAttr "floatMath31.msg" ":defaultRenderUtilityList1.u" -na;
connectAttr "blendColors7.msg" ":defaultRenderUtilityList1.u" -na;
connectAttr "blendColors8.msg" ":defaultRenderUtilityList1.u" -na;
connectAttr "condition23.msg" ":defaultRenderUtilityList1.u" -na;
connectAttr "decomposeMatrix7.msg" ":defaultRenderUtilityList1.u" -na;
connectAttr "decomposeMatrix8.msg" ":defaultRenderUtilityList1.u" -na;
connectAttr "multiplyDivide11.msg" ":defaultRenderUtilityList1.u" -na;
connectAttr "multiplyDivide12.msg" ":defaultRenderUtilityList1.u" -na;
connectAttr "blendColors9.msg" ":defaultRenderUtilityList1.u" -na;
connectAttr "decomposeMatrix9.msg" ":defaultRenderUtilityList1.u" -na;
connectAttr "decomposeMatrix10.msg" ":defaultRenderUtilityList1.u" -na;
connectAttr "blendColors10.msg" ":defaultRenderUtilityList1.u" -na;
connectAttr "multiplyDivide13.msg" ":defaultRenderUtilityList1.u" -na;
connectAttr "floatMath32.msg" ":defaultRenderUtilityList1.u" -na;
connectAttr "floatMath33.msg" ":defaultRenderUtilityList1.u" -na;
connectAttr "floatMath34.msg" ":defaultRenderUtilityList1.u" -na;
connectAttr "floatMath35.msg" ":defaultRenderUtilityList1.u" -na;
connectAttr "floatMath36.msg" ":defaultRenderUtilityList1.u" -na;
connectAttr "floatMath37.msg" ":defaultRenderUtilityList1.u" -na;
connectAttr "floatMath38.msg" ":defaultRenderUtilityList1.u" -na;
connectAttr "condition25.msg" ":defaultRenderUtilityList1.u" -na;
connectAttr "multiplyDivide14.msg" ":defaultRenderUtilityList1.u" -na;
connectAttr "multiplyDivide15.msg" ":defaultRenderUtilityList1.u" -na;
connectAttr "condition26.msg" ":defaultRenderUtilityList1.u" -na;
connectAttr "remapValue7.msg" ":defaultRenderUtilityList1.u" -na;
connectAttr "condition27.msg" ":defaultRenderUtilityList1.u" -na;
connectAttr "condition28.msg" ":defaultRenderUtilityList1.u" -na;
connectAttr "condition29.msg" ":defaultRenderUtilityList1.u" -na;
connectAttr "remapValue8.msg" ":defaultRenderUtilityList1.u" -na;
connectAttr "floatMath39.msg" ":defaultRenderUtilityList1.u" -na;
connectAttr "floatMath40.msg" ":defaultRenderUtilityList1.u" -na;
connectAttr "multiplyDivide16.msg" ":defaultRenderUtilityList1.u" -na;
connectAttr "floatMath41.msg" ":defaultRenderUtilityList1.u" -na;
connectAttr "floatMath42.msg" ":defaultRenderUtilityList1.u" -na;
connectAttr "floatMath43.msg" ":defaultRenderUtilityList1.u" -na;
connectAttr "floatMath44.msg" ":defaultRenderUtilityList1.u" -na;
connectAttr "floatMath45.msg" ":defaultRenderUtilityList1.u" -na;
connectAttr "floatMath46.msg" ":defaultRenderUtilityList1.u" -na;
connectAttr "floatMath47.msg" ":defaultRenderUtilityList1.u" -na;
connectAttr "floatMath48.msg" ":defaultRenderUtilityList1.u" -na;
connectAttr "floatMath49.msg" ":defaultRenderUtilityList1.u" -na;
connectAttr "floatMath50.msg" ":defaultRenderUtilityList1.u" -na;
connectAttr "floatMath51.msg" ":defaultRenderUtilityList1.u" -na;
connectAttr "floatMath52.msg" ":defaultRenderUtilityList1.u" -na;
connectAttr "condition33.msg" ":defaultRenderUtilityList1.u" -na;
connectAttr "multiplyDivide17.msg" ":defaultRenderUtilityList1.u" -na;
connectAttr "multiplyDivide18.msg" ":defaultRenderUtilityList1.u" -na;
connectAttr "multiplyDivide19.msg" ":defaultRenderUtilityList1.u" -na;
connectAttr "remapValue9.msg" ":defaultRenderUtilityList1.u" -na;
connectAttr "condition34.msg" ":defaultRenderUtilityList1.u" -na;
connectAttr "multiplyDivide20.msg" ":defaultRenderUtilityList1.u" -na;
connectAttr "remapValue10.msg" ":defaultRenderUtilityList1.u" -na;
connectAttr "multiplyDivide21.msg" ":defaultRenderUtilityList1.u" -na;
connectAttr "multiplyDivide22.msg" ":defaultRenderUtilityList1.u" -na;
connectAttr "floatMath53.msg" ":defaultRenderUtilityList1.u" -na;
connectAttr "floatMath54.msg" ":defaultRenderUtilityList1.u" -na;
connectAttr "condition35.msg" ":defaultRenderUtilityList1.u" -na;
connectAttr "floatMath55.msg" ":defaultRenderUtilityList1.u" -na;
connectAttr "condition36.msg" ":defaultRenderUtilityList1.u" -na;
connectAttr "floatMath56.msg" ":defaultRenderUtilityList1.u" -na;
connectAttr "floatMath57.msg" ":defaultRenderUtilityList1.u" -na;
connectAttr "condition37.msg" ":defaultRenderUtilityList1.u" -na;
connectAttr "floatMath58.msg" ":defaultRenderUtilityList1.u" -na;
connectAttr "floatMath59.msg" ":defaultRenderUtilityList1.u" -na;
connectAttr "floatMath60.msg" ":defaultRenderUtilityList1.u" -na;
connectAttr "floatMath61.msg" ":defaultRenderUtilityList1.u" -na;
connectAttr "condition38.msg" ":defaultRenderUtilityList1.u" -na;
connectAttr "condition39.msg" ":defaultRenderUtilityList1.u" -na;
connectAttr "condition40.msg" ":defaultRenderUtilityList1.u" -na;
connectAttr "condition41.msg" ":defaultRenderUtilityList1.u" -na;
connectAttr "condition42.msg" ":defaultRenderUtilityList1.u" -na;
connectAttr "condition43.msg" ":defaultRenderUtilityList1.u" -na;
connectAttr "condition44.msg" ":defaultRenderUtilityList1.u" -na;
connectAttr "place2dTexture1.msg" ":defaultRenderUtilityList1.u" -na;
connectAttr "condition45.msg" ":defaultRenderUtilityList1.u" -na;
connectAttr "condition46.msg" ":defaultRenderUtilityList1.u" -na;
connectAttr "condition47.msg" ":defaultRenderUtilityList1.u" -na;
connectAttr "colorMath1.msg" ":defaultRenderUtilityList1.u" -na;
connectAttr "colorMath2.msg" ":defaultRenderUtilityList1.u" -na;
connectAttr "rgbToHsv1.msg" ":defaultRenderUtilityList1.u" -na;
connectAttr "hsvToRgb1.msg" ":defaultRenderUtilityList1.u" -na;
connectAttr "rt_eyeWhite_projection.msg" ":defaultRenderUtilityList1.u" -na;
connectAttr "colorConstant1.msg" ":defaultRenderUtilityList1.u" -na;
connectAttr "mainSquare_place2dTexture.msg" ":defaultRenderUtilityList1.u" -na;
connectAttr "floatMath62.msg" ":defaultRenderUtilityList1.u" -na;
connectAttr "lf_eyeWhite_projection.msg" ":defaultRenderUtilityList1.u" -na;
connectAttr "rt_eyeWhite_colorConstant.msg" ":defaultRenderUtilityList1.u" -na;
connectAttr "lf_eyeWhite_colorConstant.msg" ":defaultRenderUtilityList1.u" -na;
connectAttr "colorMath3.msg" ":defaultRenderUtilityList1.u" -na;
connectAttr "colorMath4.msg" ":defaultRenderUtilityList1.u" -na;
connectAttr "lf_eyePupil_projection.msg" ":defaultRenderUtilityList1.u" -na;
connectAttr "rt_eyePupil_projection.msg" ":defaultRenderUtilityList1.u" -na;
connectAttr "rt_eyePupilColoring_colorMath.msg" ":defaultRenderUtilityList1.u" -na
		;
connectAttr "lf_eyePupil_colorConstant.msg" ":defaultRenderUtilityList1.u" -na;
connectAttr "rt_eyePupil_colorConstant.msg" ":defaultRenderUtilityList1.u" -na;
connectAttr "lf_eyePupilColoring_colorMath.msg" ":defaultRenderUtilityList1.u" -na
		;
connectAttr "lf_eyePupilAlpha_floatMath.msg" ":defaultRenderUtilityList1.u" -na;
connectAttr "rt_eyePupilAlpha_floatMath.msg" ":defaultRenderUtilityList1.u" -na;
connectAttr "eyebrow_colorConstant.msg" ":defaultRenderUtilityList1.u" -na;
connectAttr "lf_eyelidEyebrowCutout_colorMath.msg" ":defaultRenderUtilityList1.u"
		 -na;
connectAttr "rt_eyelidEyebrowCutout_colorMath.msg" ":defaultRenderUtilityList1.u"
		 -na;
connectAttr "blendColors11.msg" ":defaultRenderUtilityList1.u" -na;
connectAttr "place2dTexture2.msg" ":defaultRenderUtilityList1.u" -na;
connectAttr "lf_eyebrowRecolor_colorMath.msg" ":defaultRenderUtilityList1.u" -na
		;
connectAttr "lf_eyebrow_contrast.msg" ":defaultRenderUtilityList1.u" -na;
connectAttr "eyebrowContrast_floatConstant.msg" ":defaultRenderUtilityList1.u" -na
		;
connectAttr "lf_eyebrow_projection.msg" ":defaultRenderUtilityList1.u" -na;
connectAttr "lf_eyebrowAlpha_rgbToHsv.msg" ":defaultRenderUtilityList1.u" -na;
connectAttr "rt_eyebrowAlpha_rgbToHsv.msg" ":defaultRenderUtilityList1.u" -na;
connectAttr "rt_eyebrow_projection.msg" ":defaultRenderUtilityList1.u" -na;
connectAttr "rt_eyebrowRecolor_colorMath.msg" ":defaultRenderUtilityList1.u" -na
		;
connectAttr "rt_eyebrow_contrast.msg" ":defaultRenderUtilityList1.u" -na;
connectAttr "place2dTexture3.msg" ":defaultRenderUtilityList1.u" -na;
connectAttr "mouthClosedOpen_blendColors.msg" ":defaultRenderUtilityList1.u" -na
		;
connectAttr "mouthClosedOpenAlpha_blendColors.msg" ":defaultRenderUtilityList1.u"
		 -na;
connectAttr "mouthClosedOpenBlender_floatConstant.msg" ":defaultRenderUtilityList1.u"
		 -na;
connectAttr "mouthLip_colorConstant.msg" ":defaultRenderUtilityList1.u" -na;
connectAttr "lf_lipRecolor_colorMath.msg" ":defaultRenderUtilityList1.u" -na;
connectAttr "m_lip_projection.msg" ":defaultRenderUtilityList1.u" -na;
connectAttr "lf_lip_projection.msg" ":defaultRenderUtilityList1.u" -na;
connectAttr "rt_lip_projection.msg" ":defaultRenderUtilityList1.u" -na;
connectAttr "m_lipRecolor_colorMath.msg" ":defaultRenderUtilityList1.u" -na;
connectAttr "rt_lipRecolor_colorMath.msg" ":defaultRenderUtilityList1.u" -na;
connectAttr "mouthInside_colorConstant.msg" ":defaultRenderUtilityList1.u" -na;
connectAttr "mouthTongue_colorConstant.msg" ":defaultRenderUtilityList1.u" -na;
connectAttr "mouthTeeth_colorConstant.msg" ":defaultRenderUtilityList1.u" -na;
connectAttr "eyebrowOpacity_floatConstant.msg" ":defaultRenderUtilityList1.u" -na
		;
connectAttr "lf_eyebrowOpacity_floatMath.msg" ":defaultRenderUtilityList1.u" -na
		;
connectAttr "rt_eyebrowOpacity_floatMath.msg" ":defaultRenderUtilityList1.u" -na
		;
connectAttr "rt_mouth_projection.msg" ":defaultRenderUtilityList1.u" -na;
connectAttr "m_mouth_projection.msg" ":defaultRenderUtilityList1.u" -na;
connectAttr "lf_mouth_projection.msg" ":defaultRenderUtilityList1.u" -na;
connectAttr "rt_mouthRecolor_colorMath.msg" ":defaultRenderUtilityList1.u" -na;
connectAttr "m_mouthRecolor_colorMath.msg" ":defaultRenderUtilityList1.u" -na;
connectAttr "lf_mouthRecolor_colorMath.msg" ":defaultRenderUtilityList1.u" -na;
connectAttr "tongue_projection.msg" ":defaultRenderUtilityList1.u" -na;
connectAttr "tongueRecolor_colorMath.msg" ":defaultRenderUtilityList1.u" -na;
connectAttr "tongueCutoutAlpha_floatMath.msg" ":defaultRenderUtilityList1.u" -na
		;
connectAttr "b_teeth_projection.msg" ":defaultRenderUtilityList1.u" -na;
connectAttr "t_teeth_projection.msg" ":defaultRenderUtilityList1.u" -na;
connectAttr "t_teethRecolor_colorMath.msg" ":defaultRenderUtilityList1.u" -na;
connectAttr "b_teethRecolor_colorMath.msg" ":defaultRenderUtilityList1.u" -na;
connectAttr "b_teethCutoutAlpha_floatMath.msg" ":defaultRenderUtilityList1.u" -na
		;
connectAttr "t_teethCutoutAlpha_floatMath.msg" ":defaultRenderUtilityList1.u" -na
		;
connectAttr "mouthSidesAlpha_floatMath.msg" ":defaultRenderUtilityList1.u" -na;
connectAttr "mouthAlpha_floatMath.msg" ":defaultRenderUtilityList1.u" -na;
connectAttr "mouthOpenY_distanceBetween.msg" ":defaultRenderUtilityList1.u" -na;
connectAttr "mouthOpenY_scaleOffset_floatMath.msg" ":defaultRenderUtilityList1.u"
		 -na;
connectAttr "lf_mouthSmileOffset_floatMath.msg" ":defaultRenderUtilityList1.u" -na
		;
connectAttr "rt_mouthSmileOffset_floatMath.msg" ":defaultRenderUtilityList1.u" -na
		;
connectAttr "lf_mouthSmile_clamp.msg" ":defaultRenderUtilityList1.u" -na;
connectAttr "lf_mouthSmile_remapValue.msg" ":defaultRenderUtilityList1.u" -na;
connectAttr "rt_mouthSmile_remapValue.msg" ":defaultRenderUtilityList1.u" -na;
connectAttr "rt_mouthSmile_clamp.msg" ":defaultRenderUtilityList1.u" -na;
connectAttr "mouthOpenX_scaleMultiply_floatMath.msg" ":defaultRenderUtilityList1.u"
		 -na;
connectAttr "mouthOpenX_addedTranslate_floatMath.msg" ":defaultRenderUtilityList1.u"
		 -na;
connectAttr "mouthOpenX_divideTranslate_floatMath.msg" ":defaultRenderUtilityList1.u"
		 -na;
connectAttr "mouthOpenX_scaleOffset_floatMath.msg" ":defaultRenderUtilityList1.u"
		 -na;
connectAttr "rt_mouthSmileMin_clamp.msg" ":defaultRenderUtilityList1.u" -na;
connectAttr "lf_mouthSmileMin_clamp.msg" ":defaultRenderUtilityList1.u" -na;
connectAttr "rt_mouthMin_clamp.msg" ":defaultRenderUtilityList1.u" -na;
connectAttr "multMatrix1.msg" ":defaultRenderUtilityList1.u" -na;
connectAttr "decomposeMatrix11.msg" ":defaultRenderUtilityList1.u" -na;
connectAttr "floatMath63.msg" ":defaultRenderUtilityList1.u" -na;
connectAttr "mouthOpenXInverse_floatMath.msg" ":defaultRenderUtilityList1.u" -na
		;
connectAttr "multMatrix2.msg" ":defaultRenderUtilityList1.u" -na;
connectAttr "decomposeMatrix12.msg" ":defaultRenderUtilityList1.u" -na;
connectAttr "multMatrix3.msg" ":defaultRenderUtilityList1.u" -na;
connectAttr "multMatrix4.msg" ":defaultRenderUtilityList1.u" -na;
connectAttr "decomposeMatrix13.msg" ":defaultRenderUtilityList1.u" -na;
connectAttr "decomposeMatrix14.msg" ":defaultRenderUtilityList1.u" -na;
connectAttr "decomposeMatrix15.msg" ":defaultRenderUtilityList1.u" -na;
connectAttr "decomposeMatrix16.msg" ":defaultRenderUtilityList1.u" -na;
connectAttr "multMatrix5.msg" ":defaultRenderUtilityList1.u" -na;
connectAttr "multMatrix6.msg" ":defaultRenderUtilityList1.u" -na;
connectAttr "multMatrix7.msg" ":defaultRenderUtilityList1.u" -na;
connectAttr "decomposeMatrix17.msg" ":defaultRenderUtilityList1.u" -na;
connectAttr "condition48.msg" ":defaultRenderUtilityList1.u" -na;
connectAttr "mouthOpenY_bottomLimitMult_floatMath.msg" ":defaultRenderUtilityList1.u"
		 -na;
connectAttr "mouthOpenY_bottomLimitAdd_floatMath.msg" ":defaultRenderUtilityList1.u"
		 -na;
connectAttr "condition49.msg" ":defaultRenderUtilityList1.u" -na;
connectAttr "defaultRenderLayer.msg" ":defaultRenderingList1.r" -na;
connectAttr "skinPromoArt_tex.msg" ":defaultTextureList1.tx" -na;
connectAttr "tex_promo_art_face.msg" ":defaultTextureList1.tx" -na;
connectAttr "armorPromoArt_tex.msg" ":defaultTextureList1.tx" -na;
connectAttr "ramp1.msg" ":defaultTextureList1.tx" -na;
connectAttr "rsBumpBlender1.msg" ":defaultTextureList1.tx" -na;
connectAttr "rsRoundCorners1.msg" ":defaultTextureList1.tx" -na;
connectAttr "rsRoundCorners2.msg" ":defaultTextureList1.tx" -na;
connectAttr "rsBumpBlender2.msg" ":defaultTextureList1.tx" -na;
connectAttr "armorPromoArt_filtered_tex.msg" ":defaultTextureList1.tx" -na;
connectAttr "rsBumpMap2.msg" ":defaultTextureList1.tx" -na;
connectAttr "mainFace_layeredTexture.msg" ":defaultTextureList1.tx" -na;
connectAttr "mainSquare_ramp.msg" ":defaultTextureList1.tx" -na;
connectAttr "eyebrows_layeredTexture.msg" ":defaultTextureList1.tx" -na;
connectAttr "lf_eyebrow_tex.msg" ":defaultTextureList1.tx" -na;
connectAttr "lf_eyebrowAlpha_ramp.msg" ":defaultTextureList1.tx" -na;
connectAttr "lf_eyebrowCutoutAlpha_ramp.msg" ":defaultTextureList1.tx" -na;
connectAttr "rt_eyebrowCutoutAlpha_ramp.msg" ":defaultTextureList1.tx" -na;
connectAttr "rt_eyebrowAlpha_ramp.msg" ":defaultTextureList1.tx" -na;
connectAttr "rt_eyebrow_tex.msg" ":defaultTextureList1.tx" -na;
connectAttr "mouthClosed_layeredTexture.msg" ":defaultTextureList1.tx" -na;
connectAttr "mouthOpen_layeredTexture.msg" ":defaultTextureList1.tx" -na;
connectAttr "ikRPsolver.msg" ":ikSystem.sol" -na;
connectAttr "ikRPsolver1.msg" ":ikSystem.sol" -na;
connectAttr "ikRPsolver2.msg" ":ikSystem.sol" -na;
// End of rig_player.ma
