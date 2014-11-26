package stscorrection;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author sergei
 */
public enum OperationType {
        CancelRemoveDevice                                  (1,"CancelRemoveDevice"),
        CancelStopDevice                                    (2,"CancelStopDevice"),
        CloseFile                                           (3,"CloseFile"),
        CreateFile                                          (4,"CreateFile"),
        CreateFileMapping                                   (5,"CreateFileMapping"),
        CreateMailSlot                                      (6,"CreateMailSlot"),
        CreatePipe                                          (7,"CreatePipe"),
        DebugOutputProfiling                                (8,"Debug Output Profiling"),
        DeviceChange                                        (9,"DeviceChange"),
        DeviceIoControl                                     (10,"DeviceIoControl	"),
        DeviceUsageNotification                             (11,"DeviceUsageNotification"),
        Eject                                               (12,"Eject"),
        FileStreamInformation                               (13,"FileStreamInformation"),
        FileSystemControl                                   (14,"FileSystemControl"),
        FilterResourceRequirements                          (15,"FilterResourceRequirements"),
        FlushBuffersFile                                    (16,"FlushBuffersFile"),
        InternalDeviceIoControl                             (17,"InternalDeviceIoControl"),
        LoadImage                                           (18,"Load Image"),
        LockFile                                            (19,"LockFile"),
        NotifyChangeDirectory                               (20,"NotifyChangeDirectory"),
        Power                                               (21,"Power"),
        ProcessCreate                                       (22,"Process Create"),
        ProcessExit                                         (23,"Process Exit"),
        ProcessProfiling                                    (24,"Process Profiling"),
        ProcessStart                                        (25,"Process Start"),
        ProcessStatistics                                   (26,"Process Statistics"),
        QueryAllInformationFile                             (27,"QueryAllInformationFile"),
        QueryAttributeCacheInformation                      (28,"QueryAttributeCacheInformation"),
        QueryAttributeInformationVolume                     (29,"QueryAttributeInformationVolume"),
        QueryAttributeTag                                   (30,"QueryAttributeTag"),
        QueryAttributeTagFile                               (31,"QueryAttributeTagFile"),
        QueryBasicInformationFile                           (32,"QueryBasicInformationFile"),
        QueryBusInformation                                 (33,"QueryBusInformation"),
        QueryCapabilities                                   (34,"QueryCapabilities"),
        QueryCompressionInformationFile                     (35,"QueryCompressionInformationFile"),
        QueryDeviceInformationVolume                        (36,"QueryDeviceInformationVolume"),
        QueryDeviceRelations                                (37,"QueryDeviceRelations"),
        QueryDeviceText                                     (38,"QueryDeviceText"),
        QueryDirectory                                      (39,"QueryDirectory"),
        QueryEAFile                                         (40,"QueryEAFile"),
        QueryEaInformationFile                              (41,"QueryEaInformationFile"),
        QueryEndOfFile                                      (42,"QueryEndOfFile"),
        QueryFileInternalInformationFile                    (43,"QueryFileInternalInformationFile"),
        QueryFileQuota                                      (44,"QueryFileQuota"),
        QueryFullSizeInformationVolume                      (45,"QueryFullSizeInformationVolume"),
        QueryId                                             (46,"QueryId"),
        QueryIdBothDirectory                                (47,"QueryIdBothDirectory"),
        QueryIdExtdDirectoryInformation                     (48,"QueryIdExtdDirectoryInformation"),
        QueryIdGlobalTxDirectoryInformation                 (49,"QueryIdGlobalTxDirectoryInformation"),
        QueryIdInformation                                  (50,"QueryIdInformation"),
        QueryInformationVolume                              (51,"QueryInformationVolume"),
        QueryInterface                                      (52,"QueryInterface"),
        QueryIoPiorityHint                                  (53,"QueryIoPiorityHint"),
        QueryIsRemoteDeviceInformation                      (54,"QueryIsRemoteDeviceInformation"),
        QueryLabelInformationVolume                         (55,"QueryLabelInformationVolume"),
        QueryLegacyBusInformation                           (56,"QueryLegacyBusInformation"),
        QueryLinkInformationBypassAccessCheck               (57,"QueryLinkInformationBypassAccessCheck"),
        QueryLinks                                          (58,"QueryLinks"),
        QueryMoveClusterInformationFile                     (59,"QueryMoveClusterInformationFile"),
        QueryNameInformationFile                            (60,"QueryNameInformationFile"),
        QueryNetworkOpenInformationFile                     (61,"QueryNetworkOpenInformationFile"),
        QueryNetworkPhysicalNameInformationFile             (62,"QueryNetworkPhysicalNameInformationFile"),
        QueryNormalizedNameInformationFile                  (63,"QueryNormalizedNameInformationFile"),
        QueryNumaNodeInformation                            (64,"QueryNumaNodeInformation"),
        QueryObjectIdInformationVolume                      (65,"QueryObjectIdInformationVolume"),
        QueryOpen                                           (66,"QueryOpen"),
        QueryPnpDeviceState                                 (67,"QueryPnpDeviceState"),
        QueryPositionInformationFile                        (68,"QueryPositionInformationFile"),
        QueryRemoteProtocolInformation                      (69,"QueryRemoteProtocolInformation"),
        QueryRemoteDevice                                   (70,"QueryRemoteDevice"),
        QueryRenameInformationBypassAccessCheck             (71,"QueryRenameInformationBypassAccessCheck"),
        QueryResourceRequirements                           (72,"QueryResourceRequirements"),
        QueryResources                                      (73,"QueryResources"),
        QuerySecurityFile                                   (74,"QuerySecurityFile"),
        QueryShortNameInformationFile                       (75,"QueryShortNameInformationFile"),
        QuerySizeInformationVolume                          (76,"QuerySizeInformationVolume"),
        QueryStandardInformationFile                        (77,"QueryStandardInformationFile"),
        QueryStandardLinkInformation                        (78,"QueryStandardLinkInformation"),
        QueryStopDevice                                     (79,"QueryStopDevice"),
        QueryStreamInformationFile                          (80,"QueryStreamInformationFile"),
        QueryValidDataLength                                (81,"QueryValidDataLength"),
        QueryVolumeNameInformation                          (82,"QueryVolumeNameInformation"),
        ReadConfig                                          (83,"ReadConfig"),
        ReadFile                                            (84,"ReadFile"),
        RegCloseKey                                         (85,"RegCloseKey"),
        RegCreateKey                                        (86,"RegCreateKey"),
        RegDeleteKey                                        (87,"RegDeleteKey"),
        RegDeleteValue                                      (88,"RegDeleteValue"),
        RegEnumKey                                          (89,"RegEnumKey"),
        RegEnumValue                                        (80,"RegEnumValue"),
        RegFlushKey                                         (81,"RegFlushKey"),
        RegLoadKey                                          (82,"RegLoadKey"),
        RegOpenKey                                          (83,"RegOpenKey"),
        RegQueryKey                                         (84,"RegQueryKey"),
        RegQueryKeySecurity                                 (85,"RegQueryKeySecurity"),
        RegQueryMultipleValueKey                            (86,"RegQueryMultipleValueKey"),
        RegQueryValue                                       (87,"RegQueryValue"),
        RegRenameKey                                        (88,"RegRenameKey"),
        RegSetInfoKey                                       (89,"RegSetInfoKey"),
        RegSetKeySecurity                                   (90,"RegSetKeySecurity"),
        RegSetValue                                         (91,"RegSetValue"),
        RegUnloadKey                                        (92,"RegUnloadKey"),
        RemoveDevice                                        (93,"RemoveDevice"),
        SetAllocationInformationFile                        (94,"SetAllocationInformationFile"),
        SetBasicInformationFile                             (95,"SetBasicInformationFile"),
        SetDispositionInformationFile                       (96,"SetDispositionInformationFile"),
        SetEAFile                                           (97,"SetEAFile"),
        SetEndOfFileInformationFile                         (98,"SetEndOfFileInformationFile"),
        SetFileQuota                                        (99,"SetFileQuota"),
        SetLinkInformationFile                              (100,"SetLinkInformationFile"),
        SetLock                                             (101,"SetLock"),
        SetPipeInformation                                  (102,"SetPipeInformation"),
        SetPositionInformationFile                          (103,"SetPositionInformationFile"),
        SetRenameInformationFile                            (104,"SetRenameInformationFile"),
        SetSecurityFile                                     (105,"SetSecurityFile"),
        SetShortNameInformation                             (106,"SetShortNameInformation"),
        SetValidDataLengthInformationFile                   (107,"SetValidDataLengthInformationFile"),
        SetVolumeInformation                                (108,"SetVolumeInformation"),
        ShutDown                                            (109,"ShutDown"),
        StartDevice                                         (110,"StartDevice"),
        StopDevice                                          (111,"StopDevice"),
        SurpriseRemoval                                     (112,"SurpriseRemoval"),
        SystemStatistics                                    (113,"System Statistics"),
        SystemControl                                       (114,"SystemControl"),
        TCPAccept                                           (115,"TCP Accept"),
        TCPConnect                                          (116,"TCP Connect"),
        TCPDisconnect                                       (117,"TCP Disconnect"),
        TCPOther                                            (118,"TCP Other"),
        TCPReceive                                          (119,"TCP Receive"),
        TCPReconnect                                        (120,"TCP Reconnect"),
        TCPRetransmit                                       (121,"TCP Retransmit"),
        TCPSend                                             (122,"TCP Send"),
        TCPTCPCopy                                          (123,"TCP TCPCopy"),
        TCPUnknown                                          (124,"TCP Unknown"),
        ThreadCreate                                        (125,"Thread Create"),
        ThreadExit                                          (126,"Thread Exit"),
        ThreadProfile                                       (127,"Thread Profile"),
        ThreadProfiling                                     (128,"Thread Profiling"),
        UDPAccept                                           (129,"UDP Accept"),
        UDPConnect                                          (130,"UDP Connect"),
        UDPDisconnect                                       (131,"UDP Disconnect"),
        UDPOther                                            (132,"UDP Other"),
        UDPReceive                                          (133,"UDP Receive"),
        UDPReconnect                                        (134,"UDP Reconnect"),
        UDPRetransmit                                       (135,"UDP Retransmit"),
        UDPSend                                             (136,"UDP Send"),
        UDPTCPCopy                                          (137,"UDP TCPCopy"),
        UDPUnknown                                          (138,"UDP Unknown"),
        UnlockFileAll                                       (139,"UnlockFileAll"),
        UnlockFileByKey                                     (140,"UnlockFileByKey"),
        UnlockFileSingle                                    (141,"UnlockFileSingle"),
        VolumeDismount                                      (142,"VolumeDismount"),
        VolumeMount                                         (143,"VolumeMount"),
        WriteConfig                                         (144,"WriteConfig"),
        WriteFile                                           (145,"WriteFile");

        public final String _textValue;
        public final int _ID;
        
        OperationType(int ID, String textValue){
            this._textValue=textValue;
            this._ID=ID;
        }
        
        private static OperationType[] _operations = new OperationType[145];
        
        public static void init()
        {
            for (OperationType op : OperationType.values())
            {
                _operations[op._ID-1] = op;
            }
        }
        
        public static OperationType getOperationType(int ID)
        {
            return _operations[ID-1];
        }
    }
