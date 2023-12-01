/*
 *
 *                         _ _        ____  ____
 *               _____   _(_) |_ __ _|  _ \| __ )
 *              / _ \ \ / / | __/ _` | | | |  _ \
 *             |  __/\ V /| | || (_| | |_| | |_) |
 *              \___| \_/ |_|\__\__,_|____/|____/
 *
 *   Copyright (c) 2023
 *
 *   Licensed under the Business Source License, Version 1.1 (the "License");
 *   you may not use this file except in compliance with the License.
 *   You may obtain a copy of the License at
 *
 *   https://github.com/FgForrest/evitaDB/blob/main/LICENSE
 *
 *   Unless required by applicable law or agreed to in writing, software
 *   distributed under the License is distributed on an "AS IS" BASIS,
 *   WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *   See the License for the specific language governing permissions and
 *   limitations under the License.
 */

// Generated by the protocol buffer compiler.  DO NOT EDIT!
// source: GrpcEntitySchemaMutation.proto

package io.evitadb.externalApi.grpc.generated;

public final class GrpcEntitySchemaMutationOuterClass {
  private GrpcEntitySchemaMutationOuterClass() {}
  public static void registerAllExtensions(
      com.google.protobuf.ExtensionRegistryLite registry) {
  }

  public static void registerAllExtensions(
      com.google.protobuf.ExtensionRegistry registry) {
    registerAllExtensions(
        (com.google.protobuf.ExtensionRegistryLite) registry);
  }
  static final com.google.protobuf.Descriptors.Descriptor
    internal_static_io_evitadb_externalApi_grpc_generated_GrpcEntitySchemaMutation_descriptor;
  static final
    com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
      internal_static_io_evitadb_externalApi_grpc_generated_GrpcEntitySchemaMutation_fieldAccessorTable;

  public static com.google.protobuf.Descriptors.FileDescriptor
      getDescriptor() {
    return descriptor;
  }
  private static  com.google.protobuf.Descriptors.FileDescriptor
      descriptor;
  static {
    java.lang.String[] descriptorData = {
      "\n\036GrpcEntitySchemaMutation.proto\022%io.evi" +
      "tadb.externalApi.grpc.generated\032\"GrpcAtt" +
      "ributeSchemaMutations.proto\032\'GrpcAssocia" +
      "tedDataSchemaMutations.proto\032\037GrpcEntity" +
      "SchemaMutations.proto\032\"GrpcReferenceSche" +
      "maMutations.proto\"\337-\n\030GrpcEntitySchemaMu" +
      "tation\022{\n\"createAssociatedDataSchemaMuta" +
      "tion\030\001 \001(\0132M.io.evitadb.externalApi.grpc" +
      ".generated.GrpcCreateAssociatedDataSchem" +
      "aMutationH\000\022\235\001\n3modifyAssociatedDataSche" +
      "maDeprecationNoticeMutation\030\002 \001(\0132^.io.e" +
      "vitadb.externalApi.grpc.generated.GrpcMo" +
      "difyAssociatedDataSchemaDeprecationNotic" +
      "eMutationH\000\022\221\001\n-modifyAssociatedDataSche" +
      "maDescriptionMutation\030\003 \001(\0132X.io.evitadb" +
      ".externalApi.grpc.generated.GrpcModifyAs" +
      "sociatedDataSchemaDescriptionMutationH\000\022" +
      "\203\001\n&modifyAssociatedDataSchemaNameMutati" +
      "on\030\004 \001(\0132Q.io.evitadb.externalApi.grpc.g" +
      "enerated.GrpcModifyAssociatedDataSchemaN" +
      "ameMutationH\000\022\203\001\n&modifyAssociatedDataSc" +
      "hemaTypeMutation\030\005 \001(\0132Q.io.evitadb.exte" +
      "rnalApi.grpc.generated.GrpcModifyAssocia" +
      "tedDataSchemaTypeMutationH\000\022{\n\"removeAss" +
      "ociatedDataSchemaMutation\030\006 \001(\0132M.io.evi" +
      "tadb.externalApi.grpc.generated.GrpcRemo" +
      "veAssociatedDataSchemaMutationH\000\022\207\001\n(set" +
      "AssociatedDataSchemaLocalizedMutation\030\007 " +
      "\001(\0132S.io.evitadb.externalApi.grpc.genera" +
      "ted.GrpcSetAssociatedDataSchemaLocalized" +
      "MutationH\000\022\205\001\n\'setAssociatedDataSchemaNu" +
      "llableMutation\030\010 \001(\0132R.io.evitadb.extern" +
      "alApi.grpc.generated.GrpcSetAssociatedDa" +
      "taSchemaNullableMutationH\000\022q\n\035createAttr" +
      "ibuteSchemaMutation\030\024 \001(\0132H.io.evitadb.e" +
      "xternalApi.grpc.generated.GrpcCreateAttr" +
      "ibuteSchemaMutationH\000\022\211\001\n)modifyAttribut" +
      "eSchemaDefaultValueMutation\030\025 \001(\0132T.io.e" +
      "vitadb.externalApi.grpc.generated.GrpcMo" +
      "difyAttributeSchemaDefaultValueMutationH" +
      "\000\022\223\001\n.modifyAttributeSchemaDeprecationNo" +
      "ticeMutation\030\026 \001(\0132Y.io.evitadb.external" +
      "Api.grpc.generated.GrpcModifyAttributeSc" +
      "hemaDeprecationNoticeMutationH\000\022\207\001\n(modi" +
      "fyAttributeSchemaDescriptionMutation\030\027 \001" +
      "(\0132S.io.evitadb.externalApi.grpc.generat" +
      "ed.GrpcModifyAttributeSchemaDescriptionM" +
      "utationH\000\022y\n!modifyAttributeSchemaNameMu" +
      "tation\030\030 \001(\0132L.io.evitadb.externalApi.gr" +
      "pc.generated.GrpcModifyAttributeSchemaNa" +
      "meMutationH\000\022y\n!modifyAttributeSchemaTyp" +
      "eMutation\030\031 \001(\0132L.io.evitadb.externalApi" +
      ".grpc.generated.GrpcModifyAttributeSchem" +
      "aTypeMutationH\000\022q\n\035removeAttributeSchema" +
      "Mutation\030\032 \001(\0132H.io.evitadb.externalApi." +
      "grpc.generated.GrpcRemoveAttributeSchema" +
      "MutationH\000\022\177\n$setAttributeSchemaFilterab" +
      "leMutation\030\033 \001(\0132O.io.evitadb.externalAp" +
      "i.grpc.generated.GrpcSetAttributeSchemaF" +
      "ilterableMutationH\000\022}\n#setAttributeSchem" +
      "aLocalizedMutation\030\034 \001(\0132N.io.evitadb.ex" +
      "ternalApi.grpc.generated.GrpcSetAttribut" +
      "eSchemaLocalizedMutationH\000\022{\n\"setAttribu" +
      "teSchemaNullableMutation\030\035 \001(\0132M.io.evit" +
      "adb.externalApi.grpc.generated.GrpcSetAt" +
      "tributeSchemaNullableMutationH\000\022\207\001\n(setA" +
      "ttributeSchemaRepresentativeMutation\030\036 \001" +
      "(\0132S.io.evitadb.externalApi.grpc.generat" +
      "ed.GrpcSetAttributeSchemaRepresentativeM" +
      "utationH\000\022{\n\"setAttributeSchemaSortableM" +
      "utation\030\037 \001(\0132M.io.evitadb.externalApi.g" +
      "rpc.generated.GrpcSetAttributeSchemaSort" +
      "ableMutationH\000\022w\n setAttributeSchemaUniq" +
      "ueMutation\030  \001(\0132K.io.evitadb.externalAp" +
      "i.grpc.generated.GrpcSetAttributeSchemaU" +
      "niqueMutationH\000\022w\n useGlobalAttributeSch" +
      "emaMutation\030! \001(\0132K.io.evitadb.externalA" +
      "pi.grpc.generated.GrpcUseGlobalAttribute" +
      "SchemaMutationH\000\022}\n#allowCurrencyInEntit" +
      "ySchemaMutation\0302 \001(\0132N.io.evitadb.exter" +
      "nalApi.grpc.generated.GrpcAllowCurrencyI" +
      "nEntitySchemaMutationH\000\022\207\001\n(allowEvoluti" +
      "onModeInEntitySchemaMutation\0303 \001(\0132S.io." +
      "evitadb.externalApi.grpc.generated.GrpcA" +
      "llowEvolutionModeInEntitySchemaMutationH" +
      "\000\022y\n!allowLocaleInEntitySchemaMutation\0304" +
      " \001(\0132L.io.evitadb.externalApi.grpc.gener" +
      "ated.GrpcAllowLocaleInEntitySchemaMutati" +
      "onH\000\022\203\001\n&disallowCurrencyInEntitySchemaM" +
      "utation\0305 \001(\0132Q.io.evitadb.externalApi.g" +
      "rpc.generated.GrpcDisallowCurrencyInEnti" +
      "tySchemaMutationH\000\022\215\001\n+disallowEvolution" +
      "ModeInEntitySchemaMutation\0306 \001(\0132V.io.ev" +
      "itadb.externalApi.grpc.generated.GrpcDis" +
      "allowEvolutionModeInEntitySchemaMutation" +
      "H\000\022\177\n$disallowLocaleInEntitySchemaMutati" +
      "on\0307 \001(\0132O.io.evitadb.externalApi.grpc.g" +
      "enerated.GrpcDisallowLocaleInEntitySchem" +
      "aMutationH\000\022\215\001\n+modifyEntitySchemaDeprec" +
      "ationNoticeMutation\0308 \001(\0132V.io.evitadb.e" +
      "xternalApi.grpc.generated.GrpcModifyEnti" +
      "tySchemaDeprecationNoticeMutationH\000\022\201\001\n%" +
      "modifyEntitySchemaDescriptionMutation\0309 " +
      "\001(\0132P.io.evitadb.externalApi.grpc.genera" +
      "ted.GrpcModifyEntitySchemaDescriptionMut" +
      "ationH\000\022\223\001\n.setEntitySchemaWithGenerated" +
      "PrimaryKeyMutation\030: \001(\0132Y.io.evitadb.ex" +
      "ternalApi.grpc.generated.GrpcSetEntitySc" +
      "hemaWithGeneratedPrimaryKeyMutationH\000\022\177\n" +
      "$setEntitySchemaWithHierarchyMutation\030; " +
      "\001(\0132O.io.evitadb.externalApi.grpc.genera" +
      "ted.GrpcSetEntitySchemaWithHierarchyMuta" +
      "tionH\000\022w\n setEntitySchemaWithPriceMutati" +
      "on\030< \001(\0132K.io.evitadb.externalApi.grpc.g" +
      "enerated.GrpcSetEntitySchemaWithPriceMut" +
      "ationH\000\022q\n\035createReferenceSchemaMutation" +
      "\030P \001(\0132H.io.evitadb.externalApi.grpc.gen" +
      "erated.GrpcCreateReferenceSchemaMutation" +
      "H\000\022\203\001\n&modifyReferenceAttributeSchemaMut" +
      "ation\030Q \001(\0132Q.io.evitadb.externalApi.grp" +
      "c.generated.GrpcModifyReferenceAttribute" +
      "SchemaMutationH\000\022\207\001\n(modifyReferenceSche" +
      "maCardinalityMutation\030R \001(\0132S.io.evitadb" +
      ".externalApi.grpc.generated.GrpcModifyRe" +
      "ferenceSchemaCardinalityMutationH\000\022\223\001\n.m" +
      "odifyReferenceSchemaDeprecationNoticeMut" +
      "ation\030S \001(\0132Y.io.evitadb.externalApi.grp" +
      "c.generated.GrpcModifyReferenceSchemaDep" +
      "recationNoticeMutationH\000\022\207\001\n(modifyRefer" +
      "enceSchemaDescriptionMutation\030T \001(\0132S.io" +
      ".evitadb.externalApi.grpc.generated.Grpc" +
      "ModifyReferenceSchemaDescriptionMutation" +
      "H\000\022y\n!modifyReferenceSchemaNameMutation\030" +
      "U \001(\0132L.io.evitadb.externalApi.grpc.gene" +
      "rated.GrpcModifyReferenceSchemaNameMutat" +
      "ionH\000\022\225\001\n/modifyReferenceSchemaRelatedEn" +
      "tityGroupMutation\030V \001(\0132Z.io.evitadb.ext" +
      "ernalApi.grpc.generated.GrpcModifyRefere" +
      "nceSchemaRelatedEntityGroupMutationH\000\022\213\001" +
      "\n*modifyReferenceSchemaRelatedEntityMuta" +
      "tion\030W \001(\0132U.io.evitadb.externalApi.grpc" +
      ".generated.GrpcModifyReferenceSchemaRela" +
      "tedEntityMutationH\000\022q\n\035removeReferenceSc" +
      "hemaMutation\030X \001(\0132H.io.evitadb.external" +
      "Api.grpc.generated.GrpcRemoveReferenceSc" +
      "hemaMutationH\000\022y\n!setReferenceSchemaFace" +
      "tedMutation\030Y \001(\0132L.io.evitadb.externalA" +
      "pi.grpc.generated.GrpcSetReferenceSchema" +
      "FacetedMutationH\000\022|\n!setReferenceSchemaI" +
      "ndexedMutation\030Z \001(\0132O.io.evitadb.extern" +
      "alApi.grpc.generated.GrpcSetReferenceSch" +
      "emaFilterableMutationH\000B\n\n\010mutationB\014P\001\252" +
      "\002\007EvitaDBb\006proto3"
    };
    descriptor = com.google.protobuf.Descriptors.FileDescriptor
      .internalBuildGeneratedFileFrom(descriptorData,
        new com.google.protobuf.Descriptors.FileDescriptor[] {
          io.evitadb.externalApi.grpc.generated.GrpcAttributeSchemaMutations.getDescriptor(),
          io.evitadb.externalApi.grpc.generated.GrpcAssociatedDataSchemaMutations.getDescriptor(),
          io.evitadb.externalApi.grpc.generated.GrpcEntitySchemaMutations.getDescriptor(),
          io.evitadb.externalApi.grpc.generated.GrpcReferenceSchemaMutations.getDescriptor(),
        });
    internal_static_io_evitadb_externalApi_grpc_generated_GrpcEntitySchemaMutation_descriptor =
      getDescriptor().getMessageTypes().get(0);
    internal_static_io_evitadb_externalApi_grpc_generated_GrpcEntitySchemaMutation_fieldAccessorTable = new
      com.google.protobuf.GeneratedMessageV3.FieldAccessorTable(
        internal_static_io_evitadb_externalApi_grpc_generated_GrpcEntitySchemaMutation_descriptor,
        new java.lang.String[] { "CreateAssociatedDataSchemaMutation", "ModifyAssociatedDataSchemaDeprecationNoticeMutation", "ModifyAssociatedDataSchemaDescriptionMutation", "ModifyAssociatedDataSchemaNameMutation", "ModifyAssociatedDataSchemaTypeMutation", "RemoveAssociatedDataSchemaMutation", "SetAssociatedDataSchemaLocalizedMutation", "SetAssociatedDataSchemaNullableMutation", "CreateAttributeSchemaMutation", "ModifyAttributeSchemaDefaultValueMutation", "ModifyAttributeSchemaDeprecationNoticeMutation", "ModifyAttributeSchemaDescriptionMutation", "ModifyAttributeSchemaNameMutation", "ModifyAttributeSchemaTypeMutation", "RemoveAttributeSchemaMutation", "SetAttributeSchemaFilterableMutation", "SetAttributeSchemaLocalizedMutation", "SetAttributeSchemaNullableMutation", "SetAttributeSchemaRepresentativeMutation", "SetAttributeSchemaSortableMutation", "SetAttributeSchemaUniqueMutation", "UseGlobalAttributeSchemaMutation", "AllowCurrencyInEntitySchemaMutation", "AllowEvolutionModeInEntitySchemaMutation", "AllowLocaleInEntitySchemaMutation", "DisallowCurrencyInEntitySchemaMutation", "DisallowEvolutionModeInEntitySchemaMutation", "DisallowLocaleInEntitySchemaMutation", "ModifyEntitySchemaDeprecationNoticeMutation", "ModifyEntitySchemaDescriptionMutation", "SetEntitySchemaWithGeneratedPrimaryKeyMutation", "SetEntitySchemaWithHierarchyMutation", "SetEntitySchemaWithPriceMutation", "CreateReferenceSchemaMutation", "ModifyReferenceAttributeSchemaMutation", "ModifyReferenceSchemaCardinalityMutation", "ModifyReferenceSchemaDeprecationNoticeMutation", "ModifyReferenceSchemaDescriptionMutation", "ModifyReferenceSchemaNameMutation", "ModifyReferenceSchemaRelatedEntityGroupMutation", "ModifyReferenceSchemaRelatedEntityMutation", "RemoveReferenceSchemaMutation", "SetReferenceSchemaFacetedMutation", "SetReferenceSchemaIndexedMutation", "Mutation", });
    io.evitadb.externalApi.grpc.generated.GrpcAttributeSchemaMutations.getDescriptor();
    io.evitadb.externalApi.grpc.generated.GrpcAssociatedDataSchemaMutations.getDescriptor();
    io.evitadb.externalApi.grpc.generated.GrpcEntitySchemaMutations.getDescriptor();
    io.evitadb.externalApi.grpc.generated.GrpcReferenceSchemaMutations.getDescriptor();
  }

  // @@protoc_insertion_point(outer_class_scope)
}
